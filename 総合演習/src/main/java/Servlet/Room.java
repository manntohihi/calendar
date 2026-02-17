package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.CalendarDAO;
import dao.Room_membersDAO;
import model.User;
import model.UserEvent;
import model.barEvent;

/**
 * Servlet implementation class Room
 */
@WebServlet("/Room")
public class Room extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Room() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		Room_membersDAO rmemDao = new Room_membersDAO();
		
		User user = new User();
		user = (User)session.getAttribute("loginUser");
		model.Room room = (model.Room) session.getAttribute("room");
		
		List<barEvent> UserCalendarEventList;
		CalendarDAO cDao = new CalendarDAO();
		List<UserEvent> userEvList = new ArrayList<>();
		System.out.println("aaaaa"+user.getUserId() + room.getId());
		UserCalendarEventList = cDao.findUserCalendarDate(user.getUserId(), room.getId());
		for(barEvent event: UserCalendarEventList){ 
			LocalDateTime endDate = event.getEnd_datetime();
			LocalDate today = LocalDate.now();
			LocalDate end = endDate.toLocalDate();
			long remainingDays = ChronoUnit.DAYS.between(today, end);
			UserEvent userEv = new UserEvent(event.getTitle(), remainingDays);
			userEvList.add(userEv);
		}
		session.setAttribute("userEvList", userEvList);
		session.setAttribute("room", room);
		dispatcher = request.getRequestDispatcher("/Room.jsp");// /jsp/Room.jsp
		dispatcher.forward(request,response);//Room.jsp遷移
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
