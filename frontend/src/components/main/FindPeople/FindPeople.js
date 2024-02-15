import React, { useEffect, useState } from 'react';
import './FindPeople.css'
import { Link } from 'react-router-dom';
import { findPeople, sendFriendRequest } from '../../../api/FindPeopleApi';
import { Container, Dropdown, Row, Col, Button, Card, Form } from 'react-bootstrap';
import { listOfAddress } from '../../../constants';
const FindPeople = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [gymLevel, setGymLevel] = useState('Gym Level');
    const [province, setProvince] = useState('Select Province');
    const [city, setCity] = useState('Select City');
    const [minAge, setMinAge] = useState('0');
    const [maxAge, setMaxAge] = useState('130');
    const [gender, setGender] = useState('Select Gender');
    const [peopleList, setPeopleList] = useState([]);
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';
    

    async function handleKeyPress(event) {
        event.preventDefault();
        if (
            gender === 'Select Gender' &&
            gymLevel === 'Gym Level' &&
            province === 'Select Province' &&
            city === 'Select City'
        ) {
            window.alert('Please select at least one filter');
            return; // Exit function if all values are still the default
        }
        const data = await findPeople(gender, gymLevel, city, minAge, maxAge, successCallbackFrindPeople, failureCallback);
        
    }
    // Send friend request
    const handleFriendRequest = async (receiver) => {
        let response = await sendFriendRequest(currentUser, receiver, friendRequestCallback,failureCallback);
    };

    const friendRequestCallback = (response) => {
        window.alert('Friend request sent!');
    }

    const successCallbackFrindPeople = (response) => {
        setPeopleList(response);
    }

    const failureCallback = (response) => {
        window.alert(response);
    }
    
    return (   
        <div className='findPeopleBody'>  
            <h2>Find New People!</h2>

            <Container>
                <Row className ='d-flex align-items-center justify-content-center' style = {{background:'#f6f9f8', borderRadius:'10px'}}> 
                    <Col>
                        <Dropdown>
                            <Dropdown.Toggle variant="success" id="dropdown-basic">
                                {province}
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                                {Object.keys(listOfAddress).map((address, index) => {
                                    return <Dropdown.Item key={index} onClick={() => setProvince(address)}>{address}</Dropdown.Item>
                                })}
                            </Dropdown.Menu>
                        </Dropdown>
                    </Col>
                    {province !== 'Select Province' && (
                        <Col>
                            <Dropdown>
                                <Dropdown.Toggle variant="success" id="dropdown-basic">
                                    {city}
                                </Dropdown.Toggle>
                                <Dropdown.Menu>
                                    {listOfAddress[province].map((cityName, index) => (
                                        <Dropdown.Item key={index} onClick={() => setCity(cityName)}>{cityName}</Dropdown.Item>
                                    ))}
                                </Dropdown.Menu>
                            </Dropdown>
                        </Col>
                    )}
                    <Col>
                        <Dropdown>
                            <Dropdown.Toggle variant="success" id="dropdown-basic">
                                {gymLevel}
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                                <Dropdown.Item onClick={() => setGymLevel('Beginner')} >Beginner</Dropdown.Item>
                                <Dropdown.Item onClick={() => setGymLevel('Intermediate')}>Intermediate</Dropdown.Item>
                                <Dropdown.Item onClick={() => setGymLevel('Advanced')}>Advanced</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    </Col>
                    <Col>
                        <Dropdown>
                            <Dropdown.Toggle variant="success" id="dropdown-basic">
                                {gender}
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                                <Dropdown.Item onClick={() => setGender('Male')} >Male</Dropdown.Item>
                                <Dropdown.Item onClick={() => setGender('Female')}>Female</Dropdown.Item>
                                <Dropdown.Item onClick={() => setGender('Other')}>Other</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    </Col>
                    <Col>
                        <Form.Group>
                            <Form.Label>Age Range</Form.Label>
                            <Form.Control
                                type="number"
                                placeholder="Min"
                                value={minAge}
                                onChange={(e) => setMinAge(e.target.value)}
                                min={0}
                            />
                            <Form.Control
                                type="number"
                                placeholder="Max"
                                value={maxAge}
                                onChange={(e) => setMaxAge(e.target.value)}
                                min={0}
                            />
                        </Form.Group>
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={handleKeyPress}>Search</Button>
                    </Col>
                </Row>
            </Container>


            {peopleList.length === 0 ? (
                <Card className="mt-3">
                    <Card.Body>
                        <Card.Title>No people found</Card.Title>
                        <Card.Text>
                            Sorry, no people match your search criteria. Try adjusting your search filters or check back later.
                        </Card.Text>
                    </Card.Body>
                </Card>
            ) : (

            <div className='peopleList'>
                {peopleList.map((people, index) => (
                    <div key={index} className='peopleBox'>
                        {/* <div>
                            <img src={`data:image/png;base64,${people.profilePicture}`} alt='./dummy_pro' className='peopleProfilePicture'/>
                        </div> */}
                        <div>
                            <img src='dummy_profile.png' alt='./dummy_pro' className='peopleProfilePicture'/>
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
                         <Link to={'/profile/' + people.username}>
                            <Button variant='danger' className='m-2'>
                                View Profile
                            </Button>
                        </Link>
                        </div>
                    </div>
                ))} 
            </div>
             )}
        </div>
    )
}

export default FindPeople;