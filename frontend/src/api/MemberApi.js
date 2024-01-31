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
        // const params = new URLSearchParams();
        const formData = new FormData();
        for (const key in form) {
            console.log(key);
            formData.append(key, form[key]);
        }
        // console.log(params);
        const response = await fetch(onlineAPI + 'member/', {
            method: 'POST',
            body: formData,
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

// to get friend list
// export async function getFriendList(username, successCallback, failCallback) {
//     try {
//         const params = new URLSearchParams();
//         params.append('username', username);
        
//         const response = await fetch(onlineAPI + 'member/friendList?' + params, {
//             method: 'GET',
//             headers: { 'Content-Type': 'application/json' },
//         });
//         const data = await response.json();
//         if (response.ok) {
//             successCallback(data);
//         } else {
//             failCallback(data);
//         }
//     } catch (error) {
//         console.log(error);
//     }
// }