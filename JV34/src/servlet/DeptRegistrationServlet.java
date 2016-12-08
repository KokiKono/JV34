/***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/11/24
 * 内容　　:部門登録に関するServlet。
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

import beans.DeptRegist;

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

		//入力した部署名だけ受け取り
		String err = "";//エラーメッセージ格納
		String deptname = request.getParameter("deptname");//部署名

		//部の空白＆nullチェック。
		boolean deptcheck = DeptRegist.checkVal(deptname);
		if (deptcheck){
			err = "部署名が入力されていません。";
			request.setAttribute("err",err);
	    	RequestDispatcher rd = request.getRequestDispatcher("dept_registration.jsp");
			rd.forward(request,response);
			return;
		}

		//重複チェック
		try {
			boolean overlap = DeptRegist.SelectDept(deptname);
			if (!overlap){
				err = "その部は既に登録されています";
				request.setAttribute("err",err);
		    	RequestDispatcher rd = request.getRequestDispatcher("dept_registration.jsp");
				rd.forward(request,response);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//記号チェック
		DeptRegist.replaceSQL(deptname);

		//部課情報をINSERT
		try {
			DeptRegist.InsertDept(deptname);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//部課リストページへフォワード
		err = "登録完了しました";
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
