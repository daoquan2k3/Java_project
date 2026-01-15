package presentation;

import business.IInvoiceService;
import business.Impl.InvoiceServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class InvoiceManageView {
    public static Scanner sc = new Scanner(System.in);
    private static final IInvoiceService invoiceService = new InvoiceServiceImpl();

    public static void invoiceManageView() throws SQLException {
        while (true) {
            System.out.println("============= QUẢN LÝ HÓA ĐƠN =============");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("===========================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Danh sách hóa đơn");
                    invoiceService.displayInvoice();
                    break;
                case 2:
                    invoiceService.addInvoice();
                    break;
                case 3:
                    searchInvoiceView();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-4!");
            }
        }
    }

    private static void searchInvoiceView() throws SQLException {
        while (true) {
            System.out.println("============== MENU SEARCH ===============");
            System.out.println("1. Tìm theo tên khách hàng");
            System.out.println("2. Tìm theo ngày/tháng/năm");
            System.out.println("3. Quay lại menu hóa đơn");
            System.out.println("==========================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    invoiceService.searchInvoiceByCustomerName();
                    break;
                case 2:
                    invoiceService.searchInvoiceByDate();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-3!");
            }
        }
    }
}
