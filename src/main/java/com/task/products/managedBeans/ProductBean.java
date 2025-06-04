package com.task.products.managedBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.task.products.models.Product;
import com.task.products.services.ProductService;

import jakarta.faces.view.ViewScoped;
import lombok.Data;

// import java.io.Serializable;
import java.util.List;

@Component(value = "ProductBean")
@Data
@ViewScoped
public class ProductBean {

    @Autowired
    private ProductService productService;

    private List<Product> products;
    private Product newProduct = new Product();

    public List<Product> getProducts() {
        if (products == null) {
            products = productService.getAllProducts();
        }
        return products;
    }

    public String addProduct() {

        if (newProduct != null) {
            productService.saveProduct(newProduct);
            products = productService.getAllProducts();
            newProduct = new Product();
        }
        return "products.xhtml?faces-redirect=true";
    }

    public String deleteProduct(Long id) {
        if (id != null) {
            productService.deleteProduct(id);
            products = productService.getAllProducts();
        }
        return null;
    }
}
