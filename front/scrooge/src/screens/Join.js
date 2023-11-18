import React, { useState } from "react";
import axios from "axios";
import ApiEndpoint from "../components/api/ApiEndpoint";
import "./Join.css";

const Join = () =>{
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [nickname, setNickname] = useState("");
    const [job, setJob] = useState("");
    const [income, setIncome] = useState("");

    const reqeustJoin = async(e) => {
        e.preventDefault();
        try {
            const response = await axios.post(
                ApiEndpoint.join,
                { userId, password, email, username, nickname, job, income }
            );
    
            if(response.status === 200) {
                alert("회원가입 완료");
            }
        } catch (error){
            alert(error.response.data);
        }
    }    

    return (
        <div className="loginForm">
            <form onSubmit={reqeustJoin} >
                <h1>회원가입</h1>
                <input id = "userId" name = "userId" value={userId} onChange={e=>setUserId(e.target.value)} placeholder="아이디"/>
                <input id = "password" name = "password" value={password} onChange={e=>setPassword(e.target.value)} placeholder="비밀번호"/>
                <input id = "email" name = "email" value={email} onChange={e=>setEmail(e.target.value)} placeholder="이메일"/>
                <input id = "username" name = "username" value={username} onChange={e=>setUsername(e.target.value)} placeholder="이름"/>
                <input id = "nickname" name = "nickname" value={nickname} onChange={e=>setNickname(e.target.value)} placeholder="닉네임"/>
                <input id = "job" name = "job" value={job} onChange={e=>setJob(e.target.value)} placeholder="직업"/>
                <input id = "income" name = "income" value={income} onChange={e=>setIncome(e.target.value)} placeholder="수입"/>
                <button type = "submit" >가입완료</button>
            </form>
        </div>
    );
}

export default Join;