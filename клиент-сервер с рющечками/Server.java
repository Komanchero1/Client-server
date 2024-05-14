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
                 //записываем данные в буфер
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                System.out.println("Client connected");
                //отправляем в буфер вопрос об имени
                out.write("Write your name.\n");
                //освобождаем буфер  отправляем сообщение
                out.flush();
                //считываем ответ на вопрос об имени
                String name = in.readLine();
                //отправляем в буфер  вопрос клиенту про возраст
                out.write("Are you child? (yes/no)\n");
                //выталкиваем сформированную строку из буфера в исходящее соединение
                out.flush();
                //считываем ответ про возраст
                String isChild = in.readLine();
                //если клиент ребенок  то выводим сообщение Welcome to the kids area
                //если ответ нет то Welcome to the adult zone
                String response = isChild.equalsIgnoreCase("yes") ? "Welcome to the kids area,"
                        + name + "! Lets play" : "Welcome to the adult zone," + name
                        + "! Have a good rest, or a good working day";
                //считываем из буфера входящее сообщение и сохраняем его
                out.write(response + "\n");
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