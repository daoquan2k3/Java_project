package dao;

import model.Invoice;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IInvoiceDAO {
    List<Invoice> displayInvoice() throws SQLException;

    boolean addInvoice(Invoice invoice) throws SQLException;

    List<Invoice> searchInvoiceByCustomerName(String name) throws SQLException;

    List<Invoice> searchInvoiceByDate(LocalDate date) throws SQLException;
}
