package presentation;

import business.IAdminService;
import business.Impl.AdminServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminView {
    public static Scanner sc = new Scanner(System.in);
    public static IAdminService adminService = new AdminServiceImpl();

    public static void startView() throws SQLException {
        while (true) {
            System.out.println("========== HỆ THỐNG QUẢN LÝ CỬA HÀNG ===========");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    signInView();
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn 1 hoặc 2!");
            }
        }
    }

    private static void signInView() throws SQLException {
        while (true) {
            boolean isSuccess = adminService.loginAdmin();
            if (isSuccess) {
                mainView();
                return;
            }
        }
    }

    private static void mainView() throws SQLException {
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

    private static void productManageView() throws SQLException {
        while (true) {
            System.out.println("=============== QUẢN LÝ SẢN PHẨM ===============");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm sản phẩm theo Brand");
            System.out.println("6. Tìm kiếm sản phẩm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Danh sách sản phẩm: ");
                    adminService.displayAllProducts();
                    break;
                case 2:
                    adminService.addProduct();
                    break;
                case 3:
                    adminService.updateProduct();
                    break;
                case 4:
                    adminService.deleteProduct();
                    break;
                case 5:
                    adminService.searchProductByBrand();
                    break;
                case 6:
                    adminService.searchProductByPrice();
                    break;
                case 7:
                    adminService.searchProductByStock();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-8!");
            }
        }
    }

    private static void customerManageView() throws SQLException {
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
                    adminService.displayAllCustomers();
                    break;
                case 2:
                    adminService.addCustomer();
                    break;
                case 3:
                    adminService.updateCustomer();
                    break;
                case 4:
                    System.out.println("Bạn có chắc chắn muốn xóa (Y/N): ");
                    String choice2 = sc.nextLine();
                    if (choice2.equalsIgnoreCase("Y")) {
                        adminService.deleteCustomerById();
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-5!");
            }
        }
    }

    private static void invoiceManageView() throws SQLException {
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
                    adminService.displayInvoice();
                    break;
                case 2:
                    adminService.addInvoice();
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
                    adminService.searchInvoiceByCustomerName();
                    break;
                case 2:
                    adminService.searchInvoiceByDate();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-3!");
            }
        }
    }

    private static void statisticsManageView() throws SQLException {
        while (true) {
            System.out.println("============ THỐNG KÊ DOANH THU =============");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo năm");
            System.out.println("4. Quay lại menu chính");
            System.out.println("=============================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    adminService.getRevenuePerDay();
                    break;
                case 2:
                    adminService.getRevenuePerMonth();
                    break;
                case 3:
                    adminService.getRevenuePerYear();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Vui lòng nhập từ 1-4!");
            }
        }
    }
}
