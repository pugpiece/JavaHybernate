package DAO.Interfaces;

import Entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface IProductDAO {
    void addProduct(Product product);
    List<Product> readProducts() throws JsonProcessingException;
    List<Product> findProductsName(String name) throws JsonProcessingException;
    List<Product> findProductsCalories(float calories) throws JsonProcessingException;
    void deleteProduct(int id);
}
