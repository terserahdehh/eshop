package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("123");
    }

    @Test
    void testCreate() {
        when(productRepository.create(sampleProduct)).thenReturn(sampleProduct);
        Product createdProduct = productService.create(sampleProduct);
        assertEquals(sampleProduct, createdProduct);
        verify(productRepository).create(sampleProduct);
    }

    @Test
    void testFindAll() {
        Iterator<Product> iterator = Arrays.asList(sampleProduct).iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> products = productService.findAll();
        assertEquals(1, products.size());
        assertEquals(sampleProduct, products.get(0));
    }

    @Test
    void testDelete() {
        when(productRepository.delete("123")).thenReturn(true);
        assertTrue(productService.delete("123"));
        verify(productRepository).delete("123");
    }

    @Test
    void testFindById() {
        when(productRepository.findById("123")).thenReturn(sampleProduct);
        Product foundProduct = productService.findById("123");
        assertEquals(sampleProduct, foundProduct);
    }

    @Test
    void testUpdate() {
        when(productRepository.update(sampleProduct)).thenReturn(sampleProduct);
        Product updatedProduct = productService.update(sampleProduct);
        assertEquals(sampleProduct, updatedProduct);
        verify(productRepository).update(sampleProduct);
    }
}
