<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

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
      <span id="year"></span>
       <button id="prevBtn">＜</button>
      <span id="month"></span>
      <button id="nextBtn">＞</button>
    </div>
    
    
    
    
    
   	<form action="CalendarServlet" method="post">
		<input type="submit" value="予定を入力する">
		<input type="hidden" name="from" value="Calendar.jsp">

	</form>
	
  </div>
	
  <!-- カレンダー -->
  <div id="calendar"></div>

</div>

<script>
	const events = [
		{
			start: new Date(2026, 0, 28), // 2026/1/28
	    	end:   new Date(2026, 1, 3),  // 2026/2/3（月またぎ）
	    	color: 'yellow'
	  	}
	];
	function getEventsForDay(day) {
		return events.filter(e => e.start <= day && day <= e.end);
	}
	
	const calendarEl = document.getElementById('calendar');

	let currentDate = new Date(); // 表示中の日付

	function renderCalendar(date) {
 		const currentYear = date.getFullYear();
  		const currentMonth = date.getMonth(); // 0始まり
  		const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();


  document.getElementById('year').textContent = currentYear;
  document.getElementById('month').textContent = (currentMonth + 1) + '月';

  const today = new Date();
  const todayYear = today.getFullYear();
  const todayMonth = today.getMonth();
  const todayDate = today.getDate();

  let calendarHtml = '<table><thead><tr>';
  const weeks = ['日', '月', '火', '水', '木', '金', '土'];

  for (let i = 0; i < 7; i++) {
    calendarHtml += '<th>' + weeks[i] + '</th>';
  }

  calendarHtml += '</tr></thead><tbody>';

  for (let i = 1; i <= daysInMonth; i++) {
    const dayOfWeek = new Date(currentYear, currentMonth, i).getDay();

    if (i === 1) {
      calendarHtml += '<tr>';
      for (let j = 0; j < dayOfWeek; j++) {
        calendarHtml += '<td></td>';
      }
    }
	
    let isToday =
    currentYear === todayYear &&
    currentMonth === todayMonth &&
      i === todayDate;

    let todayClass = isToday ? 'today' : '';
    let eventHtml = '';

    /* 仮の予定（あとでDBに置き換える） */
   	if (i >= 20 && i <= 23) {
   		dayEvents.forEach(e(配列名) => {
   		  eventHtml += '<div class="event ' + e.color + '"></div>';
   		});
	}else{
		eventHtml = '';
	}
	
   
    calendarHtml += '<td class="' + todayClass + '">'  + '<div>' + i + '</div>'+ eventHtml + '</td>';
    
    
    if (dayOfWeek === 6) {
        calendarHtml += '</tr>';
        if (i < daysInMonth) calendarHtml += '<tr>';
      } else if (i === daysInMonth) {
        for (let j = dayOfWeek + 1; j <= 6; j++) {
          calendarHtml += '<td></td>';
        }
        calendarHtml += '</tr>';
      }
    }

    calendarHtml += '</tbody></table>';
    calendarEl.innerHTML = calendarHtml;
  }
	  /* 初期表示 */
	  renderCalendar(currentDate);

	  /* 前月 */
	  document.getElementById('prevBtn').addEventListener('click', () => {
	    currentDate.setMonth(currentDate.getMonth() - 1);
	    renderCalendar(currentDate);
	  });

	  /* 次月 */
	  document.getElementById('nextBtn').addEventListener('click', () => {
	    currentDate.setMonth(currentDate.getMonth() + 1);
	    renderCalendar(currentDate);
	  });


</script>

</body>

</html>