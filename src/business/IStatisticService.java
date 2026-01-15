package business;

import java.sql.SQLException;

public interface IStatisticService {
    void getRevenuePerDay() throws SQLException;
    void getRevenuePerMonth() throws SQLException;
    void getRevenuePerYear() throws SQLException;
}
