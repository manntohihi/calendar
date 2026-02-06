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

/**
 * Servlet implementation class NameChangeServlet
 */
@WebServlet("/NameChangeServlet")
public class NameChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NameChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
		
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 入力値取得
        String currentName = request.getParameter("currentName");
        String newName = request.getParameter("newName");
        String newNameConfirm = request.getParameter("newNameConfirm");

        // 入力チェック
        if (currentName == null || currentName.trim().isEmpty()) {
            request.setAttribute("error", "現在の名前を入力してください");
            forward(request, response);
            return;
        }

        if (newName == null || newName.trim().isEmpty()) {
            request.setAttribute("error", "新しい名前を入力してください");
            forward(request, response);
            return;
        }

        if (!newName.equals(newNameConfirm)) {
            request.setAttribute("error", "確認用の名前が一致しません");
            forward(request, response);
            return;
        }

        // ログイン中ユーザーID取得（例）
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loginUser");

        if (userId == null) {
            request.setAttribute("error", "ログイン情報がありません");
            forward(request, response);
            return;
        }
        
         UserDao dao = new UserDao();
         String dbCurrentName = dao.getUsernameById(userId);
         if (!currentName.equals(dbCurrentName)) {
             request.setAttribute("error", "現在の名前が正しくありません");
             forward(request, response);
             return;
         }
         boolean result = dao.updateUsername(userId, newName);
         if (!result) {
             request.setAttribute("error", "名前の更新に失敗しました");
             forward(request, response);
             return;
         }

        // とりあえず成功扱いで遷移
        request.setAttribute("newName", newName);
        RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
        rd.forward(request, response);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("nameChange.jsp");
        rd.forward(request, response);
    }
}
