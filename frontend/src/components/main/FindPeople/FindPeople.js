import React, { useEffect, useState } from 'react';
import './FindPeople.css'
const FindPeople = () => {

    function handleKeyPress(event) {
        if (event.key === 'Enter') {
            console.log('enter press here! ')
        }
    }
    return (
        <div className='findPeopleBody'>
            <h2>Find New People!</h2>
            <div className='searchBox'>
                <div className='searchBar'> 
                    <input type='text' placeholder='Search for people' onKeyUpCapture={handleKeyPress}/>
                    
                </div>
                <div className='searchTypeBox'>
                    <select id='searchType' name='searchType'>
                        <option value='findBy' selected>Find By</option>
                        <option value='username'>Username</option>
                        <option value='email'>Email</option>
                    </select>
                </div>

            </div>

            <div className='peopleList'>
                
            </div>
        </div>
    )
}

export default FindPeople;