package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

@WebServlet("/Mypage")
public class MypageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MypageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    	
    	//JSPからユーザーのIDと名前を取得
    	int id = Integer.parseInt(request.getParameter("userid"));
    	String name = request.getParameter("username");
    	
    	//セッションの取得
        HttpSession session = request.getSession();
        
        //DAOからユーザー情報を取得
        User user = (User) session.getAttribute("loginuser");
        UserDao dao = new UserDao();
        
        dao.findIdAndPass(id, name);
        
        // 未ログインならログイン画面へ
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // JSPに渡す
        request.setAttribute("user", user);
        
        //現在の画面の情報をJSPに渡す
        request.setAttribute("currentPage","Mypage");
        
        //フォワード
        request.getRequestDispatcher("mypage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}