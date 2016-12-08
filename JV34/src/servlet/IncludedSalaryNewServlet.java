/***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/12/04
 * 内容　　:付加給与新規登録に関するサーブレット。
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

import beans.IncludedSalary;

/**
 * Servlet implementation class IncludedSalaryNewServlet
 */
@WebServlet("/IncludedSalaryNewServlet")
public class IncludedSalaryNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncludedSalaryNewServlet() {
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
		String from = request.getParameter("from"); //勤務年数from
		String to = request.getParameter("to"); //勤務年数to
		String salary = request.getParameter("salary"); //付加給与額

		//空白＆nullチェック。
		boolean check = IncludedSalary.checkVal(from, to, salary);
		if (check){
			err = "いずれかの欄が入力されていません。";
			request.setAttribute("err", err);
			RequestDispatcher rd = request.getRequestDispatcher("included_salary_regist.jsp");
			rd.forward(request,response);
			return;
		}

		//FROM TOの大小チェックと数値型チェック
		try{
			if(Integer.parseInt(from)>Integer.parseInt(to)){
				Integer.parseInt(salary);
				err="終了年以上の開始年が入力されています。";
				request.setAttribute("err", err);
				RequestDispatcher rd = request.getRequestDispatcher("included_salary_regist.jsp");
				rd.forward(request,response);
				return;
			}
		}catch(Exception e){
			err = "半角数字以外が含まれています。";
			request.setAttribute("err", err);
			RequestDispatcher rd = request.getRequestDispatcher("included_salary_regist.jsp");
			rd.forward(request,response);
			return;
		}

		//新規付加給与をINSERT
		try {
			IncludedSalary.InsertAdvantageSalary(from, to, salary);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//付加給与リストへフォワード
		RequestDispatcher rd = request.getRequestDispatcher("included_salary.jsp");
		rd.forward(request,response);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
