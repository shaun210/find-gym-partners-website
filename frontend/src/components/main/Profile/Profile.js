import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { Container, Image, Row, Col, Button, Card, Form } from 'react-bootstrap';
import { getProfileData, checkIfFriends } from '../../../api/MemberApi';
import { sendFriendRequest } from '../../../api/FindPeopleApi';
import facebookIcon from './boostrap-icons/facebook.svg';
import instagramIcon from './boostrap-icons/instagram.svg';
import tiktokIcon from './boostrap-icons/tiktok.svg';
import snapchat from './boostrap-icons/snapchat.svg';
import './Profile.css';
const Profile = () => {
    const [profileData, setProfileData] = useState({});
    const { username } = useParams();
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const currentUser = storedMember ? storedMember.username : '';
    const [isFriend, setIsFriend] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await getProfileData(username, successCallback, failureCallback);
            } catch (error) {
                console.error('Error fetching profile data:', error);
            }
        };

        fetchData();
    }, []);

    const successCallback = async (response) => {
        setProfileData(response);
        if (currentUser ==  username) {
            return;
        }
        else {
            const response = await checkIfFriends(currentUser, username);
            if (response === 'true') {
                setIsFriend(true);
            }
        }
    };

    const handleSocialMediaClick = (link) => {
        if (link) window.open(link, '_blank');
        else window.alert('oops no link available');
    };

    const handleFriendRequest = async (receiver) => {
        let response = await sendFriendRequest(currentUser, receiver, friendRequestCallback,failureCallback);
    };

    const friendRequestCallback = (response) => {
        window.alert('Friend request sent!');
    }

    const failureCallback = (response) => {
        window.alert(response);
    }

    return (
        <div>
            <Container style={{ marginTop: '6rem' }}>
                <Row className="justify-content-center align-items-center">
                    <Col>
                        <Card>
                            <Card.Body className='text-center'>
                                <Image
                                    roundedCircle
                                    src={require('./dummy_profile.png')}
                                    alt='./dummy_pro'
                                    style={{ height: '10rem', width: 'auto' }}
                                />
                                <Card.Text className='profile-username'>
                                    {profileData.firstName} {profileData.lastName} ({profileData.username})
                                </Card.Text>
                                {profileData.personalDescription && (
                                    <Card.Text> ~ {profileData.personalDescription}</Card.Text>
                                )}
                                <Card.Text>Email: {profileData.email}</Card.Text>
                                <Card.Text>Gender: {profileData.gender}</Card.Text>
                                <Card.Text>Age: {profileData.age}</Card.Text>
                                <Card.Text>Gym Level: {profileData.gymLevel}</Card.Text>
                                <Card.Text>Address: {profileData.addressTown} {profileData.addressProvince}</Card.Text>
                                <Row className='p-3'>
                                    <Col>
                                        <Image src={facebookIcon} alt='facebook' style={{ height: '2rem', width: 'auto' }} onClick={() => handleSocialMediaClick(profileData.facebookLink)}/>
                                    </Col>
                                    <Col>
                                        <Image src={instagramIcon} alt='instagram' style={{ height: '2rem', width: 'auto' }} onClick={() => handleSocialMediaClick(profileData.instagramLink)} />
                                    </Col>

                                    <Col>
                                        <Image src={tiktokIcon} alt='tiktok' style={{ height: '2rem', width: 'auto' }} onClick={() => handleSocialMediaClick(profileData.snapchatLink)}/>
                                    </Col>
                                    <Col>
                                        <Image src={snapchat} alt='snapchat' style={{ height: '2rem', width: 'auto' }} onClick={() => handleSocialMediaClick(profileData.tiktokLink)}/>
                                    </Col>
                                </Row>

                                <Row>
                                    <Col>
                                        {isFriend ? (
                                            <Link to={'/singleChat/' + username}>
                                                <Button variant='danger' className='m-2'>
                                                    Message
                                                </Button>
                                            </Link>
                                           
                                        ): (currentUser !=  username) ? (
                                            <Button variant='danger' className='m-2' onClick={() => handleFriendRequest(username)}>
                                                Add friend
                                            </Button>
                                        ) :  null
                                        }
                                    </Col>
                                </Row>
                                
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Profile;
