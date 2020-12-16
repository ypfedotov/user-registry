import {v4 as uuid} from "uuid";

export type Gender = "MALE" | "FEMALE" | undefined;

export interface User {
    id: string,
    fullName: string,
    email: string,
    username: string,
    dateOfBirth: string,
    gender: Gender,
    photo: string | undefined,
}

export function createUser(): User {
    return {
        id: uuid(),
        fullName: "",
        email: "",
        username: "",
        dateOfBirth: "",
        gender: undefined,
        photo: undefined
    }
}
