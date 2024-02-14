import React, { useEffect, useState } from 'react';
import './FindPeople.css'
import { findPeople, sendFriendRequest } from '../../../api/FindPeopleApi';
import { Container, Dropdown, Row, Col, Button, Card, Form } from 'react-bootstrap';
const FindPeople = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [gymLevel, setGymLevel] = useState('Beginner');
    const [searchType, setSearchType] = useState('findBy');
    const [peopleList, setPeopleList] = useState([]);
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';
    

    async function handleKeyPress(event) {
    
        if (searchType === 'findBy') {
            window.alert('Please select search type');
            return;
        }
        let response = await findPeople(searchQuery, gymLevel, searchType);
        setPeopleList(response); 
    }    
    // Send friend request
    const handleFriendRequest = async (receiver) => {
        let response = await sendFriendRequest(currentUser, receiver, friendRequestCallback,failureCallback);
        console.log(response);
    };

    const friendRequestCallback = (response) => {
        window.alert('Friend request sent!');
    }

    const failureCallback = (response) => {
        window.alert(response);
    }
    
    return (   
        <div className='findPeopleBody'>  
            <h2>Find New People!</h2>

            <Container>
                <Row className ='d-flex align-items-center justify-content-center' style = {{background:'#f6f9f8', height: '5rem', borderRadius:'10px'}}> 
                    <Col> 
                        <Form.Control
                            type="text"
                            placeholder="Search for people/address"
                            value={searchQuery}
                            onChange={(event) => setSearchQuery(event.target.value)}
                            style={{ margin: 'auto' }}
                        />
                        
                    </Col>
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
                                {searchType}
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                                <Dropdown.Item onClick={() => setSearchType('username')}>Username</Dropdown.Item>
                                <Dropdown.Item onClick={() => setSearchType('address')}>Address</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
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
                        </div>
                    </div>
                ))} 
            </div>
             )}
        </div>
    )
}

export default FindPeople;