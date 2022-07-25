package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Payslip
 */
@WebServlet("/Payslip")
public class Payslip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payslip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String empno = request.getParameter("empno");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			
			PayslipDetails getpayslipdetails = getpayslipdetails(empno,year,month);
			int basicSalary = 0 ;
			int hradeduction = 0 ;
			int pf = 1200;
			int professionaltax = 500;
			int netpay = 0;
			int grosssalary = 0;
			int totaldeduction= 0;
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("email");
			
			if(getpayslipdetails != null) {
				    
					int monthlybasicSalary = (int) ((getpayslipdetails.getSalary()/100) * 72.5);
					int dailybasicsalary = monthlybasicSalary / 31;
					basicSalary = dailybasicsalary * getpayslipdetails.getWorkingday();
					
					System.out.println(basicSalary);
					int monthlyhradeduction = 40 *  (basicSalary/100);
					int dailyhradeduction = monthlyhradeduction / 31 ;
					hradeduction = dailyhradeduction * getpayslipdetails.getWorkingday();
					grosssalary = basicSalary + hradeduction ;
					totaldeduction = pf + professionaltax;
					netpay = grosssalary - totaldeduction;
					 
					
					
					
//					out.print("<p style='float:right'>"+email+"</p>");
					
					
				out.print( "<html>" + "<head>"+ "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>" +
			    "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'>"+"</script>" +"</head>"+
				
						"<body style=\"background-color: #ADD8E6;\">"+ "<nav class='navbar navbar-expand-lg navbar-light bg-secondary'>" +
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
				      "</nav>"+ 
		        "<h3 style='text-align:center' >" + "Payslip "+ "</h3>" +
		        "<p style='text-align:center'>" + "Learnscopic "+"</p>" +
		    "</div>" +

		    "<div class='container' style='border:1px solid black; width:900px'>" +
		        "<div class='row'>" +
		    
		          "<div class='col'>" +
		            "Employee ID : " + getpayslipdetails.getEmpno() +
		          "</div>" +
		          "<div class='col'>" +
		           "Desigination: " + getpayslipdetails.getJob()+
		          "</div>" +
		        "</div>"+
		          
				"<div class='row'>" +
		          "<div class='col'>" +
		            "Employee Name : " + getpayslipdetails.getEname() +
		          "</div>" +
		          "<div class='col'>" +
		           "Nummber of working days: " + getpayslipdetails.getWorkingday() +
		          "</div>" +
		            
		          "</div>"+
		          "<div class='row'>" +
		          "<div class='col'>" +
		            "Employee Band : " + getpayslipdetails.getCommision() +
		          "</div>" +
		          "<div class='col'>" +
		           "Pay slip on: " +  getpayslipdetails.getMonth() +"-"  + getpayslipdetails.getYear() +  
		          "</div>" +
		            
		          "</div>"+
		      "</div>"+ 
		         "<table class='table' style='border:1px solid black; margin-left:300px;  margin-top:20px;width:400px; position:absolute' >" +
		        "<thead>"+
		          "<tr>"+
		            "<th scope='col'>"+"Earnings"+"</th>"+
		            "<th scope='col'>"+"Amount"+"</th>"+
		            
		          "</tr>"+
		       "</thead>"+
		        "<tbody>"+
		            "<tr>"+
		            "<th scope='row'>"+"Basic Salary"+"</th>"+
		            
		            "<td>"+basicSalary+"</td>"+
		           
		         "</tr>"+
		          "<tr>"+
		            "<th scope='row'>"+"House Rent"+"</th>"+
		            "<td>"+hradeduction+"</td>"+
		           
		          "</tr>"+
		          "<tr>"+
		            "<th scope='row'>"+"Total salary"+"</th>" +
		            "<td colspan='2'>"+grosssalary+"</td>" +
		            
		          "</tr>"+
		        "</tbody>"+
		      "</table>"+

		      "<table class='table'  style='border:1px solid black; width:400px; margin-right:300px; margin-top:20px; float:right; position:relative'>" +
		        "<thead>"+
		          "<tr>"+
		            "<th scope='col'>"+"Deductions"+"</th>"+
		            "<th scope='col'  style='margin-left: 90px'>"+"Amount"+"</th>"+
		            
		          "</tr>"+
		       "</thead>"+
		        "<tbody>"+
		            "<tr>"+
		            "<th scope='row'>"+"Privident Fund"+"</th>"+
		            
		            "<td>"+pf+"</td>"+
		           
		         "</tr>"+
		          "<tr>"+
		            "<th scope='row'>"+"Provisional tax"+"</th>"+
		            "<td>"+professionaltax+"</td>"+
		           
		          "</tr>"+
		          "<tr>"+
		            "<th scope='row'>" + "Total Deduction" + "</th>"+
		            "<td colspan='2'>"+totaldeduction+"</td>"+
		            
		          "</tr>"+
		        "</tbody>"+
		      "</table>"





		      

		     + "<h4 style=' margin-top:170px; margin-left:300px'>" + " Net pay after deduction : " + netpay +  "</h4>" +"</body>"+"</html>");
				
				
			}else {
//				out.print("<html><body><div style = 'color :red;'><h4>payslip is not generated</h4></div></body></html>");
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
				      "</nav>"+"<div style = 'color :red;'>"+"<p style = 'margin-left:110px'>"+"Ther is no payslip for selected month"+"</p>"+"</div>"+"</body>"+"</html>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Payslip.html");
				dispatcher.include(request, response);
			}
		    
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
	
	public PayslipDetails getpayslipdetails(String empno,String year,String month) throws SQLException, ClassNotFoundException {
		
		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("select * from Employees inner join TimeSheet on Employees.EmpNo = TimeSheet.EmpNo where TimeSheet.month =? and TimeSheet.year=? ");
		p.setString(1, month);
		p.setString(2,year);
		
//		ArrayList<PayslipDetails> empdetails = new ArrayList<>();
		
		ResultSet r = p.executeQuery();
		
		while(r.next()) {
             
			if(r.getString(1).equals(empno)) {
			
			return new PayslipDetails(r.getInt(1),r.getString(2),r.getString(3),r.getInt(6),r.getInt(10),r.getString(11),r.getString(12),r.getString(7));
			
		}
			}
		return null;
			
			
			
		}
			
		


	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/NPCI","root","sivan");



	}

}
