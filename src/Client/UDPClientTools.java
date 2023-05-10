package Client;

import Public.Account;
import Public.Request;

import java.io.*;
import java.net.*;

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
    public static void receiveRequest() throws IOException, ClassNotFoundException {
        byte[] receiveMessage = new byte[1024];
        datagramPacket = new DatagramPacket(receiveMessage, receiveMessage.length);
        ds.receive(datagramPacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveMessage);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        Request request = (Request) objectInputStream.readObject();
        if(request.r_status == Request.ACC) {
            System.out.println("成功！");
        }
        else{
            System.out.println("失败！");
        }
    }
}
