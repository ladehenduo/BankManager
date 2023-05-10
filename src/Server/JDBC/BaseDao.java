package Server.JDBC;

import Server.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    private static Connection conn;

    static {
        conn = DBCNUnit.getConnection();
    }
    public void addAccount(String user, String password, String ciphertext) throws SQLException {
        String sql = "INSERT INTO account values(?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, user);
        ptmt.setString(2, password);
        ptmt.setString(3, ciphertext);
        ptmt.execute();
    }
    public List<Account> queryAllAccount() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from account");
        Account account = null;
        List<Account> list = new ArrayList<>();
        while(resultSet.next()){
            account = new Account();
            account.setUser(resultSet.getString("account"));
            account.setPassword(resultSet.getString("password"));
            account.setCiphertext(resultSet.getString("ciphertext"));

            list.add(account);
        }
        return list;
    }

//    public static void main(String[] args){
//        try {
//            List<Account> list = queryAllAccount();
//            for(int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).getUser());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
