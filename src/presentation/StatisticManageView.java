package presentation;

import business.IStatisticService;
import business.Impl.StatisticServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class StatisticManageView {
    public static Scanner sc = new Scanner(System.in);
    private static final IStatisticService statisticService = new StatisticServiceImpl();

    public static void statisticsManageView() throws SQLException {
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
                    statisticService.getRevenuePerDay();
                    break;
                case 2:
                    statisticService.getRevenuePerMonth();
                    break;
                case 3:
                    statisticService.getRevenuePerYear();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Vui lòng nhập từ 1-4!");
            }
        }
    }
}
