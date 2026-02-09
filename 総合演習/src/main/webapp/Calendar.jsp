<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CalendarEvent,java.util.List,model.Room" %>
<!DOCTYPE html>
<%
List<CalendarEvent> CalendarEventList =
    (List<CalendarEvent>)request.getAttribute("CalendarEventList");
%>
<%
    Room room = (Room)session.getAttribute("room");
    if(room == null){
        response.sendRedirect("Mypage");
        return;
    }
%>
<html>
<head>
<meta charset="UTF-8">
<title>カレンダー</title>
<link rel="stylesheet" href="Calendar.css">
</head>

<body>

<div class="calendar-container">

  <!-- ヘッダー -->
  <div class="calendar-header">
    <div class="ym">
      <button id="prevBtn">＜</button>
      <span id="year"></span>年
      <span id="month"></span>月
      <button id="nextBtn">＞</button>
    </div>

    <form action="CalendarServlet" method="post">
      <input type="submit" value="予定を入力する">
      <input type="hidden" name="from" value="Calendar.jsp">
    </form>
  </div>

  <div id="calendar"></div>
</div>

<script>
/* ===============================
   DB → JSP → JS（events配列）
   =============================== */
const events = [
<%
if (CalendarEventList != null) {
  for (int i = 0; i < CalendarEventList.size(); i++) {
    CalendarEvent e = CalendarEventList.get(i);
%>
  {
    start: new Date(<%= e.getStart_datetime().getYear() %>,
                    <%= e.getStart_datetime().getMonthValue() - 1 %>,
                    <%= e.getStart_datetime().getDayOfMonth() %>),
    end:   new Date(<%= e.getEnd_datetime().getYear() %>,
                    <%= e.getEnd_datetime().getMonthValue() - 1 %>,
                    <%= e.getEnd_datetime().getDayOfMonth() %>),
    color: "<%= e.getColler() %>",
    id: "<%= e.getId() %>",
    description: "<%= e.getTexdescription() %>"
  }<%= (i < CalendarEventList.size() - 1) ? "," : "" %>
<%
  }
}
%>
];
/* 指定日のイベント取得 */
function getEventsForDay(day) {
  return events.filter(e => e.start <= day && day <= e.end);
}

const calendarEl = document.getElementById('calendar');
let currentDate = new Date();

/* ===============================
   カレンダー描画
   =============================== */
function renderCalendar(date) {
  const year = date.getFullYear();
  const month = date.getMonth();
  const daysInMonth = new Date(year, month + 1, 0).getDate();

  document.getElementById('year').textContent = year;
  document.getElementById('month').textContent = month + 1;

  const today = new Date();
  let html = '<table><thead><tr>';
  const weeks = ['日','月','火','水','木','金','土'];

  weeks.forEach(w => html += '<th>' + w + '</th>');
  html += '</tr></thead><tbody><tr>';

  const firstDay = new Date(year, month, 1).getDay();
  for (let i = 0; i < firstDay; i++) html += '<td></td>';

  for (let day = 1; day <= daysInMonth; day++) {
    const current = new Date(year, month, day);
    const dayEvents = getEventsForDay(current);

    let eventHtml = '<div class="events">';
    dayEvents.forEach(e => {
      eventHtml +=
        '<a href="EntryServlet?id=' + encodeURIComponent(e.id) + '">' +
          '<div class="event ' + e.color + '"></div>' +
        '</a>';
    });
    eventHtml += '</div>';
 
    const isToday =
      year === today.getFullYear() &&
      month === today.getMonth() &&
      day === today.getDate();

    html += '<td class="' + (isToday ? 'today' : '') + '">' +
              '<div class="day">' + day + '</div>' +
              eventHtml +
            '</td>';

    if (current.getDay() === 6 && day !== daysInMonth) {
      html += '</tr><tr>';
    }
  }

  html += '</tr></tbody></table>';
  calendarEl.innerHTML = html;
}

/* 初期表示 */
renderCalendar(currentDate);

/* 前月・次月 */
document.getElementById('prevBtn').onclick = () => {
  currentDate.setMonth(currentDate.getMonth() - 1);
  renderCalendar(currentDate);
};
document.getElementById('nextBtn').onclick = () => {
  currentDate.setMonth(currentDate.getMonth() + 1);
  renderCalendar(currentDate);
};
</script>
  		<div class="grid_test">
 			<div class="grid_test-child">
	       	 <a href="RoomSelection">
	        	<img src="img/Home.png" alt="Home" width="100">
	        </a>
	     	 </div>
	      <div class="grid_test-child">
	        <a href="CalendarServlet">
	        	<img src="img/Chalendar.png" alt="Calendar" width="100">
	        </a>
	      </div>
	      <div class="grid_test-child">
	        <a href="Mypage">
        		<img src="img/Mypage.png" alt="Move" width="100">
    		</a>
	    </div>
</div>
</body>
</html>