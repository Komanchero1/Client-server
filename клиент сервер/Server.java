import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

     
        //создаем для прослушивания входящих сообщений на порту 8080
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started...");


            try (Socket socket = serverSocket.accept();//ждем обращения клиента
                 //буферизируем входящий поток
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 //бзаписываем данныев буфер
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                System.out.println("Client connected");
                //считываем из буфера входящее сообщение и сохраняем его
                String request = in.readLine();
                //выводим в консоль входящее сообщение и номер порта клиента
                System.out.println("Received from port " + socket.getPort() + ": " + request);
                //формируем строку для ответа клиенту
                String response = String.format("Hello, your request = %s", request);
                //записываем ответ сервера в исходящее соединение
                out.write(response);
                //добовляем символы строки в исходящее соединение
                out.newLine();
                //выталкиваем сформированную строку из буфера в исходящее соединение
                out.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



