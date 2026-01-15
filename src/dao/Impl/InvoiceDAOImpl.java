package dao.Impl;

import dao.IInvoiceDAO;
import model.Invoice;
import utils.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements IInvoiceDAO {
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
}
