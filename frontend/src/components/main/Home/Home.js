import React, { useState } from "react";
import { Button, Card, Container, Image, Row, Col } from 'react-bootstrap';
import gymPic from './home-gym-pic.png';
import personArmUp from './bootstrapIcon/person-arms-up.svg';
import chatSquareText from './bootstrapIcon/chat-square-text-fill.svg';
import peopleFill from './bootstrapIcon/people-fill.svg';
import secondHomePic from './second-home-pic.png';
import { Link } from 'react-router-dom';
import { paths } from '../../../constants';

function Home() {
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';

    const handleGoToFindPeople = () => {
        if (!currentUser) {
            window.alert('Please sign in to use this feature');
            return;
        }
        window.location.href = paths.FIND_PEOPLE;
    }

    const handleGoToViewFriends = () => {
        if (!currentUser) {
            window.alert('Please sign in to use this feature');
            return;
        }
        window.location.href = paths.FRIEND_LIST;
    }
    
    return (
        <Container fluid>
            <Row className = "mb-5"style={{ marginTop: '5rem', marginBottom: '5rem'}}>
                <Col s={12} md={12} xl={7}>
                    <div className="d-flex align-items-center justify-content-center h-100" style={{background:'rgb(50, 116, 142)', color:'white', borderRadius:'5px'}}>
                        <div className="p-5">
                        <Row className="mb-4">
                            <h1>Welcome to GymBuddy</h1>
                        </Row>
                        <Row>
                            <p style={{ fontSize: '1.4rem' }}>
                            Discover the power of teamwork in achieving your fitness goals! Research shows that having a gym partner can significantly boost your motivation, accountability, and enjoyment of workouts. With a partner by your side, you'll have the support you need to stay consistent and push yourself further than you ever thought possible. Whether you're aiming to increase strength, lose weight, or simply improve your overall health, having a gym buddy can make the journey more fun and rewarding.
                            </p>
                        </Row>
                        </div>
                    </div>
                </Col>
                <Col s={12} md={12} xl={5}>
                    <Image src={gymPic} style={{borderRadius:'5px'}} className="img-fluid" />
                </Col>
            </Row>

            <hr className="bg-primary"></hr>


            

            <Row>
                <h1 className="text-center mb-5"> How it works?</h1>
            </Row>
            <Row className="d-flex justify-content-center">
                <Col md={5}>
                    <Card>
                        <div className="d-flex justify-content-center">
                            <Card.Img className='m-2' src={personArmUp} style={{ width: '10%', height: 'auto' }}/>
                        </div>
                        <Card.Body>
                            <Card.Title>Find new fitness partners</Card.Title>
                            <Card.Text>
                                Search for friends based on their fitness goals and location.
                            </Card.Text>

                            <Button onClick ={handleGoToFindPeople} variant="primary" size="sm" >Find people</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={5}>
                    <Card>
                        <div className="d-flex justify-content-center">
                            <Card.Img className='m-2' src={chatSquareText} style={{ width: '10%', height: 'auto' }}/>
                        </div>
                        <Card.Body>
                            <Card.Title>Start Chatting</Card.Title>
                            <Card.Text>
                                Connect with friends and start planning your workouts!
                            </Card.Text>
                            <Button onClick ={handleGoToViewFriends} variant="primary" size="sm" >View Friends</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

            <Row className="d-flex justify-content-center" style={{background: '#f8f9f9', marginTop:'4rem'} }>
                <div style={{width:'70%'}}>
                    <Image src={secondHomePic} style={{ width: '100%', height: 'auto' }} className="mb-3"/>

                    <h2 style={{ margin:'2rem 0' }} className="text-left">Why having a gym partner is important?</h2>
                    <p style={{ fontSize: '1.5rem' }}> Having a gym partner can significantly enhance your fitness journey in numerous ways. Studies have consistently shown that exercising with a companion not only boosts motivation but also improves overall performance and adherence to a workout regimen. Research conducted by the University of Aberdeen found that individuals who exercised with a partner were more likely to stick to their fitness routine compared to those who worked out alone. Moreover, having a gym buddy can lead to a more enjoyable and rewarding exercise experience, fostering a sense of camaraderie and support that encourages individuals to push their limits further.</p>

                    <p style={{ fontSize: '1.5rem' }}> Additionally, exercising with a partner can increase accountability, making individuals more committed to their fitness goals. A study published in the Annals of Behavioral Medicine revealed that participants who engaged in group workouts, such as those with a friend or partner, were more consistent in their exercise habits and demonstrated greater improvements in physical fitness over time. This sense of accountability stems from the mutual encouragement and shared commitment to achieving common goals, resulting in a more dedicated and disciplined approach to fitness.</p>

                    <p style={{ fontSize: '1.5rem' }}>Furthermore, working out with a partner can lead to greater success in achieving fitness milestones. According to a study published in the Journal of Sports Medicine and Physical Fitness, individuals who trained with a partner showed higher levels of performance and endurance during workouts compared to those who exercised alone. This phenomenon, known as the "social facilitation effect," suggests that the presence of a supportive companion can positively influence one's motivation, effort, and perseverance, ultimately leading to better fitness outcomes.</p>
                    
                </div>
            </Row>
        </Container>
    )
}

export default Home;