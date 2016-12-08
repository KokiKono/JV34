package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeptRegistrationServlet
 */
@WebServlet("/DeptRegistrationServlet")
public class DeptRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		/**
		 * 値受け取り
		 */

//		String deptname = request.getParameter("deptname");//品名

		String deptname = new String(request.getParameter("deptname").getBytes("UTF-8"), "UTF-8");

		System.out.println("文字化け対象は"+deptname);

		/**
		 * DB接続開始
		 */
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jv34team","root", "");
			st = cn.createStatement();

			//実行するSQL文
			String strSQL = "INSERT INTO department_master(department_name) VALUES('"+deptname+"')";

			System.out.println(strSQL);

			//SQL実行
			st.executeUpdate(strSQL);

			//catch（仮）
			}catch (ClassNotFoundException e){
				out.println("ClassNotFoundException:"+e.getMessage());
				return;
			}catch(SQLException e){
				out.println("SQLException:" +e.getMessage());
				return;
			}catch (Exception e){
				out.println("Exception:"+e.getMessage());
				return;
			}finally{
				try{
					if(rs!=null){
						rs.close();
					}
					if(st!=null){
						st.close();
					}
					if(cn!=null){
						cn.close();
					}
					}catch(SQLException e){
						out.println("SQLEXception" +e.getMessage());
						return;
				}
				/**
				*DB接続終了
				*/
			}

		//問題が無ければ戻る
    	RequestDispatcher rd = request.getRequestDispatcher("dept_registration.jsp");
		rd.forward(request,response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
