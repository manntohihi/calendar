package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PostPrductLogic;
import model.Product;
import model.User;

public class CalendarDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/ECommerceDB";	
	private final String JDBC_USER = "root";
	private final String JDBC_PASS = "kcsf";


	public User findUser(String userName){
		
		User user = null;
		
		long start;
		long end;
		start = System.currentTimeMillis();
		
		
		// JBDCドライバの読み込みorエラー表示
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch(ClassNotFoundException e) {
			 throw new IllegalStateException("JDBSドライバを読み込めませんでした");
		 }
		//データベース接続、connに詰め替え
		try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS)){
		
		//SELCT文を準備
		String sql = "select * from user WHERE name = '"+ userName +"';";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果表に格納された内容を
		//Employeeインスタンスに設定し、ArrayListインスタンスに追加
		while(rs.next()) {
			String userid = rs.getString("id");
			String passwd = rs.getString("password");
			String address = rs.getString("address");
			user = new User(userid,userName, passwd, address);
		}
		end = System.currentTimeMillis();

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		System.out.println(user);
		return user;
		
		
		
	}
	
	public Product findProduct(String productId){
		
		Product product = null;
		
		long start;
		long end;
		start = System.currentTimeMillis();
		
		
		// JBDCドライバの読み込みorエラー表示
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch(ClassNotFoundException e) {
			 throw new IllegalStateException("JDBSドライバを読み込めませんでした");
		 }
		//データベース接続、connに詰め替え
		try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS)){
		
		//SELCT文を準備
		String sql = "select * from  product WHERE id = '"+ productId +"';";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		//結果表に格納された内容を
		//Employeeインスタンスに設定し、ArrayListインスタンスに追加
		while(rs.next()) {
			String Id = rs.getString("Id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			String imagePath = rs.getString("imagePath");
			product = new Product(Id, name, price, stock, imagePath);
		}

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return product;
		
		
		
	}


	public List<Product> findAll(){
		PostPrductLogic PostPrductLogic = new PostPrductLogic();
		List<Product> ProductList = new ArrayList();
		
		
		long start;
		long end;
		start = System.currentTimeMillis();
		
		
		// JBDCドライバの読み込みorエラー表示
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch(ClassNotFoundException e) {
			 throw new IllegalStateException("JDBSドライバを読み込めませんでした");
		 }
		//データベース接続、connに詰め替え
		try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS)){
		
		//SELCT文を準備
		String sql = "select * from product;";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();
		
		//結果表に格納された内容を
		//Employeeインスタンスに設定し、ArrayListインスタンスに追加
		while(rs.next()) {
			String Id = rs.getString("Id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			String imagePath = rs.getString("imagePath");
			Product employee = new Product(Id, name, price, stock, imagePath);
			PostPrductLogic.execute(employee, ProductList);
		}

		}catch(SQLException e){
			e.printStackTrace();

			return null;
		}
		
		return ProductList;
		
		
		
	}
	
	
	
}
