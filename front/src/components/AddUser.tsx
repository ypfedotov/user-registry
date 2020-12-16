import React, {useState} from "react";
import {useHistory} from "react-router-dom";
import {createUser, Gender, User} from "../model/User";
import "./AddUser.scss";
import backend from "../backend/backend";
import BackToUserList from "./BackToUserList";
import DocumentTitle from "react-document-title";


function filterDate(input: string): string | null {
    return input.match(/^\d{0,4}-?\d{0,2}-?\d{0,2}$/) ? input : null;
}

function AddUser() {
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

                    <label htmlFor="dateOfBirth">Date of Birth</label>
                    <input type="text"
                           name="dateOfBirth"
                           placeholder="YYYY-MM-DD"
                           value={user.dateOfBirth}
                           onChange={e => {
                               const newValue = filterDate(e.target.value);
                               if (newValue !== null) {
                                   setUser({
                                       ...user,
                                       dateOfBirth: newValue
                                   });
                               }
                           }} />

                    <label htmlFor="gender">Gender</label>
                    <div className="radio-container">
                        <label className="radio-label">
                            <input type="radio"
                                   name="gender"
                                   checked={user.gender === "MALE"}
                                   onChange={e => setUser({
                                       ...user,
                                       gender: "MALE"
                                   })
                                   } />
                           Male
                        </label>
                        <label className="radio-label">
                            <input type="radio"
                                   name="gender"
                                   checked={user.gender === "FEMALE"}
                                   onChange={e => setUser({
                                       ...user,
                                       gender: "FEMALE"
                                   })
                                   } />
                            Female
                        </label>
                    </div>

                    <label htmlFor="photo">Photo</label>
                    <input type="file"
                           onChange={e => {
                               console.log(e.target.files);
                               const file = e.target.files && e.target.files[0];
                               if (file) {
                                   const reader = new FileReader();
                                   reader.readAsDataURL(file);
                                   reader.onload = e => setUser({
                                       ...user,
                                       photo: e.target?.result as string
                                   });
                               }
                           }}
                    />

                    <input type="submit" value="Save" />
                </form>
            </div>
        </DocumentTitle>
    );
}


export default AddUser;
