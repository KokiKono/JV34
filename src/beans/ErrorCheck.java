/***************************
 * 作成者　:dyf
 * 作成日　:2016/11/20
 * 内容　　:入力された値などチェッククラス
 * *************************/
package beans;

import java.util.ArrayList;

public class ErrorCheck
{

	/**
	 * パータンマッチ
	 * 
	 * @auther dyf 2016/11/20
	 * @param pattern
	 *            マッチするパータン
	 * @param value
	 *            チェックしたい値
	 * @return マッチしたらtrue,マッチしなかったらfalse
	 */

	public boolean patternMacher(String pattern, String value)
	{

		if (value.matches(pattern))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * パターンマッチしてるかどうか、場合によりメッセージを返す
	 * 
	 * @auther dyf 2016/11/29
	 * @param pattern
	 *            チェックするパターン
	 * @param value
	 *            チェックする値
	 * @param mes
	 *            返したいメッセージ
	 * @return マッチしなかったら返したいメッセージを返す,マッチできたら空を返す
	 */

	public String patternMacherMes(String pattern, String value, String mes)
	{
		if (patternMacher(pattern, value) == false)
		{
			return mes;
		}
		return "";
	}

	/**
	 * StringをIntに変換
	 * 
	 * @auther dyf 2016/11/20
	 * @param value
	 * @return 変換できたらそのまま,変換失敗したらnull
	 */
	public Integer StrToInt(String value)
	{
		int intValue;
		try
		{
			intValue = Integer.parseInt(value);
			return intValue;
		} catch (NumberFormatException ex)
		{
			return null;
		}

	}

	/**
	 * nullなら空を返す
	 * 
	 * @auther dyf 2016/11/20
	 * @param value
	 * @return 空
	 */
	public String nullReturnEmpty(String value)
	{
		if (value == null)
		{
			return "";
		}
		return value;
	}

	/**
	 * 数値と入力された値の範囲(1-12)をチェック
	 * 
	 * @auther dyf 2016/11/20
	 * @param 月のチェック
	 * @return 正しい値ならそのまま、正しくないならnull
	 */
	public String getMonth(String value)
	{
		String _value = "00" + (value == null ? null : value.trim());
		_value = _value.substring(_value.length() - 2);
		if (_value.matches("(0[1-9]|1[012])"))
		{
			return _value;
		} else
		{
			return null;
		}
	}

	/**
	 * nullまたは空かどうかのチェック
	 * @auther dyf
	 * 2016/12/04
	 * @param チェックする値
	 * @return　nullまたは空ならtrue,そうじゃないならfalse
	 */
	public boolean isNullOrEmpty(String value)
	{
		return value == null || value.isEmpty();
	}
	
	/**
	 * ArrayListがnullの場合空を返す
	 * @auther dyf
	 * 2016/12/05
	 * @param list
	 * @return　nullなら空を返す
	 */
	public ArrayList<ArrayList<String>> listIsNullReturnEmpty(ArrayList<ArrayList<String>> list) 
	{
		ArrayList<ArrayList<String>> emptyArray = new ArrayList<ArrayList<String>>();
		if (list == null)
		{
			return emptyArray;
		}
		else
		{
			return list;
		}
				
	}
	
	/**
	 * 社員パスワードチェック 6から8桁まで
	 */

}
