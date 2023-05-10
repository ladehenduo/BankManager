package Public;

public class Request {
    public static int SAVE = 1;
    public static int WITHDRAW = 2;
    public static int ENROLL = 3;
    public static int TRANSFER = 4;

    public int r_status = -1;
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