import React from "react";
import {Link} from "react-router-dom";
import "./BackToUserList.scss";


interface Props {

}

function BackToUserList(props: Props) {
    return (
        <div className="back-to-user-list">
            <Link to="/">
                <i className="material-icons">navigate_before</i>
                <span>Back to user list</span>
            </Link>
        </div>
    );
}


export default BackToUserList;
