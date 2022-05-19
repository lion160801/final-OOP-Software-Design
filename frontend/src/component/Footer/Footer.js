import React from 'react';

import "./Footer.css";

const Footer = () => {
    return (
        <footer className="page-footer p-5 bg-black text-white">
            <div className="container">
                <div className="d-flex justify-content-between">
                    <div className="footer-left">
                        <h3>Perfume Shop</h3>
                        <p>0843 017 796</p>
                        <br/>
                        <p>Giờ làm việc: 8h - 20h (Chủ nhật nghỉ)</p>
                    </div>
                    <div className="footer-right">
                        <h3>Mạng xã hội</h3>
                        <a href="https://www.linkedin.com/in/merikbest/">
                            <i className="fab fa-linkedin fa-2x mr-3" style={{color: "white"}}></i>
                        </a>
                        <a href="#"><i className="fab fa-facebook-f fa-2x mr-3" style={{color: "white"}}></i></a>
                        <a href="#"><i className="fab fa-twitter fa-2x mr-3" style={{color: "white"}}></i></a>
                    </div>
                </div>
                <div className="mx-auto" style={{width: "200px"}}>
                    <p>© Copy right g2perfumeshop</p>
                </div>
            </div>
        </footer>
    );
}

export default Footer