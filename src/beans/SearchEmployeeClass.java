/***************************
 * 作成者　: dyf
 * 作成日　: 2016/12/04
 * 内容　　: 社員検索
 * *************************/
package beans;

public class SearchEmployeeClass
{
	String department;
	String officialPosition;
	String minYear;
	String maxYear;
	String rank;

	ErrorCheck check = new ErrorCheck();

	/**
	 * 入力チェック 検索条件かどうか
	 */

	/**
	 * 勤務年数の計算
	 */

	/**
	 * 入社年数で社員検索する場合 3パターン ① minYearしか入力してない場合 ② maxYearしか入力してない場合 ③ 両方とも入力した場合
	 */
	public String test()
	{
		String sql = "";
		if (!check.isNullOrEmpty(minYear) && !check.isNullOrEmpty(maxYear))
		{
			sql = "";
		} else
		{

		}
		return "";
	}

	/**
	 * 退社したかどうか 退社してない場合は : 現在の年から入社年で検索 退社した場合は : 退社から入社まで sqlで
	 * 
	 */

	/**
	 * 社員検索条件
	 */
//	public ArrayList<String> searchCriteria(Object... value)
//	{
//		ArrayList<String> searchList = new ArrayList<String>();
//		for (int i = 0; i < value.length; i++)
//		{
//			String strValue = (String) value[i];
//			if (!check.isNullOrEmpty(strValue))
//			{
//				searchList.add(strValue);
//			}
//			else
//			{
//				searchList.add(null);
//			}
//		}
//		return searchList;
//	}
	public boolean yearsOfService(String minYear,String maxYear)
	{
		int intMinYear = Integer.parseInt(minYear);
		int intMaxYear = Integer.parseInt(maxYear);
		
		if (intMinYear > intMaxYear)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
