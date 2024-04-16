import javax.swing.plaf.TableHeaderUI;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {


        ServerSocket serverSocket=null;
        Socket clientSocket=null;
        UniversitySystem universitySystem=new UniversitySystem();
        try{
            serverSocket=new ServerSocket(1222);
            System.out.println("Server is listening.....");
            while(true){
                clientSocket=serverSocket.accept();
                System.out.println("Client with "+clientSocket.getInetAddress()+" is connected");
                UserThread userThread=new UserThread(clientSocket,universitySystem);
                Thread thread=new Thread(userThread);
                thread.start();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }


    }
}