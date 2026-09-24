package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
    this.productService = productService;
        }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
    }  

    @GetMapping
    public List<Product> getProducts() {

        return productService.getProducts();
    }
    
    @PostMapping
     public Product createProduct(@Valid @RequestBody ProductRequest productRequest) {

    return productService.createProduct(productRequest);
}
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
        }
    @PutMapping("/{id}")
    public Product updateProduct(
        @PathVariable Long id,
        @Valid @RequestBody ProductRequest productRequest) {

        return productService.updateProduct(id, productRequest);
    }

}

