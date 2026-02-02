package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Room;

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
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/RoomChoice.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("RCdP");//削除
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		int ID = Integer.parseInt( request.getParameter("roomID"));
		String password = request.getParameter("password");
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
					session.setAttribute("room", room);
					dispatcher = request.getRequestDispatcher("/Room.jsp");
					dispatcher.forward(request,response);//Room.jsp遷移
				}
			}
		}
		System.out.println("error");//削除
		dispatcher = request.getRequestDispatcher("/RoomChoiceError.jsp");
		dispatcher.forward(request,response);//Room.jsp遷移
	}

}
