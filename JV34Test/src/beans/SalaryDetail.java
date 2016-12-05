/***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/11/17
 * 内容　　:各社員の給与計算に関する処理をまとめたクラス。
 * *************************/
package beans;

import java.sql.SQLException;


public class SalaryDetail {

	/**
	 * 出勤日数に応じて、給料を10000増減する。
	 * @param days 予め決められた各社員ごとの最低勤怠日数
	 * @param days2 実際の出勤日数。
	 * @params allowance 出勤日数
	 * @return salary
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see
	 */
	public static int SalaryByDays(int days, int days2, int salary){


		int allowance = (days2 - days) * 10000;
		int commit_salary = allowance + 250000;
		return salary;
	}

	/**
	 * 所持している資格で、最も大きいレベルの資格手当を、給与に加算する。
	 * @param licence 資格レベル
	 * @param licence_salary 資格手当
	 * @return ￥資格手当
	 */
	public static int SalaryByLicence(int licence, int licence_salary){

//        if (licence == 1){
//        	licence_salary = 5000;
//        }else if (licence == 2){
//        	licence_salary = 10000;
//        }else if (licence == 3){
//        	licence_salary = 15000;
//        }else if (licence == 4){
//        	licence_salary = 20000;
//        }else{
//        	licence_salary = 0;
//        }

		switch (licence){
			case 0:
				licence_salary = 0;
				break;
			case 1:
				licence_salary = 5000;
				break;
			case 2:
				licence_salary = 10000;
				break;
			case 3:
				licence_salary = 15000;
				break;
			case 4:
				licence_salary = 20000;
				break;
		}

		return licence_salary;
	}

	/**
	 * 詳細ボタンに、各レコードのID(employee_master)を割り当てる
	 * 【未完成】
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 *
	 */
//	public static String EmployeeMaster(String EmployeeMaster) throws ClassNotFoundException, SQLException{
//
//		DBManager db = new DBManager("jv34team");
//
//		ArrayList<ArrayList<String>> result = db.runSelect("SELECT employee_master FROM employee_master");
//
//		ResultSet rs = null;
//
//		while(rs.next()){//以下追加するカラム。arraylistに入れていく。
//			ArrayList<String> rec = new ArrayList<String>();//ArrayList型でrecを宣言
//			rec.add("<form method='get' action ='each_employee.jsp'>" + rs.getString("member_id"));
//			String strEmployeeMaster = rs.getString("member_id");
//			rec.add("<input type='hidden' name='member_id' value ='" +strEmployeeMaster+ "'><input type = 'submit' name = sb value = '詳細参照'/></form>");
//			result.add(rec);//tblに変数を代入していってくれる
//		}
//
//		db.closeDB();
//
//		return EmployeeMaster;
//	}

	/**
	 * 最終的な給与計算
	 * @param salary 時給
	 * @param weekday 平日出勤数
	 * @param holiday 休日出勤数
	 * @return 最終的な給与
	 */
	public static double SalaryCommit(double salary, int weekday , int holiday){

		salary = salary * 8 * (weekday - holiday) + salary * 8 * holiday * 1.2;
		return salary;
	}
}
