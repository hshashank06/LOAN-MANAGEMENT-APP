package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;
import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import utils.IssueStatus;
import utils.ItemMake;
import utils.LoanType;


@Service
public class ItemService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	LoanRepo loanRepo;
	
	
	public ItemService(UserRepo userRepo,AdminRepo adminRepo,ItemRepo itemRepo,LoanRepo loanRepo) {
		this.userRepo = userRepo;
		this.adminRepo = adminRepo;
		this.itemRepo = itemRepo;
		this.loanRepo = loanRepo;
	}
	
	public List<Item> displayUserItems(Long userId) {
		List<Item> allItems=new ArrayList<>();
		User user = userRepo.findById(userId).orElse(null);
		Set<Loan> loans = user.getLoan();
		
		for(Loan loan:loans) {
			Set<Item> itemsForGivenLoan = loan.getItem();
			allItems.addAll(itemsForGivenLoan);
			
		}
		return allItems;
	}
	
	@SuppressWarnings("deprecation")
	public Item displayItemForGivenItemId(Long itemId) {
		Item item = itemRepo.getById(itemId);
		return item;
	}
	
	
	public List<Item> displayAllItems(){
		List<Item> listOfItems = itemRepo.findAll();
		return listOfItems;
	}
	
	@Transactional
	public Boolean deleteItems(Long id) {
		itemRepo.deleteById(id);
		return true;
	}
	
	@Transactional
	public void editItem(Long itemId,Map<String,Object> fields) {
		
		Item item = itemRepo.findById(itemId).orElse(null);
		if(item != null) {
			for(Map.Entry<String, Object> eachItem : fields.entrySet()) {
				String field = eachItem.getKey();
				Object value = eachItem.getValue();
				if(field.equals("description")){
					item.setDescription((String) value);
				}
				else if(field.equals("issueStatus")) {
					IssueStatus issueStatus = IssueStatus.valueOf((String) value);
					item.setIssueStatus(issueStatus);
				}
				else if(field.equals("itemMake")) {
					ItemMake itemMake = ItemMake.valueOf((String) value);
					item.setItemMake((ItemMake) itemMake);
				}
				else if(field.equals("itemType")) {
					LoanType loanType = LoanType.valueOf((String) value);
					item.setItemType((LoanType) loanType);
				}
				else if(field.equals("itemValue")) {
					item.setItemValue((Integer) value);
				}
				
			}
			itemRepo.save(item);
		}
		
		
		
	}
	
	 public Long returnLoanId(Long itemId) {
		 Item item =itemRepo.findById(itemId).orElse(null);
		 if(item != null) {
			 return item.getLoan().getLoanId();
		 }
		 else {
			 return null;
		 }
		 
		 
	 }
	public Boolean registerItem(Long loanId,Item item) {
		

		Loan loanItem = loanRepo.findById(loanId).orElse(null);
		if(loanItem.getStatus().equals(IssueStatus.YES)) {
			item.setLoan(loanItem);	
			itemRepo.save(item);		
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	@Transactional
	public List<Item> displayItemsForGivenLoanIds(Long loanId) {
		try {			
		List<Item> items = itemRepo.findByLoanId(loanId);
		return items;
		}
		catch(Exception e) {
			return Collections.EMPTY_LIST;
		}
	}


}
