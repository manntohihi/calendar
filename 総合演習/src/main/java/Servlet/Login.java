package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.Room_membersDAO;
import dao.UserDao;
import model.Room_members;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Login.java");
		int ID = Integer.parseInt(request.getParameter("ID"));
		String password = request.getParameter("password");
		User user = new User();
		user.setUserId(ID);
		user.setPasswd(password);
		UserDao ud = new UserDao();
		User loginUser = new User();
		RequestDispatcher dispatcher;
		String name = null;

		System.out.println("Login.java f2");
		loginUser = ud.login(user);
		name = loginUser.getUserName();
		if (Objects.isNull(name)) {
			dispatcher = request.getRequestDispatcher("/LoginError.jsp");// /jsp/LoginError.jsp
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			Room_membersDAO rmdao = new Room_membersDAO();//Room_memberに登録 String id,int roomid,int userid
			List<Room_members> roomids = rmdao.searchByUseridForGroup(ID);
			ServletContext application = this.getServletContext();
			application.setAttribute("roomids", roomids);//アプリケーションスコープroomids
			dispatcher = request.getRequestDispatcher("/RoomSelection.jsp");// /jsp/RoomSelection.jsp
			dispatcher.forward(request, response);
		}
		System.out.println("login.java end");
		/*
		dispatcher = request.getRequestDispatcher("/LoginError.jsp");
		System.out.println("login.java eroor");
		dispatcher.forward(request,response);
		*/
		//テスト
		/*
		if(ID==111111) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);
			dispatcher = request.getRequestDispatcher("/RoomSelection");
			dispatcher.forward(request,response);
		}else {
			dispatcher = request.getRequestDispatcher("/LoginError.html");
			dispatcher.forward(request,response);
		}
		*/

	}

}
