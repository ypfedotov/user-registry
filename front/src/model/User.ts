import {v4 as uuid} from "uuid";

export interface User {
    id: string,
    fullName: string,
    email: string,
    username: string,
}

export function createUser(): User {
    return {
        id: uuid(),
        fullName: "",
        email: "",
        username: "",
    }
}
