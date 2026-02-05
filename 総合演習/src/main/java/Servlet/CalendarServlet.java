package Servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CalendarDAO;
import model.CalendarEvent;
import model.Room;
import model.User;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		Room room = (Room) request.getSession().getAttribute("room");
		System.out.println("room:"+room);
		if(room == null){

		    response.sendRedirect("Mypage");
		    System.out.println("Mypage");
		    return;

		}else {
		 
			System.out.println("CalendarServlet;doget");
			List<CalendarEvent> CalendarEventList;
			CalendarDAO cDao = new CalendarDAO();
			CalendarEventList = cDao.findCalendarDate(user.getUserId(),room.getId());
			request.setAttribute("CalendarEventList", CalendarEventList);
			System.out.println(2);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("Calendar.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CalendarServlet;dopost");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		Room room = (Room) request.getSession().getAttribute("room");
		String from =
				 (String)request.getParameter("from");

		if(room == null){

		    response.sendRedirect("Mypage");

		    return;

		}else if("ScheduleEntry.jsp".equals(from)) {
			try {
				System.out.println("サーブレットに移動");



				String memo      = request.getParameter("memo");
				String staffName = request.getParameter("staffName");
				String color = "yellow";
	    
				String s = request.getParameter("startDate");
				LocalDateTime sqlStartDate = LocalDateTime.parse(s);
				String e = request.getParameter("endDate");
				LocalDateTime sqlEndDate = LocalDateTime.parse(e);
	        
				
				CalendarDAO cDao = new CalendarDAO();
				cDao.setCalendarDate(room.getId(), memo, staffName, sqlStartDate, sqlEndDate, user.getUserId(),color);
	   
				response.sendRedirect("CalendarServlet");
			}catch (Exception e) {
				RequestDispatcher dispatcher = 
			    		request.getRequestDispatcher("error.jsp");
			    	dispatcher.forward(request, response);
			}
		}else if("Calendar.jsp".equals(from)) {
	    	RequestDispatcher dispatcher = 
	    		request.getRequestDispatcher("SchedulEntry.jsp");
	    	dispatcher.forward(request, response);
		}
	}

}
