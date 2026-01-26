<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	User user = (User)session.getAttribute("user");
	if(user == null){
		response.sendRedirect("login.jsp");
		return;
	}
	String current = request.getAttribute("currentPage");
	boolean isMypage = "Mypage".equals(current);
%>
<%@ page import="java.util.List,java.util.ArrayList,model.User" %>
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
            <label class="username"><%= user.getUserName() %></label>
            <a href="#"><button class="namecgbtn">名前を変更</button></a>
            <label class="userid">ID:<%= user.getUserId() %></label>
            <a href="#"><button class="logoutbtn">ログアウト</button></a>
        </div>
    </div>
    <div class="bottom-nav">
        <a href="roomSelection.html" class="nav-item" img src="/image/homeChange.png"><i class="fa-solid fa-house"></i></a>
        <a href="Calendar.jsp" class="nav-item" img src="/image/calendarChange.png"><i class="fa-solid fa-calendar"></i><span>カレンダー画面へ</span></a>
        <% if(isMypage){ %>
        	<span class="fa-solid fa-house">
        		<i class="fa-solid fa-user"></i>
        	</span>
        <% }else{ %>
        	<a href="Mypage" class="nav-item">
        		<i class="fa-solid fa-user"></i>
        	</a>
        <% %>
    </div>
</body>
</html>