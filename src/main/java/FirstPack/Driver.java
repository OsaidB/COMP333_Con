package FirstPack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {

    public static Connection con;


    //login pack
    public static int checkUser(String username, String password) throws SQLException, SQLException {
        String STR_admins = "SELECT uPassword FROM SystemUsers WHERE Username = '" + username + "'";
        Statement STT_admins = con.createStatement();

        ResultSet RS_admins = STT_admins.executeQuery(STR_admins);

        if (RS_admins.next()) {
            if (RS_admins.getString(1).equals(password))
                return 1;
        }

        String STR_employees = "SELECT uPassword FROM EmployeeUsers WHERE eID = " + username;

        if (isInteger(username)) {

            Statement STT_employees = con.createStatement();
            ResultSet RS_employees = STT_employees.executeQuery(STR_employees);

            if (RS_employees.next()) {
                if (RS_employees.getString(1).equals(password))
                    return 2;
            }

        }

        return 0;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);

        } catch (Exception e) {
            return false;

        }

        return true;
    }

    //login pack

}
