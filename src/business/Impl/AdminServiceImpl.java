package business.Impl;

import business.IAdminService;
import dao.IAdminDAO;
import dao.Impl.AdminDAOImpl;
import model.Customer;
import model.Invoice;
import model.Product;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AdminServiceImpl implements IAdminService {
    private IAdminDAO adminDAO = new AdminDAOImpl();
    public static Scanner sc = new Scanner(System.in);

    @Override
    public boolean loginAdmin() throws SQLException {
        System.out.println("========== ĐĂNG NHẬP HỆ THỐNG ==========");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        boolean isSuccess = adminDAO.loginAdmin(username, password);
        if (isSuccess) {
            System.out.println("Đăng nhập thành công!");
            return true;
        } else {
            System.err.println("Sai tên đăng nhập hoặc mật khẩu!");
            return false;
        }
    }

    @Override
    public void displayAllProducts() throws SQLException {
        List<Product> productList = adminDAO.displayAllProducts();
        productList.forEach(System.out::println);
    }

    @Override
    public void addProduct() throws SQLException {
        Product product = new Product();
        product.inputData(sc);
        boolean result = adminDAO.addProduct(product);
        if (result) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.err.println("Có lỗi xuất hiện trong quá trình thêm mới!");
        }
    }

    @Override
    public void updateProduct() throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật: ");
        int productId = Integer.parseInt(sc.nextLine());
        Product product = adminDAO.findProductById(productId);
        if (product == null) {
            System.err.println("Mã sản phẩm không tồn tại!");
            return;
        }
        boolean isExist = true;
        while (isExist) {
            System.out.println("1. Cập nhật tên sản phẩm");
            System.out.println("2. Cập nhật nhãn hàng");
            System.out.println("3. Cập nhật giá sản phẩm");
            System.out.println("4. Cập nhật số lượng sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập vào tên sản phẩm mới: ");
                    product.setName(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Nhập vào nhãn hàng mới: ");
                    product.setBrand(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Nhập vào giá mới: ");
                    product.setPrice(Double.parseDouble(sc.nextLine()));
                    break;
                case 4:
                    System.out.println("Nhập vào số lượng mới: ");
                    product.setStock(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5!");
            }
        }
        boolean result = adminDAO.updateProduct(product);
        if (result) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.err.println("Có lỗi trong quá trình cập nhật!");
        }
    }

    @Override
    public void deleteProduct() throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần xóa: ");
        int productId = Integer.parseInt(sc.nextLine());
        Product product = adminDAO.findProductById(productId);
        if (product == null) {
            System.out.println("Mã sản phẩm không tồn tại!");
            return;
        }
        boolean result = adminDAO.deleteProductById(productId);
        if (result) {
            System.out.println("Xóa thành công");
        } else {
            System.err.println("Có lỗi trong quá trình xóa!");
        }
    }

    @Override
    public void searchProductByBrand() throws SQLException {
        System.out.println("Nhập vào nhãn hàng cần tìm: ");
        String brand = sc.nextLine();
        List<Product> productList = adminDAO.searchProductByBrand(brand);
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào thuộc nhãn hàng: " + brand);
            return;
        }
        productList.forEach(System.out::println);
    }

    @Override
    public void searchProductByPrice() throws SQLException {
        System.out.println("Nhập vào khoảng giá cần tìm");
        System.out.println("Giá khởi điểm: ");
        double startPrice = Double.parseDouble(sc.nextLine());
        System.out.println("Giá kết thúc: ");
        double endPrice = Double.parseDouble(sc.nextLine());
        List<Product> productList = adminDAO.searchProductByPrice(startPrice, endPrice);
        if (productList.isEmpty()) {
            System.err.println("Không có sản phẩm nào trong khoảng giá này!");
            return;
        }
        productList.forEach(System.out::println);
    }

    @Override
    public void searchProductByStock() throws SQLException {
        System.out.println("Nhập vào số lượng sản phẩm: ");
        int productStock = Integer.parseInt(sc.nextLine());
        List<Product> productList = adminDAO.searchProductByStock(productStock);
        if (productList.isEmpty()) {
            System.err.printf("Không có sản phẩm nào còn dư %d sản phẩm\n", productStock);
            return;
        }
        productList.forEach(System.out::println);
    }

    @Override
    public void displayAllCustomers() throws SQLException {
        List<Customer> customerList = adminDAO.displayAllCustomers();
        customerList.forEach(System.out::println);
    }

    @Override
    public void addCustomer() throws SQLException {
        Customer customer = new Customer();
        customer.inputData(sc);
        boolean result = adminDAO.addCustomer(customer);
        if (result) {
            System.out.println("Thêm khách hàng thành công!");
        } else {
            System.err.println("Có lỗi khi thêm khách hàng!");
        }
    }

    @Override
    public void updateCustomer() throws SQLException {
        System.out.println("Nhập vào mã khách hàng cần cập nhật: ");
        int customerId = Integer.parseInt(sc.nextLine());
        Customer customer = adminDAO.findCustomerById(customerId);
        if (customer == null) {
            System.err.println("Không tìm thấy sản phẩm nào!");
            return;
        }
        boolean isExist = true;
        while (isExist) {
            System.out.println("1. Cập nhật tên khách hàng");
            System.out.println("2. Cập nhật số điện thoại khách hàng");
            System.out.println("3. Cập nhật email khách hàng");
            System.out.println("4. Cập nhật địa chỉ khách hàng");
            System.out.println("5. Thoát");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập vào tên khách hàng mới: ");
                    customer.setName(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Nhập vào số điện thoại mới: ");
                    customer.setPhone(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Nhập vào địa chỉ email mới: ");
                    customer.setEmail(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Nhập vào địa chỉ mới: ");
                    customer.setAddress(sc.nextLine());
                    break;
                case 5:
                    isExist = false;
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-5!");
            }
        }
        boolean result = adminDAO.updateCustomer(customer);
        if (result) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.err.println("Có lỗi trong quá trình cập nhật!");
        }
    }

    @Override
    public void deleteCustomerById() throws SQLException {
        System.out.println("Nhập vào khách hàng cần xóa: ");
        int customerId = Integer.parseInt(sc.nextLine());
        Customer customer = adminDAO.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Mã khách hàng không tồn tại!");
            return;
        }
        boolean result = adminDAO.deleteCustomerById(customerId);
        if (result) {
            System.out.println("Xóa khách hàng thành công!");
        } else {
            System.err.println("Có lỗi trong quá trình xóa");
        }
    }

    @Override
    public void displayInvoice() throws SQLException {
        List<Invoice> invoiceList = adminDAO.displayInvoice();
        invoiceList.forEach(System.out::println);
    }

    @Override
    public void addInvoice() throws SQLException {
        Invoice invoice = new Invoice();
        invoice.inputData(sc);
        boolean result = adminDAO.addInvoice(invoice);
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
        List<Invoice> invoiceList = adminDAO.searchInvoiceByCustomerName(customerName);
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
        List<Invoice> invoiceList = adminDAO.searchInvoiceByDate(localDate);
        if (invoiceList.isEmpty()) {
            System.err.println("Ngày tạo không đúng hoặc không có đơn hàng trong ngày này!");
            return;
        }
        invoiceList.forEach(System.out::println);
    }

    @Override
    public void getRevenuePerDay() throws SQLException {
        System.out.println("Nhập ngày muốn thống kê (định dạng dd-MM-yyyy): ");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            double total = adminDAO.getRevenuePerDay(localDate);
            System.out.printf("==> Tổng doanh thu ngày %s là: %,.2f VNĐ\n", localDate, total);
        } catch (DateTimeException e) {
            System.err.println("Định dạng ngày không đúng! Vui lòng nhập lại theo mẫu!");
        }
    }

    @Override
    public void getRevenuePerMonth() throws SQLException {
        try {
            System.out.print("Nhập tháng (1-12): ");
            int month = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập năm (yyyy): ");
            int year = Integer.parseInt(sc.nextLine());
            if (month < 1 || month > 12) {
                System.err.println("Tháng không hợp lệ! Vui lòng nhập từ 1 đến 12.");
                return;
            }
            double total = adminDAO.getRevenuePerMonth(month, year);

            System.out.printf("==> Tổng doanh thu tháng %02d/%d là: %,.2f VNĐ\n", month, year, total);

        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Vui lòng chỉ nhập số nguyên!");
        }
    }

    @Override
    public void getRevenuePerYear() throws SQLException {
        try {
            System.out.print("Nhập năm muốn thống kê (yyyy): ");
            int year = Integer.parseInt(sc.nextLine());
            double total = adminDAO.getRevenuePerYear(year);

            System.out.printf("==> Tổng doanh thu năm %d là: %,.2f VNĐ\n", year, total);

        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Vui lòng nhập năm bằng số (Ví dụ: 2025)!");
        }
    }
}
