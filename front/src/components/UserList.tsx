import React, {useEffect, useState} from "react";
import "./UserList.scss";
import backend from "../backend/backend";
import {User} from "../model/User";
import UserRow from "./UserRow";
import AddUserButton from "./AddUserButton";
import DocumentTitle from "react-document-title";
import SearchForm from "./SearchForm";



function UserList() {
    const [userList, setUserList] = useState<User[]>([]);

    async function refreshUserList() {
        const userList = await backend.fetchUserList()
        setUserList(userList);
    }

    useEffect(() => {
        refreshUserList();
    }, []);

    const userRows = userList.map(user => (
        <UserRow key={user.id}
                    user={user}
        />
    ));

    return (
        <DocumentTitle title="User List - User Registry">
            <div id="user-list-container">
                <div className="header">
                    <SearchForm resultCallback={setUserList} />
                    <AddUserButton />
                </div>
                <table id="user-list">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>username</th>
                        </tr>
                    </thead>
                    <tbody>
                    { userRows }
                    </tbody>
                </table>
            </div>
        </DocumentTitle>
    );
}

export default UserList;
