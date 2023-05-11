package Client;

import Public.Account;
import Public.Request;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClientTools {
    private static InetAddress inetAddress = null; //服务器ip地址
    private static int port = 12345;   // 服务端口
    private static DatagramSocket ds = null;  // 数据包的socket
    private static DatagramPacket datagramPacket = null;

    static {
        try {
            inetAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.out.println("服务器ip地址获取失败（localhost）");
        }
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void transMoney(String obuser, Double money) throws IOException { //向obuser账户存money，从CurrentUser取money
        if(money > 0) {
            if(CurrentUser.account.getBalance() >= money) {
                Account account = new Account();
                account.setUser(obuser);
                account.setBalance(money);
                sendRequest(Request.TRANSFER, account);
                int status = 0;
                try {
                    status = receiveRequest();  //接收回应
                } catch (ClassNotFoundException e) {
                    System.out.println("UDPClientTools.transMoney:" + e.getMessage());
                }
                if(status == Request.TRANSFER_OK) {
                    withdrawMoney(money);
                }
                else {
                    System.out.println("转账失败！请检查收款账户是否正确！");
                }
            }else{
                System.out.println("当前账户余额不足！");
            }
        }else {
            System.out.println("转账金额不能小于0！");
        }
    }
    public static void withdrawMoney(Double money) throws IOException {
        if(money > 0) {
            if(CurrentUser.account.getBalance() >= money) {
                Account account = new Account(CurrentUser.account);
                account.setBalance(money); // 此处可能要改
                sendRequest(Request.WITHDRAW, account);
                int status = 0;
                try {
                    status = receiveRequest();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if(status == Request.ACC) {
                    System.out.println("取款成功！");
                }else{
                    System.out.println("取款失败，请重新操作！");
                }
            }
            else {
                System.out.println("余额不足！");
            }
        } else{
            System.out.println("取款金额不能低于0元");
        }
    }
    public static void saveMoney(Double money) throws IOException {
        if(money > 0) {
            Account account = new Account(CurrentUser.account);
            account.setBalance(money);
            sendRequest(Request.SAVE, account);
            int status = 0;
            try {
                status = receiveRequest();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(status == Request.ACC) {
                System.out.println("存款成功！");
            }else{
                System.out.println("存款失败，请重新操作！");
            }
        } else{
            System.out.println("存款金额不能低于0元");
        }
    }
    public static void sendRequest(int status, Account account) throws IOException {
        Request request = new Request();
        request.r_status = status;
        request.setAccount(account);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 用于序列化请求对象
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(request);
        byte[] sendMessage = baos.toByteArray();  // 发送的数据
        datagramPacket = new DatagramPacket(sendMessage, sendMessage.length, inetAddress, port);
        ds.send(datagramPacket);
    }
    public static int receiveRequest() throws IOException, ClassNotFoundException {
        byte[] receiveMessage = new byte[1024];
        datagramPacket = new DatagramPacket(receiveMessage, receiveMessage.length);
        System.out.println("正在处理....");
        ds.receive(datagramPacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveMessage);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        Request request = (Request) objectInputStream.readObject();
        if(request.r_status == Request.ACC) {
            CurrentUser.account = new Account(request.getAccount());
//            System.out.println("成功！");
            return Request.ACC;
        }
        return request.r_status;
    }
}
