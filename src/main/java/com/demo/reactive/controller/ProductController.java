package com.demo.reactive.controller;

import com.demo.reactive.dto.ProductDto;
import com.demo.reactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Flux<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    public Mono<ProductDto> getProduct(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double minRange,@RequestParam("max") double maxRange) {
        return productService.getProductInRange(minRange,maxRange);
    }

    @PostMapping("/product")
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/updateProduct/{productId}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto,@PathVariable("productId") String productId) {
        return productService.updateProduct(productDto,productId);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public Mono<Void> deleteProduct(@PathVariable("productId") String productId) {
        return productService.deleteProduct(productId);
    }
}
