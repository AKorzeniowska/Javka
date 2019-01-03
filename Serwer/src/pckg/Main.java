package pckg;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        EchoServer server=new EchoServer();
        server.listen();
        server.connect();
        server.close();
    }
}
