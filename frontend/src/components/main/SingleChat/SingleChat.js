import React, { useEffect, useState, useRef }  from 'react';
import { getAllMessages, getChatId, checkChatExists, createChat} from '../../../api/ChatApi';
import { useParams } from 'react-router-dom';
import './SingleChat.css';
import SingleMessage from './SingleMessage';
import { Client } from '@stomp/stompjs';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowAltCircleRight } from '@fortawesome/free-solid-svg-icons';


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
                let answer = await getChatId(currentUser, friendName);
                if (answer === undefined) {
                    let check = await checkChatExists(currentUser, friendName);
                    if (check === false) {
                        let chat = await createChat(currentUser, friendName);
                        chatID.current = chat.chatId;
                    }
                    else return;
                }
                else{
                    chatID.current = answer;
                }    
                let messages = await getAllMessages(answer);
                setChatMessages(messages);

                socketClient.current = new Client({
                    brokerURL: 'ws://localhost:8080/gs-guide-websocket',
                    reconnectDelay: 5000,
                    heartbeatIncoming: 4000,
                    heartbeatOutgoing: 4000,
                    onConnect: onConnected,
                    onDisconnect: onDisconnected,
                });

                socketClient.current.activate();
                
                return () => {
                    // Clean up the WebSocket connection when the component unmountsd
                    socketClient.current && socketClient.current.deactivate();
                };            
            } catch (error) {
                console.error(error);
            }
        };
        fetchData(); // Invoke the async function inside useEffectdd
        
    }, []);

    function onConnected() {
        if (socketClient.current.connected) {
            console.log('Connected!!');
            console.log(chatID);
            socketClient.current.subscribe('/topic/chat/' + chatID.current, (message) => {
                console.log('Received message:', message.body);
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
        console.log(message);
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
        console.log(socketClient.connected);
        if (socketClient && socketClient.current.connected) {
            console.log('connected');
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
