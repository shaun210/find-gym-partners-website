import { onlineAPI } from "../constants"
export async function getFriendList(username) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        const response = await fetch(onlineAPI + 'member/friendList?' + params, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
        });
        const data = await response.json();
        if (response.ok) {
            return data;
        } else {
            console.log(data);
        }
    } catch (error) {
        console.log(error);
    }
}

export async function getFriendRequests(username) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        const response = await fetch(onlineAPI + 'friendRequest/getAllFriendRequests?' + params, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
        });
        const data = await response.json();
        if (response.ok) {
            return data;
        } else {
            console.log(data);
        }
    } catch (error) {
        console.log(error);
    }
}

export async function changeAcceptedStatus(sender, receiver, accepted) {
    try {
        console.log(sender); console.log(receiver); console.log(accepted);
        const params = new URLSearchParams();
        params.append('sender', sender);
        params.append('receiver', receiver);
        params.append('accepted', accepted);
        const response = await fetch(onlineAPI + 'friendRequest/changeAcceptedStatus?' + params, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
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