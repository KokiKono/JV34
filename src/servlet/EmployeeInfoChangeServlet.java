package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;

/**
 * Servlet implementation class EmployeeInfoChangeServlet
 */
@WebServlet("/protect/EmployeeInfoChangeServlet")
public class EmployeeInfoChangeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeInfoChangeServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 文字化け対策
		response.setContentType("application/json; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");

		String changeEmployeeID = request.getParameter("changeEmployeeID");

		String employeeMaster = "";
		String employeeNameKana = "";
		String employeeName = "";
		String birthday = "";
		String sex = "";
		String postalCode = "";
		String address = "";
		String tel = "";
		String departmentID = "";
		String subDepartmentID = "";
		String officialPositionID = "";
		String joinedMonth = "";
		String leavingMonth = "";

		HashMap<String, String> map = new HashMap();
		
		try
		{
			DBManager db = new DBManager("JV34_team");

			String sql = "SELECT employee_master,employee_name_kana,employee_name,birthday,sex,postal_code,address,tell,department_id,sub_department_id,official_position_id,joined_month,leaving_month FROM employee_master where employee_master = ?";

		//	ArrayList<ArrayList> list = new ArrayList<ArrayList<String>>();
//			list = db.select(sql, changeEmployeeID);
//			for (int i = 0; i < list.size(); i++)
//			{
//				map.put("employeeMaster", list.get(0).toString());
//				map.put("employeeNameKana", list.get(1).toString());
//				map.put("employeeName", list.get(2).toString());
//				map.put("sex", "");
//				map.put("postalCode", "");
//				map.put("address", "");
//				map.put("tel", "");
//				map.put("departmentID", "");
//				map.put("subDepartmentID", "");
//				map.put("officialPositionID", "");
//				map.put("joinedMonth", "");
//				map.put("leavingMonth", "");

			}
			
			System.out.println(map.get(employeeName));
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("changeEmployeeID ->" + changeEmployeeID);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 文字化け対策
		response.setContentType("application/json; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");


	}

}
