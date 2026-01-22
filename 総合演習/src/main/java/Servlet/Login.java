package Servlet;

import java.io.IOException;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/Login.html");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login.java");
		int ID = Integer.parseInt( request.getParameter("ID"));
		String password = request.getParameter("password");
		User user = new User();
		user.setUserId(ID);
		user.setPasswd(password);
		boolean loginJud = false;
		
		UserDao ud = new UserDao();
		//ud.login(user);
		
		RequestDispatcher dispatcher;
		if (loginJud==true){
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);
			dispatcher = request.getRequestDispatcher("/roomSelection.html");
			dispatcher.forward(request,response);
		}else {
			dispatcher = request.getRequestDispatcher("/LoginError.html");
			dispatcher.forward(request,response);
		}
		//テスト
		/*
		if(ID==111111) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);
			dispatcher = request.getRequestDispatcher("/roomSelection.html");
			dispatcher.forward(request,response);
		}else {
			dispatcher = request.getRequestDispatcher("/LoginError.html");
			dispatcher.forward(request,response);
		}
		*/
		
	}

}
