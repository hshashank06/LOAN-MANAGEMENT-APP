package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import utils.IssueStatus;
import utils.ItemMake;
import utils.LoanType;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	@NotEmpty(message="Item description cannot be null")
	@Column(name = "Item Description",nullable = false)
	private String description;
	@Enumerated(EnumType.STRING)
	@Column(name = "Issue Status",nullable = false)
	private IssueStatus issueStatus;
	@Enumerated(EnumType.STRING)
	@Column(name = "Item Make", nullable = false)
	private ItemMake itemMake;
	@Enumerated(EnumType.STRING)
	@Column(name = "Item Type",nullable = false)
	private LoanType itemType;
	@Column(name = "Item Value",nullable = false)
	private Integer itemValue;
	@ManyToOne
	@JoinColumn(name = "loanId",referencedColumnName = "loanId")
	@JsonIgnore
	private Loan loan;
	
	
	public Item(Long itemId, @NotEmpty(message = "Item description cannot be null") String description,
			@NotEmpty(message = "Issue Status of item cannot be null or empty") IssueStatus issueStatus,
			@NotEmpty(message = "Item make cannot be null or empty") ItemMake itemMake,
			@NotEmpty(message = "Item Type cannot be null or empty") LoanType itemType,
			@NotEmpty(message = "Item Value cannot be null or empty") Integer itemValue) {
		super();
		this.itemId = itemId;
		this.description = description;
		this.issueStatus = issueStatus;
		this.itemMake = itemMake;
		this.itemType = itemType;
		this.itemValue = itemValue;
	}
	
	
	
	public Item(Long itemId, @NotEmpty(message = "Item description cannot be null") String description,
			@NotEmpty(message = "Issue Status of item cannot be null or empty") IssueStatus issueStatus,
			@NotEmpty(message = "Item make cannot be null or empty") ItemMake itemMake,
			@NotEmpty(message = "Item Type cannot be null or empty") LoanType itemType,
			@NotEmpty(message = "Item Value cannot be null or empty") Integer itemValue, Loan loan) {
		super();
		this.itemId = itemId;
		this.description = description;
		this.issueStatus = issueStatus;
		this.itemMake = itemMake;
		this.itemType = itemType;
		this.itemValue = itemValue;
		this.loan = loan;
	}
	
	
	public Item() {
		super();
	}
	
	
	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IssueStatus getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}
	public ItemMake getItemMake() {
		return itemMake;
	}
	public void setItemMake(ItemMake itemMake) {
		this.itemMake = itemMake;
	}
	public LoanType getItemType() {
		return itemType;
	}
	public void setItemType(LoanType itemType) {
		this.itemType = itemType;
	}
	public Integer getItemValue() {
		return itemValue;
	}
	public void setItemValue(Integer itemValue) {
		this.itemValue = itemValue;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", description=" + description + ", issueStatus=" + issueStatus
				+ ", itemMake=" + itemMake + ", itemType=" + itemType + ", itemValue=" + itemValue + "]";
	}
	
	
	
	
	

}
