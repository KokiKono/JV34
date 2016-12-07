/***************************
 * 学籍番号:40313
 * 作成者　:K.koki
 * 作成日　:2016/12/05
 * 内容　　:各部長が勤怠日登録をする際の部内社員リストを取得する。
 * 			部署IDをリクエストに設定してください。
 * *************************/
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CalendarByKoki;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import common.DataBase;
import dao.DepartmentDao;
import dtd.Department;
import dtd.Employment;
import dtd.OfficailPosition;
import dtd.SubDepartment;

/**
 * Servlet implementation class EachManagerServlet
 */
@WebServlet("/protect/EachManagerServlet")
public class EachManagerServlet extends HttpServlet implements DataBase {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EachManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 社員のリストを取得する。
		// セッションから社員IDを取得
		// String empNo=(String)request.getSession().getAttribute("employeeId");
		String empNo = "0000002";
		if (empNo == null) {
			// セッションなし
			return;
		}
		// リクエストから部署IDを取得する。
		// String depNo=(String)request.getParameter("depNo");
		String depNo = "1";
		//部署名をリクエストに設定
		request.setAttribute("depName", DepartmentDao.findDeptName(Integer.parseInt(depNo)));

		DBManager dbManager = null;
		ArrayList<Employment> employments = new ArrayList<>();
		try {
			// データベースから社員リストを取得する。
			dbManager = new DBManager(DBName);
			PreparedStatementByKoki selectEmpLisy = dbManager
					.getStatementByKoki(InspectionValue.readSql(this, "SelectEmploymentList.sql"));
			selectEmpLisy.setInt("DEPARTMENT_ID", Integer.parseInt(depNo));
			// 表示用に整形し、リクエスト設定
			employments = convertSelectEmpList(selectEmpLisy.select());
			request.setAttribute("empList", employments);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("each_manager.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * SelectEmploymentList.sql結果を整形するメソッド。
	 *
	 * @auther 浩生 2016/12/05
	 * @param list
	 * @return
	 */
	private ArrayList<Employment> convertSelectEmpList(ArrayList<ArrayList<String>> list) {
		ArrayList<Employment> empList = new ArrayList<>();
		for (ArrayList<String> row : list) {
			Employment employment = new Employment();
			employment.employmentId = row.get(0);
			employment.kName = row.get(1);
			employment.name = row.get(2);
			employment.birthDay = new CalendarByKoki(row.get(3));
			employment.sex = Integer.parseInt(row.get(4));
			employment.postalCode = row.get(5);
			employment.address = row.get(6);
			employment.tel = row.get(7);
			Department department = new Department();
			department.departmentId = Integer.parseInt(row.get(8));
			department.name = row.get(9);
			employment.department = department;
			SubDepartment subDepartment = new SubDepartment();
			subDepartment.department = department;
			if(row.get(11)!=null)subDepartment.subDepartmentId = Integer.parseInt(row.get(10));
			subDepartment.name = row.get(11);
			OfficailPosition officailPosition = new OfficailPosition();
			officailPosition.id = Integer.parseInt(row.get(12));
			officailPosition.name = row.get(13);
			officailPosition.allowance = Integer.parseInt(row.get(14));
			officailPosition.rank = Integer.parseInt(row.get(15));
			employment.officailPosition = officailPosition;
			employment.joinedDate = new CalendarByKoki(row.get(16));
			if (row.get(17) != null) {
				employment.leavingDate = new CalendarByKoki(row.get(17));
			}
			empList.add(employment);
		}
		return empList;
	}

}
