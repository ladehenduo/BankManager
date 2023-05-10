package Server.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(12345);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        System.out.println("服务器已启动，等待接收");
        datagramSocket.receive(datagramPacket);
        String s = new String(bytes, 0, datagramPacket.getLength());
        System.out.println(s);
    }
}
