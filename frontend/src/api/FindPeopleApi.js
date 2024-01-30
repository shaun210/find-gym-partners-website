import { onlineAPI } from "../constants"
export async function findPeople(query, gymLevel, searchType) {
    try {
        const params = new URLSearchParams();
        const response = '';
        if (searchType === 'username') {
            params.append('username', query);
            response = await fetch(onlineAPI + 'findPeopleByUsername?' + params, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
            });
        }
        else if (searchType === 'address') {
            params.append('gymLevel', gymLevel);
            params.append('addressTown', query);
            response = await fetch(onlineAPI + 'findPeopleByGymLevelAndAddress?' + params, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
            });
        }
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