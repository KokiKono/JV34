/**
 * 作成日：2016/12/08
 * 作成者：m.haruka
 * ヘッダーとナビゲーションに必要な情報を取得するクラス。
 */
package beans;

import java.util.ArrayList;

import common.DataBase;

public class UserInfoGetter implements DataBase{

	private static String _dbName = DBName;
	private String _userId = "";

	/**
	 * ユーザーIDを設定する。
	 * @param userId
	 */
	public UserInfoGetter(String userId){
		_userId = userId;
	}

	/**
	 * ユーザー名を取得する。
	 * @return
	 */
	public String getUserName(){
		String userName = "ゲスト";
		DBManager dbManager = null;
		try{
			dbManager = new DBManager(_dbName);
			String sql = "SELECT emp.employee_name FROM employee_master emp WHERE emp.employee_master = '" + _userId + "'";
			ArrayList<ArrayList> list = dbManager.select(sql);
			if(list.size() > 0){
				userName = list.get(0).get(0).toString();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userName;
	}

	/**
	 * 部署名を取得する。
	 * @return
	 */
	public String getDeptId() {
		String deptId = "所属部署なし";
		DBManager dbManager = null;
		try{
			dbManager = new DBManager(_dbName);
			String sql = "SELECT emp.department_id FROM employee_master emp WHERE emp.employee_master = '"+ _userId +"'";
			ArrayList<ArrayList> list = dbManager.select(sql);
			if(list.size() > 0){
				deptId = list.get(0).get(0).toString();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deptId;
	}



}
