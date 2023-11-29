import axios from "axios";
import React from "react";
import ApiEndpoint from "../components/api/ApiEndpoint";

const MyPage = () => {

    const reqeustLogout = async (e) => {
        e.preventDefault();

        try {
            await axios.get(ApiEndpoint.logout, {
                    headers: {
                        Authorization : `Bearer ${document.cookie}`,
                    },
                });
            
            // document.cookie = "accessToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC"
            alert('로그아웃')
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