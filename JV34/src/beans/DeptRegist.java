/***************************
 * 学籍番号:40313
 * 作成者　:T.Kanz
 * 作成日　:2016/12/04
 * 内容　　:部課登録に関するクラス。
 * *************************/
package beans;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeptRegist {

	/**
	 * 部署へのINSERT。
	 * @auther ohs40292
	 *
	 */
	public static void InsertDept(String deptname) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager("jv34team");
		db.update("INSERT INTO department_master(department_name) VALUES('"+deptname+"')");
		db.closeDB();
	}

	/**
	 * 課へのINSERT。
	 * @auther ohs40292
	 *
	 */
	public static void InsertSubDept(String subdeptname, String deptid) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager("jv34team");
		db.update("INSERT INTO sub_department_master(sub_department_name, department_id) VALUES('"+subdeptname+"', '"+deptid+"')");
		db.closeDB();
	}


	/**
	 * 空白＆NULLチェック。
	 * @auther ohs40292
	 * @param str
	 * @return
	 */
	public static boolean checkVal(String str) {
		str = InspectionValue.cheackNull(str);
		if (str.equals("")){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 部の重複チェック。
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean SelectDept(String deptname) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager("jv34team");
		ArrayList<ArrayList<String>> result = db.runSelect("SELECT COUNT(department_name) FROM department_master WHERE department_name = ('"+deptname+"')");
		String hikaku = result.get(0).get(0);
		if (hikaku .equals("0")){
			return true;
		}
		else {
		return false;
		}
	}

	/**
	 * 課の重複チェック。
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean SelectSubDept(String deptname, String deptid) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager("jv34team");
		ArrayList<ArrayList<String>> result = db.runSelect("SELECT COUNT(sub_department_name) FROM sub_department_master WHERE sub_department_name = ('"+deptname+"') AND department_id = ('"+deptid+"') ");
		String hikaku = result.get(0).get(0);
		if (hikaku .equals("0")){
			return true;
		}
		else {
		return false;
		}
	}

	/**
	 * SQLインジェクション。削除してInspectionValueの同メソッドを使用予定。
	 * @auther ohs40292
	 * 2016/12/07
	 * @param val
	 * @return
	 */
	public static String replaceSQL(String val) {
		val = val.replaceAll("<", "&lt;");
		val = val.replaceAll(">", "&gt;");
		val = val.replaceAll("\"", "&quot;");
		val = val.replaceAll("&", "&amp;");
		val = val.replaceAll("'", "&rsquo;");
		return val;

	}
}

