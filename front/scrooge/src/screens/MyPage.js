import axios from "axios";
import React from "react";
import ApiEndpoint from "../components/api/ApiEndpoint";
import { useNavigate } from "react-router-dom";

function getCookie(name) {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
      const [cookieName, cookieValue] = cookie.split('=');
      if (cookieName === name) {
        return cookieValue;
      }
    }
    return null;
}

const MyPage = () => {
    

    const navigate = useNavigate();
    const reqeustLogout = async (e) => {
        e.preventDefault();

        try {
            alert(getCookie('accessToken'));
            await axios.get(ApiEndpoint.logout, {
                    headers: {
                        Authorization : `Bearer ${getCookie('accessToken')}`,
                    },
                });

            document.cookie = `access=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
            alert('로그아웃')
            navigate('/login')
        } catch (error){
            alert(error.response.data)
        }
    }


    return(
        <div>
            <h1>마이페이지</h1>
            <form onSubmit={reqeustLogout}>
                <button type = "submit">로그아웃</button>
            </form>
        </div>
    );
};

export default MyPage;