<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Room_members,model.Room,dao.RoomDao,java.util.ArrayList,java.util.List" %>
<% 	User user = new User();
	user = (User) session.getAttribute("loginUser");
	int userid = 0;
	userid = user.getUserId();
	List<Room> roomList = 
			(List<Room>) session.getAttribute("roomList");
	int rmuserid = 0;
	String roomname = null;
	int roomid = 0;
	RoomDao rdao = new RoomDao();
	%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <title>部屋選択画面</title>
    <link rel="stylesheet" href="roomSelection.css" />
  </head>
  <body>
	<form action="RoomSelection" method="post">
	    <div class="grid_test">
	      <div class="grid_test-child">
	        <!-- <div class="nav">
	          <input id="drawer_input" class="drawer_hidden" type="checkbox">
	          <label for="drawer_input" class="drawer_open"><span></span></label>
	          <nav class="nav_content">
	            <ul class="nav_list">
	              <li class="nav_item"><a href="CalendarServlet">カレンダー画面</a></li>
	              <li class="nav_item"><a href="EntryServlet">予定入力画面</a></li>
	            </ul>
	          </nav>
	        </div> -->
	      </div>
	      <div class="grid_test-child">
	      	<a href="Mypage">
        		<img src="img/Mypage.png" alt="Move" width="100">
    		</a>
	      </div>
	      <div class="grid_test-child">
	        <details>
	          <summary>新規部屋作成</summary>
	            <input class="text" type="text" name="roomID" pattern="^[0-9]+$" placeholder="部屋のID">
	            <input class="text" type="text" name="roomname" maxlength="16" placeholder="部屋の名前 半角16文字">
	            <input class="text" type="text" name="roompassword" maxlength="6" placeholder="部屋のパスワード 半角6文字">
	            <input class="new"type="submit" name="botton" value="新規部屋作成" >
	        </details>
	      </div>
	      <div class="grid_test-child">
	        <input class="text" type="text" name="roomSearchID" pattern="^[0-9]+$" placeholder="部屋ID">
	      </div>
	      <div class="grid_test-child">
	        <input type="submit" name="botton" value="検索">
	      </div>
	      <div class="grid_test-child">
	        <!-- <p>グループアイコン</p> -->
	        	<%for(Room rm : roomList){
		        	if(rm.getRoomname() != null){
		        			%><a href="RoomChoice?roomID=<%= rm.getId() %>">
		        				<p><%= rm.getRoomname()%></p>
		        			</a> <%
		        			}
	        	}%>
	      </div>
	    </div>
	</form>
  </body>
</html>