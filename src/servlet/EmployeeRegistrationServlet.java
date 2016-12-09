/***************************
 * 作成者　: dyf
 * 作成日　: 2016/11/20
 * 内容　　: 社員登録する
 * *************************/

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CalendarClass;
import beans.DBManager;
import beans.ErrorCheck;

/**
 * Servlet implementation class EmployeeRegistrationServlet
 */
@WebServlet("/protect/EmployeeRegistrationServlet")
public class EmployeeRegistrationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	CalendarClass cal = new CalendarClass();
	DBManager db;
	
	//生年月日
	private String minBirthday;
	private String maxBirthday;
	
	//入社
	private String joinMaxYear;
	private String joinMinYear;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeRegistrationServlet()
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
		try
		{
			// 部署
			ArrayList<ArrayList<String>> departmentAndSub = new ArrayList<ArrayList<String>>();

			// 役職
			ArrayList<ArrayList<String>> officialPosition = new ArrayList<ArrayList<String>>();

			// 資格情報
			ArrayList<ArrayList<String>> licence = new ArrayList<ArrayList<String>>();

			// データベース接続
			db = new DBManager("JV34_team");

			// 部署
		//	String departmentSql = "SELECT department_id,department_name FROM department_master";
			//department = db.runSelect(departmentSql);
			//session.setAttribute("department", department);
			String sql = "SELECT dm.department_id,dm.department_name,sdm.sub_department_id,sub_department_name "
					+ "FROM department_master dm "
					+ "inner join sub_department_master sdm on dm.department_id = sdm.department_id";
			
		
			departmentAndSub = db.runSelect(sql);
			session.setAttribute("departmentAndSub", departmentAndSub);

			// 役職
			String officialPositionSql = "SELECT official_position_id,official_position_name FROM official_position_master";
			officialPosition = db.runSelect(officialPositionSql);
			session.setAttribute("officialPosition", officialPosition);

			// 資格情報
			String licenceSql = "SELECT licence_id,licence_name FROM licence_master";
			licence = db.runSelect(licenceSql);
			session.setAttribute("licence", licence);

			//生年月日入力可能範囲
			minBirthday = cal.dateFormat("yyyy-mm-dd", cal.range(String.valueOf(cal.retirementAge())));
			maxBirthday = cal.dateFormat("yyyy-mm-dd",cal.range(String.valueOf(cal.joinedAge())));

			//入社入力可能範囲
			joinMaxYear = cal.dateFormat("yyyy-mm-dd",cal.range(cal.getYear()));
			joinMinYear = cal.dateFormat("yyyy-mm-dd",cal.range(String.valueOf(Integer.parseInt(cal.getYear()) - 1)));
			
			System.out.println("joinMaxYeaar" + joinMaxYear);
			System.out.println("joinMinYeaar" + joinMinYear);
			
			// 遷移
			String strJspName = "EmployeeRegistration.jsp";
			request.setAttribute("minBirthday", minBirthday);
			request.setAttribute("maxBirthday", maxBirthday);
			request.setAttribute("joinMaxYear", joinMaxYear);
			request.setAttribute("joinMinYear", joinMinYear);
			RequestDispatcher rd = request.getRequestDispatcher(strJspName);
			rd.forward(request, response);

		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		// 入力値チェック
		ErrorCheck check = new ErrorCheck();

		// メッセージを格納する
		HashMap<String, String> mesMap = new HashMap<String, String>();

		/**
		 * 値取得
		 */
		// 社員番号すでに登録されたかどうかチェック
		String employeeIsExist = "SELECT employee_master from employee_master where employee_master = ?";

		// パスワード
		String password = request.getParameter("password");

		// かな名
		String kanaName = request.getParameter("kanaName");

		// 社員名
		String name = request.getParameter("name");

		// 生年月日
		String birthdayDate = request.getParameter("birthday");
		
		// 性別
		String sex = request.getParameter("sex");

		// 郵便番号
		String postalCode1 = request.getParameter("postalCode1");
		String postalCode2 = request.getParameter("postalCode2");

		// 入力チェック
		// 入力ミスなかったら
		String postalCode = postalCode1 + postalCode2;
		// 住所
		String address = request.getParameter("address");

		// 入社日(現在から)
		String joined = request.getParameter("joined");
		

		// 入力チェック
		// ミスなかった場合
		
		
		// 入力しなくでもいいもの
		// 電話番号
		String tel = request.getParameter("tel");
		mesMap.put("password", check.patternMacherMes("[a-zA-Z0-9]{6,8}", password, "パスワードは半角英数6桁から8桁まで"));
		mesMap.put("postalCode1Mes", check.patternMacherMes("[0-9]{3}", postalCode1, "半角数字3桁を入力してください"));
		mesMap.put("postalCode2Mes", check.patternMacherMes("[0-9]{4}", postalCode2, "半角数字4桁を入力してください"));
		mesMap.put("tel", check.patternMacherMes("[0-9]{11}", tel, "半角数字11桁を入力してください"));

		// 社員番号
		String employeeMaster = request.getParameter("employeeMaster");

		// if (mesMap.)
		{
			try
			{
				db = new DBManager("JV34_team");
				// 社員番号使われていない場合は
				if (db.selectIsExist(employeeIsExist, employeeMaster) == false)
				{

					// 課
					// String subDepartmentID =
					// request.getParameter("subDepartmentID"); //
					// 登録しなくでもいい

					// 入力項目ごとにメッセージを格納するList

					// 入力チェックしなくでもいいもの
					// 部署,課
					String departmentAndSub = request.getParameter("departmentAndSub");
					
					//部署
					String departmentID = "";
					
					//課
					String subDepartmentID = "";
					
					//取得してきた部署、課をそれぞれ分割
					String[] department = departmentAndSub.split(",",0);
					
					for (int i = 0; i < department.length; i++)
					{
						departmentID = department[0];
						subDepartmentID = department[1];
					}
					System.out.println("部署" + departmentID);
					System.out.println("課名" + subDepartmentID);
					// 役職
					String officialPosition = request.getParameter("officialPosition");

					// 資格情報登録最大三件
					// for文で取り出し
					// 入力チェック nullまたは空ならinsertしない
					// nullまたは空ではない場合はinsert
					String[] licence = request.getParameterValues("licence");
					for (int i = 0; i < licence.length; i++)
					{
						if (!licence[i].isEmpty())
						{
							String employeeInsertSql = "INSERT INTO have_licence_table (employee_master, licence_id, get_licence_date) VALUES (?,?,?)";
							db.updateORInsert(employeeInsertSql, employeeMaster, licence[i], "2016-07-09");
						}
						// String sql = "insert into "
						// db.updateORInsert()
					}

					String sql = "INSERT INTO employee_master (" + "employee_master, " + "password, "
							+ "employee_name_kana, " + "employee_name, " + "birthday, " + "sex, " + "postal_code, "
							+ "address, " + "tell, " + "department_id, " + "sub_department_id," + "official_position_id, " + "joined_month) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
					db.updateORInsert(sql, employeeMaster, password, kanaName, name, birthdayDate, sex, postalCode,
							address, tel, departmentID, subDepartmentID, officialPosition, joined);

				} else
				{
					mesMap.put("employeeMasterMes", "社員IDは無効です");
					// mesMap.put("employeeMasterMes",
					// check.patternMacherMes("[0-9]{7}", employeeMaster,
					// "半角数字7桁を入力してください"));
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String strJspName = "EmployeeRegistration.jsp";
		request.setAttribute("mesMap", mesMap);
		RequestDispatcher rd = request.getRequestDispatcher(strJspName);
		rd.forward(request, response);

	}

}
