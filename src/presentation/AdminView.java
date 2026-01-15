package presentation;

import java.sql.SQLException;
import java.util.Scanner;

import static presentation.CustomerManageView.customerManageView;
import static presentation.InvoiceManageView.invoiceManageView;
import static presentation.ProductManageView.productManageView;
import static presentation.StatisticManageView.statisticsManageView;

public class AdminView {
    public static Scanner sc = new Scanner(System.in);

    public static void mainView() throws SQLException {
        while (true) {
            System.out.println("================= MENU CHÍNH ==================");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("===============================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    productManageView();
                    break;
                case 2:
                    customerManageView();
                    break;
                case 3:
                    invoiceManageView();
                    break;
                case 4:
                    statisticsManageView();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-5!");
            }
        }
    }
}
