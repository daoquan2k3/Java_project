package dao.Impl;

import dao.IStatisticDAO;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;

public class StatisticDAOImpl implements IStatisticDAO {
    @Override
    public double getRevenuePerDay(LocalDate date) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{?= call get_revenue_by_date(?)}");
            callSt.registerOutParameter(1, Types.NUMERIC);
            callSt.setDate(2, Date.valueOf(date));
            callSt.execute();
            return callSt.getBigDecimal(1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public double getRevenuePerMonth(int month, int year) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{?= call get_revenue_by_month(?, ?)}");
            callSt.registerOutParameter(1, Types.NUMERIC);
            callSt.setInt(2, month);
            callSt.setInt(3, year);
            callSt.execute();
            return callSt.getBigDecimal(1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public double getRevenuePerYear(int year) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{?= call get_revenue_by_year(?)}");
            callSt.registerOutParameter(1, Types.NUMERIC);
            callSt.setInt(2, year);
            callSt.execute();
            return callSt.getBigDecimal(1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return 0;
    }
}
