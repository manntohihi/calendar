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

import dao.RoomDao;
import model.Room;

/**
 * Servlet implementation class RoomSelection
 */
@WebServlet("/RoomSelection")
public class RoomSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomSelection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RoomSelection.javaGet");
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/roomSelection.html");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RoomSelection.javaPost");
		String botton = request.getParameter("botton");
		int ID = 0;
		HttpSession sessrion = request.getSession();
		RequestDispatcher dispatcher;
		RoomDao rdao = new RoomDao();
		if("新規部屋作成".equals(botton)) {//新規部屋作成bottonが押される
			ID = Integer.parseInt(request.getParameter("roomID"));
			String roomID = request.getParameter("roomname");
			String userID = request.getParameter("roompassword");
			
			
			
			Room room = new Room(ID,roomID,userID); 
			rdao.createRoom(room);
			dispatcher = request.getRequestDispatcher("/roomChoice.html");
			dispatcher.forward(request,response);
		}else if ("検索".equals(botton)){//検索bottonが押される
			ID = Integer.parseInt(request.getParameter("roomSearchID"));
			List<Room> roomList = rdao.findFromID(ID);
			/*if(roomList==null) {//検索結果＝なし
				
			}else {//検索結果＝あり
				sessrion.setAttribute("roomList", roomList);//セッションにroomListを保存
				dispatcher = request.getRequestDispatcher("/roomChoice.html");
				dispatcher.forward(request,response);
			}*/
			if(ID == 1) {
				dispatcher = request.getRequestDispatcher("/roomSelectionError.html");
				dispatcher.forward(request,response);
			}else {
				dispatcher = request.getRequestDispatcher("/roomChoice.html");
				dispatcher.forward(request,response);
			}
			
		}
	}

}
