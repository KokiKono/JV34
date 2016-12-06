/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/05/18
 * 内容　　:日付処理に関する処理。
 * *************************
 * 更新者:k.koki
 * 更新日:2016/05/24
 * 内容:カレンダーを表示するための二次元配列を生成するメソッド。
 * *************************
 * 更新者:k,koki
 * 更新日:2016/05/31
 * 内容:年、月、日の妥当性をチェックするメソッド。
 * *************************
 * 更新者:k,koki
 * 更新日:2016/06/28
 * 内容:カレンダーのカスタマイズクラスで二次元配列を生成するメソッド。
 * *************************
 * 更新者:k,koki
 * 更新日:2016/011/07
 * 内容:日付チェックに関するメソッド追加、列挙追加。
 * *************************
 * 更新者:k,koki
 * 更新日:2016/011/07
 * 内容:datetimeに対応するコンストラクタを生成。
 * *************************/
package beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarByKoki {
	private Calendar calendar = new GregorianCalendar(Locale.JAPAN);
	// 曜日を格納した文字列配列
	private String[] _dayOfWeek = getDayOfWeekCharacter(DAY_OF_WEEK_FOR_JAPAN);
	/**
	 * 曜日取得時のフォーマット定数フィールド。日本
	 */
	public static final int DAY_OF_WEEK_FOR_JAPAN = 1;
	/**
	 * 曜日取得時のフォーマット定数フィールド。アメリカ
	 */
	public static final int DAY_OF_WEEK_FOR_US = 2;
	public static final int DAY_OF_WEEK_FOR_ORIGINAL = 3;
	/**
	 * ********************** 作成日時:2016/05/18 16:50:45 作成者 :k.koki
	 *
	 * コンストラクター 現在日時情報を持つCalendarByKokiを構築します。
	 ***********************
	 */
	public CalendarByKoki() {
		calendar.set(getYear(), getMonth(), getDay(), getHour(), getMin(),
				getSec());
	}

	/**
	 * ********************** 作成日時:2016/05/18 16:51:02 作成者 :k.koki
	 *
	 * コンストラクター 指定日付を持つCalendarByKokiを構築します。
	 *
	 * @param year
	 * @param month
	 * @param date
	 ***********************
	 */
	public CalendarByKoki(int year, int month, int date) {
		calendar.set(year, month-1, date);

	}
	/**
	 * ********************** 作成日時:2016/05/18 16:51:26 作成者 :k.koki
	 *
	 * コンストラクター 指定日時を持つCalendarByKokiを構築します。
	 *
	 * @param year
	 * @param month
	 * @param date
	 * @param hrs
	 * @param min
	 * @param sec
	 ***********************
	 */
	public CalendarByKoki(int year, int month, int date, int hrs, int min,
			int sec) {
		calendar.set(year, month-1, date, hrs, min, sec);
	}
	/************************
	 *作成日時:2016/08/24 12:00:26
	 *作成者  :k.koki
	 *@param コンストラクター
	 *		 カレンダー情報を持つCalendarByKokiを構築します。
	 *@param dateMoment yyyy-MM-ddまたはyyyy年MM月dd日のフォーマットのみ動作します。
	 ************************/
	public CalendarByKoki(String dateMoment){
		this(Integer.parseInt(dateMoment.substring(0, 4)), Integer.parseInt(dateMoment.substring(5, 7)), Integer.parseInt(dateMoment.substring(8, 10)));
	}

	/**
	 * 作成日:2016/05/18 16:59:02 作成者:k.koki
	 *
	 * 設定した日時情報から年を取得します 。
	 *
	 * @return
	 */
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 作成日:2016/05/18 16:59:30 作成者:k.koki
	 *
	 * 設定した日時情報から月を取得します 。
	 * 0月～11月
	 * @return
	 */
	public int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 作成日:2016/05/18 17:00:20 作成者:k.koki
	 *
	 * 設定した日時情報から日を取得します 。
	 *
	 * @return
	 */
	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 作成日:2016/05/18 17:02:59 作成者:k.koki
	 *
	 * 設定した日時情報から時 (24時間)を取得します。
	 *
	 * @return
	 */
	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 作成日:2016/05/18 17:04:48 作成者:k.koki
	 *
	 * 設定した日時情報から時 (12時間)を取得します。
	 *
	 * @return
	 */
	public int getHourOfDay() {
		if (getHour() < 12) {
			return getHour();
		}
		return getHour() - 12;
	}

	/**
	 * 作成日:2016/05/18 17:07:18 作成者:k.koki
	 *
	 * 設定した日時情報から分を取得します 。
	 *
	 * @return
	 */
	public int getMin() {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 作成日:2016/05/18 17:07:58 作成者:k.koki
	 *
	 * 設定した日時情報から秒を取得します 。
	 *
	 * @return
	 */
	public int getSec() {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 作成日:2016/05/18 17:45:28 作成者:k.koki
	 *
	 * 設定した国定数から日時情報から曜日を取得します 。 指定の曜日で表示したい場合はsettingDayOfWeekで編集してください。
	 *
	 * @return
	 */
	public String getDayOfWeek(int Countory) {
		switch (this.calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			return getDayOfWeekCharacter(Countory)[0];
		case Calendar.MONDAY:
			return getDayOfWeekCharacter(Countory)[1];
		case Calendar.TUESDAY:
			return getDayOfWeekCharacter(Countory)[2];
		case Calendar.WEDNESDAY:
			return getDayOfWeekCharacter(Countory)[3];
		case Calendar.THURSDAY:
			return getDayOfWeekCharacter(Countory)[4];
		case Calendar.FRIDAY:
			return getDayOfWeekCharacter(Countory)[5];
		case Calendar.SATURDAY:
			return getDayOfWeekCharacter(Countory)[6];
		}
		return "";
	}

	/**
	 * 作成日:2016/05/19 1:56:36 作成者:k.koki
	 *
	 * 引数には配列要素数7の文字列配列をしてください 。 設定できた場合はtrue、できない場合はfalseを返します。
	 *
	 * @param val
	 * @return
	 */
	public boolean settingDayOfWeek(String[] val) {
		if (val.length == 7) {
			this._dayOfWeek = val;
			return true;
		}
		return false;
	}

	/**
	 * @author k.koki 曜日や月を文字列に変換する時のデフォルト定型書式を定義した内部クラス 。
	 * @retunr Stringvalue
	 */
	private class Stringvalue {
		String[] _dayOfWeek;

		public Stringvalue() {
			this._dayOfWeek = new String[7];
		}

		String[] getDayOfWeekForJapan() {
			this._dayOfWeek[0] = "日";
			this._dayOfWeek[1] = "月";
			this._dayOfWeek[2] = "火";
			this._dayOfWeek[3] = "水";
			this._dayOfWeek[4] = "木";
			this._dayOfWeek[5] = "金";
			this._dayOfWeek[6] = "土";
			return this._dayOfWeek;
		}

		String[] getDayOfWeekForUs() {
			this._dayOfWeek[0] = "Sun.";
			this._dayOfWeek[1] = "Mon.";
			this._dayOfWeek[2] = "Tue.";
			this._dayOfWeek[3] = "Wed.";
			this._dayOfWeek[4] = "Thu.";
			this._dayOfWeek[5] = "Fri.";
			this._dayOfWeek[6] = "Sat.";
			return this._dayOfWeek;
		}
	}

	/**
	 * 作成日:2016/05/19 2:51:25 作成者:k.koki
	 *
	 * 定数フィールドで指定された書式文字列を返します 。
	 */
	private String[] getDayOfWeekCharacter(int CharacterMode) {
		Stringvalue stringvalue = new Stringvalue();
		switch (CharacterMode) {
		case DAY_OF_WEEK_FOR_JAPAN:
			return stringvalue.getDayOfWeekForJapan();
		case DAY_OF_WEEK_FOR_US:
			return stringvalue.getDayOfWeekForUs();
		case DAY_OF_WEEK_FOR_ORIGINAL:
			return this._dayOfWeek;
		}
		return this._dayOfWeek;
	}

	/**
	 * @author 浩生 CalendarByKokiのコンストラクタで定義した日時情報と比較するための内部クラス 。
	 * @retunr Diffrence
	 */
	public class Diffrence {
		private Calendar _calendar = new GregorianCalendar(Locale.JAPAN);

		/************************
		 * 作成日時:2016/05/20 12:41:07 作成者 :k.koki
		 *
		 * コンストラクター 比較日時情報を持つDiffrenceを構築します。
		 *
		 * @param year
		 * @param month
		 * @param day
		 ************************/
		public Diffrence(int year, int month, int day) {
			this._calendar.set(year, month-1, day, 0, 0, 0);
		}

		/************************
		 * 作成日時:2016/05/20 12:41:26 作成者 :k.koki
		 *
		 * コンストラクター 比較日時情報を持つDiffrenceを構築します。
		 *
		 * @param year
		 * @param month
		 * @param day
		 * @param hour
		 * @param min
		 * @param sec
		 ************************/
		public Diffrence(int year, int month, int day, int hour, int min,
				int sec) {
			this._calendar.set(year, month-1, day, hour, min, sec);
		}
		/**
		 * CalendarByKokiの日時情報を比較情報とします。
		 * @auther 浩生
		 * 2016/11/08
		 * @param byKoki
		 */
		public Diffrence(CalendarByKoki byKoki){
			this(byKoki.getYear(), byKoki.getMonth()-1, byKoki.getDay(),byKoki.getHour(),byKoki.getMin(),byKoki.getSec());
		}
		/**
		 * 作成日:2016/05/19 8:56:37 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との年数差分を取得します 。
		 *
		 * @param year
		 * @param month
		 * @param day
		 * @return
		 */
		public int isDiffrenceYear() {
			int result = getYear() - this._calendar.get(Calendar.YEAR);
			if ((getMonth() - this._calendar.get(Calendar.MONTH)) < 0
					|| (getDay() - this._calendar.get(Calendar.DATE)) < 0) {
				return result - 1;
			}
			return result;
		}

		/**
		 * 作成日:2016/05/20 13:04:35 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との日数差分を取得します 。
		 *
		 * @return
		 */
		public long isDiffrenceDay() {
			return isDiffrenceHour() / 24;
		}

		/**
		 * 作成日:2016/05/20 13:04:23 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との時間差分を取得します 。
		 *
		 * @return
		 */
		public long isDiffrenceHour() {
			return isDiffrenceMinute() / 60;
		}

		/**
		 * 作成日:2016/05/20 13:04:11 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との分差分を取得します 。
		 *
		 * @return
		 */
		public long isDiffrenceMinute() {
			return isDiffrenceSecond() / 60;
		}

		/**
		 * 作成日:2016/05/20 13:00:14 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との秒数差分を取得します 。
		 *
		 * @return
		 */
		public long isDiffrenceSecond() {
			long leftCalendar = calendar.getTimeInMillis();
			long rightCalendar = this._calendar.getTimeInMillis();
			return (leftCalendar - rightCalendar) / 1000;
		}

		/**
		 * 作成日:2016/05/20 14:53:50 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との月差分を取得します 。 1>月差分0>-1の時は0を返します。
		 *
		 * @return
		 */
		/*
		 * public int isDiffrenceMonth(){ int result=isDiffrenceYear()*12;
		 * //日付差が一年+最低月日数の場合、そのまま終了します。 if(isDiffrenceDay()<365+28){ return
		 * result; }else{ //
		 * if(getDay()-this._calendar.get(Calendar.DAY_OF_MONTH)) } }
		 */
		/**
		 * 作成日:2016/05/21 0:10:32 作成者:k.koki
		 *
		 * 指定日付とコンストラクタで定義された日付との間でうるう年が何回あるか取得します 。
		 *
		 * @return
		 */
		public int getLeapYear() {
			int leap = 0;
			int year = this._calendar.get(Calendar.YEAR);
			System.out.println(year + ",year");
			System.out.println(getYear() + ":year");
			for (int i = 0; i <= isDiffrenceYear(); i++) {
				if (new GregorianCalendar(Locale.JAPAN).isLeapYear(year + i)) {
					leap++;
				}
			}
			return leap;
		}
		/**
		 * 差分をbooleanで表現します。
		 * この差分は秒数に変換した場合で判断します。
		 * Diffrenceの日時（以下、Diffrence）
		 * CalendarByKokiの日時（以下、CalendarByKoki）
		 * if(Diffrence>CalndarByKoki){
		 *  return true;
		 * }else{
		 * 	return false;
		 * }
		 *まったく同時刻だった場合、falseを返します。
		 * @auther 浩生
		 * 2016/11/08
		 * @return
		 */
		/*public boolean diffrence(){
			if(this._calendar.get(Calendar.YEAR)>calendar.get(Calendar.YEAR)){
				return true;
			}else if(this._calendar.get(Calendar.YEAR)==calendar.get(Calendar.YEAR)){
				if(this._calendar.get(Calendar.MONTH)>calendar.get(Calendar.MONTH)){
					return true;
				}else if(this._calendar.get(Calendar.MONTH)==calendar.get(Calendar.MONTH)){
					if(this._calendar.get(Calendar.DAY_OF_MONTH)>calendar.get(Calendar.DAY_OF_MONTH)){
						return true;
					}else if(this._calendar.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH)){
						if(this._calendar.get(Calendar.HOUR_OF_DAY)>calendar.get(Calendar.HOUR_OF_DAY)){
							return true;
						}else if(this._calendar.get(Calendar.HOUR_OF_DAY)==calendar.getActualMaximum(Calendar.HOUR_OF_DAY)){
							if(this._calendar.get(Calendar.MINUTE)>calendar.get(Calendar.MINUTE)){
								return true;
							}else if(this._calendar.get(Calendar.MINUTE)==calendar.get(Calendar.MINUTE)){
								if(this._calendar.get(Calendar.SECOND)>calendar.get(Calendar.SECOND)){
									return true;
								}else if(this._calendar.get(Calendar.SECOND)==calendar.get(Calendar.SECOND)){
									return false;
								}else return false;
							}else return false;
						}else return false;
					}else return false;
				}else return false;
			}else return false;
		}*/
	}

	/**
	 * 作成日:2016/05/20 23:29:36 作成者:k.koki
	 *
	 * コンストラクタで定義された日時情報がうるう年かどうか判断します 。
	 *
	 * @return true=うるう年 false=ではない
	 */
	public boolean isLeapYear() {
		return new GregorianCalendar(Locale.JAPAN).isLeapYear(getYear());
	}

	/**
	 * 作成日:2016/05/24 17:53:20 作成者:k.koki
	 *
	 * コンストラクタで指定された月のカレンダーを二次元配列で取得します。 　　　　｜日｜月｜火｜水｜木｜金｜土｜ ここから生成→ ｜　｜１｜２｜...
	 * onEmpty １日が週始まりではない時、引数のemptyを入れるかをtrue（入れる）、false(入れない）で指定します。
	 *
	 * @return　生成した二次元配列
	 */
	public ArrayList<ArrayList<String>> getCalendarList(boolean onEmpty,
			String empty) {
		// 戻り値用変数
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		// 一行単位の配列
		ArrayList<String> row = new ArrayList<String>();
		// 曜日をindex方式に変更
		String[] stack = this._dayOfWeek;
		String[] val = { "0", "1", "2", "3", "4", "5", "6" };
		// 曜日のindex変数
		CalendarByKoki calendarByKoki=new CalendarByKoki(getYear(), getMonth()+1, 1);
		calendarByKoki.settingDayOfWeek(val);
		int index = Integer.parseInt(calendarByKoki.getDayOfWeek(DAY_OF_WEEK_FOR_ORIGINAL));
		this.settingDayOfWeek(stack);
		// emptyを入れるか
		if (onEmpty) {
			for (int i = 0; i < index; i++) {
				row.add(empty);
			}
		}
		// リストに日にちを入れていく
		for (int i = 0; i < getMaxDay(); i++) {
			if (row.size() == 7) {
				result.add(row);
				row = new ArrayList<String>();
			}
			row.add(String.valueOf(i + 1));
		}
		if (onEmpty) {
			if (0 < row.size() && row.size() < 8) {
				for (; row.size() != 7;) {
					row.add(empty);
				}
			}
		}
		result.add(row);
		return result;
	}

	/**
	 * 作成日:2016/05/24 17:59:32 作成者:k.koki
	 *
	 * コンストラクタで定義された月の最終日を取得します 。
	 *
	 * @return
	 */
	public int getMaxDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(getYear(), getMonth(), getDay());
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 作成日:2016/05/31 21:53:42
	 * 作成者:k.koki
	 * 年、月、日を総合的に存在するのか判断します。
	 * @return
	 */
	public boolean isExist(){
		if(!(getYear()>0)){
			return false;
		}
		if(!(getMonth()>=0 && getMonth()<12)){
			return false;
		}
		int day=getDay();
		int max=getMaxDay();
		if(!(day>0 && day<=max)){
			return false;
		}
		return true;
	}
	/**
	 * 作成日:2016/08/19 22:22:06
	 * 作成者:k.koki
	 * @param SQLで使用するフォーマットで返します。
	 * @return yyyy-MM-dd
	 */
	public String outSQLDate(){
		int month=getMonth()+1;
		return getYear()+"-"+month+"-"+getDay();
	}
	public String[] outSQLBeginWeek(){
		int point=getDay()-7;
		int resultMonth=0;
		if(point<=0){
			//前月にまたぐ場合
			int month=getMonth()+1;
			CalendarByKoki calendarByKoki=new CalendarByKoki(getYear(),month-1,1);
			int maxDay=calendarByKoki.getMaxDay();
			int endDay=getDay();
			int startDay=(getDay()-7)+maxDay;
			resultMonth=calendarByKoki.getMonth()+1;
			String[] val={getYear()+"-"+resultMonth+"-"+startDay,getYear()+"-"+resultMonth+"-"+endDay};
			return val;
			}
		//今月中の場合
		resultMonth=getMonth()+1;
		int endDay=getDay()-1;
		String[] val={getYear()+"-"+resultMonth+"-"+point,getYear()+"-"+resultMonth+"-"+endDay};
		return val;
	}

	/**
	 * 作成日:2016/08/23 11:16:48
	 * 作成者:k.koki
	 * @param yyyy年MM月dd日で取得します。
	 * @return
	 */
	public String outOfJP(){
		int month=getMonth()+1;
		return getYear()+"年"+month+"月"+getDay()+"日";
	}
	/**
	 * 作成日:2016/08/24 11:15:17
	 * 作成者:k.koki
	 * @param コンストラクタで定義された月の前月の情報を取得します。
	 * @return
	 */
	public CalendarByKoki lastTimeMonth(){
		int year=getYear();
		int month=getMonth();
		int day=getDay();
		if(getMonth()==0){
			year--;
			month=11;
		}
		if(day==getMaxDay()){
			CalendarByKoki calendarByKoki=new CalendarByKoki(year, month, day);
			day=calendarByKoki.getMaxDay();
		}
		return new CalendarByKoki(year, month, day);


	}
	/**
	 * 作成日:2016/08/24 11:16:03
	 * 作成者:k.koki
	 * @param コンストラクタで定義された月の次月の情報を取得します。
	 * @return
	 */
	public CalendarByKoki nextMonth(){
		int year=getYear();
		int month=getMonth();
		int day=getDay();
		if(getMonth()==11){
			year++;
			month=0;
		}
		if(day==getMaxDay()){
			CalendarByKoki calendarByKoki=new CalendarByKoki(year, month, day);
			day=calendarByKoki.getMaxDay();
		}
		return new CalendarByKoki(year, month, day);
	}
	private void setDate(int year,int month,int day){
		this.calendar.set(year, month-1, day);
	}
	public void plusDay(int day){
		int plus=getDay()+day;
		int month=getMonth()+1;
		int year=getYear();
		if(plus>getMaxDay()){
			//足した結果が月末を超す場合
			if(11==getMonth()){
				//12月の場合は翌年に設定する。
				month=1;
				year++;
			}else {
				month++;
			}
			plus=plus-getMaxDay();
		}
		setDate(year, month, plus);
	}
	/**
	 * CalendarByKokiの新しいインスタンスを生成します。
	 * 値に不整合な値がある場合はnullを返します。
	 * @auther 浩生
	 * 2016/11/07
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static final CalendarByKoki newInstance(String year,String month,String day){
		if(year==null || month==null || day==null){
			return null;
		}
		if(year.isEmpty())return null;
		if(month.isEmpty())return null;
		if(day.isEmpty())return null;
		try{
			return new CalendarByKoki(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * [yyyy-MM-dd HH:mm:ss]または[yyyy-MM-dd]
	 * に対応するCalendarByKokiを作成します。
	 * 時分秒がない場合は00:00:00がセットされます。
	 * 必ず年月日と時分秒の間には半角スペースを入れてください。
	 * そのほかに空白は入れないでください。
	 * 不正な値が入った場合はnullを返します。
	 * @auther 浩生
	 * 2016/11/08
	 * @param dateMoment
	 */
	public static CalendarByKoki newInstance(String dateTime){
		if(dateTime==null)return null;
		int center=dateTime.indexOf(" ");
		if(center==-1){
			return null;
		}
		String left=dateTime.substring(0, center);
		String right=dateTime.substring(center+1, dateTime.length());
		String[] date=left.split("-");
		if(date.length>0){
			//- : 区切りで生成
			if(date.length!=3){
				return null;
			}
			if(right.length()==0)return new CalendarByKoki(dateTime);
			String[] time=right.split(":");
			if(time.length!=3){
				return null;
			}
			//timeが00.0に対応→00に変換
			if(time[2].indexOf(".")>0){
				time[2]=time[2].substring(0, 2);
			}
			return new CalendarByKoki(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
		}
		return null;
	}



}


