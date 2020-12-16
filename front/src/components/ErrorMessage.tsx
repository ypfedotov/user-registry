import React from "react";
import "./ErrorMessage.scss";


interface Props {
    message: string,
}

function ErrorMessage(props: Props) {
    const {message} = props;

    return message
        ? <><div>&nbsp;</div><div className="error">{message}</div></>
        : <><div>&nbsp;</div><div className="error">&nbsp;</div></>;
}


export default ErrorMessage;
