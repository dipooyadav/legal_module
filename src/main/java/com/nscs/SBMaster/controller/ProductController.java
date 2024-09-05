package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.Entity.Product;
import com.nscs.SBMaster.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/allproducts")
    public List<Product> getproduct() {
        return productRepository.findAll() ;
    }

    @GetMapping("/allproductsById/{id}")
    public Optional<Product> getproductById(@PathVariable("id") int id) {
        return productRepository.findById(id) ;
    }

    @PostMapping("/updateproductsName")
    public Product updateProducts(@RequestBody Product updatedProduct) {
        int id = updatedProduct.getId(); // Extracting ID from the updated partners object
        Optional<Product> existingProductsOptional = productRepository.findById(id);
        if (existingProductsOptional.isPresent()) {
            Product existingProducts = existingProductsOptional.get();
            existingProducts.setModel(updatedProduct.getModel());
            existingProducts.setDescription(updatedProduct.getDescription());
            return productRepository.save(existingProducts);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("Partners with ID " + id + " not found");
        }
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> productsList) {
        // You may want to add validation logic here before saving
        return productRepository.saveAll(productsList);
    }

}
    /*
    @Autowired
    private ProductService productService;

    @GetMapping("allproduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("findProdectById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null)    {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PostMapping("updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Long id = product.getId(); // Extract ID from the JSON object

        // Update the product using the provided data
        Product updatedProduct = productService.updateProduct(id, product);

        // Check if the product was successfully updated
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct); // Return updated product
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if product not found
        }
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
*/