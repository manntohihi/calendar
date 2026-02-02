package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;

@WebServlet("/Mypage")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MypageServlet() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションの取得
		HttpSession session = request.getSession(false);
		
		//ログインユーザーを取得
		List<User> userList = new ArrayList<User>();
		userList = (List<User>)session.getAttribute("loginUser");
				
		// 未ログインならログイン画面へ
		if (session == null || session.getAttribute("loginUser") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		// JSPに渡す
		request.setAttribute("userList", userList);
		//現在の画面の情報をJSPに渡す
		request.setAttribute("currentPage","Mypage");

		//フォワード
		request.getRequestDispatcher("myPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}