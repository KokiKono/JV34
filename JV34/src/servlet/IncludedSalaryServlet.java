/***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/12/04
 * 内容　　:付加給与変更に関するサーブレット。
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
 * Servlet implementation class IncludedSalaryServlet
 */
@WebServlet("/IncludedSalaryServlet")
public class IncludedSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncludedSalaryServlet() {
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
		String err = "";//エラーメッセージ
		String id = request.getParameter("id"); //付加給与ID
		String from = request.getParameter("from"); //勤務年数from
		String to = request.getParameter("to"); //勤務年数to
		String salary = request.getParameter("salary"); //付加給与額


		//空白＆nullチェック。
		boolean check = IncludedSalary.checkVal(from, to, salary);
		if (check){
			err = "いずれかの欄が入力されていません。";
			request.setAttribute("err", err);
			request.setAttribute("salaryid", id);
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
				request.setAttribute("salaryid", id);
				RequestDispatcher rd = request.getRequestDispatcher("included_salary_regist.jsp");
				rd.forward(request,response);
				return;
			}
		}catch(Exception e){
			err = "半角数字以外が含まれています。";
			request.setAttribute("err", err);
			request.setAttribute("salaryid", id);
			RequestDispatcher rd = request.getRequestDispatcher("included_salary_regist.jsp");
			rd.forward(request,response);
			return;
		}

		System.out.println(from + to + salary +"IDは"+ id);
		//エラーに引っかからなければ既存付加給与をUPDATE
		try{
			IncludedSalary.UpdateAdvantageSalary(from, to, salary, id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//リスト画面にフォワード
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
