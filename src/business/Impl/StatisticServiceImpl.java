package business.Impl;

import business.IStatisticService;
import dao.IStatisticDAO;
import dao.Impl.StatisticDAOImpl;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StatisticServiceImpl implements IStatisticService {
    public static Scanner sc  = new Scanner(System.in);
    public static final IStatisticDAO statisticDAO = new StatisticDAOImpl();

    @Override
    public void getRevenuePerDay() throws SQLException {
        System.out.println("Nhập ngày muốn thống kê (định dạng dd-MM-yyyy): ");
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            double total = statisticDAO.getRevenuePerDay(localDate);
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
            double total = statisticDAO.getRevenuePerMonth(month, year);

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
            double total = statisticDAO.getRevenuePerYear(year);

            System.out.printf("==> Tổng doanh thu năm %d là: %,.2f VNĐ\n", year, total);

        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Vui lòng nhập năm bằng số (Ví dụ: 2025)!");
        }
    }
}
