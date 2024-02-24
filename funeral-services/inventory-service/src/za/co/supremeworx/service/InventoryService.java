package za.co.supremeworx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.supremeworx.dto.InventoryResponse;
import za.co.supremeworx.repository.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		
		return inventoryRepository.findBySkuCodeIn(skuCode)
				.stream()
				.map(inventory -> 
					InventoryResponse.builder().skuCode(inventory.getSkuCode())
					.isInStock(inventory.getQuantity() > 0)
					.build()
				).toList();
		
	}

}//------------end of class