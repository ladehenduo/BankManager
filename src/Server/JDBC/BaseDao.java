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
    public void addAccount(Account account) throws SQLException {
        String sql = "INSERT INTO account values(?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, account.getUser());
        ptmt.setString(2, account.getPassword());
        ptmt.setString(3, account.getCiphertext());
        ptmt.execute();
        String sql1 = "INSERT INTO userinfo values(?,?,?,?,?,?)";
        PreparedStatement ptmt1 = conn.prepareStatement(sql1);
        ptmt1.setString(1, account.getUser());
        ptmt1.setString(2, account.getName());
        ptmt1.setString(3, account.getSex());
        ptmt1.setString(4, account.getIdnumber());
        ptmt1.setString(5, account.getEmail());
        ptmt1.setDouble(6, account.getBalance());
        ptmt1.execute();
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
    public void Withdraw(double money, String account) throws SQLException {
        String sql = "UPDATE account SET balance=balance-? WHICH account=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setDouble(1, money);
        ptmt.setString(2, account);
        ptmt.execute();
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
