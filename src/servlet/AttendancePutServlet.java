/***************************
 * 作成者　: dyf
 * 作成日　: 2016/11/19
 * 内容　　: 経理部門が最低勤怠日数登録用
 * *************************/
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

import beans.CalendarClass;
import beans.DBManager;
import beans.ErrorCheck;

/**
 * Servlet implementation class AttendancePutServlet
 */
@WebServlet("/protect/AttendancePutServlet")
public class AttendancePutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBManager db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendancePutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け対策
		response.setContentType("application/json; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			// 登録された最低勤怠日数情報を取得
			ArrayList<ArrayList<String>> lowestWorkingDays = new ArrayList<ArrayList<String>>();

			db = new DBManager("JV34_team");
			String sql = "SELECT period, lowest_working_days FROM lowest_working_days_master";
			lowestWorkingDays = db.runSelect(sql);

			// 遷移
			String strJspName = "AttendancePut.jsp";

			request.setAttribute("lowestWorkingDays", lowestWorkingDays);

			RequestDispatcher rd = request.getRequestDispatcher(strJspName);
			rd.forward(request, response);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 文字化け対策
		response.setContentType("application/json; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 値を取得

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String days = request.getParameter("days");

		System.out.println(year);
		System.out.println(month);

		// 入力値をチェックする
		ErrorCheck errorCheck = new ErrorCheck();

		// 年のチェック半角数値かどうか
		String pattern = "[0-9]{4}";
		String mes = "";
		String _month = "";

		// 年は数値,月はnull,最低出勤日はnullではなかったら

		if (errorCheck.patternMacher(pattern, year) == true && errorCheck.getMonth(month) != null
				&& errorCheck.StrToInt(days) != null) {

			_month = errorCheck.getMonth(month);

			// カレンダークラスに入力された日付をセットする
			CalendarClass cleandar = new CalendarClass();
			cleandar._setYear(year);
			cleandar._setMonth(_month);
			cleandar._setDays(days);
			// 過去の年月ならエラメッセージに
			if (cleandar.pastPresentFuture() == 1) {
				mes += "正しい年月を入力してください<br>";
			} else if (cleandar.daysCheck() == false) {
				mes += "正しい最低勤怠日数を入力してください<br>";
			}

		} else {
			if (errorCheck.patternMacher(pattern, year) == false) {
				mes += "年を正しく入力してください<br>";
			}
			if (errorCheck.StrToInt(days) == null) {
				mes += "最低勤怠日数入力してください<br>";
			}
			if (errorCheck.getMonth(month) == null) {
				mes += "月を正しく入力してください<br>";
			}

		}
		// エラメッセージないなら
		if (mes.isEmpty()) {
			try {
				// JV34_teamというデータベースにアクセス
				db = new DBManager("JV34_team");

				boolean isExist = db.selectIsExist("select * from lowest_working_days_master where period = ?",
						Integer.parseInt(year + _month));

				if (isExist == false) {
					// 最低勤怠日数を登録
					String sql = "insert into lowest_working_days_master(period,lowest_working_days) values(?,?)";
					int flag = db.updateORInsert(sql, Integer.parseInt(year + _month), Integer.parseInt(days));

					if (flag == 1) {
						mes += "登録成功しました<br>";
					} else {
						mes += "登録失敗しました<br>";
					}

				} else {
					mes += year + "年" + month + "月はすでに最低勤務日数を登録しました";
				}

				db.closeDB();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// jspに
		String strJspName = "AttendancePut.jsp";
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("days", days);
		request.setAttribute("mes", mes);

		RequestDispatcher rd = request.getRequestDispatcher(strJspName);
		rd.forward(request, response);

	}

}
