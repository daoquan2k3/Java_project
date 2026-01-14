package dao.Impl;

import dao.IAdminDAO;
import model.Customer;
import model.Invoice;
import model.Product;
import utils.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements IAdminDAO {

    @Override
    public boolean loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{? = call login_admin(?, ?)}");
            callSt.registerOutParameter(1, Types.INTEGER);
            callSt.setString(2, username);
            callSt.setString(3, password);
            callSt.execute();
            int count = callSt.getInt(1);
            result = (count > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return result;
    }

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

    @Override
    public List<Customer> displayAllCustomers() throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> listCustomers = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_all_customer()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                listCustomers = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setPhone(resultSet.getString("phone"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));
                    listCustomers.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listCustomers;
    }

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call add_customer(?,?,?,?)");
            callSt.setString(1, customer.getName());
            callSt.setString(2, customer.getPhone());
            callSt.setString(3, customer.getEmail());
            callSt.setString(4, customer.getAddress());
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
    public Customer findCustomerById(int id) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_customer_by_id(?)}");
            callSt.setInt(1, id);
            boolean hasData = callSt.execute();
            if (hasData) {
                ResultSet resultSet = callSt.getResultSet();
                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhone(resultSet.getString("phone"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return customer;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call update_customer(?,?,?,?,?)");
            callSt.setInt(1, customer.getId());
            callSt.setString(2, customer.getName());
            callSt.setString(3, customer.getAddress());
            callSt.setString(4, customer.getEmail());
            callSt.setString(5, customer.getPhone());
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
    public boolean deleteCustomerById(int cusId) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call delete_customer(?)");
            callSt.setInt(1, cusId);
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
    public List<Invoice> displayInvoice() throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoices = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_all_invoice()}");
            boolean hasData = callSt.execute();
            if (hasData) {
                listInvoices = new ArrayList<>();
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(resultSet.getInt("id"));
                    invoice.setCustomerId(resultSet.getInt("customer_id"));
                    Date invoiceDate = resultSet.getDate("create_at");
                    if (invoiceDate != null) {
                        invoice.setCreationDate(invoiceDate.toLocalDate());
                    }
                    invoice.setAmount(resultSet.getDouble("total_amount"));
                    listInvoices.add(invoice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listInvoices;
    }

    @Override
    public boolean addInvoice(Invoice invoice) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("call add_invoice(?,?)");
            callSt.setInt(1, invoice.getCustomerId());
            callSt.setBigDecimal(2, BigDecimal.valueOf(invoice.getAmount()));
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
    public List<Invoice> searchInvoiceByCustomerName(String name) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoices = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_invoice_by_name(?)}");
            callSt.setString(1, name);
            ResultSet resultSet = callSt.executeQuery();
            listInvoices = new ArrayList<>();

            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                Date invoiceDate = resultSet.getDate("create_at");
                if (invoiceDate != null) {
                    invoice.setCreationDate(invoiceDate.toLocalDate());
                }
                invoice.setAmount(resultSet.getDouble("total_amount"));
                listInvoices.add(invoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listInvoices;
    }

    @Override
    public List<Invoice> searchInvoiceByDate(LocalDate date) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoices = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{call find_invoice_by_date(?)}");
            callSt.setDate(1, Date.valueOf(date));
            ResultSet resultSet = callSt.executeQuery();
            listInvoices = new ArrayList<>();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                Date invoiceDate = resultSet.getDate("create_at");
                if (invoiceDate != null) {
                    invoice.setCreationDate(invoiceDate.toLocalDate());
                }
                invoice.setAmount(resultSet.getDouble("total_amount"));
                listInvoices.add(invoice);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return listInvoices;
    }

    @Override
    public double getRevenuePerDay(LocalDate date) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{?= call get_revenue_by_date(?)}");
            callSt.registerOutParameter(1, Types.NUMERIC);
            callSt.setDate(2, Date.valueOf(date));
            callSt.execute();
            return callSt.getBigDecimal(1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public double getRevenuePerMonth(int month, int year) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{?= call get_revenue_by_month(?, ?)}");
            callSt.registerOutParameter(1, Types.NUMERIC);
            callSt.setInt(2, month);
            callSt.setInt(3, year);
            callSt.execute();
            return callSt.getBigDecimal(1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public double getRevenuePerYear(int year) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{?= call get_revenue_by_year(?)}");
            callSt.registerOutParameter(1, Types.NUMERIC);
            callSt.setInt(2, year);
            callSt.execute();
            return callSt.getBigDecimal(1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return 0;
    }
}
