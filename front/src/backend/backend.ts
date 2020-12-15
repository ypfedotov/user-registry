import {User} from "../model/User";

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

const backend = {
    async fetchUserList(): Promise<User[]> {
        return new Promise(resolve => {
            setTimeout(() => {
                resolve(userList);
            }, 300);
        });
    },

    async fetchUser(userId: string): Promise<User | undefined> {
        return new Promise(resolve => {
            setTimeout(() => {
                resolve(userList.find(u => u.id === userId))
            }, 300);
        });
    },

    async saveUser(user: User) {
        return new Promise(resolve => {
            setTimeout(() => {
                userList.push(user);
                resolve(undefined);
            }, 300);
        });
    },

    async fetchUsersByName(query: string): Promise<User[]> {
        return new Promise(resolve => {
            setTimeout(() => {
                resolve(userList.slice(0, 1));
            }, 300);
        });
    }
}

export default backend;
