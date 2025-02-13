package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    // positive scenario for deleting a product
    @Test
    void testDeleteProductPositive() {
        Product product = new Product();
        product.setProductId("id-1234");
        product.setProductName("Test Product");
        product.setProductQuantity(50);
        productRepository.create(product);

        boolean deleted = productRepository.delete("id-1234");
        assertTrue(deleted, "Product should be successfully deleted");

        // verify product is no longer in the repository
        Iterator<Product> iterator = productRepository.findAll();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            assertNotEquals("id-1234", p.getProductId());
        }
    }

    // negative scenario for deleting a product
    @Test
    void testDeleteProductNegative() {
        boolean deleted = productRepository.delete("non-existent-id");
        assertFalse(deleted, "Deleting a non-existent product should return false");
    }

    // positive scenario for editing a product
    @Test
    void testUpdateProductPositive() {
        Product product = new Product();
        product.setProductId("id-5678");
        product.setProductName("Original Product");
        product.setProductQuantity(30);
        productRepository.create(product);

        //  create updated product with the same ID
        Product updatedProduct = new Product();
        updatedProduct.setProductId("id-5678");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(45);

        Product result = productRepository.update(updatedProduct);
        assertNotNull(result, "Product update should return a non-null product for an existing product");
        assertEquals("id-5678", result.getProductId());
        assertEquals("Updated Product", result.getProductName());
        assertEquals(45, result.getProductQuantity());
    }

    // negative scenario for updating a product
    @Test
    void testUpdateProductNegative() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existent-id");
        updatedProduct.setProductName("Non Existing");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.update(updatedProduct);
        assertNull(result, "Product update should return null when the product does not exist");
    }
}