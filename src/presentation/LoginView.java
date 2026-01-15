package presentation;

import business.IAdminService;
import business.Impl.AdminServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

import static presentation.AdminView.mainView;

public class LoginView {
    public static Scanner sc = new Scanner(System.in);
    public static IAdminService adminService = new AdminServiceImpl();

    public static void signInView() throws SQLException {
        while (true) {
            boolean isSuccess = adminService.loginAdmin();
            if (isSuccess) {
                mainView();
                return;
            }
        }
    }
}
