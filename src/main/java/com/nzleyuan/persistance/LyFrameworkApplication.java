package com.nzleyuan.persistance;

import com.nzleyuan.persistance.com.nzleyuan.entity.GroupOrder;
import com.nzleyuan.persistance.com.nzleyuan.entity.Product;
import com.nzleyuan.persistance.repositories.GroupOrderRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.nzleyuan.persistance.repositories.ProductRepository;
import com.nzleyuan.persistance.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class LyFrameworkApplication {
	
	@Autowired
	GroupOrderRepository groupOrderRepository;

	@Autowired
  ProductRepository productRepository;
	
	@RequestMapping("/")
	String greeting() {
		return "hello world";
	}

	@GetMapping(value = "createGroupOrder")
	GroupOrder createGroupOrder(String title, long category, long seller, String content) {
		GroupOrder groupOrder = new GroupOrder()
				.setTitle(title)
				.setCategory(category)
				.setSeller(seller)
				.setContent(content);
		return groupOrderRepository.save(groupOrder);
	}

	@PostMapping(value = "saveProduct")
	Product saveProduct(@RequestBody String payload) throws IOException {
		Product product = (Product) JacksonUtils.convertJson2Object(payload, Product.class);
		return productRepository.save(product);
	}

	@GetMapping(value = "getGroupOrder")
	Optional<GroupOrder> getGroupOrder(long objid) {
		return groupOrderRepository.findById(objid);
	}
	@GetMapping(value = "getGroupOrders")
	List<GroupOrder> getGroupOrders() {
		return groupOrderRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(LyFrameworkApplication.class, args);
	}
}
