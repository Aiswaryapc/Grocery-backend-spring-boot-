package com.axis.projectBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.projectBackend.dto.product.ProductDto;
import com.axis.projectBackend.entity.Product;
import com.axis.projectBackend.service.AuthenticationService;
import com.axis.projectBackend.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService; 

    @Autowired
    private AuthenticationService authenticationService;
    
    public ProductController() {
		super();
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// list all the products
    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> product = productService.listProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto,@RequestParam("token") String token) {
    	authenticationService.authenticate(token);
        productService.addProduct(productDto);
        return new ResponseEntity<String>("Product has been added", HttpStatus.CREATED);
    }
}