<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名前変更画面</title>
</head>
<body>
	<h2>名前の変更</h2>
	
	<form action="NameChangeServlet" method="post">
		<!-- 現在の名前（手入力） -->
		<label>現在の名前：</label>
		<input type="text" name="currentName" maxlength="30" required>
		
		<!-- 新しい名前（手入力） -->
		<label>新しい名前：</label>
		<input type="text" name="newName" maxlength="30" required>
		
		<br><br>
		
		<!-- 新しい名前（確認用）（手入力） -->
		<label>新しい名前（確認）</label>
		<input type="text" name="newNameConfirm" maxlength="30" required>
		
		<br><br>
		
		<button type="submit">変更する</button>
		<button type="button" onclick="history.back()">キャンセル</button>
		
		<!-- エラーメッセージ -->
		<p style="color:red;">${error }</p>
	</form>
</body>
</html>