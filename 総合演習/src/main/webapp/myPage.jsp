<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ画面</title>
    <link rel="stylesheet" href="mypage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <div class="icon">
            <img src="default.png" alt="アイコン">
        </div>
        <div class="info">
            <label class="name">ユーザー名</label>
            <a href="#"><button class="namecgbtn">名前を変更</button></a>
            <label class="id">ID: 1234</label>
            <a href="#"><button class="logoutbtn">ログアウト</button></a>
        </div>
    </div>
    <div class="bottom-nav">
        <a href="roomSelection.html" class="nav-item"><i class="fa-solid fa-house"></i></a>
        <a href="Calendar.jsp" class="nav-item"><i class="fa-solid fa-calendar"></i><span>カレンダー画面へ</span></a>
        <a href="mypage.jsp" class="nav-item"><i class="fa-solid fa-user"></i></a>
    </div>
</body>
</html>