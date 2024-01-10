import React, { useState, useRef, useEffect }  from 'react';
import './SingleChat.css';
const SingleMessage = ({ message, sender, time }) => {
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const [currentUsername, setCurrentUsername] = useState(storedMember ? storedMember.username : '');


    const formatDateTime = (dateTime) => {
        const date = new Date(dateTime);
        const formattedDate = date.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'numeric',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: true,
        });
        return formattedDate;
    };
    const messageStyle = {
        backgroundColor: currentUsername === sender ? '#94A7AE' : '#F4F2F3',
        color: currentUsername === sender ? '#FFFFFF' : '#000000',
    };
    return (
        <div className="messageBox">
            <div className="messageNameAndTme">
                <p>{sender}</p>
                <p>{formatDateTime(time)}</p>
            </div>
            <div className="messageContent" style={messageStyle}>
                <p sty>{message}</p>
            </div>
        </div>
    );
}
export default SingleMessage;