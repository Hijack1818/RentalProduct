package com.rentalProduct.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalProduct.Entities.CustomResponse;
import com.rentalProduct.Entities.User;
import com.rentalProduct.Repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private ProductService productService;

	public String save(User user) {
		CustomResponse product = productService.getProduct(user.getProductName());
		
		if(product==null) {
			return "NO Product is in inventory";
		}
		
		if(product.getStartTime().after(user.getEndTime())) {
			product.setEndTime(user.getEndTime());
			product.setStartTime(user.getStartTime());
			productService.updateProduct(product);
			return userRepository.save(user).toString()+" - Successfully Booked";
		}
		if(product.getEndTime().before(user.getStartTime())) {
			product.setEndTime(user.getEndTime());
			product.setStartTime(user.getStartTime());
			productService.updateProduct(product);
			return userRepository.save(user).toString()+" - Successfully Booked";
		}
		return "Item is Not Available at that Time";
	}

	public Object getUser(String name) {
		User user = userRepository.getByName(name);
		if(user==null) {
			return "User Not Found";
		}
		return user;
	}
	
	
	
}
