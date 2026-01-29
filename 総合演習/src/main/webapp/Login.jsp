<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <title>ログイン画面</title>
    <link rel="stylesheet" href="Login.css">
  </head>
  <body>
	<form action="Login" method="post">
		<div class="grid_test">
	        <div class="grid_test-child" style="text-align: center;">
	          <h1>マトリョシカ</h1><br>
	        </div>
	        <div class="grid_test-child">
	          <table class="line">
	                <tr>
	                    <th>ID</th>
	                </tr>
	                <tr>
	                    <th><input class="text" type="text" name="ID" placeholder="部屋のID 数字"></th>
	                </tr>
	            </table>
	        </div>
	        <div class="grid_test-child">
	          <table class="line">
	                <tr>
	                    <th>password</th>
	                </tr>
	                <tr>
	                    <th><input class="text" type="password" name="password" placeholder="部屋のパスワード 半角6文字" maxlength="6"></th>
	                </tr>
	            </table>
	        </div>
	        <div class="grid_test-child">
	          <input type="submit" name="login" value="ログイン">
	        </div>
	        <div class="grid_test-child" align="right">
	          <a href="CreateNewUserServlet" class="btn-link">新規登録</a>
	        </div>
		</div>
	</form> 
  </body>
</html>