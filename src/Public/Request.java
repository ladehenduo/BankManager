package Public;

import java.io.Serializable;
import java.net.InetAddress;

public class Request implements Serializable {
    public static int NO_SERVER = 1; // 服务器未运行或无法连接
    public static int SAVE = 2; // 存款请求
    public static int WITHDRAW = 3; // 取款请求
    public static int BALANCE_NOT_ENOUGH = 4; // 余额不足
    public static int LOGIN = 5;    // 登录请求
    public static int NOT_FOUND_USER = 6;   //不存在的账号
    public static int PASSWORD_ERROR = 7; // 密码错误
    public static int ENROLL = 8;   // 注册请求
    public static int ACCOUNT_EXIST = 9; // 账号已经存在
    public static int TRANSFER = 10; // 转账请求
    public static int TRANSFER_OK = 11; // 转账请求成功
    public static int TRANSFER_FAIL = 12; // 转账请求失败
    public static int ACC = 13;  // 请求处理成功
    public static int NOT_FOUND = 14;
    public static int INFO_NOT_ENOUGH = 15;
    public int r_status = -1;
    public int port;
    public InetAddress address = null;
    Account account = null;

    public int getR_status() {
        return r_status;
    }

    public void setR_status(int r_status) {
        this.r_status = r_status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
