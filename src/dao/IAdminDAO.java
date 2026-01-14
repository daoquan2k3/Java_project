package dao;

import model.Customer;
import model.Invoice;
import model.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IAdminDAO {
    boolean loginAdmin(String username, String password) throws SQLException;

    List<Product> displayAllProducts() throws SQLException;

    boolean addProduct(Product product) throws SQLException;

    Product findProductById(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    boolean deleteProductById(int pId) throws SQLException;

    List<Product> searchProductByBrand(String brand) throws SQLException;

    List<Product> searchProductByPrice(double startPrice, double endPrice) throws SQLException;

    List<Product> searchProductByStock(int stock) throws SQLException;

    List<Customer> displayAllCustomers() throws SQLException;

    boolean addCustomer(Customer customer) throws SQLException;

    Customer findCustomerById(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    boolean deleteCustomerById(int cusId) throws SQLException;

    List<Invoice> displayInvoice() throws SQLException;

    boolean addInvoice(Invoice invoice) throws SQLException;

    List<Invoice> searchInvoiceByCustomerName(String name) throws SQLException;

    List<Invoice> searchInvoiceByDate(LocalDate date) throws SQLException;

    double getRevenuePerDay(LocalDate date) throws SQLException;

    double getRevenuePerMonth(int month, int year) throws SQLException;

    double getRevenuePerYear(int year) throws SQLException;
}
