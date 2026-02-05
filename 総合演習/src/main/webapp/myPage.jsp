<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
    User user = (User)request.getAttribute("user");
    if(user == null){
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ画面</title>
    <link rel="stylesheet" href="myPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

    <!-- 上部プロフィール -->
    <div class="container">
        <div class="profile-icon">
            <img src="default.png" alt="アイコン">
        </div>

        <div class="info">
            <label class="username"><%= user.getUserName() %></label>
            <a href="#"><button class="namecgbtn">名前を変更</button></a>

            <label class="userid">ID: <%= user.getUserId() %></label>
            <a href="#"><button class="logoutbtn">ログアウト</button></a>
        </div>
    </div>

    <div class="bottom-nav">

    <!-- 部屋選択（常にクリック可能） -->
    <a href="RoomSelection" class="nav-item">
    	<div class="nav-icon">
      	  <img src="img/Home.png" alt="部屋選択">
    	</div>
	</a>
</div>

</body>
</html>