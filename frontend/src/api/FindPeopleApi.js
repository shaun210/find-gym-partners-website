import { onlineAPI } from "../constants"
export async function findPeople(query, gymLevel, searchType) {
    try {
        const params = new URLSearchParams();
        let response = '';
        if (searchType === 'username') {
            console.log(query + ' ' + gymLevel + ' ' + searchType);
            params.append('username', query);
            response = await fetch(onlineAPI + 'member/findPeopleByUsername?' + params, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
            });
        }
        else if (searchType === 'address') {
            params.append('gymLevel', gymLevel);
            params.append('addressTown', query);
            response = await fetch(onlineAPI + 'member/findPeopleByGymLevelAndAddress?' + params, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
            });
        }
        console.log(response);
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