import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        //создаем сокет клиента который подключается к порту 8080
        try (Socket socket = new Socket("netology.homework", 8080);
             //буферизируем входной поток для чтения данных от сервера
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             //буферизируем исходящий поток для отправки данных на сервер
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            //считываем строку полученную от сервера после подключения
            String request = in.readLine();
            //выводим в консоль строку полученную от сервера
            System.out.println("Received from server" + request);
            //записываем сообщение для сервера  в буфер
            out.write("Join\n");
            //отправляем данные на сервер и очищаем буфер
            out.flush();
            //считываем запрос про возраст
            request = in.readLine();
            //выводим в консоль полученный запрос
            System.out.println("Received from server" + request);
            //записываем сообщение для сервера  в буфер
            out.write("yes\n");
            //отправляем данные на сервер и очищаем буфер
            out.flush();
//считываем ответ от сервера на наш возраст
            String response = in.readLine();
            //выводим в консоль ответ от сервера
            System.out.println("Received from server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
