package Client;

import Public.Account;
import Public.Request;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in); //标准输入流，与用户交互
        String tp = null;   // 临时字符串，临时存储读入的字符串
        int op = -1;    // 操作类型，与menu对应
        double money;   // 操作金额，根据场景起不同作用

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
                        Account account = new Account();
                        account.setUser(user);
                        account.setPassword(password);
                        UDPClientTools.sendRequest(Request.LOGIN, account);
                        UDPClientTools.receiveRequest();
                        CurrentUser.Login(user, password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Client\\Main.java, 没有找到类");
                    }
                }
            }
            else if(op == 2) {
                Account account = null;
                int status = -10;
                while(status != Request.ACC) {
                    if(status != -10) System.out.println("注册失败！");
                    account = new Account(UI.showRegisterView());
                    UDPClientTools.sendRequest(Request.ENROLL, account);
                    try {
                        status = UDPClientTools.receiveRequest();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("注册成功！");
                CurrentUser.account = new Account(account);
                CurrentUser.isLog = true;
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
                CurrentUser.Logout();
                break;
            } else if(op == 1) {
                System.out.println("当前余额为：" + CurrentUser.account.getBalance());
            } else if (op == 2) {
                Account.showInfo(CurrentUser.account);
            } else if (op == 3) {
                System.out.print("请输入存款金额："); money = scanner.nextDouble();
                UDPClientTools.saveMoney(money);
            } else if (op == 4) {   //取款操作
                System.out.print("请输入取款金额："); money = scanner.nextDouble();
                UDPClientTools.withdrawMoney(money);
            } else if (op == 5) {
                System.out.print("请输入接收账户："); tp = scanner.next();
                System.out.print("请输入转款金额："); money = scanner.nextDouble();
                UDPClientTools.transMoney(tp, money);
            } else {
                System.out.println("操作不合法！");
            }
        }
    }

}