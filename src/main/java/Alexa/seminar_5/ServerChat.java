package Alexa.seminar_5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {
    private ServerSocket serverSocket;

    public ServerChat(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void runServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый участник");
                Service service = new Service(socket);
                Thread thread =  new Thread(service);
                thread.start();
            }
        }catch (IOException e){
            closeSocket();
        }
    }


    private void closeSocket() {
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1500);
        ServerChat serverChat = new ServerChat(serverSocket);
        serverChat.runServer();
    }

}
