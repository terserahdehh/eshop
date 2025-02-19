package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    @Mock
    private Model model;

    @Test
    void testCreateProductPage() {
        assertEquals("CreateProduct", controller.createProductPage(model));
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        assertEquals("redirect:/product/list", controller.createProductPost(product, model));
        verify(service).create(product);
    }

    @Test
    void testProductListPage() {
        when(service.findAll()).thenReturn(List.of(new Product()));
        assertEquals("ProductList", controller.productListPage(model));
        verify(model).addAttribute(eq("products"), anyList());
    }

    @Test
    void testDeleteProduct() {
        assertEquals("redirect:/product/list", controller.deleteProduct("123"));
        verify(service).delete("123");
    }

    @Test
    void testEditProductPage_Found() {
        Product product = new Product();
        when(service.findById("123")).thenReturn(product);
        assertEquals("EditProduct", controller.editProductPage("123", model));
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_NotFound() {
        when(service.findById("123")).thenReturn(null);
        assertEquals("redirect:/product/list", controller.editProductPage("123", model));
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        assertEquals("redirect:/product/list", controller.editProductPost(product, model));
        verify(service).update(product);
    }
}
