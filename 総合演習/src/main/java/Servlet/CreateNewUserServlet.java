package Servlet;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

@WebServlet("/CreateNewUserServlet")
public class CreateNewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDAO = new UserDao();

	public CreateNewUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/createNewUser.html");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;

		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		int iconNum = 0;

		//新規ユーザーのユーザーIDを作成
		int userID = CreateID();

		//パスワードと確認用パスワードが同じなら登録へ
		if (password.equals(confirmPassword)) {

			User newUser = new User(userID, password, name, iconNum);
			userDAO.createUser(newUser);

			HttpSession session = request.getSession(true);
			session.setAttribute("loginUser", newUser);

			//登録が完了したら部屋選択画面へ遷移する。
			dispatcher = request.getRequestDispatcher("/RoomSelection");
			dispatcher.forward(request, response);

		} else {
			//違ったらユーザー登録画面へ
			response.sendRedirect("createNewUser.html");
		}
	}

	public int CreateID() {
		Random rand = new Random();

		//ランダムな6桁の数値を入力
		int newID;

		//作ったIDが既存のIDと被りがないかのチェック
		while (true) {
			newID = 100000 + rand.nextInt(900000);
			User user = userDAO.findById(newID);
			if (Objects.isNull(user)) {
				break;
			}
		}
		return newID;
	}

}
