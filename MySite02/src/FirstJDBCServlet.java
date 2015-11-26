

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstJDBCServlet
 */
@WebServlet("/FirstJDBCServlet")
public class FirstJDBCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstJDBCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Northwind";
		String query = "select ProductID,ProductName,UnitPrice from Products where CategoryID=1";

		try{
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			conn = DriverManager.getConnection(url, "sa", "P@ssw0rd");
			stmt = conn.createStatement();
			
			 rs = stmt.executeQuery(query);
			 out.println("<HTML><HEAD><TITLE>Products</TITLE></HEAD>");
		      out.println("<BODY>");
		      out.println("<table><tr><th>ProductID</th><th>ProductName</th><th>UnitPrice</th></tr>");

			 while (rs.next()) {
				 
				 out.println("<tr><td>" + rs.getInt("ProductID") + "</td><td>"
				            + rs.getString("ProductName") + "</td><td>" + rs.getDouble("UnitPrice") + "</td></tr>");
			     
			 }	
			 out.println("</table>");
		      out.println("</BODY></HTML>");
		}
		catch(SQLException e){
			System.out.println("Error:" + e.getMessage());
		}
		finally{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
