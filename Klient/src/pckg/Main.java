package pckg;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        EchoClient client=new EchoClient();
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
