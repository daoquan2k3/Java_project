package presentation;

import business.ICustomerService;
import business.Impl.CustomerServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerManageView {
    public static Scanner sc = new Scanner(System.in);
    private static final ICustomerService customerService = new CustomerServiceImpl();

    public static void customerManageView() throws SQLException {
        while (true) {
            System.out.println("============== QUẢN LÝ KHÁCH HÀNG =============");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.println("================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Danh sách khách hàng: ");
                    customerService.displayAllCustomers();
                    break;
                case 2:
                    customerService.addCustomer();
                    break;
                case 3:
                    customerService.updateCustomer();
                    break;
                case 4:
                    System.out.println("Bạn có chắc chắn muốn xóa (Y/N): ");
                    String choice2 = sc.nextLine();
                    if (choice2.equalsIgnoreCase("Y")) {
                        customerService.deleteCustomerById();
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-5!");
            }
        }
    }
}
