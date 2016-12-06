/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/12/05
 * 内容　　:社員情報を格納する箱クラス。
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Employment {
	/**
	 * 社員ID
	 * @auther 浩生
	 * 2016/12/05
	 * @param employmentId String
	 */
	public String employmentId;
	/**
	 * カナ社員名
	 * @auther 浩生
	 * 2016/12/05
	 * @param kName String
	 */
	public String kName;
	/**
	 * 社員名
	 * @auther 浩生
	 * 2016/12/05
	 * @param name String
	 */
	public String name;
	/**
	 * 誕生日
	 * @auther 浩生
	 * 2016/12/05
	 * @param birthDay CalendarByKoki
	 */
	public CalendarByKoki birthDay;
	/**
	 * 性別
	 * @auther 浩生
	 * 2016/12/05
	 * @param sex int
	 */
	public int sex;
	/**
	 * 郵便番号
	 * @auther 浩生
	 * 2016/12/05
	 * @param postalCode String
	 */
	public String postalCode;
	/**
	 * 住所
	 * @auther 浩生
	 * 2016/12/05
	 * @param address String
	 */
	public String address;
	/**
	 * 電話番号
	 * @auther 浩生
	 * 2016/12/05
	 * @param tel String
	 */
	public String tel;
	/**
	 * 部署情報
	 * @auther 浩生
	 * 2016/12/05
	 * @param department Department
	 */
	public Department department;
	/**
	 * 課情報
	 * @auther 浩生
	 * 2016/12/05
	 * @param subDepartment SubDepartment
	 */
	public SubDepartment subDepartment;
	public OfficailPosition officailPosition;
	/**
	 * 入社日付
	 * @auther 浩生
	 * 2016/12/05
	 * @param joinedDate CalendarByKoki
	 */
	public CalendarByKoki joinedDate;
	/**
	 * 退社日付
	 * @auther 浩生
	 * 2016/12/05
	 * @param leavingDate CalendarByKoki
	 */
	public CalendarByKoki leavingDate;
	{
		this.employmentId=new String();
		this.kName=new String();
		this.name=new String();
		this.sex=-1;
		this.postalCode=new String();
		this.address=new String();
		this.tel=new String();
		this.department=new Department();
		this.subDepartment=new SubDepartment();
		this.officailPosition=new OfficailPosition();
	}
}
