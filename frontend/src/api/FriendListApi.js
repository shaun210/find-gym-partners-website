import { onlineAPI } from "../constants"
export async function getFriendList(username) {
    try {
        const params = new URLSearchParams();
        params.append('username', username);
        const response = await fetch(onlineAPI + 'friendList?' + params, {
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