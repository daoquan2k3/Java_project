package business.Impl;

import business.IInvoiceService;
import dao.IInvoiceDAO;
import dao.Impl.InvoiceDAOImpl;
import model.Invoice;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InvoiceServiceImpl implements IInvoiceService {
    public static Scanner sc  = new Scanner(System.in);
    private static final IInvoiceDAO invoiceDAO = new InvoiceDAOImpl();

    @Override
    public void displayInvoice() throws SQLException {
        List<Invoice> invoiceList = invoiceDAO.displayInvoice();
        invoiceList.forEach(System.out::println);
    }

    @Override
    public void addInvoice() throws SQLException {
        Invoice invoice = new Invoice();
        invoice.inputData(sc);
        boolean result = invoiceDAO.addInvoice(invoice);
        if (result) {
            System.out.println("Thêm đơn hành thành công!");
        } else {
            System.err.println("Có lỗi trong quá trình tạo đơn hàng!");
        }
    }

    @Override
    public void searchInvoiceByCustomerName() throws SQLException {
        System.out.println("Nhập vào tên khách hàng: ");
        String customerName = sc.nextLine();
        List<Invoice> invoiceList = invoiceDAO.searchInvoiceByCustomerName(customerName);
        if (invoiceList.isEmpty()) {
            System.err.printf("Khách hàng %s chưa mua sản phẩm nào!\n", customerName);
            return;
        }
        invoiceList.forEach(System.out::println);
    }

    @Override
    public void searchInvoiceByDate() throws SQLException {
        System.out.println("Nhập ngày tạo đơn hàng: ");
        LocalDate localDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Invoice> invoiceList = invoiceDAO.searchInvoiceByDate(localDate);
        if (invoiceList.isEmpty()) {
            System.err.println("Ngày tạo không đúng hoặc không có đơn hàng trong ngày này!");
            return;
        }
        invoiceList.forEach(System.out::println);
    }
}
