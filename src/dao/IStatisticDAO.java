package dao;

import java.sql.SQLException;
import java.time.LocalDate;

public interface IStatisticDAO {
    double getRevenuePerDay(LocalDate date) throws SQLException;

    double getRevenuePerMonth(int month, int year) throws SQLException;

    double getRevenuePerYear(int year) throws SQLException;
}
