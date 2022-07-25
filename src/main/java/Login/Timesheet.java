package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Timesheet
 */
@WebServlet("/Timesheet")
public class Timesheet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Timesheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	//	/**
	//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	//	 */
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

		String empno = request.getParameter("empno");
		String dayofworking = request.getParameter("dayno");
		String year = request.getParameter("year");
		String month = request.getParameter("month");

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		System.out.println(empno);
		System.out.println(dayofworking);
		System.out.println(year);
		System.out.println(month);

		try {
			if(validate(empno,year,month)) {
				
				addTimeSheet(empno,dayofworking,year,month);
			
				
				
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("Payslip.html");
				dispatcher.include(request, response);
				
			}else {
				System.out.println("false");
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
				                "<a class='nav-link' href='EmployeeManagmentApp/Payslip'>"+"Payslip"+"</a>" +
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
				      "</nav>"+"<div style = 'color :red;'>"+"<p style = 'margin-left:110px'>"+"Please re-check Month and Year"+"</p>"+"</div>"+"</body>"+"</html>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("TimeSheet.html");
				dispatcher.include(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/NPCI","root","sivan");



	}


		public String addTimeSheet(String empno,String dayofworking,String year,String month) throws SQLException, ClassNotFoundException {
			
			Connection connect = getConnection();
			PreparedStatement p = connect.prepareStatement("insert into TimeSheet values(?,?,?,?)");
			p.setInt(1, Integer.parseInt(empno));
			p.setInt(2, Integer.parseInt(dayofworking));
			p.setString(3, month);
			p.setString(4, year);
			
			
			int executeUpdate = p.executeUpdate();
			return executeUpdate + "row inserted";
		}

	public boolean validate(String empno,String year,String month) throws NumberFormatException, SQLException, ClassNotFoundException  {

		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("select * from Employees where EmpNo = ? ");
		p.setInt(1, Integer. parseInt(empno) );


		ResultSet r = p.executeQuery();
		if(r.next()) {
			String date = r.getString(4);
			String[] arrOfdatestr = date.split("-");
			int yeardata = Integer.parseInt(arrOfdatestr[2]);
			int monthdata = Integer.parseInt(arrOfdatestr[1]);
			int datecondition= (yeardata * 365 ) + monthdata;
			int userdate = (Integer.parseInt(year) * 365) + Integer.parseInt(month);
			//			int day = Integer.parseInt(arrOfdatestr[0]);
			System.out.println(userdate);
			System.out.println(((2022 * 365 ) + 7));
			
			if(userdate >= datecondition ) {
				if(userdate <= ((2022 * 365 )+ 7)) {
					return true;
				}
				
		    }
		}
		return false;

	}
}
