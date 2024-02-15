import { onlineAPI } from "../constants"
export async function login(username, password, successCallback, failCallback) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        params.append('password', password);
        
        const response = await fetch(onlineAPI + 'member/login?' + params, {
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

export async function signin(form, successCallback, failCallback) {
    try {
        const formData = new FormData();
        for (const key in form) {
            formData.append(key, form[key]);
        }
        const response = await fetch(onlineAPI + 'member/', {
            method: 'POST',
            body: formData,
        });

        if (response.ok) {
            const data = await response.json();
            successCallback(data);
        } else {
            const errorMessage = await response.text(); // Get the error message as text
            failCallback(errorMessage); // Pass the error message to the fail callback
        }
    } catch (error) {
        failCallback(error.message);
    }
}


export async function getProfilePicture(username) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        
        const response = await fetch(onlineAPI + 'member/profilePicture?' + params, {
            method: 'GET',
        });
        const data = await response.blob();
        if (response.ok) {
            return data;
        } else {
            console.log(data);
        }
    } catch (error) {
        console.log(error);
    }
}

export async function getProfileData(username, successCallback, failCallback) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        
        const response = await fetch(onlineAPI + 'member/singleProfile?' + params, {
            method: 'GET',
        });
        if (response.ok) {
            const data = await response.json();
            successCallback(data);
        } else {
            const data = await response.text();
            failCallback(data);
        }
    } catch (error) {
        failCallback(error);
    }
}

export async function checkIfFriends(username1, username2) {
    try {
        const params = new URLSearchParams();
        params.append('username1', username1);
        params.append('username2', username2);
        
        const response = await fetch(onlineAPI + 'friendRequest/areFriends?' + params, {
            method: 'GET',
        });
        const data = await response.text();
        if (response.ok) {
            return data;
        } else {
            console.log(data);
        }
    } catch (error) {
        console.log(error);
    }
} 