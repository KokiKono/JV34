/***************************
 * 作成者　:  dyf
 * 作成日　:  2016/11/29
 * 内容　　:  社員情報検索・一覧ページに遷移
		 　　部署、役職、取得情報をデータベースから取得
 		　　 JSPに渡し選択できるように
 * *************************/

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DBManager;
import beans.ErrorCheck;
import beans.SearchEmployeeClass;

/**
 * Servlet implementation class ShowEmployeeServlet
 */
@WebServlet("/protect/ShowEmployeeServlet")
public class ShowEmployeeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowEmployeeServlet()
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

		// 値保持
		HttpSession session = request.getSession();

		// 従業員一覧情報
		ArrayList<ArrayList<String>> employeeInfo = new ArrayList<ArrayList<String>>();

		// 部署
		ArrayList<ArrayList<String>> department = new ArrayList<ArrayList<String>>();

		// 役職
		ArrayList<ArrayList<String>> officialPosition = new ArrayList<ArrayList<String>>();

		// 資格ランク
		ArrayList<ArrayList<String>> rank = new ArrayList<ArrayList<String>>();

		
		try
		{
			DBManager db = new DBManager("JV34_team");

			// 従業員一覧
			String employeeSql = "SELECT employee_master,employee_name,employee_name_kana,sex,birthday,joined_month FROM employee_master";
			employeeInfo = db.runSelect(employeeSql);
			session.setAttribute("employeeInfo", employeeInfo);

			// 部署
			String departmentSql = "SELECT department_id,department_name FROM department_master";
			department = db.runSelect(departmentSql);
			session.setAttribute("department", department);

			// 役職
			String officialPositionSql = "SELECT official_position_id,official_position_name FROM official_position_master";
			officialPosition = db.runSelect(officialPositionSql);
			session.setAttribute("officialPosition", officialPosition);

			// 資格ランク
			String rankSql = "SELECT rank FROM licence_master group by rank";
			rank = db.runSelect(rankSql);
			session.setAttribute("rank", rank);

			db.closeDB();

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String strJspName = "ShowEmployee.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(strJspName);
		rd.forward(request, response);
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

		ErrorCheck check = new ErrorCheck();
		// 値取得
		String department = request.getParameter("department");
		String officialPosition = request.getParameter("officialPosition");
		String minYear = request.getParameter("minYear");
		String maxYear = request.getParameter("maxYear");
		String rank = request.getParameter("rank");
		SearchEmployeeClass search = new SearchEmployeeClass();
		if (!check.isNullOrEmpty(minYear) && !check.isNullOrEmpty(maxYear))
		{
			search.yearsOfService(minYear, maxYear);
		}
		
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("department_id", department);
		map.put("official_position_id", officialPosition);
		map.put("department_id", minYear);
		map.put("department_id", maxYear);
		map.put("rank", rank);
		ArrayList<String> searchCriteria = new ArrayList<String>();

		//searchCriteria = search.searchCriteria(department, officialPosition, minYear, maxYear, rank);
		System.out.println(searchCriteria.toString());

		// ランクについては？値大きいほどランクが大き？
		
		StringBuilder sql = new StringBuilder();
		sql.append("select employee_master,employee_name,employee_name_kana,sex,birthday,joined_month,TIMESTAMPDIFF(YEAR,joined_month,leaving_month) from employee_master");
		
		//勤務年数計算　String sql = "SELECT TIMESTAMPDIFF(YEAR,joined_month,leaving_month) from employee_master";
		int j = 0;
		for (int i = 0; i < searchCriteria.size(); i++)
		{
			if (!check.isNullOrEmpty(searchCriteria.get(i)))
			{
				if (j == 0)
				{
					sql.append(" where ");
					j = 1;
				}
				else if (j == 1)
				{
					sql.append()
				}
				sql.append("")
				System.out.println(searchCriteria.toString());
			}
		}
		
		System.out.println("sql- >"+sql.toString());
		// 空チェック

		// 入力されたものを取り出し
		// ANDを追加し
		// sql文を発行
		// データベースの結果をListに
		// Jspに飛ばし
	}

}
