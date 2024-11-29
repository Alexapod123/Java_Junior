package Alexa.seminar_5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public String name;

    public ClientChat(Socket socket, String clientName) {
        this.socket = socket;
        name = clientName;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }


    public void sendMsg() {
        try {
            writer.write(name);
            writer.newLine();
            writer.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String msg = scanner.nextLine();
                writer.write(name + ": " + msg);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void listenMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChat;
                while (socket.isConnected()) {
                    try {
                        msgFromChat = reader.readLine();
                        System.out.println(msgFromChat);
                    } catch (IOException e) {
                        closeEverything(socket, reader, writer);
                    }
                }
            }
        }).start();
    }
    private void closeEverything(Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        String clientName = scanner.nextLine();
        Socket socket = new Socket("localhost", 1500);
        ClientChat clientChat = new ClientChat(socket, clientName);
        clientChat.listenMsg();
        clientChat.sendMsg();

    }
}
