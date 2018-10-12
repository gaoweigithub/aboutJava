package NIO.Reactor.ThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
//        String hostName = args[0];
//        int port = Integer.parseInt(args[1]);

        String hostName = "localhost";
        int port = 1333;

        System.out.println("connecting to " + hostName + ":" + port);
        try {
            Socket client = new Socket(hostName, port);
            System.out.println("Connected to " + hostName);

            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader stdIN = new BufferedReader(new InputStreamReader(System.in));
            String input;

            while ((input = stdIN.readLine()) != null) {
                out.print(input);
                out.flush();
                if (input.equals("exit")) {
                    break;
                }
                System.out.println("server:" + in.readLine());
            }
            client.close();
            System.out.println("client stop.");
        } catch (UnknownHostException e) {
            System.err.println("dont know about host: "+ hostName);
        } catch (IOException e) {
            System.err.print("could not get I/O for the socket connection");
        }
    }
}
