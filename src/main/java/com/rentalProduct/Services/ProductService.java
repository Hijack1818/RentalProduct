package com.rentalProduct.Services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalProduct.Entities.CustomResponse;
import com.rentalProduct.Entities.Product;
import com.rentalProduct.Repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public void save(Product product) {
		productRepository.save(product);
	}

	public CustomResponse getProduct(String name) {
		Product product = productRepository.findById(name).orElse(null);
		if(product==null) {
			return null;
		}
		CustomResponse response = new CustomResponse();
		response.setAmount(product.getAmount());
		response.setName(product.getName());
		response.setStartTime(product.getStartTime());
		response.setEndTime(product.getEndTime());
		
		response.setImage("/product/image?name="+name);
		return response;
	}


	
	public List<CustomResponse> getByTime(Time startTime, Time endTime) {
		
		List<Product> productList = productRepository.findAvailableProducts(startTime,endTime);
		List<CustomResponse> responseList = new ArrayList<>();
		
		for(Product item:productList) {
			CustomResponse response = new CustomResponse();
			response.setAmount(item.getAmount());
			response.setName(item.getName());
			response.setStartTime(item.getStartTime());
			response.setEndTime(item.getEndTime());
			response.setImage("/product/image?name="+item.getName());
			responseList.add(response);
		}
		if(responseList.size()==0) {
			responseList.add(new CustomResponse());
			responseList.get(0).setName("Item not Found");
		}
		return responseList;
	}

	public Product getProductWithImage(String name) {
		return productRepository.findById(name).orElse(null);
	}
	
	public void updateProduct(CustomResponse customResponse) {
		
		Product product = productRepository.findById(customResponse.getName()).orElse(null);
		product.setEndTime(customResponse.getEndTime());
		product.setStartTime(customResponse.getStartTime());
		product.setAmount(customResponse.getAmount());
		product.setName(customResponse.getName());
		productRepository.save(product);
	}
	
	
}
