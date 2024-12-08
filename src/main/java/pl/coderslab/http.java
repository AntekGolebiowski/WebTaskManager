package pl.coderslab;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class http {
    public static void runHTTP() {
        try (ServerSocket serverSocket = new ServerSocket(config.port)) {
            Main.logger.info("Uruchomiono serwer HTTP: http://localhost:" + config.port + "/");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String httpResponse = """
                        HTTP/1.1 200 OK
                        Content-Type: text/html
                        Connection: close
                        
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Simple Java Server</title>
                        </head>
                        <body>
                            <h1>Witaj na mojej stronie!</h1>
                            <p>To jest prosty serwer WWW wykorzystujący java.net.</p>
                        </body>
                        </html>
                        """;

                OutputStream out = clientSocket.getOutputStream();
                out.write(httpResponse.getBytes());
                out.flush();
                clientSocket.close();
            }
        } catch (Exception e) {
            Main.logger.severe("Błąd serwera HTTP: " + e.getMessage());
        }
    }
}
