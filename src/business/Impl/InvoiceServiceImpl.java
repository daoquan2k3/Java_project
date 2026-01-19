package business.Impl;

import business.IInvoiceService;
import dao.IInvoiceDAO;
import dao.Impl.InvoiceDAOImpl;
import model.Invoice;
import utils.TablePrinter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InvoiceServiceImpl implements IInvoiceService {
    public static Scanner sc  = new Scanner(System.in);
    private static final IInvoiceDAO invoiceDAO = new InvoiceDAOImpl();

    private List<Invoice> invoiceList;

    @Override
    public void displayInvoice() throws SQLException {
        invoiceList = invoiceDAO.displayInvoice();
        printTable();
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
        invoiceList = invoiceDAO.searchInvoiceByCustomerName(customerName);
        if (invoiceList.isEmpty()) {
            System.err.printf("Khách hàng %s chưa mua sản phẩm nào!\n", customerName);
            return;
        }
        printTable();
    }

    @Override
    public void searchInvoiceByDate() throws SQLException {
        LocalDate localDate = null;
        while (true) {
            System.out.print("Nhập ngày tạo đơn hàng (định dạng dd-MM-yyyy): ");
            String input = sc.nextLine();
            try {
                localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                break;
            } catch (DateTimeParseException e) {
                System.err.println("Lỗi: Định dạng ngày không hợp lệ (Ví dụ đúng: 25-12-2025). Hãy thử lại!");
            }
        }

        invoiceList = invoiceDAO.searchInvoiceByDate(localDate);

        if (invoiceList == null || invoiceList.isEmpty()) {
            System.out.println("Không tìm thấy đơn hàng nào trong ngày: " + localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else {
            printTable();
        }
    }

    private void printTable() {
        TablePrinter.printTable(
                "Danh sách Hóa đơn",
                new String[]{"ID", "MÃ KH", "NGÀY TẠO", "TỔNG TIỀN"},
                new int[]{3, 10, 12, 14},
                invoiceList,
                i -> new Object[]{i.getId(), i.getCustomerId(), i.getCreationDate(), String.format("%,.0f", i.getAmount())}
        );
    }
}
