import React from "react";
import ScroogeLogo from "../../images/header/scrooge.png";
import NightLogo from "../../images/header/night.png";
import './Header.css';

const Header = () => {
    return (
        <header>
            <div className="headerLeft">
                <img src={ScroogeLogo} alt="imgErr"/>
                <div className="appName" >Scrooge</div>
            </div>
            <div className="headerRight">
                <img src={NightLogo} alt="imgErr"/>
            </div>
        </header>
    );
};

export default Header;