import React from "react";
import MainLogo from "../../images/menu/chart.png";
import AccountBookLogo from "../../images/menu/feather-pen.png";
import CommunityLogo from "../../images/menu/comment.png";
import MyPageLogo from "../../images/menu/user.png";
import { Link } from "react-router-dom";
import './Menu.css';

const Menu = () => {
    return (
        <div className="Menu">
            <Link to = "/"> 
                <img src={MainLogo} alt ="imgErr"/>
            </Link>
            <Link to ="/accountBook"> 
                <img src={AccountBookLogo} alt ="imgErr"/>
            </Link>
            <Link to = "/community">
                <img src={CommunityLogo} alt ="imgErr"/>
            </Link>
            <Link to = "/myPage">
                <img src={MyPageLogo} alt ="imgErr"/>
            </Link>
        </div>
    );
};

export default Menu;