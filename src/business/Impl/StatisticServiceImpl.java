package business.Impl;

import business.IStatisticService;
import dao.IStatisticDAO;
import dao.Impl.StatisticDAOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class StatisticServiceImpl implements IStatisticService {
    public static Scanner sc  = new Scanner(System.in);
    public static final IStatisticDAO statisticDAO = new StatisticDAOImpl();

    @Override
    public void getRevenuePerDay() throws SQLException {
        LocalDate localDate;

        while (true) {
            System.out.print("Nhập ngày muốn thống kê (dd-MM-yyyy): ");
            try {
                localDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("=> Lỗi: Ngày nhập vào không hợp lệ. Hãy thử lại!");
            }
        }

        double total = statisticDAO.getRevenuePerDay(localDate);
        System.out.println("--------------------------------------------------");
        System.out.printf("KẾT QUẢ: Tổng doanh thu ngày %s là: %,.0f VNĐ\n",
                localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), total);
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void getRevenuePerMonth() throws SQLException {
        int month = 0;
        int year = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print("Nhập tháng (1-12): ");
                month = Integer.parseInt(sc.nextLine());
                System.out.print("Nhập năm (yyyy): ");
                year = Integer.parseInt(sc.nextLine());

                if (month < 1 || month > 12) {
                    System.out.println("=> Lỗi: Tháng phải nằm trong khoảng từ 1 đến 12. Hãy thử lại!");
                    continue;
                }
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("=> Lỗi: Định dạng không hợp lệ! Vui lòng chỉ nhập số nguyên.");
            }
        }

        double total = statisticDAO.getRevenuePerMonth(month, year);
        System.out.println("--------------------------------------------------");
        System.out.printf("KẾT QUẢ: Tổng doanh thu tháng %02d/%d là: %,.0f VNĐ\n", month, year, total);
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void getRevenuePerYear() throws SQLException {
        int year = 0;

        while (true) {
            System.out.print("Nhập năm muốn thống kê (yyyy): ");
            try {
                year = Integer.parseInt(sc.nextLine());
                if (year > 0) {
                    break;
                } else {
                    System.out.println("=> Lỗi: Năm phải là số dương. Vui lòng nhập lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("=> Lỗi: Định dạng không hợp lệ! Vui lòng nhập năm bằng số (Ví dụ: 2026).");
            }
        }

        double total = statisticDAO.getRevenuePerYear(year);

        System.out.println("--------------------------------------------------");
        System.out.printf("KẾT QUẢ: Tổng doanh thu năm %d là: %,.0f VNĐ\n", year, total);
        System.out.println("--------------------------------------------------");
    }
}
