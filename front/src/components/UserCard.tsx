import React, {useEffect, useState} from "react";
import "./UserCard.scss";
import {useParams} from "react-router-dom";
import BackToUserList from "./BackToUserList";
import backend from "../backend/backend";
import {User} from "../model/User";
import DocumentTitle from "react-document-title";
import LoadingIndicator from "./LoadingIndicator";


function UserCard() {
    const { userId } = useParams<{userId: string}>();
    const [user, setUser] = useState<User | undefined>();

    useEffect(() => {
        (async () => {
            const fetchedUser = await backend.fetchUser(userId);
            setUser(fetchedUser);
        })();
    }, [userId, setUser]);

    const content = user
        ? (
            <>
                <h1>User {user.username}</h1>
                <div id="user-card">
                    <div className="label">ID</div>
                    <div className="data">{user.id}</div>

                    <div className="label">Full Name</div>
                    <div className="data">{user.fullName}</div>

                    <div className="label">email</div>
                    <div className="data">{user.email}</div>

                    <div className="label">username</div>
                    <div className="data">{user.username}</div>

                </div>
            </>
        )
        : <LoadingIndicator />

    const title = `User ${ user ? user.username : userId} - User Registry`

    return (
        <DocumentTitle title={title}>
            <div id="user-card-container">
                <BackToUserList />
                {content}
            </div>
        </DocumentTitle>
    );
}


export default UserCard;
