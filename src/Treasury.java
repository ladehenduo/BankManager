public class Treasury {
    private static double balance; // 金库余额
    private static int accountNumber; // 使用该金库的账户数量，不包括管理员，只算往里面存款的人

    private static int debtorNumber; // 欠款人数量

    // 使用者列表

    // 欠款者列表

    // 往金库存款
    public static boolean Save(double money) {
        balance += money;
        System.out.println("成功存储" + money + "元");
        return true;
    }
    // 从金库取款
    public static boolean WithDraw(double money) throws BalanceNotEnoughException {
        if(balance >= money) { // 判断余额与取款量
            balance -= money;
            System.out.println("取走：" + money + "元");
            System.out.println("库存：" + balance + "元");
            return true;
        }
        else {
            throw new BalanceNotEnoughException(money - balance);   //抛出余额不足异常
        }
    }
    // 获取余额
    public static double getBalance() {
        return balance;
    }
    // 获取使用者数量
    public static int getAccountNumber(){
        return accountNumber;
    }
    // 打印信息
    public static void Print() {
        double sum = 0; //计算sum
        System.out.println("金库余额：" + balance + "    使用者：" + accountNumber + "人" + "    欠款：" + debtorNumber + "人"
        + "    应还金额：" + sum + "元");
    }
}
