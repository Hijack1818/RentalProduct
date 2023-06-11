package com.rentalProduct.Controllers;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentalProduct.Entities.CustomResponse;
import com.rentalProduct.Entities.Product;
import com.rentalProduct.Services.ProductService;
import com.rentalProduct.Util.ImageUtility;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("save")
	public ResponseEntity<String> saveProduct(@RequestParam("image") MultipartFile image,@RequestParam("name") String name,@RequestParam("amount") int amount,@RequestParam("startTime") Time startTime,@RequestParam("endTime") Time endTime)throws IOException{
		
		Product product = new Product();
		product.setAmount(amount);
		product.setEndTime(endTime);
		product.setStartTime(startTime);
		product.setName(name);
		product.setImage(ImageUtility.compressImage(image.getBytes()));
		
		productService.save(product);
		return ResponseEntity.status(HttpStatus.OK).body("Uploaded Success with name: "+product.getName());
	}
	
	
	@GetMapping("get")
	public ResponseEntity<List<CustomResponse>> getByTime(@RequestParam("startTime") Time startTime,@RequestParam("endTime") Time endTime) {
		
		List<CustomResponse> responseList = productService.getByTime(startTime,endTime);
		return ResponseEntity.ok().body(responseList);
		
	}
	
	
	@GetMapping("getProduct")
	public ResponseEntity<CustomResponse> getByName(@RequestParam("name") String name) {
		return ResponseEntity.ok().body(productService.getProduct(name));
	}
	
	@GetMapping("image")
	public ResponseEntity<byte[]> getImage(@RequestParam("name") String name) {
		Product product = productService.getProductWithImage(name);
		byte[] image = ImageUtility.decompressImage(product.getImage());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		
	}
	
}
