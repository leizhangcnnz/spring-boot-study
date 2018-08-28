package com.nzleyuan.persistance;

import com.nzleyuan.persistance.com.nzleyuan.entity.GroupOrder;
import com.nzleyuan.persistance.repositories.GroupOrderRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class LyFrameworkApplication {
	
	@Autowired
	GroupOrderRepository groupOrderRepository;
	
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

	@GetMapping(value = "getGroupOrder")
	Optional<GroupOrder> getGroupOrder(long objid) {
		return groupOrderRepository.findById(objid);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LyFrameworkApplication.class, args);
	}
}
