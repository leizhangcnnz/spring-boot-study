package com.nzleyuan.persistance;

import com.nzleyuan.persistance.com.nzleyuan.entity.GroupOrder;
import com.nzleyuan.persistance.com.nzleyuan.entity.Product;
import com.nzleyuan.persistance.repositories.GroupOrderRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.nzleyuan.persistance.repositories.ProductRepository;
import com.nzleyuan.persistance.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

	@GetMapping(value = "getGroupOrder")
	Optional<GroupOrder> getGroupOrder(long objid) {
		return groupOrderRepository.findById(objid);
	}
	@GetMapping(value = "getGroupOrders")
	List<GroupOrder> getGroupOrders() {
		return groupOrderRepository.findAll();
	}


	@PostMapping(value = "saveProduct")
	Product saveProduct(@RequestBody String payload) throws IOException {
		Product product = (Product) JacksonUtils.convertJson2Object(payload, Product.class);
		return productRepository.save(product);
	}

	@GetMapping(value = "getProducts")
	List<Product> getProducts() {
		return productRepository.findAll();
	}

	@ResponseBody
	@RequestMapping("uploadPicture")
	public String uploadPicture(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("user");
		if(!file.isEmpty()) {
			file.getBytes();
			String fileName = file.getOriginalFilename();
			String path = null;
			String type = null;
			type = fileName != null && fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
					// 项目在容器中实际发布运行的根路径
					String realPath = request.getSession().getServletContext().getRealPath("/");
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					path = realPath + "/uploads/" + trueFileName;
					file.transferTo(new File(path));
				}else {
					return "error";
				}
			}else {
				return "error";
			}
		}else {
			return "error";
		}
		return "success";
	}

	public static void main(String[] args) {
		SpringApplication.run(LyFrameworkApplication.class, args);
	}
}
