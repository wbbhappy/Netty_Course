package NIOInduction.BIO.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[]agrs)throws IOException{
        int port = 8080;
        if(agrs!=null && agrs.length>0){
            try {
                port = Integer.valueOf(agrs[0]);
            }
            catch (NumberFormatException ex){}
        }
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("The Server start in port : " + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new NIOInduction.BIO.Server.TimeServerHandler(socket)).start();
            }
        }
        finally {
            if(server != null){
                System.out.println("The Server close...");
                server.close();
                server = null;
            }
        }
    }
}
