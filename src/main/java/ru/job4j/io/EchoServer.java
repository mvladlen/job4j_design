package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    private static String getCommand(String getString) {
        var begin = getString.indexOf("/?msg=");
        var end = getString.indexOf(" ");
        if (end < begin) {
            end = getString.length();
        }
        String ret = getString.substring(begin + 6, end);
        return ret;
    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    System.out.println(in);
                    String str = in.readLine();
                    while (str != null && !str.isEmpty()) {
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
                        str = in.readLine();
                    }
                } catch (IOException e1) {
                    LOG.error("Input error", e1);
                }
            }
        } catch (IOException e2) {
            LOG.error("Input error", e2);
        }
    }
}
