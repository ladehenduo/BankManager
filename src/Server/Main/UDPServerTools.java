package Server.Main;

import Public.Account;
import Public.Request;
import Server.JDBC.BalanceNotEnoughException;
import Server.JDBC.BaseDao;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.SQLException;

public class UDPServerTools {
    private static DatagramSocket ds = null;

    static {
        try {
            ds = new DatagramSocket(12345);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static Request receiveRequest() throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        ds.receive(datagramPacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Request request = (Request) ois.readObject();
        request.port = datagramPacket.getPort();
        request.address = datagramPacket.getAddress();
        System.out.println("接收到用户：" + request.getAccount().getUser() + " 的请求");
        return request;
    }
    public static Request dealRequest(Request request) {
        Account tp = request.getAccount();
        System.out.println("正在处理用户：" + tp.getUser() + " 的请求");
        if(request.r_status == Request.LOGIN) {
            Account account = BaseDao.queryAccount(tp.getUser());
            if(account == null) {
                request.r_status = Request.NOT_FOUND;
            }else if(tp.getPassword().equals(account.getPassword())){
                request.r_status = Request.ACC;
                request.setAccount(account);
            } else{
                request.r_status = Request.PASSWORD_ERROR;
            }
        } else if (request.r_status == Request.ENROLL) {
            try {
                BaseDao.addAccount(tp);
                request.r_status = Request.ACC;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                if(e.getErrorCode() == 1062) {  //说明用户已经存在
                    request.r_status = Request.ACCOUNT_EXIST;
                }
                else{ //说明信息不全
                    request.r_status = Request.INFO_NOT_ENOUGH;
                }
            }
        } else if (request.r_status == Request.SAVE) {
            try {
                BaseDao.saveMoney(tp.getBalance(), tp.getUser());
                request.r_status = Request.ACC;
                request.setAccount(BaseDao.queryAccount(tp.getUser()));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if(request.r_status == Request.TRANSFER) {
            try {
                Account account = BaseDao.queryAccount(tp.getUser());
                if(account != null) {
                    BaseDao.saveMoney(tp.getBalance(), tp.getUser());
                    request.setAccount(BaseDao.queryAccount(tp.getUser()));
                    request.r_status = Request.TRANSFER_OK;
                } else {
                    request.r_status = Request.TRANSFER_FAIL;
                }
            } catch (SQLException e) {
                request.r_status = Request.TRANSFER_FAIL;
                System.out.println(e.getMessage());
            }
        } else if (request.r_status == Request.WITHDRAW) {
            try {
                BaseDao.Withdraw(tp.getBalance(), tp.getUser());
                request.setAccount(BaseDao.queryAccount(tp.getUser()));
                request.r_status = Request.ACC;
            } catch (SQLException e) {
                request.r_status = Request.NO_SERVER;
                throw new RuntimeException(e);
            } catch (BalanceNotEnoughException e) {
                request.r_status = Request.BALANCE_NOT_ENOUGH;
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("无法识别的操作：" + request.r_status);
            request.r_status = Request.NO_SERVER;
        }
        return request;
    }
    public static void sendResponse(Request request) throws IOException {
        System.out.println("正在回应用户：" + request.getAccount().getUser() + " 的请求");
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        bytes = baos.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, request.address, request.port);
        ds.send(datagramPacket);
    }

}
