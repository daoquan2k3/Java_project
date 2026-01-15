package business;

import java.sql.SQLException;

public interface ICustomerService {
    void displayAllCustomers() throws SQLException;
    void addCustomer() throws SQLException;
    void updateCustomer() throws SQLException;
    void deleteCustomerById() throws SQLException;
}
