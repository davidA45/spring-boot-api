package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

      private final ProductRepository productRepository;

      public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    
    }



    
    public Product getProductById(Long id) {

        Product product = productRepository.findById(id);
        if (product == null) {
        throw new ProductNotFoundException();
         }

        return product;
     }



    public void deleteProduct(Long id) {
          int rowsDeleted = productRepository.deleteById(id);

        if (rowsDeleted == 0) {
        throw new ProductNotFoundException();
     }    
   } 

    public Product updateProduct(Long id, ProductRequest productRequest) {

    Product product = new Product(
            id,
            productRequest.getName(),
            productRequest.getPrice()
    );

    
    int rowsUpdated = productRepository.update(product);

    if (rowsUpdated == 0) {
        throw new ProductNotFoundException();
    }

    return product;
}



    public Product createProduct(ProductRequest productRequest) {

            Product product = new Product(
            null,
            productRequest.getName(),
            productRequest.getPrice()
        );
        return productRepository.save(product);
    }
    public List<Product> getProducts() {
    return productRepository.findAll();
}

}