package za.co.supremeworx.services;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.supremeworx.model.Items;
import za.co.supremeworx.repositories.ItemsRepository;
import za.co.supremeworx.requests.ItemsRequests;
import za.co.supremeworx.responses.ItemsResponse;
import za.co.supremeworx.utils.Utils;

@Service
@Slf4j
public class ItemsService {
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	public ItemsResponse createItem(ItemsRequests itemsRequests) {
		Items items = itemsRepository.save(mapItemsRequestsToItems(itemsRequests));
		log.info("Item {} saved",items);
		return mapItemsToItemsResponse(items);
	}
	
	public Items mapItemsRequestsToItems(ItemsRequests itemsRequests) {
		Items items = new Items();
		items.setName(itemsRequests.getName());
		items.setDescription(itemsRequests.getDescription());
		items.setPrice(itemsRequests.getPrice());
		items.setQuantity(itemsRequests.getQuantity());
		return items;
		
	}
	
	public ItemsResponse mapItemsToItemsResponse(Items item) {
		ItemsResponse itemsResponse = new ItemsResponse();
		itemsResponse.setName(item.getName());
		itemsResponse.setDescription(item.getDescription());
		itemsResponse.setPrice(item.getPrice());
		itemsResponse.setQuantity(item.getQuantity());
		return itemsResponse;
	}
	
	public List<Items> getItems(){
		return Utils.convertIterableToList(itemsRepository.findAll());
	}
	
	public ItemsResponse getItems(String name) {
		return mapItemsToItemsResponse(itemsRepository.findByName(name).get());
	}

}
