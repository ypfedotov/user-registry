import {User} from "../model/User";
import axios from "axios";

const userList = [
    {
        id: "d5a1ad4c-01b5-41f9-b827-5641b5fc8b61",
        fullName: "Ivan Ivanov",
        email: "ivan@gmail.com",
        username: "ivan",
    },
    {
        id: "2c285b30-506c-4fae-b3eb-ba2e23ff5e52",
        fullName: "Peter Petrov",
        email: "peter@gmail.com",
        username: "peter",
    },
];

axios.defaults.baseURL = "/";


const backend = {
    async fetchUserList(): Promise<User[]> {
        const response = await axios.get("/users");
        return response.data;
    },

    async fetchUser(userId: string): Promise<User | undefined> {
        const response = await axios.get(`/users/${userId}`);
        return response.data;
    },

    async saveUser(user: User) {
        await axios.post(
            `/users`,
            [user],
        );
    },

    async fetchUsersByName(query: string): Promise<User[]> {
        const response = await axios.get(`/users/search?query=${encodeURIComponent(query)}`);
        return response.data;
    }
}

export default backend;
