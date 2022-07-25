package Login;

public class PayslipDetails {
	
	private int empno;
	private String ename;
	private String job;
	private int salary;
	private int workingday;
	private String month;
	private String year;
	private String commision;
	
	public PayslipDetails(int empno, String ename, String job, int salary, int workingday, String month, String year,
			String commision) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.salary = salary;
		this.workingday = workingday;
		this.month = month;
		this.year = year;
		this.commision = commision;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getWorkingday() {
		return workingday;
	}

	public void setWorkingday(int workingday) {
		this.workingday = workingday;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCommision() {
		return commision;
	}

	public void setCommision(String commision) {
		this.commision = commision;
	}

	@Override
	public String toString() {
		return "PayslipDetails [empno=" + empno + ", ename=" + ename + ", job=" + job + ", salary=" + salary
				+ ", workingday=" + workingday + ", month=" + month + ", year=" + year + ", commision=" + commision
				+ "]";
	}
	
	
	
	

}
