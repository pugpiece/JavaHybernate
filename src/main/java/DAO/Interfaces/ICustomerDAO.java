package DAO.Interfaces;

import Entities.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collection;
import java.util.List;


public interface ICustomerDAO {
    void addCustomer(Customer customer);
    List<Customer> readCustomers() throws JsonProcessingException;
    List<Customer> findCustomersName(String name) throws JsonProcessingException;
    void deleteCustomer(int id);
    Collection<Customer> loginCheck(String login, String password);
}
