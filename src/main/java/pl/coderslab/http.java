package pl.coderslab;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class http {
    private static final Logger logger = LoggerManager.getLogger();
    static Socket clientSocket;
    public static void runHTTP() {
        try (ServerSocket serverSocket = new ServerSocket(config.port)) {
            logger.info("Uruchomiono serwer HTTP: http://localhost:" + config.port + "/");

            while (true) {
                clientSocket = serverSocket.accept();
                String pageHead = """
                    HTTP/1.1 200 OK
                    Content-Type: text/html
                    Connection: close
                    
                    <!doctype html>
                    <html lang="pl">
                        <head>
                            <meta charset="utf-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1">
                            <title>WebTaskManager</title>
                            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
                            <style>
                                .bd-placeholder-img {
                                  font-size: 1.125rem;
                                  text-anchor: middle;
                                  -webkit-user-select: none;
                                  -moz-user-select: none;
                                  user-select: none;
                                }
                        
                                @media (min-width: 768px) {
                                  .bd-placeholder-img-lg {
                                    font-size: 3.5rem;
                                  }
                                }
                        
                                .b-example-divider {
                                  width: 100%;
                                  height: 3rem;
                                  background-color: rgba(0, 0, 0, .1);
                                  border: solid rgba(0, 0, 0, .15);
                                  border-width: 1px 0;
                                  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
                                }
                        
                                .b-example-vr {
                                  flex-shrink: 0;
                                  width: 1.5rem;
                                  height: 100vh;
                                }
                        
                                .bi {
                                  vertical-align: -.125em;
                                  fill: currentColor;
                                }
                        
                                .nav-scroller {
                                  position: relative;
                                  z-index: 2;
                                  height: 2.75rem;
                                  overflow-y: hidden;
                                }
                        
                                .nav-scroller .nav {
                                  display: flex;
                                  flex-wrap: nowrap;
                                  padding-bottom: 1rem;
                                  margin-top: -1px;
                                  overflow-x: auto;
                                  text-align: center;
                                  white-space: nowrap;
                                  -webkit-overflow-scrolling: touch;
                                }
                        
                                .btn-bd-primary {
                                  --bd-violet-bg: #712cf9;
                                  --bd-violet-rgb: 112.520718, 44.062154, 249.437846;
                        
                                  --bs-btn-font-weight: 600;
                                  --bs-btn-color: var(--bs-white);
                                  --bs-btn-bg: var(--bd-violet-bg);
                                  --bs-btn-border-color: var(--bd-violet-bg);
                                  --bs-btn-hover-color: var(--bs-white);
                                  --bs-btn-hover-bg: #6528e0;
                                  --bs-btn-hover-border-color: #6528e0;
                                  --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
                                  --bs-btn-active-color: var(--bs-btn-hover-color);
                                  --bs-btn-active-bg: #5a23c8;
                                  --bs-btn-active-border-color: #5a23c8;
                                }
                        
                                .bd-mode-toggle {
                                  z-index: 1500;
                                }
                        
                                .bd-mode-toggle .dropdown-menu .active .bi {
                                  display: block !important;
                                }
                             </style>
                        </head>
                        <body>
                            <div class="container">
                                <div class="row">
                                    <h1>WebTaskManager</h1>
                                    <p>Dowiedz się więcej na temat projektu przechodząc do pliku <a href="https://github.com/AntekGolebiowski/WebTaskManager/blob/main/README.md" target="_blank">README.md</a> w <a href="https://github.com/AntekGolebiowski/WebTaskManager" target="_blank">GitHub</a>.</p>
                                </div>
                                <div class="row">
                                    <div class="accordion" id="accordion">
                                    """;
                String pageBottom = """
                                    </div>
                                </div>
                            </div>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
                        </body>
                    </html>
                        """;

                String accordion = """
                        <div class="accordion-item">
                          <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse#id#" aria-expanded="false" aria-controls="collapse#id#">
                            #name#
                            </button>
                                </h2>
                                <div id="collapse#id#" class="accordion-collapse collapse" data-bs-parent="#accordion">
                                  <div class="accordion-body">
                                    <i>Utworzono:</i> <b>#created#</b> <i>Termin realizacji:</i> <b>#deadline#</b> <i>Ilość komentarzy:</i> <b>#comments#</b>
                                    <hr>
                                    #description#
                                    <hr>
                                    Analiza potrzeb klienta:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#analysis#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #analysis#%"></div>
                                    </div>
                                    Projektowanie struktury:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#structure#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #structure#%"></div>
                                    </div>
                                    Projektowanie UI/UX:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#design#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #design#%"></div>
                                    </div>
                                    Tworzenie frontend:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#frontend#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #frontend#%"></div>
                                    </div>
                                    Tworzenie back-end:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#backend#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #backend#%"></div>
                                    </div>
                                    Testowanie:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#testing#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #testing#%"></div>
                                    </div>
                                    Optymalizacja:
                                    <div class="progress" role="progressbar" aria-label="Analiza potrzeb klienta" aria-valuenow="#optimization#" aria-valuemin="0" aria-valuemax="100">
                                      <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: #optimization#%"></div>
                                    </div>
                                  </div>
                                </div>
                              </div>""";
                StringBuilder sb = new StringBuilder();
                sb.append(pageHead);

                Path path1 = Paths.get("projects.csv");
                try {
                    for (String line : Files.readAllLines(path1)) {
                        int[] separators = files.getSeparatorsIndex(line,"|");
                        String output;
                        output = accordion.replaceAll("#id#",line.substring(0, separators[0]));
                        if("true".equalsIgnoreCase(line.substring(separators[3]+1, separators[4]))) {
                            output = output.replaceAll("#name#", "<span class=\"badge text-bg-secondary\">Ukończony</span> " + line.substring(separators[0] + 1, separators[1]));
                        }else if("true".equalsIgnoreCase(line.substring(separators[2]+1, separators[3]))) {
                            output = output.replaceAll("#name#", "<span class=\"badge text-bg-danger\">Pilny</span> " + line.substring(separators[0] + 1, separators[1]));
                        }else {
                            output = output.replaceAll("#name#", line.substring(separators[0] + 1, separators[1]));
                        }

                        output = output.replaceAll("#description#",line.substring(separators[1]+1, separators[2]));

                        try {
                            output = output.replaceAll("#created#",methods.returnFomattedDate(Long.parseLong(line.substring(separators[4]+1, separators[5])), "dd/MM/yyyy HH:mm"));
                            output = output.replaceAll("#deadline#", methods.returnFomattedDate(Long.parseLong(line.substring(separators[5] + 1, separators[6])), "dd/MM/yyyy HH:mm"));
                        } catch (NumberFormatException e) {
                            logger.severe("Plik projektów prawdopodobnie jest uszkodzony! " + e.getMessage());
                        }

                        output = output.replaceAll("#analysis#", line.substring(separators[6] + 1, separators[7]));
                        output = output.replaceAll("#structure#",line.substring(separators[7]+1, separators[8]));
                        output = output.replaceAll("#design#",line.substring(separators[8]+1, separators[9]));
                        output = output.replaceAll("#frontend#",line.substring(separators[9]+1, separators[10]));
                        output = output.replaceAll("#backend#",line.substring(separators[10]+1, separators[11]));
                        output = output.replaceAll("#testing#",line.substring(separators[11]+1, separators[12]));
                        output = output.replaceAll("#optimization#",line.substring(separators[12]+1, separators[13]));
                        output = output.replaceAll("#comments#",line.substring(separators[13]+1));
                        sb.append(output);
                    }
                } catch (IOException e) {
                    logger.warning("Nie udało się odczytać pliku projektów! " + e.getMessage());
                }

                sb.append(" ");
                sb.append(pageBottom);

                OutputStream out = clientSocket.getOutputStream();
                out.write(sb.toString().getBytes());
                out.flush();
                clientSocket.close();
            }
        } catch (Exception e) {
            logger.severe("Błąd serwera HTTP: " + e.getMessage());
        }
    }
}
