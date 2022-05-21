package baith2.repository;

import baith2.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,String> {
    Product findByCode(String code);
    void deleteByCode(String code);
}
