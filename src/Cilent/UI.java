package Cilent;

import Public.Account;

import java.util.Scanner;

public class UI {
    public static void showLoginMenu() {
        System.out.println("*****************************************************************************");
        System.out.println("****************              ATM自助银行            ************************");
        System.out.println("********* 1.登录                                                        ******");
        System.out.println("********* 2.注册                                                        ******");
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
    }
    public static Account showRegisterView() {
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        String stp = null;
        System.out.print("请输入姓名："); stp = scanner.next();
        account.setName(stp);
        System.out.print("请输入性别："); stp = scanner.next();
        account.setSex(stp);
        System.out.print("请输入身份证号："); stp = scanner.next();
        account.setIdnumber(stp);
        System.out.print("请输入邮箱："); stp = scanner.next();
        account.setEmail(stp);
        System.out.print("请输入账号："); stp = scanner.next();
        account.setUser(stp);
        System.out.print("请输入密码："); stp = scanner.next();
        account.setPassword(stp);
        account.setBalance(0.0);
        System.out.println("注册成功！");

        return account;
    }
    public static void showMainMenu() {
        System.out.println("*****************************************************************************");
        System.out.println("****************              ATM自助银行            ************************");
        System.out.println("**************** 1.查询余额                                    ***************");
        System.out.println("**************** 2.查看账户信息                                 ***************");
        System.out.println("**************** 3.存款                                        ***************");
        System.out.println("**************** 4.取款                                        ***************");
        System.out.println("**************** 5.转账                                        ***************");
        System.out.println("**************** 6.退出                                        ***************");
        System.out.println("*****************************************************************************");
    }
}
