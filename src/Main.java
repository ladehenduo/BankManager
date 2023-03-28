public class Main {
    public static Console console;
    //数据库
    //IO

    public static void main(String[] args) {
        console = new Console();
        showMainMenu();
        // 1. 初始化：金库余额、存款账户数量、存款账户列表

        // 测试一下啦
        System.out.println("GIT TEST");

        System.out.println("Hello world!");
    }
    public static void showMainMenu() {
        ClearConsoleScreen();
        if(console.getCurrentUser() == null) {
            System.out.println("*****************************************************************************");
            System.out.println("****************              ATM自助银行            ************************");
            System.out.println("********* 1.登录                                                        ******");
            System.out.println("********* 2.注册                                                        ******");
            System.out.println("*****************************************************************************");
            System.out.println("*****************************************************************************");
        }
        else {
            System.out.println("*****************************************************************************");
            System.out.println("****************              ATM自助银行            ************************");
            System.out.println("**************** 1.查询余额                                    ***************");
            System.out.println("**************** 2.查看账户信息                                 ***************");
            System.out.println("**************** 3.存款                                        ***************");
            System.out.println("**************** 4.取款                                        ***************");
            System.out.println("**************** 5.转账                                        ***************");
            System.out.println("**************** 6.贷款申请                                     ***************");
            System.out.println("*****************************************************************************");
        }
        ClearConsoleScreen();
    }
    public static void ClearConsoleScreen() {
        System.out.print("Everything on the console will cleared");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}