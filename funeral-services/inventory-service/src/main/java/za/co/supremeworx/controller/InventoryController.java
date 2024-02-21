package za.co.supremeworx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.supremeworx.dto.InventoryResponse;
import za.co.supremeworx.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController { 
	
	@Autowired
	private InventoryService inventoryService;
	
//	@GetMapping("/{sku-code}")
//	public ResponseEntity<Boolean> isInStock(@PathVariable("sku-code") String skuCode){
//		return new ResponseEntity<Boolean>(inventoryService.isInStock(skuCode),HttpStatus.OK);
//			
//	}
	@GetMapping
	public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCode){
		return new ResponseEntity<List<InventoryResponse>>(inventoryService.isInStock(skuCode),HttpStatus.OK);
			
	}
	
//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iphone");
//			inventory.setQuantity(100);
//			
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("iphone_pro_max");
//			inventory1.setQuantity(0);
//			
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//			
//		};
//	}

}//-----end of class
