package com.example.crud.Service;

import com.example.crud.entity.Product;
import com.example.crud.entity.Users;
import com.example.crud.model.dto.UserDto;
import com.example.crud.request.CreateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AdminService {
    public List<Product> getListProduct();

    public Product createProduct(Product req);
    public Product updateProduct(Product req, int id);
    public void deleteProduct(int id);

}
