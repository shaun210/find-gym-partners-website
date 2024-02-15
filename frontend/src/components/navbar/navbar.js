import React, { useEffect, useState, useRef }  from 'react';
import { Link } from 'react-router-dom';
import { paths } from '../../constants'; 
import './navbar.css';
const Navbar = () => {
    const storedMember = JSON.parse(localStorage.getItem('member'));
    const [currentUser, setCurrentUser] = useState(storedMember ? storedMember.username : '');
    const [isLogged, setIsLogged] = useState(false);

    // must check if user is logged in
    useEffect(() => {
        if (currentUser === '') {
            setIsLogged(false);
        }
        else {
            setIsLogged(true);
        }
    }, [currentUser]);

    const handleLogout = () => {
        localStorage.removeItem('member');
        window.location.href = paths.LOGIN;
    };

    return (
        <div className='navbarParent'>
            <div className='navbar'>
                <div className='navbarLeft'>
                    <div className='navbarLogo'>
                        <Link to={paths.HOME} style={{ textDecoration: 'none', color: 'inherit' }} >
                            <h1>Find your gym Partner</h1>
                        </Link>
                    </div>
                </div>

                {/* this part must contain a logged in vs no logged function */}

                <div className='navbarRight'>
                    <div className='navbarButtons'>
                        {isLogged ? (
                            <div className='loggedIn'>
                                <div className='friendListButton'>
                                    <Link to={paths.FRIEND_LIST}>
                                        <button>Friend list</button>
                                    </Link>
                                </div>
                                <div className='findFriendButton'>
                                    <Link to={paths.FIND_PEOPLE}>
                                        <button>Find new partners</button>
                                    </Link>
                                </div>
                                <div className='findFriendButton'>
                                    <Link to={'/profile/' + currentUser}>
                                        <button>View profile</button>
                                    </Link>
                                </div>
                                <div className='logOutButton'>
                                    <button onClick={handleLogout}>Log out</button>
                                </div>
                            </div>
                        ): (
                            <div className='loggedOut'>
                                <div className='signInButton'>
                                    <Link to={paths.SIGNIN}>
                                        <button>Sign in</button>
                                    </Link>
                                </div>
                                <div className='logInButton'>
                                    <Link to={paths.LOGIN}>
                                        <button>Log in</button>
                                    </Link>
                                </div>
                            </div>
                        )}            
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Navbar;