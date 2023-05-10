package Cilent;

import Public.Account;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    //数据库
    //IO

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tp = null;
        int op = -1;
        double money;

        while(CurrentUser.isLog == false) { // 如果没有登录就只能选择登录或注册
            UI.showLoginMenu();
            System.out.print("请输入操作："); op = scanner.nextInt();
            if(op == 1) {
                String user = null;
                String password = null;
                while(CurrentUser.isLog == false) {
                    System.out.print("请输入账号（输入-1退出）："); user = scanner.next();
                    if(user.equals("-1")) {
                        break;
                    }
                    System.out.print("请输入密码："); password = scanner.next();
                    try {
                        CurrentUser.Login(user, password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(op == 2) {
                Account account = new Account(UI.showRegisterView());
                // 发送给服务器
            }
            else {
                System.out.println("程序退出");
                System.exit(0);
            }
        }
        UI.showMainMenu();
        while (true) {
            System.out.print("请输入操作："); op = scanner.nextInt();
            if(op == 6) {
                System.out.println("期待您下次使用！");
                break;
            } else if(op == 1) {
                System.out.println("当前余额为：" + CurrentUser.account.getBalance());
            } else if (op == 2) {
                Account.showInfo(CurrentUser.account);
            } else if (op == 3) {
                System.out.print("请输入存款金额："); money = scanner.nextDouble();
                // 存
            } else if (op == 4) {
                System.out.print("请输入存款金额："); money = scanner.nextDouble();
                // 取
            } else if (op == 5) {
                // 转
                System.out.print("请输入接收账户："); tp = scanner.next();
                System.out.print("请输入转款金额："); money = scanner.nextDouble();
            } else {
                System.out.println("操作不合法！");
            }
        }
    }

}