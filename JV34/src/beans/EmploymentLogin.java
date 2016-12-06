/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/21
 * 内容　　:
 * *************************/
package beans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager.PreparedStatementByKoki;

import common.DataBase;

public class EmploymentLogin extends BaseLogin implements DataBase{


	HttpServlet servlet;

	/**
	 * 従業員ID
	 * @auther 浩生
	 * 2016/11/24
	 * @param emoloymentId String
	 */
	private String emoloymentId;
	/**
	 * パスワード
	 * @auther 浩生
	 * 2016/11/24
	 * @param password String
	 */
	private String password;

	/**
	 * 従業員の部署ID
	 * @auther 浩生
	 * 2016/11/28
	 * @param departmentId String
	 */
	private String departmentId;

	{
		this.emoloymentId=new String();
		this.password=new String();
	}
	public EmploymentLogin(HttpServlet servlet,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			LoginSessionException, LoginParamException {
		super(request, response);
		this.servlet=servlet;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * 従業員がログインするメソッド
	 * @auther 浩生
	 * 2016/11/24
	 * @param employmentID
	 * @param password
	 */
	public void userLogin(String employmentID,String password){
		this.emoloymentId=employmentID;
		this.password=password;
	}


	@Override
	boolean charenge() {
		// TODO 自動生成されたメソッド・スタブ
		DBManager dbManager=null;
		try{
			dbManager=new DBManager(DBName);
			PreparedStatementByKoki loginSql=dbManager.getStatementByKoki(InspectionValue.readSql(this.servlet,"SelectLogin.sql"));
			loginSql.setString("ID", this.emoloymentId);
			loginSql.setString("PASS", this.password);
			for(ArrayList<String> row:loginSql.select()){
				//部署IDの取得
				this.departmentId=row.get(0);
				dbManager.closeDB();
				//従業員情報を格納
				EmploymentUserParam userParam=new EmploymentUserParam();
				userParam.employmentId=this.emoloymentId;
				setUserParam(userParam);
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				dbManager.closeDB();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
				dbManager=null;
			}
		}
		return false;
	}

	@Override
	AccessId getUserAccessID() {
		// TODO 自動生成されたメソッド・スタブ
		switch(this.departmentId){
			case "0":
				return AccessId.Master;
			case "1":
				return AccessId.KEIRI;
			case "2":
				return AccessId.JINJI;
			default:
				return AccessId.Gest;
		}
	}

	/**
	 * 従業員情報をセッションに格納する箱クラス。
	 * @author 浩生
	 *
	 */
	public class EmploymentUserParam extends UserParam{
		public String employmentId;
	}



}
