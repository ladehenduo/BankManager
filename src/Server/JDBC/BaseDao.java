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
    public void Withdraw(Double money, String account) throws SQLException, BalanceNotEnoughException {
        Double balance;
        Statement stmt = conn.createStatement();
        String sql1 = "SELECT * FROM userinfo WHERE account=?";
        ResultSet rs = stmt.executeQuery(sql1);
        balance = rs.getDouble("balance");
        if(balance < money) {
            System.out.println("余额不足");
            throw new BalanceNotEnoughException(balance - money);
        }
        else {
            String sql = "UPDATE userinfo SET balance=balance-? WHERE account=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setDouble(1, money);
            ptmt.setString(2, account);
            ptmt.execute();
        }

    }

    public void saveMoney(Double money, String account) throws SQLException {
        String sql = "UPDATE userinfo SET balance=balance+? WHERE account=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setDouble(1, money);
        ptmt.setString(2, account);
        ptmt.execute();
    }
//    public static void main(String[] args){
//        BaseDao baseDao = new BaseDao();
//        Account account = new Account();
//        account.setUser("1234");
//        account.setPassword("132465");
//        account.setSex("男");
//        account.setName("李昊");
//        account.setBalance(20.0);
//        account.setIdnumber("12346549798");
//        account.setEmail("16549874212");
//        account.setCiphertext("dasdqw");
//        try {
////            baseDao.addAccount(account);
//            baseDao.Withdraw(5.101, "1234");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
