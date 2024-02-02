import { onlineAPI } from "../constants"
export async function getChatId(username, friendname, successCallback) {
    try {
        const params = new URLSearchParams();
        params.append('member1Username', username);
        params.append('member2Username', friendname);
        
        const response = await fetch(onlineAPI + 'chatId?' + params, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
        });
        const data = await response.text();
        if (response.ok) {
            successCallback(data);
        } else {
            console.log(data);
        }
    } catch (error) {
        console.log(error);
    }
}

export async function getAllMessages(chatId) {
    try {
        const params = new URLSearchParams();
        params.append('chatId', chatId);
        
        const response = await fetch(onlineAPI + 'allMessages?' + params, {
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

// check if chat exists
export async function checkChatExists(username, friendname) {
    try {
        const params = new URLSearchParams();
        params.append('member1Username', username);
        params.append('member2Username', friendname);
        
        const response = await fetch(onlineAPI + 'chatExists?' + params, {
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

//create chat
export async function createChat(username, friendname) {
    try {
        const params = new URLSearchParams();
        params.append('member1Username', username);
        params.append('member2Username', friendname);
        
        const response = await fetch(onlineAPI + 'createChat?' + params, {
            method: 'POST',
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