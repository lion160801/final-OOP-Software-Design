import React from 'react';
import {faInfoCircle} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

function Contacts() {
    return (
        <div className="container mt-5">
            <h4><FontAwesomeIcon className="ml-2 mr-2" icon={faInfoCircle}/>Contacts</h4>
            <br/>
            <p><b>Số điện thoại:</b> 0843 017 796<br/>
                <b>E-mail:</b> g2perfumeshop@gmail.com</p>
            <br/>
            <h6>Giờ làm việc</h6>
            <p> Cửa hàng trực tuyến mở cửa từ 08:00 đến 20:00 không nghỉ và các ngày cuối tuần. <br/>
                Đơn đặt hàng trực tuyến được chấp nhận suốt ngày đêm. </p>
            <br/>
            <h6> Giao hàng </h6>
            <p> Việc giao đơn đặt hàng thông qua dịch vụ chuyển phát nhanh. </p>
        </div>
    )
}

export default Contacts