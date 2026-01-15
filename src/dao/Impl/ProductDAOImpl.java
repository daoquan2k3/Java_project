package dao.Impl;

import dao.IProductDAO;
import model.Product;
import utils.DBUtil;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    @Override
    public List<Product> displayAllProducts() throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_all_product()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                listProducts = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setBrand(resultSet.getString("brand"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setStock(resultSet.getInt("stock"));
                    listProducts.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public boolean addProduct(Product product) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call add_product(?,?,?,?)");
            callSt.setString(1, product.getName());
            callSt.setString(2, product.getBrand());
            callSt.setBigDecimal(3, BigDecimal.valueOf(product.getPrice()));
            callSt.setInt(4, product.getStock());
            callSt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Product findProductById(int id) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, id);
            boolean result = callSt.execute();
            if (result) {
                ResultSet resultSet = callSt.getResultSet();
                if(resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setBrand(resultSet.getString("brand"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setStock(resultSet.getInt("stock"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call update_product(?,?,?,?,?)");
            callSt.setInt(1, product.getId());
            callSt.setString(2, product.getName());
            callSt.setString(3, product.getBrand());
            callSt.setBigDecimal(4, BigDecimal.valueOf(product.getPrice()));
            callSt.setInt(5, product.getStock());
            callSt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean deleteProductById(int pId) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call delete_product(?)");
            callSt.setInt(1, pId);
            callSt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Product> searchProductByBrand(String brand) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_product_by_brand(?)}");
            callSt.setString(1, brand);
            boolean hasData = callSt.execute();
            if (hasData) {
                listProducts = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setBrand(resultSet.getString("brand"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setStock(resultSet.getInt("stock"));
                    listProducts.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }

    @Override
    public List<Product> searchProductByPrice(double startPrice, double endPrice) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_product_by_price(?,?)}");
            callSt.setBigDecimal(1, BigDecimal.valueOf(startPrice));
            callSt.setBigDecimal(2, BigDecimal.valueOf(endPrice));
            boolean hasData = callSt.execute();
            if (hasData) {
                listProducts = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setBrand(resultSet.getString("brand"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setStock(resultSet.getInt("stock"));
                    listProducts.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public List<Product> searchProductByStock(int stock) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_product_by_stock(?)}");
            callSt.setInt(1, stock);
            boolean hasData = callSt.execute();
            if (hasData) {
                listProducts = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setBrand(resultSet.getString("brand"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setStock(resultSet.getInt("stock"));
                    listProducts.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listProducts;
    }
}
