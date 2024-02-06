import React, { useEffect, useState, useRef }  from 'react';
import { getAllMessages, getChatId, checkChatExists, createChat} from '../../../api/ChatApi';
import { useParams } from 'react-router-dom';
import './SingleChat.css';
import SingleMessage from './SingleMessage';
import { Client } from '@stomp/stompjs';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowAltCircleRight } from '@fortawesome/free-solid-svg-icons';
import { websocketURL } from '../../../constants';
// import single message:

const SingleChat = () => {
    const [chatMessages, setChatMessages] = useState([]);
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const [currentUser, setCurrentUser] = useState(storedMember ? storedMember.username : '');
    const [friendName, setFriendName] = useState(useParams().friendName);
    const [inputMessage, setInputMessage] = useState('');
    const chatID = useRef('');
    const latestMessageId = useRef('');
    const socketClient = useRef(null);

    // what if not logged in?
    useEffect(() => {
        const fetchData = async () => {
            try {         
                let answer = await getChatId(currentUser, friendName, onSuccessCallbackChatId);
                socketClient.current = new Client({
                    brokerURL: websocketURL,
                    reconnectDelay: 5000,
                    heartbeatIncoming: 4000,
                    heartbeatOutgoing: 4000,
                    onConnect: onConnected,
                    onDisconnect: onDisconnected,
                });

                socketClient.current.activate();
                
                return () => {
                    // Clean up the WebSocket connection when the component unmounts
                    socketClient.current && socketClient.current.deactivate();
                };            
            } catch (error) {
                console.error(error);
            }
        };
        fetchData(); // Invoke the async function inside useEffectdd
        
    }, []);

    async function onSuccessCallbackChatId(response) {
        chatID.current = response; 
        let messages = await getAllMessages(response);

        setChatMessages(messages);
    }

    function onConnected() {
        if (socketClient.current.connected) {
            console.log('Connected!!');
            socketClient.current.subscribe('/topic/chat/' + chatID.current, (message) => {
                showChatMessage(JSON.parse(message.body));
            });
            
        } else {
            console.log('Socket client not initialized');
        }
    };

    const onDisconnected = () => {
        console.log("Disconnected!!")
        socketClient.current.deactivate();
    }

    // not efficient unless you apply pagninationoo
    function showChatMessage(message) {
        if (message.messageId === latestMessageId.current) {
            return;
        }
        setChatMessages((prevMessages) => [...prevMessages, message]);
        latestMessageId.current = message.messageId;
      }

    function connect() {
        console.log(socketClient.current)
        console.log(socketClient.current.connected);
    }
    
    function sendMessage() {
        if (socketClient && socketClient.current.connected) {
            let message = {
                sender: currentUser,
                receiver: friendName,
                message: inputMessage,
                chatID: chatID.current,
            };
            socketClient.current.publish({
                destination: "/app/hello/",
                body: JSON.stringify(message)
            });
            setInputMessage('');
        }
        else {
            console.log('not connected');
        }
    }

    const handleMessageChange = (e) => {
        setInputMessage(e.target.value);
      };

    return (
        <div className='wholeChat'>
            <div className='topOfChat'>
                <h1>{friendName}</h1>
            </div>       
            <div className='scrollableChat'>
                {/* do I need to check if empty? */}
                {chatMessages.map((messageObject, index) => (
                    <div key={index}>
                        <SingleMessage message={messageObject.message} sender={messageObject.sender} time ={messageObject.date}/>
                    </div>
                ))}

            </div>
            <div className='chatTypeArea'>
            <textarea
                placeholder="Type a message"
                value={inputMessage}
                onChange={handleMessageChange}
                onKeyUpCapture={(e) => e.key === 'Enter' ? sendMessage() : null}
                rows={1}
            />
            <button onClick={sendMessage} style={{ width: '10%', padding: '0' }}>
                <FontAwesomeIcon icon={faArrowAltCircleRight} style={{ fontSize: '24px', color:'black' }} />
            </button>
            </div>
        </div>
    );
}
//ss
export default SingleChat;
