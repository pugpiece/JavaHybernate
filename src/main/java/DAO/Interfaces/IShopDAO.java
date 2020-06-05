package DAO.Interfaces;

import Entities.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface IShopDAO {
    void addShop(Shop shop);
    List<Shop> readShops() throws JsonProcessingException;
    List<Shop> findShopsName(String name) throws JsonProcessingException;
    List<Shop> findShopsRating(float rating) throws JsonProcessingException;
    void deleteShop(int id);
}
