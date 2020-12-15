import React, {useState} from "react";
import {useHistory} from "react-router-dom";
import {createUser, User} from "../model/User";
import "./AddUser.scss";
import backend from "../backend/backend";
import BackToUserList from "./BackToUserList";
import DocumentTitle from "react-document-title";


interface Props {

}

function AddUser(props: Props) {
    const [user, setUser] = useState<User>(() => createUser());

    const history = useHistory();

    return (
        <DocumentTitle title="Add new user - User Registry">
            <div id="add-user">
                <BackToUserList />
                <h1>Add New User</h1>
                <form id="add-user-form"
                      onSubmit={async e => {
                          e.preventDefault();
                          await backend.saveUser(user);
                          history.push("/");
                      }}>
                    <label htmlFor="fullName">ID</label>
                    <input type="text"
                           name="id"
                           disabled={true}
                           value={user.id}
                           />


                    <label htmlFor="fullName">Full Name</label>
                    <input type="text"
                           name="fullName"
                           value={user.fullName}
                           onChange={e => {
                               setUser({
                                   ...user,
                                   fullName: e.target.value
                               });
                           }} />

                    <label htmlFor="email">email</label>
                    <input type="text"
                           name="email"
                           value={user.email}
                           onChange={e => {
                               setUser({
                                   ...user,
                                   email: e.target.value
                               });
                           }} />

                    <label htmlFor="username">username</label>
                    <input type="text"
                           name="username"
                           value={user.username}
                           onChange={e => {
                               setUser({
                                   ...user,
                                   username: e.target.value
                               });
                           }} />

                    <input type="submit" value="Save" />
                </form>
            </div>
        </DocumentTitle>
    );
}


export default AddUser;
