package business.Impl;

import business.ICustomerService;
import dao.Impl.CustomerDAOImpl;
import model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerServiceImpl implements ICustomerService {
    public static Scanner sc = new Scanner(System.in);
    public static final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @Override
    public void displayAllCustomers() throws SQLException {
        List<Customer> customerList = customerDAO.displayAllCustomers();
        customerList.forEach(System.out::println);
    }

    @Override
    public void addCustomer() throws SQLException {
        Customer customer = new Customer();
        customer.inputData(sc);
        boolean result = customerDAO.addCustomer(customer);
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
        Customer customer = customerDAO.findCustomerById(customerId);
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
        boolean result = customerDAO.updateCustomer(customer);
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
        Customer customer = customerDAO.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Mã khách hàng không tồn tại!");
            return;
        }
        boolean result = customerDAO.deleteCustomerById(customerId);
        if (result) {
            System.out.println("Xóa khách hàng thành công!");
        } else {
            System.err.println("Có lỗi trong quá trình xóa");
        }
    }
}
