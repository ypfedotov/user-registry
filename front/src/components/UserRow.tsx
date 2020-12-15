import React from "react";
import "./UserRow.scss";
import {User} from "../model/User";
import {Link} from "react-router-dom";


interface Props {
    user: User,
}

function UserRow(props: Props) {
    const {user} = props;

    return (
        <tr>
            <td><Link to={`/user/${user.id}`}>{user.id}</Link></td>
            <td>{user.fullName}</td>
            <td>{user.username}</td>
        </tr>
    );
}


export default UserRow;
