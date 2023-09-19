package utils;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;


public class LoanReturnValue {
	
	Loan loan;
	User user;
	public LoanReturnValue(Loan loan, User user) {
		super();
		this.loan = loan;
		this.user = user;
	}
	
	
	public LoanReturnValue() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
