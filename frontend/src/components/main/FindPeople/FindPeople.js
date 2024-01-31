import React, { useEffect, useState } from 'react';
import './FindPeople.css'
import { findPeople, sendFriendRequest } from '../../../api/FindPeopleApi';
import Alert from '../Alert/Alert';
const FindPeople = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [gymLevel, setGymLevel] = useState('Beginner');
    const [searchType, setSearchType] = useState('findBy');
    const [peopleList, setPeopleList] = useState([]);
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';
    const [alert, setAlert] = useState({});

    async function handleKeyPress(event) {
        if (event.key === 'Enter') {
            let response = await findPeople(searchQuery, gymLevel, searchType);
            console.log(response);
            setPeopleList(response);
        }
    }
    // Handle change in gymLevel
    const handleGymLevelChange = (event) => {
        setGymLevel(event.target.value);
    };

    // Handle change in searchType
    const handleSearchTypeChange = (event) => {
        setSearchType(event.target.value);
    };

    // console log list of people
    useEffect(() => {
        console.log(peopleList);
    }, [peopleList]);
    
    // Send friend request
    const handleFriendRequest = async (receiver) => {
        let response = await sendFriendRequest(currentUser, receiver, friendRequestCallback,failureCallback);
        console.log(response);
      };

    const friendRequestCallback = (response) => {
        setAlert({ message: response, type: 'success' });
        window.alert('Friend request sent!');
    }

    const failureCallback = (response) => {
        window.alert(response);
    }
    
    return (   
        <div className='findPeopleBody'>  
            <h2>Find New People!</h2>
            <div className='searchBox'>
                <div className='searchBar'> 
                    <input type='text' placeholder='Search for people/address' onKeyUpCapture={handleKeyPress} value={searchQuery} onChange={ (event) => setSearchQuery(event.target.value) }/>   
                </div>
                <div className='gymLevelBox'>
                    <select id='gymLevel' name='gymLevel' value={gymLevel} onChange={handleGymLevelChange}>
                        <option value='Beginner'>Beginner</option>
                        <option value='Intermediate'>Intermediate</option>
                        <option value='Advanced'>Advanced</option>
                    </select>
                </div>
                <div className='searchTypeBox'>
                    <select id='searchType' name='searchType' value={searchType} onChange={handleSearchTypeChange}>
                        <option value='findBy'>Find By</option>
                        <option value='username'>Username</option>
                        <option value='address'>Address</option>
                    </select>
                </div>
            </div>

            <div className='peopleList'>
                {peopleList.map((people, index) => (
                    <div key={index} className='peopleBox'>
                        <div>
                            <img src={`data:image/png;base64,${people.profilePicture}`} alt='./dummy_pro' className='peopleProfilePicture'/>
                        </div>
                        <div> 
                            <div className='peopleName'>
                                <p>{people.username}</p>
                            </div>
                            <div className='peopleAddress'>
                                <p>{people.address}</p>
                            </div>
                            <div className='peopleGymLevel'>
                                <p>{people.gymLevel}</p>
                            </div>
                        </div>
                        <div>
                         <button className='peopleButton' onClick={() => handleFriendRequest(people.username)}>Add Friend</button>
                        </div>
                    </div>
                ))}
            </div>
            <Alert message={alert.message} type={alert.type} onClose={() => setAlert({})} />

        </div>
    )
}

export default FindPeople;