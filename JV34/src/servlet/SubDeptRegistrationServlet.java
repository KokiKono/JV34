/***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/12/06
 * 内容　　:課登録に関するServlet。
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
 * Servlet implementation class SubDeptRegistrationServlet
 */
@WebServlet("/SubDeptRegistrationServlet")
public class SubDeptRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubDeptRegistrationServlet() {
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
		String subdeptname = request.getParameter("subdeptname");//課名
		String deptid = request.getParameter("deptid");//登録する課の部署ID
		String err = "";//エラーメッセージ格納

		//部の空白＆nullチェック。
		boolean subdeptcheck = DeptRegist.checkVal(subdeptname);
		if (subdeptcheck){
			err = "課名が入力されていません。";
			request.setAttribute("err",err);
	    	RequestDispatcher rd = request.getRequestDispatcher("dept_registration.jsp");
			rd.forward(request,response);
			return;
		}

		//重複チェック
		try {
			boolean overlap = DeptRegist.SelectSubDept(subdeptname,deptid);
			if (!overlap){
				err = "その課は既に登録されています";
				request.setAttribute("err",err);
		    	RequestDispatcher rd = request.getRequestDispatcher("dept_registration.jsp");
				rd.forward(request,response);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//記号チェック
		DeptRegist.replaceSQL(subdeptname);

		//エラーに引っかからなければINSERTする。
		try {
			DeptRegist.InsertSubDept(subdeptname,deptid);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//エラーメッセージを格納してフォワード
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
