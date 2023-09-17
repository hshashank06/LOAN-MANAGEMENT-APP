package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import utils.LoanType;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	@Column(name = "Loan Type")
	@Enumerated(EnumType.STRING)
	private LoanType loanType;
	@Column(name = "Loan Duration")
	private Integer loanDuration;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "userId")
	private User user;
	
	@OneToMany(mappedBy = "loan",cascade = CascadeType.ALL)
	private Set<Item> item;
	
	

	public Loan() {
		super();
	}
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<Item> getItem() {
		return item;
	}


	public void setItem(Set<Item> item) {
		this.item = item;
	}


	public Long getLoanId() {
		return loanId; 
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public LoanType getLoanType() {
		return loanType;
	}
	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}
	public Integer getLoanDuration() {
		return loanDuration;
	}
	public void setLoanDuration(Integer loanDuration) {
		this.loanDuration = loanDuration;
	}
	public Loan(Long loanId, LoanType loanType, Integer loanDuration) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.loanDuration = loanDuration;
	}
	
	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanType=" + loanType + ", loanDuration=" + loanDuration + "]";
	}
	
	
	

}
