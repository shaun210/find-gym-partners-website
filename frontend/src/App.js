
import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './components/main/Login/Login';
import SingleChat from './components/main/SingleChat/SingleChat';
import FriendList from './components/main/FriendList/FriendList';
import SignIn from './components/main/SignIn/SignIn';
import FindPeople from './components/main/FindPeople/FindPeople';
import Navbar from './components/navbar/navbar';
import { paths } from './constants';

function App() {
  return (
    <BrowserRouter>
      <div>
        <Navbar />
        <Routes>
          <Route path={paths.LOGIN} element={<Login />} />
          <Route path={paths.SINGLE_CHAT} element={<SingleChat />} />
          <Route path={paths.FRIEND_LIST} element={<FriendList />} />
          <Route path={paths.SIGNIN} element={<SignIn />} />
          <Route path={paths.FIND_PEOPLE} element={<FindPeople />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
