package Servlet;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDao;
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
		//		List<User> userList = new ArrayList<User>();
		User loginUser = new User();
		loginUser = ud.login(user);
		RequestDispatcher dispatcher;
		String name = null;

		System.out.println(loginUser + "size");

		/*
		System.out.println(userList.size()+"size");//削除
		>>>>>>> branch 'master' of https://github.com/manntohihi/calendar.git
		for(User use : userList) {
			System.out.println("f2");//削除
			name = use.getUserName();
			if (name.equals(null)){
				dispatcher = request.getRequestDispatcher("/LoginError.jsp");
				dispatcher.forward(request,response);//LoginError.jsp遷移
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser",userList);
				Room_members rm = new Room_members();//グループ作成	
				
				
				dispatcher = request.getRequestDispatcher("/RoomSelection");
				dispatcher.forward(request,response);//Servlet.RoomSelection.java遷移
			}
			System.out.println("end");//削除
		}
		*/

		name = loginUser.getUserName();
		System.out.println("f2");
		if (Objects.isNull(name)) {
			dispatcher = request.getRequestDispatcher("/LoginError.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			dispatcher = request.getRequestDispatcher("/RoomSelection");
			dispatcher.forward(request, response);
		}
		System.out.println("end");

		dispatcher = request.getRequestDispatcher("/LoginError.jsp");

		System.out.println("eroor");//削除
		dispatcher.forward(request, response);//LoginError.jsp遷移

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
