package Cilent;

import Public.Account;
import Server.JDBC.BaseDao;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class CurrentUser {
    public static Account account = null;
    public static boolean isLog = false;
    public static void Login(String user, String password) throws IOException {

        List<Account> list = null;
        try {
            list = BaseDao.queryAllAccount();
        } catch (SQLException e) {
            System.out.println("异常：CurrentUser/Login/ error");
            e.printStackTrace();
        }
        if(list != null) {
            boolean flag = false;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getUser().equals(user)){
                    flag = true;
                    if(list.get(i).getPassword().equals(password)) {
//                        System.out.println("------------");
//                        Account.showInfo(list.get(i));
                        account = new Account(list.get(i));
                        isLog = true;
                    }
                    else{
                        System.out.println("密码错误！");
                    }
                }
            }
            if(!flag) System.out.println("账号不存在！");
        }else{
            //登陆失败
        }
    }
    public static void Logout(){
        account = null;
        isLog = false;
    }
}
