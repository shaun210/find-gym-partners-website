import { onlineAPI } from "../constants"
export async function login(username, password, successCallback, failCallback) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        params.append('password', password);
        
        const response = await fetch(onlineAPI + 'login?' + params, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
        });
        const data = await response.json();
        if (response.ok) {
            successCallback(data);
        } else {
            failCallback(data);
        }
    } catch (error) {
        console.log(error);
    }
}

// to get friend list
export async function getFriendList(username, successCallback, failCallback) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        
        const response = await fetch(onlineAPI + 'getFriendList?' + params, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
        });
        const data = await response.json();
        if (response.ok) {
            successCallback(data);
        } else {
            failCallback(data);
        }
    } catch (error) {
        console.log(error);
    }
}