package Server.JDBC;

import Public.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    private static Connection conn;

    static {
        conn = DBCNUnit.getConnection();
    }
    public static void addAccount(Account account) throws SQLException {
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

    public static Account queryAccount(String user) {
        List<Account> list = null;
        Account account = null;
        try {
            list = queryAllAccount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser().equals(user)) {
                account = new Account(list.get(i));
                break;
            }
        }
        return account;
    }
    public static List<Account> queryAllAccount() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from account, userinfo where account.account=userinfo.account");
        Account account = null;
        List<Account> list = new ArrayList<>();
        while(resultSet.next()){
            account = new Account();
            account.setUser(resultSet.getString("account"));
            account.setPassword(resultSet.getString("password"));
            account.setCiphertext(resultSet.getString("ciphertext"));
            account.setName(resultSet.getString("name"));
            account.setSex(resultSet.getString("sex"));
            account.setIdnumber(resultSet.getString("idnumber"));
            account.setEmail(resultSet.getString("email"));
            account.setBalance(resultSet.getDouble("balance"));
            list.add(account);
        }
        return list;
    }
    public static void Withdraw(Double money, String account) throws SQLException, BalanceNotEnoughException {
        String sql = "UPDATE userinfo SET balance=balance-? WHERE account=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setDouble(1, money);
        ptmt.setString(2, account);
        ptmt.execute();
    }

    public static void saveMoney(Double money, String account) throws SQLException {
        String sql = "UPDATE userinfo SET balance=balance+? WHERE account=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setDouble(1, money);
        ptmt.setString(2, account);
        ptmt.execute();
    }
    public static void main(String[] args) throws InterruptedException {
//        try {
//            saveMoney(100.0, "123");
//            System.out.println("成功！");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
