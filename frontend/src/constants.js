const WEBSOCKET_URLS = {
    development: 'ws://localhost:3050/gs-guide-websocket',
    production: 'ws://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com/gs-guide-websocket',
};


const API_URLS = {
    development: 'http://localhost:3050/api/',
    production: 'http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com/api/',
};
  
const environment = process.env.NODE_ENV || 'development';
  
export const onlineAPI = API_URLS[environment];
export const websocketURL = WEBSOCKET_URLS[environment];

export const paths = {
    FRIEND_LIST: '/',
    LOGIN: '/login',
    SINGLE_CHAT: '/singleChat/:friendName',
    SIGNIN: '/signin',
    FIND_PEOPLE: '/findPeople',
    EDIT_PROFILE: '/editProfile',
}