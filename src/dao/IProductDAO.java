package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    List<Product> displayAllProducts() throws SQLException;

    boolean addProduct(Product product) throws SQLException;

    Product findProductById(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    boolean deleteProductById(int pId) throws SQLException;

    List<Product> searchProductByBrand(String brand) throws SQLException;

    List<Product> searchProductByPrice(double startPrice, double endPrice) throws SQLException;

    List<Product> searchProductByStock(int stock) throws SQLException;
}
