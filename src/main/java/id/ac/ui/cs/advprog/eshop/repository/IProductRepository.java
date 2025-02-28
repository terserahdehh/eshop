package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface IProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    boolean delete(String productId);
    Product findById(String productId);
    Product update(Product product);
}
