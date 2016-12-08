package filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DBManager;
import beans.ErrorCheck;
import dtd.PermissionType;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(filterName="AuthFilter", urlPatterns={"/protect/*"})
public class AuthFilter implements Filter {

    /**
     * Default constructor.
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		//アクセスしようとするページのURL
		String accessURL = req.getServletPath();
		System.out.println(accessURL);
		HttpSession session = ((HttpServletRequest)request).getSession();

		/**
		 * ここに社員IDを渡す
		 * sessionで保管した社員ID
		 */
		//String employeeID = (String) session.getAttribute("employeeID");
		String employeeID = "0000003";

		ErrorCheck check = new ErrorCheck();
		// 未認証ならサインイン画面へ
		if (session == null || check.isNullOrEmpty(employeeID))
		{
			//トップページに飛ばす、トップページのパス指定
			res.sendRedirect(req.getContextPath() + "/top.jsp");

		}

		try
		{
			DBManager db = new DBManager("JV34_team");

			if (accessURL.equals("/protect/AttendancePut.jsp"))
			{
				boolean havePermission = db.permission(PermissionType.Department, "経理部", employeeID);
				if (havePermission == false)
				{
					res.sendRedirect(req.getContextPath() + "/error/noAuth.jsp");
					return;
				}
			}
			else if (accessURL.equals("/protect/AttendanceEmployeeServlet"))
			{
				boolean havePermission = db.permission(PermissionType.Manager, "部長", employeeID);
				if (havePermission == false)
				{
					res.sendRedirect(req.getContextPath() + "/error/noAuth.jsp");
					return;
				}

			}
			else if (accessURL.equals("/protect/ShowEmployee.jsp") || accessURL.equals("/protect/dept_registration.jsp") || accessURL.equals("/protect/included_salary.jsp"))
			{
				boolean havePermission = db.permission(PermissionType.Department, "人事部", employeeID);
				if (havePermission == false)
				{
					res.sendRedirect(req.getContextPath() + "/error/noAuth.jsp");
					return;
				}
			}

			db.closeDB();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// pass the request along the filter chain
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}



}
