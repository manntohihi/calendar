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
import dao.RoomDao;
import dao.Room_membersDAO;
import model.Room;
import model.User;
import model.UserEvent;
import model.barEvent;


/**
 * Servlet implementation class RoomChoice
 */
@WebServlet("/RoomChoice")
public class RoomChoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomChoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("roomID");
		int roomid = Integer.parseInt(id);
		//なんとかroom作成
		
		RoomDao rdao = new RoomDao();
		List<Room> roomList = new ArrayList<Room>();
		roomList = rdao.findFromID(roomid);
		Room room = roomList.get(0);
		session.setAttribute("room", room);
		System.out.println("room名前"+ room.getRoomname());
		
		CalendarDAO cdao = new CalendarDAO();
		List<String> colorList = cdao.findColor(roomid);
		session.setAttribute("colorList", colorList);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/RoomChoice.jsp");// /jsp/RoomChoice.jsp
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("RCdP");//削除
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		Room_membersDAO rmemDao = new Room_membersDAO();
		int ID = Integer.parseInt( request.getParameter("roomID"));
		String password = request.getParameter("password");
		String color = request.getParameter("color");
		System.out.println(color);
		/*
		List<Room> roomList = new ArrayList<Room>();
		roomList = (List<Room>) session.getAttribute("roomList");
		for(Room room : roomList) {
			System.out.println("for");//削除
			if(ID == room.getId()) {
				System.out.println("if1");//削除
				if(password.equals(room.getRoompassword())) {
					System.out.println("if2");//削除
					/*if() {
						//calendarDao完成後collarを入れる
					}*/
		/*
					session.setAttribute("room", room);
					dispatcher = request.getRequestDispatcher("/Room.jsp");
					dispatcher.forward(request,response);//Room.jsp遷移
				}
			}
		}
		*/

		
		User user = new User();
		user = (User)session.getAttribute("loginUser");
		Room room = new Room();
		room = (Room) session.getAttribute("room");
		rmemDao.setColor(room.getId(), user.getUserId(), color);
		if(ID == room.getId()) {
			System.out.println("if1");//削除
			if(password.equals(room.getRoompassword())) {
				System.out.println("if2");//削除
				//
				

				if(rmemDao.searchUserInGroup(user.getUserId(), room.getId()) == null) {
					rmemDao.setUserGroup(user.getUserId(), room.getId());
				}
				//
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
		}
		System.out.println("error");//削除
		dispatcher = request.getRequestDispatcher("/RoomChoiceError.jsp");// /jsp/RoomChoiceError.jsp
		dispatcher.forward(request,response);//Room.jsp遷移
	}

}
