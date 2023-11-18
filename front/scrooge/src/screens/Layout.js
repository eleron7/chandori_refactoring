import React from "react";
import Header from "../components/header/Header";
import Menu from "../components/menu/Menu";
import "./Layout.css";

const Layout = ({children}) => {
    return (
        <div>
            <header>
                <Header/>
            </header>
            <contents>
                {children}
            </contents>
            <footer>
                <Menu/>
            </footer>
        </div>
    );
};

export default Layout;