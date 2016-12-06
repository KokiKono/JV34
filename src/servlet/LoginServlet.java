package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import common.DataBase;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet implements DataBase{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=(String)request.getParameter("id");
		String pass=(String)request.getParameter("pass");

		if(isEmptyFromDB(id, pass)==true){
			//ログイン成功
			request.getSession().setAttribute("employeeId", id);
		}else{
			//ログイン失敗
	    	RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request,response);
		}

	}
	private boolean isEmptyFromDB(String id,String pass){
		DBManager dbManager=null;
		try{
			dbManager=new DBManager(DBName);
			PreparedStatementByKoki loginSql=dbManager.getStatementByKoki(InspectionValue.readSql(this,"SelectLogin.sql"));
			loginSql.setString("ID", id);
			loginSql.setString("PASS", pass);
			for(ArrayList<String> row:loginSql.select()){
				dbManager.closeDB();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				dbManager.closeDB();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
				dbManager=null;
			}
		}
		return false;
	}

}
