package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {

    private static String getCommand(String getString) {
        var begin = getString.indexOf("/?msg=");
        var end = getString.indexOf(' ', begin);
        String ret = getString.substring(begin + 6, end);
        return ret;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    if (str.contains("?msg")) {
                        if (getCommand(str).equals("Exit")) {
                            server.close();
                        }
                        if (getCommand(str).equals("Hello")) {
                            out.write("Hello".getBytes());
                        } else {
                            out.write(getCommand(str).getBytes());
                        }
                    }
                }
            }
        }
    }
}
