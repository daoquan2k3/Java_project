package business.Impl;

import business.IAdminService;
import dao.IAdminDAO;
import dao.Impl.AdminDAOImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminServiceImpl implements IAdminService {
    private final IAdminDAO adminDAO = new AdminDAOImpl();
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
            System.err.println("Sai tên đăng nhập hoặc mật khẩu! Vui lòng nhập lại!");
            return false;
        }
    }
}
