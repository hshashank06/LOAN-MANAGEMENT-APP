//package com.example.demo.repository;
//
//import java.time.LocalDate;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.example.demo.entity.Item;
//import com.example.demo.entity.Loan;
//import com.example.demo.entity.User;
//
//import jakarta.transaction.Transactional;
//import utils.IssueStatus;
//import utils.ItemMake;
//import utils.LoanType;
//
//@DataJpaTest
//@Transactional
//@RunWith(SpringRunner.class)
//public class UserRepoTest {
//	@Autowired
//	private UserRepo userRepo;
//	@Autowired
//	private TestEntityManager testEntityManager;
//	
//	@Test
//	public void testRegisterUser() {
//		
//		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
//		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
//		Item item = new Item(1L,"MARUTI-CAR",IssueStatus.YES,ItemMake.Steel,LoanType.CAR,2000,loan);
//		userRepo.save(user);
//		User retrievedUser = userRepo.findById(user.getUserId()).orElse(null);
//		Assert.assertEquals(user, retrievedUser);
//	}
//
//}
