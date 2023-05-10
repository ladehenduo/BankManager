import Server.JDBC.BalanceNotEnoughException;

import java.util.List;

public class Console {
    private List<User> userlist;
    private User currentUser;
    public void Init() {
        // 加载 userlist
    }
    public void Login(String cardNumber, String password) {
        for(int i = 0; i < userlist.size(); i++) {
            if(userlist.get(i).cardNumber.equals(cardNumber)) {
                if(userlist.get(i).password.equals(password)) {
                    currentUser = new User(userlist.get(i));
                    //登陆成功
                }
                else{
                    // 密码错误
                }
                return;
            }
        }
        //无此用户
    }
    public User getCurrentUser(){
        return this.currentUser;
    }
    public void SaveMoney(double money) {
        if(currentUser == null) {
            // throw no user login
            return;
        }
        Treasury.Save(money);
        currentUser.Save(money);
    }
    public void WithDrawMoney(double money) {
        try {
            Treasury.WithDraw(money);
        } catch (BalanceNotEnoughException e) {
            System.out.println("ATM机余额不足，请等待补充...");
            throw new RuntimeException(e);
        }
        try {
            currentUser.WithDraw(money);
        } catch (BalanceNotEnoughException e) {
            System.out.println("账户余额不足：" + currentUser.getBalance());
            Treasury.Save(money);   //如果执行到此处则说明 在金库中已经取出钱，但账户中不可以取，回滚金库的金额
            throw new RuntimeException(e);
        }
    }
}
