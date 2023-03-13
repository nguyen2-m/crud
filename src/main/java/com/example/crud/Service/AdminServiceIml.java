package com.example.crud.Service;

import com.example.crud.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AdminServiceIml implements AdminService{
    @Override
    public List<Product> getListProduct() {
        return null;
    }

    @Override
    public Product createProduct(Product req) {
        return null;
    }

    @Override
    public Product updateProduct(Product req, int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
