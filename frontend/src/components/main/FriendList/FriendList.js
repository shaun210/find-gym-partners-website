import React, { useEffect, useState } from 'react';
import { getFriendList, getFriendRequests, changeAcceptedStatus, removeFriend } from '../../../api/FriendListApi';
import { Link } from 'react-router-dom';
import './FriendList.css';

const FriendList = () => {
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';
    const [friendList, setFriendList] = useState([]);
    const [friendRequests, setFriendRequests] = useState([]);
    useEffect(() => {        
        fetchFriendList();
        fetchFriendRequests();
    }, []);
    const fetchFriendList = async () => {
        try {
            const data = await getFriendList(currentUser);
            if (Array.isArray(data)) {
                setFriendList(data);
            } else {
                console.error('Friend list data is not an array.');
            }
        } catch (error) {
            console.error('Error fetching friend list:', error);
        }
    };
    const fetchFriendRequests = async () => {
        try {
            const data = await getFriendRequests(currentUser);
            if (Array.isArray(data)) {
                setFriendRequests(data);
            } else {
                console.error('Friend list data is not an array.');
            }
        } catch (error) {
            console.error('Error fetching friend list:', error);
        }
    };
    const handleAccept = async (sender, receiver, accepted) => {
        try {
            const data = await changeAcceptedStatus(sender, receiver, accepted);
            if (data) {
                fetchFriendRequests();
                fetchFriendList();
            } else {
                console.log('Not accepted');
            }
        } catch (error) {
            console.error('Error fetching friend list:', error);
        }
    }

    const handleRemoveFriend = async (receiver) => {
        try {
            const data = await removeFriend(currentUser, receiver);
            if (data) {
                fetchFriendList();
            } else {
                window.alert('Error removing friend');
            }
        } catch (error) {
            window.alert('Error removing friend:', error);
        }
    }

    const FriendBox = ({friend}) => {
        return (
            <div className='friendBox'>
                <div className='friendName'>
                    {friend}
                </div>
                <div className='friendListButton'>
                    <Link to={'/singleChat/' + friend}>
                        <button className='goToChatButton'>Go to chat</button>
                    </Link>
                    <button className='removeFriendButton' onClick={() => handleRemoveFriend(friend)}>Remove friend</button>
                </div>
            </div>
        );
    }



    return (
        <div className='friendListParent'>
            <h2>Friend Request</h2>
            <div className='listOfFriends'>
                {friendRequests.map((request, index) => (
                    <div key={index}>
                        <FriendRequestsNotification request={request} handleAccept={handleAccept} />
                    </div>
                ))}
            </div>
            <h2>Friend List</h2>
            <div className='listOfFriends'>
                {friendList.map((friend, index) => (
                    <div key={index}>
                        <FriendBox friend={friend.username} />
                    </div>
                ))}
            </div>
        </div>
    );
}



const FriendRequestsNotification = ({ request, handleAccept }) => {
    const { sender, receiver } = request;

    const handleAcceptClick = async () => {
        await handleAccept(sender, receiver, true);
    }

    const handleDeclineClick = async () => {
        await handleAccept(sender, receiver, false);
    }

    return (
        <div className='friendRequestsNotificationParent'>
            <p>{sender}</p>
            <button onClick={handleAcceptClick}>Accept</button>
            <button onClick={handleDeclineClick}>Decline</button>
        </div>
    );
}



export default FriendList;