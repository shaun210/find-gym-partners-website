import { onlineAPI } from "../constants"
export async function findPeople(gender, gymLevel, city, minAge, maxAge, successCallbackFrindPeople, failureCallback) {
    try {
        const params = new URLSearchParams();
        params.append("gymLevel", gymLevel);
        params.append("addressTown", city);
        params.append("minAge", minAge);
        params.append("maxAge", maxAge);
        params.append("gender", gender);
        const response = await fetch(onlineAPI + 'member/findPeople?' + params, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
        });
        
        if (response.ok) {
            const data = await response.json();
            console.log(data);
            successCallbackFrindPeople(data);

        } else {
            const data = await response.text();
            failureCallback(data);
        }
    } catch (error) {
        failureCallback(error.message);
    }
}

export async function sendFriendRequest(sender, receiver, friendRequestCallback, failureCallback) {
    try {
        const params = new URLSearchParams();
        params.append('sender', sender);
        params.append('receiver', receiver);
        const response = await fetch(onlineAPI + 'friendRequest/?' + params, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
        });
        const data = await response.text();
        if (response.ok) {
            friendRequestCallback(data);
        } else {
            console.log(data);
            failureCallback(data);
        }
    } catch (error) {
        console.log(error);
    }
}