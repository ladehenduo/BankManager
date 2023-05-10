package Public;

import java.io.Serializable;

public class Account implements Serializable {
    private String user;
    private String password;
    private String ciphertext;

    private String name;
    private String sex;
    private String idnumber;
    private String email;
    private Double balance;

    public static void showInfo(Account account) {
        System.out.println("姓名：" + account.getName());
        System.out.println("性别：" + account.getSex());
        System.out.println("身份证号：" + account.getIdnumber());
        System.out.println("邮箱：" + account.getEmail());
        System.out.println("账号：" + account.getUser());
        System.out.println("余额：" + account.getBalance());
    }
    public Account() {
    }
    public Account(Account account) {
        this.user = account.getUser();
        this.password = account.getPassword();
        this.ciphertext = account.getCiphertext();
        this.name = account.getName();
        this.sex = account.getSex();
        this.idnumber = account.getIdnumber();
        this.email = account.getEmail();
        this.balance = account.getBalance();
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

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }
}
