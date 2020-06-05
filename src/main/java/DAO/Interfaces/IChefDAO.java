package DAO.Interfaces;

import Entities.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collection;
import java.util.List;


public interface IChefDAO {
    void addChef(Chef chef);
    List<Chef> readChefs() throws JsonProcessingException;
    List<Chef> findChefsName(String name) throws JsonProcessingException;
    List<Chef> findChefsRating(float rating) throws JsonProcessingException;
    void deleteChef(int id);
    Collection<Chef> loginCheck(String login, String password);
}
