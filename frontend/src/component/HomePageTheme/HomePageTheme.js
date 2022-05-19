import React from 'react';
import {Link} from "react-router-dom";
import {IMG_URL} from "../../utils/constants/url";

const HomePageTheme = () => {
    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-lg-6">
                    <div className="card mb-5" >
                        <Link to={{pathname: "/menu", state: { id: "nam" }}}>
                            <img className="img-fluid" src="https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.15752-9/281568339_681156539633046_3358523241980794884_n.png?_nc_cat=100&ccb=1-7&_nc_sid=ae9488&_nc_ohc=LHInOCMTawgAX8fMD_z&_nc_ht=scontent.fsgn8-2.fna&oh=03_AVLR_c91XuaRBF7ByjzSLK8xxckdK1dJuKgJzzgW9r6ewA&oe=62ABE135"/>
                        </Link>
                    </div>
                </div>
                <div className="col-lg-6">
                    <div className="card mb-5">
                        <Link to={{pathname: "/menu", state: { id: "nữ" }}}>
                            <img className="img-fluid" src="https://scontent.fsgn4-1.fna.fbcdn.net/v/t1.15752-9/280568708_1178969049620560_6964155024865677890_n.png?_nc_cat=101&ccb=1-7&_nc_sid=ae9488&_nc_ohc=wIh3rK-25jwAX-ErAEw&_nc_oc=AQnF7i9CZ3Hp03i88eBfuEaKwUGUlZTqLLG_ZR_33s-TXX6-t7CZ6zSSWIBrg5Fk-qD33iIjfO0u6Py4w5IpzlWu&_nc_ht=scontent.fsgn4-1.fna&oh=03_AVKgkGXEWEKeQQ8Et8SfTEgeGzsc2OTuQvQ3mdH4Lry67g&oe=62ABDD48"/>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomePageTheme;