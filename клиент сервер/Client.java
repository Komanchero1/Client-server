import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

     
//создаем сокет клиента который подключается к порту 8080
        try (Socket socket = new Socket("localhost", 8080);
             //буферизируем входной поток для чтения данных от сервера
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //буферезируем исходящий поток для отправки данных на сервер
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            //инициализируем строку для отправки на сервер
            String message = "Hello, Server!";
            //записываем сообщение для сервера  в буфер
            out.write(message);
            //добовляем символ новой строки чтобы сервер знал что сообщение кончилось
            out.newLine();
            //отправляем данные на сервер и очищаем буфер
            out.flush();
//читаем сообщение от сервера пока не будет символа новой строки
            String response = in.readLine();
            //выводим в консоль ответ от сервера
            System.out.println("Received from server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
