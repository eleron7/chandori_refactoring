<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>로그인</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body style="background-color: rgb(20, 19, 31)">
<div class="mx-auto rounded" style="background-color: rgb(44, 42, 54); width: 500px;">
    <form class="form-signin" method="post" action="/login-process">
        <div class="container">
            <div class="row text-center mb-5">
                <div class="col-5 text-right">
                    <img src="/images/scrooge.png" style="width: 80px;" alt="로고">
                </div>
                <div class="col-6 text-left">
                    <p class="mt-3" style="color: rgb(171, 171, 171); font-size: 30px;">Scrooge</p>
                </div>
            </div>
        </div>

        <p>
            <label for="email" class="sr-only">이메일</label>
            <input type="text" id="email" name="email" class="form-control" placeholder="이메일" required="" autofocus="">
        </p>
        <p>
            <label for="password" class="sr-only">비밀번호</label>
            <input type="text" id="password" name="password" class="form-control" placeholder="비밀번호" required="" autofocus="">
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
    </form>

    <form class="form-signin" method="get" action="/view/join">
        <button class="btn btn-lg btn-warning btn-block" type="submit">회원가입하기</button>
    </form>
</div>
</body>
</html>