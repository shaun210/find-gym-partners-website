const WEBSOCKET_URLS = {
    development: 'ws://localhost:3000/gs-guide-websocket',
    production: 'ws://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com/gs-guide-websocket',
};


const API_URLS = {
    development: 'http://localhost:8080/',
    production: 'http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com/api/',
};
  
const environment = process.env.NODE_ENV || 'development';
  
export const onlineAPI = API_URLS[environment];
export const websocketURL = WEBSOCKET_URLS[environment];

export const paths = {
    HOME: '/',
    FRIEND_LIST: '/friendList',
    LOGIN: '/login',
    SINGLE_CHAT: '/singleChat/:friendName',
    SIGNIN: '/signin',
    FIND_PEOPLE: '/findPeople',
    EDIT_PROFILE: '/editProfile',
}