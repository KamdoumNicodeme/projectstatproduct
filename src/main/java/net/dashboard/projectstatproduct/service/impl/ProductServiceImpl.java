package net.dashboard.projectstatproduct.service.impl;


import lombok.extern.slf4j.Slf4j;
import net.dashboard.projectstatproduct.model.Product;
import net.dashboard.projectstatproduct.repository.ProductRepository;
import net.dashboard.projectstatproduct.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for product management.
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    /**
     * Constructs a product service with the necessary repository.
     *
     * @param productRepository The repository used for product operations.
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product in the repository.
     *
     * @param product The product to be created.
     * @return The created product.
     * @throws Exception if the product already exists.
     */
    @Override
    public Product createProduct(Product product) throws Exception {
        log.info("Creating product: {}", product);
        var existingProductOptional = productRepository.findByIdProduct(product.getIdProduct());
        if (existingProductOptional.isPresent()) {
            log.error("Product creation failed: product already exists with ID {}", product.getIdProduct());
            throw new Exception("Product already exists");
        }
        return productRepository.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product The updated product information.
     * @param id      The ID of the product to update.
     * @return The updated product.
     * @throws Exception if the product is not found.
     */
    @Override
    public Product updateProduct(Product product, Long id) throws Exception {
        log.info("Updating product with ID: {}", id);
        var existingProduct = productRepository.findByIdProduct(id)
            .orElseThrow(() -> {
                log.error("Product update failed: Product not found with ID {}", id);
                return new Exception("Product not found");
            });

        // Update and save the product details
        deleteProduct(id);  // Consider if this is really necessary, could lead to data inconsistency
        existingProduct.setIdProduct(product.getIdProduct());
        existingProduct.setProductName(product.getProductName());
        existingProduct.setUnitPrice(product.getUnitPrice());
        existingProduct.setCategoryProduct(product.getCategoryProduct());
        return productRepository.save(existingProduct);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     */
    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.deleteByIdProduct(id);
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return A list of all products.
     */
    @Override
    public List<Product> getAllProducts() {
        log.info("Retrieving all products");
        return productRepository.findAll();
    }
}
