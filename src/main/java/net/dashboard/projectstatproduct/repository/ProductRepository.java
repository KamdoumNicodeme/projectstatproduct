package net.dashboard.projectstatproduct.repository;

import net.dashboard.projectstatproduct.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    void deleteByIdProduct(Long id);

   Optional<Product>  findByIdProduct(Long id);
}
