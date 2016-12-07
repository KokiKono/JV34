/***************************
 * 学籍番号:40313
 * 作成者　:K.koki
 * 作成日　:2016/12/05
 * 内容　　:各社員の勤怠情報を変更するサーブレット。
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

import beans.CalendarByKoki;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import common.DataBase;
import dtd.EachManagerDetail;

/**
 * Servlet implementation class EachManagerDetailServlet
 */
@WebServlet("/protect/EachManagerDetailServlet")
public class EachManagerDetailServlet extends HttpServlet implements DataBase {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EachManagerDetailServlet() {
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
		// 部署IDと選択した社員IDを取得
		String depNo = (String) request.getParameter("depNo");
		String empNo = (String) request.getParameter("empNo");


		//当月の登録済み勤怠情報のみ取得
		CalendarByKoki nowDate=new CalendarByKoki();
		DBManager dbManager = null;
		try {
			dbManager = new DBManager(DBName);
			PreparedStatementByKoki selectAttend = dbManager
					.getStatementByKoki(InspectionValue.readSql(this, "SelectAttendFromEmp.sql"));

			selectAttend.setString("EMP_ID", empNo);
			selectAttend.setInt("DEPT_ID", Integer.parseInt(depNo));
			selectAttend.setString("DATE", nowDate.getYear()+"-"+nowDate.getMonth()+"%");
			//画面に渡す情報を整形する処理。
			EachManagerDetail detail=new EachManagerDetail();
			detail.empNo=empNo;

			for(ArrayList<String> row:selectAttend.select()){
				detail.addDays(row.get(1), Integer.parseInt(row.get(2)));
				detail.ownceSetEmpName(row.get(3));
			}

			//リクエスト設定
			request.setAttribute("detail", detail);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "異常終了しました。");
			//遷移先を定義。

			return;
		}

		RequestDispatcher rd=request.getRequestDispatcher("each_manager_detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//社員IDと勤怠日、勤怠フラグを取得する。
		String empNo=(String)request.getParameter("empNo");
		String[] attends=(String[])request.getParameterValues("attend");
		String[] attendFlg=(String[])request.getParameterValues("attendFlg");

		//メッセージ
		String msg=new String();

		//遷移先
		RequestDispatcher rd=request.getRequestDispatcher("each_manager_detail.jsp");

		//勤怠日と勤怠フラグの数が一致しない時、処理を終了する。
		if((attends.length==attendFlg.length)==false){
			msg="更新できませんでした。";
			request.setAttribute("msg", msg);
			rd.forward(request, response);
			return;
		}

		DBManager dbManager=null;
		try{
			dbManager=new DBManager(DBName);
			dbManager.setautoCommit(false);
			for(int count=0;count<attends.length;count++){
				String sql="UPDATE attendance_table SET attendance_flg = "+attendFlg[count]+" WHERE date='"+attends[count]+"';";
				dbManager.update(sql);
			}
			dbManager.runCommit();
			dbManager.setautoCommit(true);
		}catch(Exception e){
			e.printStackTrace();
			msg="処理が正常に終了しませんでした。";
		}finally{
			try {
				dbManager.closeDB();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				msg="処理が正常に終了しませんでした。";
			}
		}

		if(msg.isEmpty()==true)msg="更新しました。";

		request.setAttribute("msg", msg);

		rd.forward(request, response);

		return;




	}

}
