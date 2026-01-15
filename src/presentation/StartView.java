package presentation;

import java.sql.SQLException;
import java.util.Scanner;

import static presentation.LoginView.signInView;

public class StartView {
    public static Scanner sc = new Scanner(System.in);

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
}
