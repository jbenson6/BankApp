package bankApp;

import java.sql.*;

public class OracleAccess {

    public static Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@mybankapp_high?TNS_ADMIN=D:\\Oracle Wallet", "ADMIN", "CCsabathia52!");
    }

    public static boolean addUser(String firstname, String lastname, String username, String password) throws SQLException, ClassNotFoundException {
        System.out.println("Adding User");
        if (!checkUser(username)) {
            Connection connect = connectDB();
            String query = ("INSERT INTO users (first_name, last_name, user_name, password) "
                    + "VALUES (? , ? , ? , ? )");
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.execute();

            connect.close();
            System.out.println("User Added");
            return true;
        } else {
            System.out.println("User not added");
            return false;
        }

    }

    static boolean checkUser(String username) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt1;
        ResultSet rs;
        String query = "SELECT user_name FROM users where user_name=?";
        Connection connect = connectDB();
        pstmt1 = connect.prepareStatement(query);
        pstmt1.setString(1, username);

        rs = pstmt1.executeQuery();
        if (rs.next()) {
            pstmt1.close();
            rs.close();
            System.out.println("Username already in use");
            connect.close();
            return true;
        } else {
            pstmt1.close();
            rs.close();
            System.out.println("Username not in use");
            connect.close();
            return false;
        }
    }

    public static boolean signIn(String username, String password) throws SQLException, ClassNotFoundException {
        return checkUser(username,password);
    }

    private static boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt1;
        ResultSet rs;
        String query = "SELECT user_name, password FROM users where user_name=?";
        Connection connect = connectDB();
        pstmt1 = connect.prepareStatement(query);
        pstmt1.setString(1, username);

        rs = pstmt1.executeQuery();
        if (rs.next()) {
            if (password.equals(rs.getString("password"))) {
                pstmt1.close();
                rs.close();
                connect.close();
                System.out.println("Username and Password Matches, Logging In");
                return true;
            } else {
                pstmt1.close();
                rs.close();
                connect.close();
                System.out.println("Incorrect Password ");
                return false;
            }
        } else {
            pstmt1.close();
            rs.close();
            connect.close();
            System.out.println("Username does not exist");
            return false;
        }
    }

    public static User getUser(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users where user_name=?";
        Connection connect = connectDB();
        PreparedStatement pstmt = connect.prepareStatement(query);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User(
                rs.getString("user_name"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("password")
        );
        //rs.close();
        pstmt.close();
        connect.close();
        return user;
    }
}
