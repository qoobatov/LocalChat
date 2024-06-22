package org.ekubatov.SocketServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8081);
             Socket socket = serverSocket.accept();
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             java.util.Scanner scanner = new java.util.Scanner(System.in)) {

            String clientMessage;
            String serverMessage;

            while (true) {
                // Чтение сообщения от клиента
                clientMessage = inputStream.readUTF();
                System.out.println("Клиент: " + clientMessage);

                // Если клиент отправляет "exit", то заканчиваем цикл
                if ("exit".equalsIgnoreCase(clientMessage)) {
                    break;
                }

                System.out.print("Введите сообщение: ");
                serverMessage = scanner.nextLine();

                // Отправка ответа клиенту
                outputStream.writeUTF(serverMessage);
                outputStream.flush();

                // Если сервер отправляет "exit", то заканчиваем цикл
                if ("exit".equalsIgnoreCase(serverMessage)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
