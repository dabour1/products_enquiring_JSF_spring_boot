package com.task.products.managedBeans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.products.exceptions.ProductNotFoundException;
import com.task.products.services.ProductService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

@Component(value = "QueryBean")
@Data
@ViewScoped
public class QueryBean implements Serializable {

    @Autowired
    private ProductService productService;

    private String productName;
    private Integer productQuantity;

    public void queryProductQuantity() {
        if (isProductNameValid()) {
            try {
                productQuantity = productService.getProductQuantityByName(productName.trim());
            } catch (ProductNotFoundException e) {
                addMessage(FacesMessage.SEVERITY_WARN, "Product not found.");
                productQuantity = null;
            } catch (Exception e) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Unable to fetch product quantity.");
                productQuantity = null;
            }
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Product name cannot be empty.");
            productQuantity = null;
        }
    }

    private boolean isProductNameValid() {
        return productName != null && !productName.trim().isEmpty();
    }

    /**
     * Adds a message to the FacesContext.
     *
     * @param severity the severity of the message.
     * @param detail   the detail of the message.
     */
    private void addMessage(FacesMessage.Severity severity, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, detail, ""));
    }

}
