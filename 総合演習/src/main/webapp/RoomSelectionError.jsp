<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	        <div class="nav">
	          <input id="drawer_input" class="drawer_hidden" type="checkbox">
	          <label for="drawer_input" class="drawer_open"><span></span></label>
	          <nav class="nav_content">
	            <ul class="nav_list">
	              <li class="nav_item"><a href="">メニュー1</a></li>
	              <li class="nav_item"><a href="">メニュー2</a></li>
	              <li class="nav_item"><a href="">メニュー3</a></li>
	            </ul>
	          </nav>
	        </div>
	      </div>
	      <div class="grid_test-child">
	        <p>マイページアイコン</p>
	      </div>
	      <div class="grid_test-child">
	        <details>
	          <summary>新規部屋作成</summary>
	            <input type="text" name="roomID" class="text" placeholder="部屋のID">
	            <input type="text" name="roomname" maxlength="16" class="text" placeholder="部屋の名前 半角16文字">
	            <input type="text" name="roompassword" maxlength="6" class="text" placeholder="部屋のパスワード 半角6文字">
	            <input type="button" name="botton" value="新規部屋作成" class="new">
	        </details>
	      </div>
	      <div class="grid_test-child">
	        <input type="text" name="roomSearchID" class="text" placeholder="部屋ID">
	      </div>
	      <div class="grid_test-child">
	        <input type="button" name="botton" value="検索">
	      </div>
	      <div class="grid_test-child">
	        <p>グループアイコン</p>
	      </div>
		  <div class="grid_test-child">
		    <p class="error">IDまたはpasswordが異なります</p>
		  </div>
	    </div>
	</form>
  </body>
</html>