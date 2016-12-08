/***************************
 * 学籍番号:40292
 * 作成者　:kanz.T
 * 作成日　:2016/12/07
 * 内容　　:付加給与登録並びに変更に関するクラス。
 * *************************/
package beans;

import java.sql.SQLException;

public class IncludedSalary {

	/**
	 * 付加給与を新規に登録する。
	 * @auther ohs40292
	 *
	 */
	public static void InsertAdvantageSalary(String from, String to, String salary) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager("jv34team");
		db.update("INSERT INTO advantage_salary_master(work_year_from, work_year_to, advantage_salary) VALUES('"+from+"', '"+to+"', '"+salary+"')");
		db.closeDB();
	}

	/**
	 * 既存の付加給与を変更する。
	 * @author ohs40292
	 */
	public static void UpdateAdvantageSalary(String from, String to, String salary, String id) throws ClassNotFoundException, SQLException {
		DBManager db = new DBManager("jv34team");
		db.update("UPDATE advantage_salary_master SET work_year_from = '"+from+"', work_year_to = '"+to+"', advantage_salary = '"+salary+"' WHERE advantage_id = '"+id+"'");
		db.closeDB();
	}

	/**
	 * 開始年月終了年月付加給与額の空白＆NULLチェック。
	 * @auther ohs40292
	 * @param from 開始年月
	 * @param to 終了年月
	 * @param salary 給与額
	 * @return
	 */
	public static boolean checkVal(String from, String to, String salary) {
		from = InspectionValue.cheackNull(from);
		to = InspectionValue.cheackNull(to);
		salary = InspectionValue.cheackNull(salary);
		if (from.equals("") || to.equals("") || salary.equals("")){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * TO＜FROMチェックと、数値チェック
	 * @auther ohs40292
	 * 2016/12/08
	 * @param from 勤務年数開始
	 * @param to 勤務年数終了
	 * @return
	 */
	public static boolean range(String strFrom, String strTo, String strSalary){
		try{
			Integer.parseInt(strSalary);
			if (Integer.parseInt(strFrom) >= Integer.parseInt(strTo)) {
			return true;
		}} catch (NumberFormatException e){
			return true;
		}
		return false;
	}
}
