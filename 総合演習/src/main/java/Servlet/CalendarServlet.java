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

import dao.CalendarDAO;
import model.CalendarEvent;

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
		System.out.println("CalendarServlet;doget");
		List<CalendarEvent> CalendarEventList;
		CalendarDAO cDao = new CalendarDAO();
		int group_id = 2;
		int user_id = 301973;
		CalendarEventList = cDao.findCalendarDate(user_id, group_id);
		request.setAttribute("CalendarEventList", CalendarEventList);
		RequestDispatcher dispatcher = 
	    		request.getRequestDispatcher("Calendar.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CalendarServlet;dopost");
		String from =
				 (String)request.getParameter("from");
		System.out.println(from);
		
		if("ScheduleEntry.jsp".equals(from)) {
			try {
				System.out.println("サーブレットに移動");


				int group_id = 2;
				String memo      = request.getParameter("memo");
				String staffName = request.getParameter("staffName");
				int user_id = 301973; 
				String color = "yellow";
	    
				String s = request.getParameter("startDate");
				LocalDateTime sqlStartDate = LocalDateTime.parse(s);
				String e = request.getParameter("endDate");
				LocalDateTime sqlEndDate = LocalDateTime.parse(e);
	        
				
				CalendarDAO cDao = new CalendarDAO();
				cDao.setCalendarDate(group_id, memo, staffName, sqlStartDate, sqlEndDate, user_id,color);
	   
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
