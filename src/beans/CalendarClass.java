/***************************
 * 作成者　:dyf
 * 作成日　:2016/11/20
 * 内容　　:現在の日付取得、また入力された日付正しいかどうかチェックする
 * *************************/
package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarClass
{
	// 生年月日を入力できる範囲の基準値

	// 最高定年
	private static final int MaxAge = 65;

	// 新卒最低年
	private static final int MinAge = 20;

	// 月
	private static final String MONTH = "03";

	// 日
	private static final String DATE = "01";

	Calendar calendar = Calendar.getInstance();

	// 現在の日付
	private String year = String.valueOf(calendar.get(Calendar.YEAR));
	private String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
	private String date = String.valueOf(calendar.get(Calendar.DATE));

	// 入力された日付
	private String _year;
	private String _month;
	private String _days;

	/**
	 * 入力された年月と現在の年月チェック
	 * 
	 * @auther dyf 2016/11/20
	 * @return 同じなら0,過去なら1,未来なら2
	 */
	public int pastPresentFuture()
	{

		String yearAndMonth = year + month;

		String _yearAndMonth = _year + _month;

		int intValue = Integer.parseInt(yearAndMonth);
		int _intValue = Integer.parseInt(_yearAndMonth);

		System.out.println("現在の日付" + intValue);
		if (intValue == _intValue)
		{
			System.out.println("同じ" + _intValue);
			return 0;
		} else if (intValue > _intValue)
		{
			System.out.println("過去" + _intValue);
			return 1;
		} else
		{
			System.out.println("未来" + _intValue);
			return 2;
		}

	}

	/**
	 * 入力された年月の日数が正しいかどうか
	 * 
	 * @auther dyf 2016/11/20
	 * @return 正しいなら1,正しくないなら0
	 */
	public int daysCheck()
	{
		// 入力された年月をCalendarにセットする
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(Integer.parseInt(_year), Integer.parseInt(_month) - 1, 1);

		// 入力された年月の日数を取得
		int monthOfDay = calendar.getActualMaximum(Calendar.DATE);

		// 入力された日数
		int _monthOfDay = Integer.parseInt(_days);

		if (_monthOfDay < monthOfDay || _monthOfDay == monthOfDay)
		{
			return 1;
		} else
		{
			return 0;
		}

	}

	/**
	 * 設定した日時末日が返される
	 * 
	 * @auther dyf 2016/11/28
	 * @param _year
	 * @param _month
	 * @param _days
	 * @return 末日が返される
	 */
	private int maximum(int _year, int _month, int _days)
	{
		calendar = Calendar.getInstance();
		calendar.set(_year, _month - 1, _days);

		return calendar.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 今月の末日が返される
	 * 
	 * @auther dyf 2016/11/30
	 * @return 今月の末日
	 */
	public int maximum()
	{
		calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(this.year), Integer.parseInt(this.month) - 1, Integer.parseInt(this.date));

		return calendar.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 生年月日入力できる最小値を取得
	 * 
	 * @auther dyf 2016/11/28
	 * @return 生年月日入力できる最小値
	 */
	public int retirementAge()
	{
		return 	Integer.parseInt(this.year) - this.MaxAge;
	}

	/**
	 * 入力できる最大値を取得
	 * 
	 * @auther dyf 2016/11/28
	 * @return 生年月日入力できる最大値
	 */
	public int joinedAge()
	{
		return Integer.parseInt(this.year) - this.MinAge;
	}

	/**
	 * 日付フォーマット変換
	 * 
	 * @auther dyf 2016/11/30
	 * @param format
	 *            変換したいフォーマット形式
	 * @param parseValue
	 *            変換したい値(String)
	 * @return 変換後の値
	 * @throws ParseException
	 */
	public String dateFormat(String format, String parseValue) throws ParseException
	{
		// 変換前の日付フォーマットをdate型に
		SimpleDateFormat parser = new SimpleDateFormat("yyyymmdd");
		Date date = parser.parse(parseValue);

		// 変換したい日付フォーマットを指定
		SimpleDateFormat parserTo = new SimpleDateFormat(format);
		String parseResult = parserTo.format(date);

		return parseResult;

	}

	// 19960301より小さいなら登録可能
	// 19510301より大きくなら登録可能
	
	public boolean birthdayCheck(int birhtdayValue)
	{
		if (retirementAge() < birhtdayValue || joinedAge() > birhtdayValue)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**************************************************************************
	 * Getters Setters
	 *************************************************************************/
	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String _getYear()
	{
		return _year;
	}

	public void _setYear(String _year)
	{
		this._year = _year;
	}

	public String _getMonth()
	{
		return _month;
	}

	public void _setMonth(String _month)
	{
		this._month = _month;
	}

	public String _getDays()
	{
		return _days;
	}

	public void _setDays(String _days)
	{
		this._days = _days;
	}

}
