package pl.coderslab;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class http {
    private static final Logger logger = LoggerManager.getLogger();

    public static void runHTTP() {
        try (ServerSocket serverSocket = new ServerSocket(config.port)) {
            logger.info("Uruchomiono serwer HTTP: http://localhost:" + config.port + "/");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String httpResponse = """
                        HTTP/1.1 200 OK
                        Content-Type: text/html
                        Connection: close
                            <!doctype html>
                            <html lang="pl">
                                <head>
                                    <title>Hello World!</title>
                                </head>
                                <body>
                                <h1>Hello World!</h1>
                                </body>
                            </html>
                        """; //todo: html

                OutputStream out = clientSocket.getOutputStream();
                out.write(httpResponse.getBytes());
                out.flush();
                clientSocket.close();
            }
        } catch (Exception e) {
            logger.severe("Błąd serwera HTTP: " + e.getMessage());
        }
    }
}
