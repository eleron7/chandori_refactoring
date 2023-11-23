import React, { useState } from "react";
import axios from "axios";
import ApiEndpoint from "../components/api/ApiEndpoint";
import ScroogeLogo from  "../images/header/scrooge.png";
import "./Login.css";

const Login = () => {
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");
    const [isManager, setIsManager] = useState("");

    const reqeustLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(
                ApiEndpoint.Login,
                { userId, password, isManager }
            );
    
            if(response.status === 200) {
                alert("로그인 완료");
            }
        } catch (error){
            alert(error.response.data);
        }
    }

    return (
        <div className="loginForm">
            <form onSubmit={reqeustLogin}>
                <div className="loginFormLogo">
                    <img src = {ScroogeLogo} alt = "imgErr"/>
                    <h1>scrooge</h1>
                </div>
                <input id = "userId" name = "userId" value = {userId} onChange={e=>setUserId(e.target.value)} placeholder = "아이디"/>
                <input id = "password" name = "password" type = "password" value = {password} onChange={e=>setPassword(e.target.value)} placeholder = "비밀번호"/>
                <div className="loginCheckBox">
                    <label id = "isManager"> 관리자로 로그인 </label>
                    <input id = "isManager" name = "isManager" type = "checkbox" value= {isManager} onChange={e=>setIsManager(e.target.value)}/>
                </div>
                <button type = "submit">로그인</button>
            </form>
        </div>
    );
}

export default Login;