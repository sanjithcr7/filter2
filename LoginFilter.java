package filter2;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.*;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/welcome")
public class LoginFilter extends HttpFilter {
       Connection con;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
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
	public void doFilter(jakarta.servlet.ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		try {
			String s1=request.getParameter("uname");
			String s2=request.getParameter("pword");
			PreparedStatement ps=con.prepareStatement("select * from uinfo where uname=? and password=?");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				chain.doFilter(request, response);
			}
			else {
				PrintWriter pw=response.getWriter();
				pw.println("Invalid............");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","tiger");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
