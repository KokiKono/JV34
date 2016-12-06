/***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/11/24
 * 内容　　:部門登録に関するServlet。
 * *************************/

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DeptRegist;
import beans.InspectionValue;

/**
 * Servlet implementation class DeptRegistrationServlet
 */
@WebServlet("/protect/DeptRegistrationServlet")
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

		String deptname = request.getParameter("deptname");//品名

		String err = "";
		int flg = 0;

		//空白＆nullチェック。
		deptname = InspectionValue.cheackNull(deptname);
		if ( deptname.equals("") || deptname.equals(null) ){
			err = "部署名が入力されていません。";
			flg = 1;
		} else {
			err = "登録完了しました";
		}

		//重複チェック
		if ( deptname.equals("すでにあるもの")){
			err = "該当する部はすでに存在しています。";
			flg = 1;
		}

		//記号チェック
		InspectionValue.inspectionPattern(deptname, "[<>'']");


		if (deptname.indexOf("<") != -1) {
		  System.out.println( "部分一致です" );
		} else {
		  System.out.println( "部分一致ではありません" );
		}

//		if (deptname.startsWith("")) {
//		    System.out.println("Match!");
//		} else {
//		    System.out.println("Unmatch!");
//		}


		if ( !deptname.equals("") && flg == 0 ){
		request.setAttribute("err",err);
		DeptRegist.InsertDept(deptname);
		}

		//結果文を格納してフォワード
		request.setAttribute("err",err);
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
