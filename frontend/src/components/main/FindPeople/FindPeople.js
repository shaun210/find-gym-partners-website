import React, { useEffect, useState } from 'react';
import './FindPeople.css'
import { findPeople } from '../../../api/FindPeopleApi';
const FindPeople = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [gymLevel, setGymLevel] = useState('Beginner');
    const [searchType, setSearchType] = useState('findBy');
    const [peopleList, setPeopleList] = useState([]);
    async function handleKeyPress(event) {
        if (event.key === 'Enter') {
            let response = await findPeople(searchQuery, gymLevel, searchType);
            console.log(response);
            setPeopleList(response);
        }
    }
    return (
        <div className='findPeopleBody'>
            <h2>Find New People!</h2>
            <div className='searchBox'>
                <div className='searchBar'> 
                    <input type='text' placeholder='Search for people/address' onKeyUpCapture={handleKeyPress} value={searchQuery} onChange={ (event) => setSearchQuery(event.target.value) }/>   
                </div>
                <div className='gymLevelBox'>
                    <select id='gymLevel' name='gymLevel'>
                        <option value='Beginner' selected>Beginner</option>
                        <option value='Intermediate'>Intermediate</option>
                        <option value='Advanced'>Advanced</option> 
                    </select>
                </div>
                <div className='searchTypeBox'>
                    <select id='searchType' name='searchType'>
                        <option value='findBy' selected>Find By</option>
                        <option value='username'>Username</option>
                        <option value='address'>Address</option>
                    </select>
                </div>
            </div>

            <div className='peopleList'>
                {peopleList.map((people, index) => (
                    <div key={index} className='peopleBox'>
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
                ))}
            </div>
        </div>
    )
}

export default FindPeople;