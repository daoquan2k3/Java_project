package business;

import java.sql.SQLException;

public interface IProductService {
    void displayAllProducts() throws SQLException;
    void addProduct() throws SQLException;
    void updateProduct() throws SQLException;
    void deleteProduct() throws SQLException;
    void searchProductByBrand() throws SQLException;
    void searchProductByPrice() throws SQLException;
    void searchProductByStock() throws SQLException;
}
