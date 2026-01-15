package dao;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    List<Customer> displayAllCustomers() throws SQLException;

    boolean addCustomer(Customer customer) throws SQLException;

    Customer findCustomerById(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    boolean deleteCustomerById(int cusId) throws SQLException;
}
