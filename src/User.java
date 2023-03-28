import java.util.Date;

public class User {
    public String name; // 姓名
    public String sex; // 性别
    public String idNumber; // 身份证号
    public String address; // 家庭住址
    public String workAddress; // 工作地点
    public String phoneNumber; // 手机号
    public String cardNumber; // 卡号
    public String password; // 密码 6 位
    public double balance; // 账户余额
    public Date loanTime; // 借款时间
    public Date repaymentDate;  //还款日期
    public double rate; // 贷款年利率
    // test idea git
    public boolean isAdminister;

    public User(User user) { // 拷贝构造
        this.password = user.password;
        this.balance = user.balance;
        this.loanTime = user.loanTime;
        this.repaymentDate = user.repaymentDate;
        this.cardNumber = user.cardNumber;
        this.sex = user.sex;
        this.isAdminister = user.isAdminister;
        this.address = user.address;
        this.idNumber = user.idNumber;
        this.name = user.name;
        this.phoneNumber = user.phoneNumber;
        this.rate = user.rate;
        this.workAddress = user.workAddress;
    }

    public User(String name, String sex, String idNumber, String address, String workAddress, String phoneNumber, String cardNumber, String password) {
        this.isAdminister = false;
        this.loanTime = null;
        this.balance = 0;
        this.repaymentDate = null;
        this.name = name;
        this.sex = sex;
        this.idNumber = idNumber;
        this.address = address;
        this.workAddress = workAddress;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.password = password;
    }
    public static void Grant(User administer, User user) {
        if(administer.isAdminister) {
            user.isAdminister = true;
        }
        else {
            // throw no access
        }
    }
    public void Save(double money) {
        this.balance += money;
    }
    public void WithDraw(double money) throws BalanceNotEnoughException {
        if(this.balance >= money) {
            this.balance -= money;
        }
        else{
            throw new BalanceNotEnoughException(this.balance - money);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isAdminister() {
        return isAdminister;
    }

    public void setAdminister(boolean administer) {
        isAdminister = administer;
    }
}
