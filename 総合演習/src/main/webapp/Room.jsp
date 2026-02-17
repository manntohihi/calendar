<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Room, model.UserEvent" %>

<%
	List<UserEvent> userEvList =
    	(List<UserEvent>) session.getAttribute("userEvList");
		
		
	Room room = new Room();
	room = (Room) session.getAttribute("room");
	int roomID;
	roomID = room.getId();
	String roomName;
	roomName = room.getRoomname();
	session.setAttribute("room", room);
%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <title> 部屋メイン画面 </title>
    <link rel="stylesheet" href="room.css" />
  </head>
  <body>
  	<form action="RoomFront" method="post">
	    <div class="grid_test">
	      <div class="grid_test-child">
	        <h1><%= room.getRoomname() %>  <%= room.getId() %></h1>
	      </div>
	      <div class="grid_test-child">
	        <p>一時予定</p>
	        <p>利用者</p> 
	      </div>
	      <div class="grid_test-child">
			<% for(UserEvent event : userEvList){ %>
				<div class="box">
  				<td>
  					<span class="title"><%= event.getTitle() %></span>
  					のこり
  					<span class="days"><%= event.getRemainingDays() %></span>日
				</td>
  				</div>
  			<% } %>
	      </div>
	      <div class="grid_test-child">
	        <a href="RoomSelection">
	        	<img src="img/Home.png" alt="Home" width="100">
	        </a>
	      </div>
	      <div class="grid_test-child">
	        <a href="CalendarServlet">
	        	<img src="img/Calendar.png" alt="Calendar" width="100">
	        </a>
	      </div>
	      <div class="grid_test-child">
	        <a href="Mypage">
        		<img src="img/Mypage.png" alt="Move" width="100">
    		</a>
	      </div>
	    </div>
    </form>
  </body>
</html>