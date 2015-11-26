

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;




import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 * Servlet implementation class XMLServlet
 */
@WebServlet("/XMLServlet")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				PrintWriter out = response.getWriter();
					
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Northwind";
				
				String query = "select CategoryID,CategoryName from Categories";
				
				try{
					DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
					//conn = DriverManager.getConnection(url, "sa", "sa123456");
					conn = DriverManager.getConnection(url, "sa", "passw0rd");
					stmt = conn.prepareStatement(query);
				
					  Document document = new Document();
					rs = stmt.executeQuery();
					
		               Element root = new Element("Categories");
		               document.addContent(root);
		               while (rs.next())
						 {
		            	   Element e = new Element("Category");
		            	   root.addContent(e);
		            	   
		            	   e.addContent(new Element("CategoryID")
		                           .setText(rs.getString(1)));
		            	   e.addContent(new Element("CategoryName")
                           .setText(rs.getString(2)));
		            	   
						 }
		               response.setContentType("text/xml;charset=UTF-8");
		            
		               Format format = Format.getPrettyFormat();		              
		               format.setIndent("    ");
		               
		               XMLOutputter xml = new XMLOutputter();
		               out.write(xml.outputString(document));
					
				}
				catch(SQLException e){
					out.println("Error:" + e.getMessage());
				}
				finally{
					//if(rs != null){
					//   rs.close();
					//}
					//if(stmt != null){
					// stmt.close();
					//}
					//if(conn != null){
					//}
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
