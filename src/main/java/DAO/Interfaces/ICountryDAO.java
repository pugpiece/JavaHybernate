package DAO.Interfaces;

import Entities.Country;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface ICountryDAO {
    void addCountry(Country country);
    List<Country> readCountries() throws JsonProcessingException;
    List<Country> findCountriesName(String name) throws JsonProcessingException;
    void deleteCountry(int id);
}
