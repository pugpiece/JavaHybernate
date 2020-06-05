package DAO.Interfaces;

import Entities.Dish;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface IDishDAO {
    void addDish(Dish dish);
    List<Dish> readDishes() throws JsonProcessingException;
    List<Dish> findDishesName(String name) throws JsonProcessingException;
    void deleteDish(int id);
}
