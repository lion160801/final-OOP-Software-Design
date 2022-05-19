import React from 'react';
import {Toast} from "react-bootstrap";

import "./ToastShow.css";

function ToastShow(props) {
    return (
        <Toast className={"border border-success bg-success text white mt-3"} show={props.showToast}>
            <Toast.Header className={"bg-success text-white"} closeButton={false}>
                <strong className="mr-auto">Thành công</strong>
            </Toast.Header>
            <Toast.Body className={"text-white"}>
                {props.message}
            </Toast.Body>
        </Toast>
    );
}

export default ToastShow;