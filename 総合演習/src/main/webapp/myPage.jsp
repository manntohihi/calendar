<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Room" %>

<%
    // セッションからログインユーザーを取得
    User user = (User) session.getAttribute("loginUser");

	//セッションから部屋ログイン情報を取得
	Room currentRoom = (Room) session.getAttribute("room");
	boolean inRoom = (currentRoom != null);
	
    // 未ログインならログイン画面へ
    if (user == null) {
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
            <a href="nameChange.jsp"><button class="namecgbtn">名前を変更</button></a>

            <label class="userid">ID: <%= user.getUserId() %></label>
            <a href="LogoutServlet"><button class="logoutbtn">ログアウト</button></a>
        </div>
    </div>

    <div class="bottom-nav">

    <!-- 常に表示：部屋選択 -->
    <a href="RoomSelection" class="nav-item">
        <div class="nav-icon">
            <img src="img/Home.png" alt="部屋選択">
        </div>
    </a>

    <% if (inRoom) { %>
        <!-- 部屋ログイン中だけ表示：カレンダー -->
        <a href="CalendarServlet" class="nav-item">
            <div class="nav-icon">
                <img src="img/Calendar.png" alt="カレンダー">
            </div>
        </a>

        <!-- 部屋ログイン中だけ表示：マイページ -->
        <a href="myPage.jsp" class="nav-item">
            <div class="nav-icon">
                <img src="img/Mypage.png" alt="マイページ">
            </div>
        </a>
    <% } %>

	</div>
</body>
</html>