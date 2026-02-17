<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import= "java.util.List,model.Room"
%>
<% 
	Room room = new Room();
	room = (Room) session.getAttribute("room");
	String roomName;
	roomName = room.getRoomname();
	//
	List<String> colorList = null;
	colorList = (List<String>) session.getAttribute("colorList");
	if (colorList == null){
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/RoomSelectionError.jsp");// /jsp/RoomSelectionError.jsp
		dispatcher.forward(request,response);//RoomSelectionError.jsp遷移
	}
%>
<!doctype html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <title>部屋ログイン画面</title>
    <link rel="stylesheet" href="roomChoice.css" />
    <%if(colorList != null ){
		for(int n = 0; n < colorList.size(); n++){
			String color = colorList.get(n);
			if(color.equals("red")){//変更
				System.out.println("red");
				%><style>
					.red{display: none;}
				</style><%
			}else if(color.equals("blue")){
				%><style>
					.blue{display: none;}
				</style><%
			}else if(color.equals("yellow")){
				%><style>
					.yellow{display: none;}
				</style><%
			}else if(color.equals("green")){
				%><style>
					.green{display: none;}
				</style><%
			}else if(color.equals("lightblue")){
				%><style>
					.lightblue{display: none;}
				</style><%
			}else if(color.equals("pink")){
				%><style>
					.pink{display: none;}
				</style><%
			}else if(color.equals("orange")){
				%><style>
					.orange{display: none;}
				</style><%
			}else if(color.equals("purple")){
				%><style>
					.purple{display: none;}
				</style><%
			}else if(color.equals("yellowgreen")){
				%><style>
					.yellowgreen{display: none;}
				</style><%
			}
		}
 			}
	%>
  </head>
  <body>
  	<form action="RoomChoice" method="post">
    <div class="grid_test">
        <div class="grid_test-child">
          <h1><%= roomName%></h1><br>
        </div>
        <div class="grid_test-child">
          <table class="line">
                <tr>
                    <th>ID</th>
                </tr>
                <tr>
                    <th><input type="text" name="roomID" placeholder="部屋のID" class="text"></th>
                </tr>
            </table>
        </div>
        <div class="grid_test-child">
          <table class="line">
                <tr>
                    <th>password</th>
                </tr>
                <tr>
                    <th><input type="password" name="password" maxlength="6" placeholder="部屋のパスワード 半角6文字" class="password"></th>
                </tr>
            </table>
        </div>
        <div class="grid_test-child">
            <div class="grid_test2">
            	<input type="radio" name="color" id="color-reset" value="" checked style="display:none;">
				<div class="grid_test-child2">
			    	<input type="radio" id="red" name="color" value="red" class="red"/>
					<label for="red" class="red">red</label>
			    </div>
			    <div class="grid_test-child2">
			    	<input type="radio" id="blue" name="color" value="blue" class="blue"/>
			    	<label for="blue" class="blue">blue</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="yellow" name="color" value="yellow" class="yellow"/>
			    	<label for="yellow" class="yellow">yellow</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="green" name="color" value="green" class="green"/>
			    	<label for="green" class="green">green</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="lightblue" name="color" value="lightblue" class="lightblue"/>
			    	<label for="lightblue" class="lightblue">lightblue</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="pink" name="color" value="pink" class="pink"/>
			    	<label for="pink" class="pink">pink</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="orange" name="color" value="orange" class="orange"/>
			    	<label for="orange" class="orange">orange</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="purple" name="color" value="purple" class="purple"/>
			    	<label for="purple" class="purple">purple</label>
			    </div>
			    <div class="grid_test-child2">
					<input type="radio" id="yellowgreen" name="color" value="yellowgreen" class="yellowgreen"/>
			    	<label for="yellowgreen" class="yellowgreen">yellowgreen</label>
				</div>
            </div>
        </div>
        <div class="grid_test-child">
            <input type="submit" name="botton" value="ログイン">
        </div>
    </div>
    </form>
    <script>
        document.querySelector('.grid_test').addEventListener('click', function(e) {
            // クリックされたのが label や input でない（余白である）場合にリセット
            if (e.target.classList.contains('grid_test-child') || e.target.classList.contains('grid_test')) {
                document.getElementById('color-reset').checked = true;
            }
        });
    </script>
  </body>
</html>