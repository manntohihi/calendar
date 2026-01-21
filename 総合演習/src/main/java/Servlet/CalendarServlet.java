package Servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.CalendarDAO;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("SchedulEntry.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("サーブレットに移動");
	    String startDate = request.getParameter("startDate");
	    String endDate   = request.getParameter("endDate");
	    String staffName = request.getParameter("staffName");
	    String memo      = request.getParameter("memo");
	    
	    CalendarDAO cDao = new CalendarDAO();
	    cDao.setCalendarDate();
	    System.out.println("開始日: " + startDate);
	    System.out.println("終了日: " + endDate);
	    System.out.println("担当者: " + staffName);
	    System.out.println("メモ: " + memo);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("SchedulEntry.jsp");
		dispatcher.forward(request, response);

	}

}
