<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名前変更画面</title>
<link rel="stylesheet" href="nameChange.css">
</head>
<body>
	<div class="form-container">
    	<h2>名前の変更</h2>

    	<form action="NameChangeServlet" method="post">
        	<label>現在の名前：</label>
        	<input type="text" name="currentName" maxlength="30" required>

        	<label>新しい名前：</label>
        	<input type="text" name="newName" maxlength="30" required>

        	<label>新しい名前（確認）</label>
        	<input type="text" name="newNameConfirm" maxlength="30" required>

        	<button type="submit">変更する</button>
        	<button type="button" onclick="history.back()">キャンセル</button>

     	   <p style="color:red;">${error }</p>
    	</form>
	</div>
</body>
</html>