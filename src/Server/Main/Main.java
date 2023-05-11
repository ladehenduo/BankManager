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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Request> requests = new ArrayList<>();
//        try {
//            BaseDao.Withdraw(104.0, "1234");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (BalanceNotEnoughException e) {
//            throw new RuntimeException(e);
//        }
//        List<Request> response = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Integer lock = 1;
        System.out.println("服务器开始运行...");
        while(true) {
            Request request = UDPServerTools.receiveRequest();
            requests.add(request);
            synchronized (lock) {
                if(requests.size() != 0) {
                    Request request1 = requests.get(0);
                    requests.remove(0);
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            Request response1 = UDPServerTools.dealRequest(request1);
                            try {
                                UDPServerTools.sendResponse(response1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        }
    }
}
