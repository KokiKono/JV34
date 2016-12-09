/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/12/05
 * 内容　　:課情報を格納する箱クラス。
 * *************************/
package dtd;

public class SubDepartment {
	/**
	 * 課ID
	 * @auther 浩生
	 * 2016/12/05
	 * @param subDepartmentId int
	 */
	public int subDepartmentId;
	/**
	 * 課名
	 * @auther 浩生
	 * 2016/12/05
	 * @param name String
	 */
	public String name;
	/**
	 * 部署情報
	 * @auther 浩生
	 * 2016/12/05
	 * @param department Department
	 */
	public Department department;
	{
		this.subDepartmentId=-1;
		this.name=new String();
		this.department=new Department();
	}
}
