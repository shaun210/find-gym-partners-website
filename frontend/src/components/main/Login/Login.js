import React, { useState } from 'react';
import { login } from '../../../api/MemberApi';
import './Login.css'; // Your CSS file

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    login(username, password, successLoginCallback, failLoginCallback);
  };

  const successLoginCallback = (data) => {
    console.log("member: ");
    console.log(data);
    localStorage.setItem('member', JSON.stringify(data));
    window.location.href = '/friendList';
  };

  const failLoginCallback = (data) => {
    console.log(data);
  };

  return (
    <div className="loginParentBlock">
      <form onSubmit={handleLogin} className="loginForm">
        <h2>Login</h2>
        <div className="inputGroup">
          <label>
            Username:
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </label>
        </div>
        <div className="inputGroup">
          <label>
            Password:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </label>
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
