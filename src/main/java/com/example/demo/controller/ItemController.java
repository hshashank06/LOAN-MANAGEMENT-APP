package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanapp")
public class ItemController {

	@Autowired
	public ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@ResponseBody
	@GetMapping("/display/user/{userId}/items")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<List<Item>> displayAllUserItems(@PathVariable("userId") Long userId) {
		List<Item> items = itemService.displayUserItems(userId);
		return ResponseEntity.ok(items);
	}

	@ResponseBody
	@PostMapping("/register/item/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> itemRegister(@PathVariable("id") Long loanId, @RequestBody @Valid Item item) {
		System.out.println(item);
		Boolean response = itemService.registerItem(loanId, item);
		if (response)
			return ResponseEntity.status(HttpStatus.OK).body("True");
		else
			return ResponseEntity.badRequest().body("False");
	}

	@ResponseBody
	@GetMapping("/display/items")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<List<Item>> displayAllItems() {
		List<Item> result = itemService.displayAllItems();
		return ResponseEntity.ok(result);
	}

	@ResponseBody
	@GetMapping("/display/items/loanId/{loanId}")
	ResponseEntity<List<Item>> displayItemsForLoanId(@PathVariable("loanId") Long loanId) {
		List<Item> results = itemService.displayItemsForGivenLoanIds(loanId);
		return ResponseEntity.ok(results);
	}

	@ResponseBody
	@GetMapping("/display/items/itemId/{itemId}")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<Item> displayItem(@PathVariable("itemId") Long itemId) {
		Item item = itemService.displayItemForGivenItemId(itemId);
		return ResponseEntity.ok(item);

	}

	@ResponseBody
	@DeleteMapping("/delete/item")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> deleteItem(@RequestBody Item item) {

		Boolean result = itemService.deleteItems(item.getItemId());
		if (result)
			return ResponseEntity.status(HttpStatus.OK).body("TRUE");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FALSE");

	}

	@PostMapping("/update/item/{itemId}")
	ResponseEntity<String> editItem(@PathVariable("itemId") Long itemId, @RequestBody Map<String, Object> fieldMap) {

		System.out.println(fieldMap);
		itemService.editItem(itemId, fieldMap);
		return ResponseEntity.ok("Item fields updated");
	}

}
