package dao.Impl;

import dao.IAdminDAO;
import utils.DBUtil;
import java.sql.*;

public class AdminDAOImpl implements IAdminDAO {

    @Override
    public boolean loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = DBUtil.openConnection();
            callSt = conn.prepareCall("{? = call login_admin(?, ?)}");
            callSt.registerOutParameter(1, Types.INTEGER);
            callSt.setString(2, username);
            callSt.setString(3, password);
            callSt.execute();
            int count = callSt.getInt(1);
            result = (count > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callSt);
        }
        return result;
    }
}
