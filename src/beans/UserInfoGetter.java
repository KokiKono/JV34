/**
 * 作成日：2016/12/08
 * 作成者：m.haruka
 * ヘッダーとナビゲーションに必要な情報を取得するクラス。
 */
package beans;

public class UserInfoGetter {

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
		String userName = "";
		DBManager dbManager = null;
		try{
			String sql = "SELECT emp.employee_name FROM employee_master emp WHERE emp.department_id = '" + _userId + "'";
			dbManager.select(sql);
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
		String deptId = "";
		DBManager dbManager = null;
		try{
			String sql = "SELECT emp.department_id FROM employee_master emp WHERE emp.department_id = '"+ _userId +"'";
			dbManager.select(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deptId;
	}



}
