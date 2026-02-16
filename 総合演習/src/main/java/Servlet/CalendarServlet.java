package Servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CalendarDAO;
import model.Room;
import model.User;
import model.barEvent;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        Room room = (Room) session.getAttribute("room");

        if (room == null) {
            response.sendRedirect("Mypage");
            return;
        }

        CalendarDAO cDao = new CalendarDAO();
        List<barEvent> list =
                cDao.findCalendarDate(user.getUserId(), room.getId());

        request.setAttribute("CalendarEventList", list);

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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		Room room = (Room) request.getSession().getAttribute("room");
		String from =
				 (String)request.getParameter("from");

		if(room == null){

		    response.sendRedirect("Mypage");

		    return;


		}else {
	    	RequestDispatcher dispatcher = 
	    		request.getRequestDispatcher("SchedulEntry.jsp");
	    	dispatcher.forward(request, response);
		}
	}

}
