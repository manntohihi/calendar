package Servlet;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;

@WebServlet("/CreateNewUserServlet")
public class CreateNewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateNewUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/createNewUser.html");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreateNewUserServlet CNUserv = new CreateNewUserServlet();

		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		//新規ユーザーのユーザーIDを作成
		int userID = CNUserv.CreateID();

		//パスワードと確認用パスワードが同じなら登録へ
		if (password.equals(confirmPassword)) {
		} else {
			//違ったらユーザー登録画面へ
			response.sendRedirect("createNewUser.html");
		}
	}

	public int CreateID() {
		Random rand = new Random();

		//ランダムな6桁の数値を入力
		return rand.nextInt(100000, 999999);
	}

}
