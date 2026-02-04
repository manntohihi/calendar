<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Room" %>
<%
    User user = (User)request.getAttribute("user");
    if(user == null){
        response.sendRedirect("Login.jsp");
        return;
    }
    
    String current = (String)request.getAttribute("currentPage");
    boolean isMypage = "Mypage".equals(current);
    
    
    Room room = (Room)session.getAttribute("room");
    boolean isRoomLoggedIn = (room != null);
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
        <div class="icon">
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
        <i class="fa-solid fa-door-open"></i>
        <span>部屋選択</span>
    </a>

    <!-- カレンダー（部屋ログイン時のみ有効） -->
    <% if(isRoomLoggedIn){ %>
        <a href="CalendarServlet" class="nav-item">
            <i class="fa-solid fa-calendar"></i>
            <span>カレンダー</span>
        </a>
    <% } else { %>
        <span class="nav-item disabled" style="opacity:0.4; pointer-events:none;">
            <i class="fa-solid fa-calendar"></i>
            <span>部屋にログインしてください</span>
        </span>
    <% } %>

    <!-- マイページ（現在地ならクリック不可） -->
    <% if(isMypage){ %>
        <span class="nav-item active">
            <i class="fa-solid fa-user"></i>
            <span>マイページ</span>
        </span>
    <% } else { %>
        <a href="Mypage" class="nav-item">
            <i class="fa-solid fa-user"></i>
            <span>マイページ</span>
        </a>
    <% } %>

</div>

</body>
</html>