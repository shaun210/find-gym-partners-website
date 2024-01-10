import React, { useEffect, useState } from 'react';
import { getFriendList } from '../../../api/FriendListApi';
import { Link } from 'react-router-dom';
import './FriendList.css';

const FriendList = () => {
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';
    const [friendList, setFriendList] = useState([]);
    useEffect(() => {
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

        fetchFriendList();
    }, []);

    return (
        <div className='friendListParent'>
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

const FriendBox = ({friend}) => {
    return (
        <div className='friendBox'>
            <div className='friendName'>
                {friend}
            </div>
            <div className='goToChat'>
                <Link to={'/singleChat/' + friend}>
                    <button>Go to chat</button>
                </Link>
            </div>
        </div>
    );
}

export default FriendList;