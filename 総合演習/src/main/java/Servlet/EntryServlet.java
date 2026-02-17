package Servlet;

import java.io.IOException;
import java.time.LocalDateTime;

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
 * Servlet implementation class EntryServlet
 */
@WebServlet("/EntryServlet")
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EntryServlet;doget");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		CalendarEvent BarEvent;
		CalendarDAO cDao = new CalendarDAO();
		String Id = request.getParameter("id");
		int id = Integer.parseInt(Id);
		BarEvent = cDao.findBarDate(user.getUserId(), id );
		System.out.println(BarEvent);
		request.setAttribute("BarEvent", BarEvent);
		RequestDispatcher dispatcher = 
	    	request.getRequestDispatcher("SchedulEntry.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("loginUser");
	        Room room = (Room) session.getAttribute("room");

	        if (room == null) {
	            response.sendRedirect("Mypage");
	            return;
	        }

	        String action = request.getParameter("action");
	        CalendarDAO cDao = new CalendarDAO();

	        try {

	            if ("add".equals(action)) {

	                String title = request.getParameter("title");
	                String memo  = request.getParameter("memo");
	                String staff = request.getParameter("staffName");

	                LocalDateTime start =
	                        LocalDateTime.parse(request.getParameter("startDate"));
	                LocalDateTime end =
	                        LocalDateTime.parse(request.getParameter("endDate"));

	                cDao.setCalendarDate(
	                        room.getId(),
	                        title,
	                        memo,
	                        start,
	                        end,
	                        user.getUserId(),
	                        staff
	                );

	            } else if ("update".equals(action)) {

	                int id = Integer.parseInt(request.getParameter("id"));

	                String title = request.getParameter("title");
	                String memo  = request.getParameter("memo");
	                String staff = request.getParameter("staffName");

	                LocalDateTime start =
	                        LocalDateTime.parse(request.getParameter("startDate"));
	                LocalDateTime end =
	                        LocalDateTime.parse(request.getParameter("endDate"));

	                cDao.updateCalendarDate(
	                        id,
	                        title,
	                        memo,
	                        start,
	                        end,
	                        user.getUserId(),
	                        staff
	                );

	            } else if ("delete".equals(action)) {

	                int id = Integer.parseInt(request.getParameter("id"));
	                cDao.deleteCalendarDate(id,user.getUserId());
	            }

	            response.sendRedirect("CalendarServlet");

	        } catch (Exception e) {
	            e.printStackTrace();
	            RequestDispatcher dispatcher =
	                    request.getRequestDispatcher("error.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
	}
