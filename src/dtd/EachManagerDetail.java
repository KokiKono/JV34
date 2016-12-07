package dtd;

import java.util.ArrayList;

import beans.CalendarByKoki;

public class EachManagerDetail {
	public String empNo;
	public String empName;

	/**
	 * 日ごとの情報を格納する配列
	 * @auther 浩生
	 * 2016/12/06
	 * @param dayBoxs ArrayList<DayBox>
	 */
	public ArrayList<DayBox> dayBoxs;
	{
		this.empNo=new String();
		this.empName=new String();
		this.dayBoxs=new ArrayList<>();
	}

	public void addDays(String dateFormat,int attendCode){
		DayBox box=new DayBox();
		box.date=new CalendarByKoki(dateFormat);
		box.attend=Attend.findAttend(attendCode);
		this.dayBoxs.add(box);
	}
	public void ownceSetEmpName(String name){
		if(this.empName.isEmpty()==false)return;
		this.empName=name;
	}

	public class DayBox{
		public CalendarByKoki date;
		public Attend attend;
		public ArrayList<String> getSelectOption(){
			return Attend.getSelectOption(this.attend.code);
		}
	}

	public enum Attend{
		Absence(0,"欠勤"),
		Attendance(1,"出勤"),
		Hokiday(2,"休日"),
		Salaried(3,"有給");

		public int code;
		public String value;
		private Attend(int code,String value) {
			this.code=code;
			this.value=value;
		}
		public static ArrayList<String> getSelectOption(int checkCodde){
			ArrayList<String> result=new ArrayList<>();
			for(Attend attend:Attend.values()){
				StringBuffer sb=new StringBuffer();
				sb.append("<option value=");
				sb.append("\""+attend.code+"\"");
				if(checkCodde==attend.code)sb.append(" selected");
				sb.append(">");
				sb.append(attend.value);
				sb.append("</option>");
				result.add(sb.toString());
			}
			return result;
		}

		public static Attend findAttend(int code){
			for(Attend attend:Attend.values()){
				if(attend.code==code)return attend;
			}
			return null;
		}
	}

}
