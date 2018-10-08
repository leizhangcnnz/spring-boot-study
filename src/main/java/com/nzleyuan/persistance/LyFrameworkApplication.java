package com.nzleyuan.persistance;

import com.nzleyuan.persistance.com.nzleyuan.entity.GroupOrder;
import com.nzleyuan.persistance.com.nzleyuan.entity.Photos;
import com.nzleyuan.persistance.com.nzleyuan.entity.Product;
import com.nzleyuan.persistance.repositories.GroupOrderRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nzleyuan.persistance.repositories.PhotosRepository;
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

	@Autowired
  PhotosRepository photosRepository;
	
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
	public String uploadPicture(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
		request.setCharacterEncoding("UTF-8");
    long productId = Long.valueOf(request.getParameter("productId"));
		short sequence = Short.valueOf(request.getParameter("sequence"));
		if(!file.isEmpty()) {
			file.getBytes();
			String fileName = file.getOriginalFilename();
      SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
			String type = fileName != null && fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					String dbPath = Paths.get(yyyyMMdd.format(new Date().getTime()), String.valueOf(productId)).toString();
          Path path = Paths.get(System.getProperty("user.dir"), dbPath, trueFileName);
					// 设置存放图片文件的路径
          File newFile = path.toFile();
          if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
          }
					file.transferTo(newFile);
          Photos photos = new Photos().setPath(dbPath).setReferenceId(productId).setSequence(sequence);
          photosRepository.save(photos);
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
