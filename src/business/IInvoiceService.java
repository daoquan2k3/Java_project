package business;

import java.sql.SQLException;

public interface IInvoiceService {
    void displayInvoice() throws SQLException;
    void addInvoice() throws SQLException;
    void searchInvoiceByCustomerName() throws SQLException;
    void searchInvoiceByDate() throws SQLException;
}
