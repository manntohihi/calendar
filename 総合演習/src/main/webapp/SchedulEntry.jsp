
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CalendarEvent" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<%
	CalendarEvent BarEvent = (CalendarEvent)request.getAttribute("BarEvent");

    String start = "";
    String end = "";

    if (BarEvent != null) {
        DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        start = BarEvent.getStart_datetime().format(fmt);
        end   = BarEvent.getEnd_datetime().format(fmt);
    }
%>
<html>
<head>
<meta charset="UTF-8">
<title>予定を入力</title>
<link rel="stylesheet" href="schedule.css">

</head>
<body>
<form action="CalendarServlet" method="post" id="scheduleForm">
<div class="container">

  <div class="header">
    <a href="CalendarServlet" class="back">← 戻る</a>
    <form action="EntryServlet" method="post" >
   		 <a href="EntryServlet?id=<%= BarEvent.getId() %>"  class="delete">削除</a>
   	</form>
  </div>

  <!-- タイトル -->
  <h1><input type="text" name="title" placeholder="予定を入力"
         value="<%= BarEvent != null ? BarEvent.getTitle() : "" %>"></h1>
  
  

  <!-- 期間 -->
  <div class="period">
    <input type="datetime-local" name="startDate"  id="startDate" value="<%= start %>"> ～ 
    <input type="datetime-local" name="endDate"    id="endDate"   value="<%= end   %>">
  </div>

   <!-- 担当者 -->
  <div class="staff">
    <label>担当者名</label>
   <input type="text" name="staffName" class="name" id="staffName" placeholder="担当者名を入力"
     value="<%= BarEvent != null ? BarEvent.getUserName() : "" %>">

  </div>


  <!-- メモ -->
  <textarea name="memo" id="memo" placeholder="メモを入力">
  	<%= 
  		BarEvent != null ? BarEvent.getTexdescription() : "" 
	%>
  </textarea>

  <!-- 追加 -->
  <button class="submit" id="submitBtn">追加する</button>
  <input type="hidden" name="from" value="ScheduleEntry.jsp">

</div>
</form>
</body>
</html>