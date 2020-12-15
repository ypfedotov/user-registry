import React from "react";
import {Link} from "react-router-dom";
import "./AddUserButton.scss";


function AddUserButton() {
    return (
        <div className="add-user-button">
            <Link to="/add-user"
                  title="Add a new user"
            >
                <i className="material-icons">person_add</i>
            </Link>
        </div>
    );
}


export default AddUserButton;
