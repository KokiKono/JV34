/***************************
 * 学籍番号:40313
 * 作成者　:K.koki
 * 作成日　:2016/12/07
 * 内容　　:各部長が社員ごとの勤怠日登録をするサーブレット。
 * *************************/
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;
import common.DataBase;

/**
 * Servlet implementation class EachManagerInsertServlet
 */
@WebServlet("/protect/EachManagerInsertServlet")
public class EachManagerInsertServlet extends HttpServlet implements DataBase{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EachManagerInsertServlet() {
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
						String sql="INSERT INTO attendance_table VALUES('"+attends[count]+"','"+empNo+"',"+attendFlg[count]+");";
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

				if(msg.isEmpty()==true)msg="登録しました。";

				request.setAttribute("msg", msg);

				rd.forward(request, response);

				return;
	}

}
