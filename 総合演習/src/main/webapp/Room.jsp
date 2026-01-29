<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import java.util.ArrayList, java.util.List, model.User.java
%>
<%
	c
%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <title> 部屋メイン画面 </title>
    <link rel="stylesheet" href="room.css" />
  </head>
  <body>
  	<form action="Room" method="post">
	    <div class="grid_test">
	      <div class="grid_test-child">
	        <p><%= %></p>
	        <p>部屋ID</p>
	      </div>
	      <div class="grid_test-child">
	        <p>一時予定</p>
	        <p>利用者</p>
	      </div>
	      <div class="grid_test-child">
	        <p>残日数</p>
	      </div>
	      <div class="grid_test-child">
	        <p>部屋選択遷移</p>
	        <a href="RoomSelection" class="a"><img src="img/Home.png" class="icon"></a>
	      </div>
	      <div class="grid_test-child">
	        <p>カレンダー遷移</p>
	        <a href="CalendarServlet" class="a"><img src="img/Chalendar.png" class="icon"></a>
	      </div>
	      <div class="grid_test-child">
	        <p>マイページアイコン</p>
	        <a href="myPage.jsp" class="a"><img src="img/Mypage.png" class="icon"></a>
	      </div>
	    </div>
    </form>
  </body>
</html>