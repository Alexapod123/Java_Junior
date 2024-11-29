package Alexa.seminar_5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Service implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public String name;
    public static ArrayList<Service> clients = new ArrayList<>();

    public Service(Socket socket) {
        try {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            name = reader.readLine();
            clients.add(this);
            broadcastMessage(name + " присоединился к чату");
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    private void broadcastMessage(String msg) {
        for (Service client : clients) {
            try {
                if (!client.name.equals(name)) {
                    client.writer.write(msg);
                    client.writer.newLine();
                    client.writer.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader in, BufferedWriter out) {
        removeClient();
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

    private void removeClient() {
        clients.remove(this);
        broadcastMessage( name + " покинул чат");
    }

    @Override
    public void run() {
        String clientsMsg;
        while (socket.isConnected()) {
            try {
                clientsMsg = reader.readLine();
                broadcastMessage(clientsMsg);
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
                break;
            }
        }
    }
}

