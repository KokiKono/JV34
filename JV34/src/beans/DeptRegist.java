/***************************
 * 学籍番号:40313
 * 作成者　:T.Kanz
 * 作成日　:2016/12/04
 * 内容　　:部課登録に関する処理をまとめたクラス。
 * *************************/
package beans;

import java.sql.SQLException;

public class DeptRegist {

	/**
	 * DBへのINSERT。
	 * @auther ohs40292
	 *
	 */
	public static void InsertDept(String deptname) {
		/**
		 * DB接続
		 */
		DBManager db = null;
		try {
			db = new DBManager("jv34team");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		/**
		 * INSERT
		 */
		try {
			db.update("INSERT INTO department_master(department_name) VALUES('"+deptname+"')");
			db.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

