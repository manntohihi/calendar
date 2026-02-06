
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CalendarEvent" %>
<!DOCTYPE html>
<%
	CalendarEvent BarEvent = (CalendarEvent)request.getAttribute("BarEvent");
%>
<html>
<head>
<meta charset="UTF-8">
<title>予定を入力</title>
<link rel="stylesheet" href="schedule.css">

</head>
<body>
<form action="CalendarServlet" method="post" id="scheduleForm　">
<div class="container">

  <!-- 戻る -->
  <a href="CalendarServlet" class="back">← 戻る</a>

  <!-- タイトル -->
  <h1><input type="text" name="title" placeholder="予定を入力"
         value="<%= BarEvent != null ? BarEvent.getTitle() : "" %>"></h1>
  
  

  <!-- 期間 -->
  <div class="period">
    <input type="datetime-local" name="startDate"  id="startDate"> ～ <input type="datetime-local" name="endDate" id="endDate">
  </div>

   <!-- 担当者 -->
  <div class="staff">
    <label>担当者名</label>
   <input type="text" name="staffName" class="name" id="staffName" placeholder="担当者名を入力">

  </div>

  <button class="add-staff" name="staffName">担当者の追加</button>

  <!-- メモ -->
  <textarea name="memo" id="memo" placeholder="メモを入力"></textarea>

  <!-- 追加 -->
  <button class="submit" id="submitBtn">追加する</button>
  <input type="hidden" name="from" value="ScheduleEntry.jsp">

</div>
</form>
</body>
</html>