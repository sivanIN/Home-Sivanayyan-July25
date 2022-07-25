package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String emailID = request.getParameter("uname");
		String password = request.getParameter("psw");
		
		System.out.println(emailID);
		System.out.println(password);
		
		try {
			if(validate(emailID,password)) {
				//out.println("Welcome " + emailID);
				HttpSession session = request.getSession(); // 
				session.setAttribute("email", emailID); // email = emailID
				
				String email = (String)session.getAttribute("email");
				out.print("<html>"+"<body>"+"<nav class='navbar navbar-expand-lg navbar-light bg-secondary'>" +
				        "<div class='container-fluid'>"+
				          "<a class='navbar-brand' href='#'>"+"Adalo"+"</a>"+
				          "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>"+
				            "<span class='navbar-toggler-icon'>"+"</span>"+
				          "</button>"+
				          "<div class='collapse navbar-collapse' id='navbarSupportedContent'>"+
				            "<ul class='navbar-nav me-auto mb-2 mb-lg-0'>"+
				              "<li class='nav-item'>"+
				                "<a class='nav-link active' aria-current='page' href='#'>"+"Home"+"</a>"+
				              "</li>"+
				              "<li class='nav-item'>" +
				                "<a class='nav-link' href='#'>"+"Career"+"</a>"+
				              "</li>"+
				              "<li class='nav-item'>"+
				                "<a class='nav-link' href='#'>"+"Contacts"+"</a>" +
				              "</li>" +
				              
				         
				              "<li class='nav-item'>"+
				                "<a class='nav-link' href='Payslip'>"+"Payslip"+"</a>" +
				              "</li>"+
				             
				             
				            "</ul>" +
				            "<form class='d-flex'>"+
				            "<div class='dropdown' style='margin-right:60px'>"+
				              "<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' width='90' height='40' id=\"dropdownMenuButton1\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">"+"<path fill-rule='evenodd' d='M10.5 5a2.5 2.5 0 11-5 0 2.5 2.5 0 015 0zm.061 3.073a4 4 0 10-5.123 0 6.004 6.004 0 00-3.431 5.142.75.75 0 001.498.07 4.5 4.5 0 018.99 0 .75.75 0 101.498-.07 6.005 6.005 0 00-3.432-5.142z'>"+"</path>"+"</svg>"+
				              "<ul class='dropdown-menu' aria-labelledby='dropdownMenuButton1'>"+
				              "<li>"+"<p >"+email+"</p>" +"</li>"+
				              "<li>"+ "<a class='nav-link'  style='color:black' href='Logout'>"+"Logout"+"</a>"+"</li>"+
				             
				            "</ul>"+
				              "</div>" +
				            "</form>"+
				          "</div>"+
				        "</div>"+
				      "</nav>"+"</body>"+"</html>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("TimeSheet.html");
				dispatcher.include(request, response);
			}
			else {
			
//				out.print("<html><body><div style = 'color :red;'><h4>Invalid Credentials</h4></div></body></html>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.html");
				dispatcher.include(request, response);
				out.print("<html><body><div style = 'color :red;'><p style = 'margin-left:110px'>Invalid Credentials</p></div></body></html>");
				
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean validate(String emailID, String password) throws SQLException, ClassNotFoundException {
		
		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("select emailID , password from LoginCredentials where emailID = ? and password = ?");
		p.setString(1, emailID);
		p.setString(2, password);
		
		ResultSet r = p.executeQuery();
		if(r.next())
			return true;
		
		return false;
	}


	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/NPCI","root","sivan");
		
		
	}
	



}
