package dao.Impl;

import dao.ICustomerDAO;
import model.Customer;
import utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {
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
}
