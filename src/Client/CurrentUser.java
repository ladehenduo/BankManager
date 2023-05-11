package Client;
import Public.Account;

import java.io.*;

public class CurrentUser {
    public static Account account = null;
    public static boolean isLog = false;
    public static void Login(String user, String password) throws IOException {
        if(account == null) {
            System.out.println("未找到用户");
        }
        else {
            if(account.getUser().equals(user) && account.getPassword().equals(password)) {
                System.out.println("欢迎，" + account.getName());
                isLog = true;
            }
            else if(account.getUser().equals(user)) {
                System.out.println("密码错误！");
            }
            else{
                System.out.println("账号不存在！");
            }
        }
    }
    public static void Logout(){
        account = null;
        isLog = false;
    }
}
