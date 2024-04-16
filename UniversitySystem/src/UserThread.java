import java.io.IOException;
import java.net.Socket;

public class UserThread implements Runnable{

   private Socket socket;
   private UniversitySystem universitySystem;

    public UserThread(Socket socket, UniversitySystem universitySystem) {
        this.socket = socket;
        this.universitySystem = universitySystem;
    }

    @Override
    public void run() {
try{
    universitySystem.logIn(socket);
}catch (IOException e){
    System.out.println(e.getMessage());
}
    }
}
