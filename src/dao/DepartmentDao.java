/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/12/07
 * 内容　　:部署テーブルからデータを取得するクラス。
 * *************************/
/**
 * @auther 浩生
 * 2016/12/07
 */
package dao;

import beans.DBManager;
import common.DataBase;

/**
 * @author 浩生
 *
 */
public class DepartmentDao implements DataBase{

	/**
	 * 部署IDに紐づく部署名を取得する。
	 * @auther 浩生
	 * 2016/12/07
	 * @param depNo
	 * @return
	 */
	public static String findDeptName(int depNo){
		String sql="SELECT  department_name FROM department_master WHERE department_id = "+depNo;
		try{
			DBManager dbManager=new DBManager(DBName);
			String result=dbManager.runSelect(sql).get(0).get(0);
			dbManager.closeDB();
			if(result==null)return new String();
			return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new String();
	}

}
