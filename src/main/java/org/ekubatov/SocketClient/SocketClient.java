package org.ekubatov.SocketClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8081);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             java.util.Scanner scanner = new java.util.Scanner(System.in)) {

            String message;
            String serverResponse;

            while (true) {
                System.out.print("Введите сообщение: ");
                message = scanner.nextLine();

                // Отправка сообщения серверу
                outputStream.writeUTF(message);
                outputStream.flush();

                // Если клиент отправляет "exit", то заканчиваем цикл
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Чтение ответа от сервера
                serverResponse = inputStream.readUTF();
                System.out.println("Сервер: " + serverResponse);

                // Если сервер отправляет "exit", то заканчиваем цикл
                if ("exit".equalsIgnoreCase(serverResponse)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
