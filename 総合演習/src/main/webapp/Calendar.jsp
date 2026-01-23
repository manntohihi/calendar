<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
      <button type="button" id="prevBtn">＜</button>
      <span id="month"></span>
      <button type="button" id="nextBtn">＞</button>
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
const calendarEl = document.getElementById('calendar');
let currentDate = new Date(); // 表示中の年月

function renderCalendar(date) {
  const currentYear  = date.getFullYear();
  const currentMonth = date.getMonth(); // 0始まり
  const daysInMonth  = new Date(currentYear, currentMonth + 1, 0).getDate();

  document.getElementById('year').textContent  = currentYear;
  document.getElementById('month').textContent = (currentMonth + 1) + '月';

  const today = new Date();

  let html = '<table><thead><tr>';
  const weeks = ['日','月','火','水','木','金','土'];

  weeks.forEach(w => {
    html += `<th>${w}</th>`;
  });

  html += '</tr></thead><tbody>';

  for (let i = 1; i <= daysInMonth; i++) {
    const dayOfWeek = new Date(currentYear, currentMonth, i).getDay();

    if (i === 1) {
      html += '<tr>';
      for (let j = 0; j < dayOfWeek; j++) {
        html += '<td></td>';
      }
    }

    const isToday =
      currentYear  === today.getFullYear() &&
      currentMonth === today.getMonth() &&
      i            === today.getDate();

    let eventHtml = '';

    // 仮予定（テスト用）
    if (i === 20) {
      eventHtml = '<div class="event yellow"></div>';
    }

    html += `
      <td class="${isToday ? 'today' : ''}">
        <div class="day">${i}</div>
        ${eventHtml}
      </td>
    `;

    if (dayOfWeek === 6) {
      html += '</tr>';
      if (i < daysInMonth) html += '<tr>';
    } else if (i === daysInMonth) {
      for (let j = dayOfWeek + 1; j <= 6; j++) {
        html += '<td></td>';
      }
      html += '</tr>';
    }
  }

  html += '</tbody></table>';
  calendarEl.innerHTML = html;
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