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

import dao.RoomDao;
import model.Room;
import model.User;

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
		System.out.println("RoomSelection.java Get");//削除
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/RoomSelection.jsp");
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RoomSelection.javaPost");//削除
		String botton = request.getParameter("botton");
		System.out.println("rs.java "+botton);
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		User loginUser = new User();
		loginUser = (User) session.getAttribute("loginUser");
		int userid = 0;
		userid = loginUser.getUserId();
		System.out.println("rs.java "+userid);
		int ID = 0;
		List<Room> roomList = new ArrayList<Room>();
		RoomDao rdao = new RoomDao();
		if("新規部屋作成".equals(botton)) {//新規部屋作成bottonが押される
			System.out.println("rs.java新規部屋作成");
			ID = Integer.parseInt(request.getParameter("roomID"));
			String roomName = request.getParameter("roomname");
			String roomPasswd = request.getParameter("roompassword");
			Room room = new Room(ID,roomName,roomPasswd); //のちに出てくるroomとは別物
			roomList = rdao.findFromID(ID);
			if(roomList.size()!= 1) {//同一のroomIDがないか検索 結果＝なし
				System.out.println("rs.java create");//削除
				rdao.createRoom(room,userid);//ROOMtableに保存
				//roomList= new ArrayList<Room>();
				//roomList.add(room);
				session.setAttribute("room", room);
				dispatcher = request.getRequestDispatcher("/RoomChoice.jsp");
				dispatcher.forward(request,response);//RoomChoice.jsp遷移
			}else {//検索結果＝あり
				System.out.println("rs.java not create");//削除
				dispatcher = request.getRequestDispatcher("/RoomSelectionCreateError.jsp");
				dispatcher.forward(request,response);//RoomSelectionCreateError.jsp遷移
			}
		}else if ("検索".equals(botton)){//検索bottonが押される
			System.out.println("rs.java 検索");//削除
			ID = Integer.parseInt(request.getParameter("roomSearchID"));
			System.out.println(ID);
			roomList = rdao.findFromID(ID);//ROOMtableにIDを送りIDに合致したデータをもらう
			if(roomList.size()!= 1) {//検索結果＝なし
				System.out.println("rs.java 検索結果＝なし");//削除
				dispatcher = request.getRequestDispatcher("/RoomSelectionError.jsp");
				dispatcher.forward(request,response);//RoomSelectionError.jsp遷移
			}else {//検索結果＝あり
				session.setAttribute("roomList", roomList);//セッションにroomListを保存
				for(Room room : roomList) {
					if(ID == room.getId()) {//sessionに行く部屋の情報を追加
						System.out.println("rs.java yes se");//削除
						session.setAttribute("room", room);
					}else {
						System.out.println("rs.java not se");//削除
					dispatcher = request.getRequestDispatcher("/RoomSelectionError.jsp");
					dispatcher.forward(request,response);//RoomSelectionError.jsp遷移
					}
					
				}
				System.out.println(roomList);//削除
				System.out.println("rs.java 検索結果＝あり");//削除
				
				dispatcher = request.getRequestDispatcher("/RoomChoice.jsp");
				dispatcher.forward(request,response);//RoomChoice.jsp遷移
			}
			/*
			if(ID == 1) {
				dispatcher = request.getRequestDispatcher("/roomChoice.html");
				dispatcher.forward(request,response);
			}else {
				dispatcher = request.getRequestDispatcher("/roomSelectionError.html");
				dispatcher.forward(request,response);
			}
			*/
		}else {
			System.out.println("rs.java エラー");
			dispatcher = request.getRequestDispatcher("/RoomSelection.jsp");
			dispatcher.forward(request,response);
		}
	}

}
