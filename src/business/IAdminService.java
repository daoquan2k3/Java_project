package business;

import model.Admin;

import java.sql.SQLException;


public interface IAdminService {
    boolean loginAdmin() throws SQLException;
    void displayAllProducts() throws SQLException;
    void addProduct() throws SQLException;
    void updateProduct() throws SQLException;
    void deleteProduct() throws SQLException;
    void searchProductByBrand() throws SQLException;
    void searchProductByPrice() throws SQLException;
    void searchProductByStock() throws SQLException;
    void displayAllCustomers() throws SQLException;
    void addCustomer() throws SQLException;
    void updateCustomer() throws SQLException;
    void deleteCustomerById() throws SQLException;
    void displayInvoice() throws SQLException;
    void addInvoice() throws SQLException;
    void searchInvoiceByCustomerName() throws SQLException;
    void searchInvoiceByDate() throws SQLException;
    void getRevenuePerDay() throws SQLException;
    void getRevenuePerMonth() throws SQLException;
    void getRevenuePerYear() throws SQLException;
}
