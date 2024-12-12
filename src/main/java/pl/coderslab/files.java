package pl.coderslab;

import javax.sound.midi.Patch;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class files {
    private static final Logger logger = LoggerManager.getLogger();

    public static boolean systemCheck() {
        Path path = Paths.get("users.csv");
        if (!Files.exists(path)) {
            logger.warning("Nie znaleziono pliku użytkowników. Rozpoczynam instalację.");
            return false;
        }
        logger.info("Weryfikacja plików zakończona powodzeniem.");
        return true;
    }

    public static boolean loginUserPassword(String username, String password) {
        Path path = Paths.get("users.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int comma1 = -1;
            int comma2 = -1;

            while ((line = reader.readLine()) != null) {
                comma1 = line.indexOf(",");
                comma2 = line.indexOf(",", comma1 + 1);

                if (username.equals(line.substring(comma1 + 1, comma2).trim())) {
                    int[] separators = getSeparatorsIndex(line, ",");
                    if (users.validatePassword(password, line.substring(separators[1] + 1, separators[2]).trim())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            logger.severe("Nie udało się otworzyć pliku users.csv. " + e.getMessage());
        }
        return false;
    }

    public static int getUserId(String username) {
        Path path = Paths.get("users.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int comma1 = -1;
            int comma2 = -1;

            while ((line = reader.readLine()) != null) {
                comma1 = line.indexOf(",");
                comma2 = line.indexOf(",", comma1 + 1);

                if (username.equals(line.substring(comma1 + 1, comma2).trim())) {
                    try {
                        return Integer.parseInt(line.substring(0, comma1).trim());
                    } catch (NumberFormatException e) {
                        logger.severe("Nie udało się pobrać id użytkownika. " + e.getMessage());
                        return -1;
                    }
                }
            }
        } catch (IOException e) {
            logger.severe("Nie udało się otworzyć pliku users.csv. " + e.getMessage());
        }
        return -1;
    }

    public static String getUserName(int id) {
        if (id == -1) {
            return null;
        }

        Path path = Paths.get("users.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            String stringId = Integer.toString(id);

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringId)) {
                    int[] comma = getSeparatorsIndex(line, ",");
                    return line.substring(comma[2] + 1, comma[3]) + " " + line.substring(comma[3] + 1, comma[4]);
                }
            }
        } catch (IOException e) {
            logger.severe("Nie udało się otworzyć pliku users.csv. " + e.getMessage());
        }
        return null;
    }

    public static int[] getSeparatorsIndex(String string, String separator) {
        int count = 0;
        int index = string.indexOf(separator);
        while (index != -1) {
            count++;
            index = string.indexOf(separator, index + 1);
        }

        int[] indexes = new int[count];
        index = string.indexOf(separator);
        int i = 0;

        while (index != -1) {
            indexes[i++] = index;
            index = string.indexOf(separator, index + 1);
        }

        return indexes;
    }

    public static boolean install(boolean samples) {
        File file = new File("projects.csv");
        try {
            if (file.createNewFile()) {
                logger.info("Utworzono plik projektów: projects.csv");
            } else {
                logger.info("Nie utworzono pliku projektów, plik już istnieje! Plik: projects.csv");
            }
        } catch (IOException e) {
            logger.severe("Nie udało się utworzyć pliku projektów! " + e.getMessage());
            return false;
        }

        if (!samples) {
            return true;
        }

        String sampleProjects = """
                0|Strona internetowa dla sklepu|Stwórz responsywną stronę internetową dla sklepu, która umożliwia użytkownikom przeglądanie produktów, dodawanie ich do koszyka i finalizowanie zamówień. Wykorzystaj system zarządzania treścią (CMS) do łatwej edycji produktów i stron. Integracja z bramkami płatności oraz systemem dostawy jest niezbędna.|false|true|1733936993846|1739575023558|100|100|100|100|100|100|100|5
                1|System zarządzania projektami|Zaprojektuj aplikację, która umożliwia zarządzanie projektami, przypisywanie zadań do członków zespołu i śledzenie postępów. Powinna zawierać formularze do tworzenia i edytowania projektów oraz zadań, a także kalendarz do planowania terminów. Zaimplementuj system uprawnień (np. administrator, użytkownik) oraz powiadomienia.|true|true|1733935382401|1735717236000|100|100|100|100|100|100|100|5
                2|Aplikacja mobilna do bankowości|Stwórz aplikację mobilną z funkcjami bankowymi, umożliwiającą użytkownikom logowanie, sprawdzanie salda, dokonywanie przelewów oraz zarządzanie kontem. Zadbaj o bezpieczeństwo aplikacji, implementując autentykację dwuskładnikową i szyfrowanie danych. Użyj nowoczesnych frameworków mobilnych, takich jak Flutter lub React Native.|false|false|1733930990808|1742785028501|25|16|20|32|65|12|12|5
                3|Sklep internetowy|Rozwijaj sklep internetowy z funkcjonalnościami e-commerce, takimi jak przeglądanie katalogu produktów, dodawanie ich do koszyka i proces zakupu. Zadbaj o integrację z systemami płatności, trackingiem zamówień oraz procesem realizacji dostaw. Stosuj technologię front-endową (HTML, CSS, JavaScript) oraz backendową (np. PHP, Node.js) z bazą danych MySQL lub MongoDB.|false|true|1733936204350|1740156007568|100|100|100|100|100|100|100|5
                4|Strona internetowa dla eventu|Stwórz dedykowaną stronę internetową dla wydarzenia, która zawiera informacje o wydarzeniu, programie oraz możliwość rejestracji uczestników. Zadbaj o formularz rejestracyjny, opcję zakupu biletów i integrację z platformą płatniczą. Strona powinna być responsywna i atrakcyjna wizualnie.|false|true|1733932560462|1734984242089|100|100|100|100|100|100|100|5
                5|Aplikacja mobilna dla sklepu|Zaprojektuj aplikację mobilną dla sklepu, umożliwiającą użytkownikom przeglądanie produktów, zarządzanie zamówieniami oraz śledzenie statusu dostawy. Integracja z systemem zarządzania produktami i płatnościami jest kluczowa. Aplikacja powinna oferować użytkownikowi powiadomienia push o promocjach i statusie zamówienia.|false|true|1733930309833|1735961490016|100|100|100|100|100|100|100|5
                6|Platforma wideo|Stwórz platformę do przesyłania i oglądania treści wideo. Zaimplementuj funkcje takie jak tworzenie kont użytkowników, przesyłanie filmów, komentowanie oraz oceny. Możesz użyć technologii takich jak HTML5 video, PHP do obsługi backendu oraz bazy danych do przechowywania filmów i metadanych.|true|true|1733930627471|1742928402114|100|100|100|100|100|100|100|5
                7|Portal wideo|Zbuduj portal umożliwiający przesyłanie i oglądanie filmów wideo. Użytkownicy powinni mieć możliwość logowania się, dodawania filmów oraz interakcji z treściami (komentarze, polubienia). Warto zaimplementować system tagowania filmów oraz filtrowania treści według kategorii.|false|false|1733933108036|1743575014360|25|16|20|32|65|12|12|5
                8|Strona internetowa dla organizacji|Stwórz profesjonalną stronę internetową dla organizacji, zawierającą informacje o jej działalności, celach i projektach. Powinna mieć sekcję kontaktową z formularzem oraz integrację z mediami społecznościowymi. Użyj systemu CMS do łatwego zarządzania treścią i aktualizowania informacji.|true|false|1733933199773|1740201467695|25|16|20|32|65|12|12|5
                9|Blog o zdrowiu|Zaprojektuj bloga o zdrowiu, który umożliwia publikowanie artykułów na tematy zdrowotne. Zintegruj funkcje dodawania komentarzy, kategorii artykułów oraz wyszukiwania. Strona powinna być responsywna i łatwa do nawigacji, z możliwością subskrypcji nowych wpisów przez użytkowników.|false|true|1733931061475|1741827661728|100|100|100|100|100|100|100|5
                10|System analityczny do biznesu|Zbuduj system analityczny umożliwiający gromadzenie i przetwarzanie danych biznesowych, tworzenie raportów oraz analizę trendów. Zaimplementuj możliwość importu danych z różnych źródeł (np. CSV, API) oraz ich wizualizację (wykresy, tabele).|false|false|1733930269794|1739377066004|25|16|20|32|65|12|12|5
                11|Portal społecznościowy|Stwórz portal społecznościowy, który umożliwia tworzenie profili, dodawanie postów, komentowanie oraz tworzenie grup. Aplikacja powinna wspierać prywatność użytkowników, pozwalając na zarządzanie ustawieniami prywatności i uprawnieniami dostępu.|false|true|1733932806303|1735472572652|100|100|100|100|100|100|100|5
                12|Serwis streamingowy|Zbuduj serwis streamingowy do odtwarzania treści wideo na żądanie. Aplikacja powinna obsługiwać różne formaty wideo, pozwalać na tworzenie kont użytkowników i subskrypcji, a także oferować opcję rekomendacji na podstawie oglądanych treści.|false|true|1733938295749|1740188296713|100|100|100|100|100|100|100|5
                13|Platforma wideo edukacyjna|Zaimplementuj platformę do nauki online, oferującą kursy wideo, testy, zadania do wykonania oraz interaktywne materiały edukacyjne. Użytkownicy powinni móc śledzić swoje postępy oraz uzyskiwać certyfikaty po zakończeniu kursów.|true|true|1733938265446|1737846694652|100|100|100|100|100|100|100|5
                14|Aplikacja mobilna do fitnessu|Stwórz aplikację mobilną, która umożliwia użytkownikom śledzenie treningów, celów fitness oraz zdrowych nawyków. Powinna mieć funkcje dodawania ćwiczeń, monitorowania postępów i integracji z urządzeniami do śledzenia aktywności.|true|true|1733934737497|1742795905740|100|100|100|100|100|100|100|5
                15|Sklep internetowy z elektroniką|Zaprojektuj sklep internetowy, który oferuje szeroki asortyment elektroniki. Strona powinna umożliwiać filtrowanie produktów według kategorii, marki, ceny oraz opinii użytkowników. Zadbaj o system płatności online oraz możliwość śledzenia zamówień.|false|true|1733933134991|1741796943726|100|100|100|100|100|100|100|5
                16|Aplikacja do nauki języków|Zbuduj aplikację mobilną do nauki języków obcych, oferującą interaktywne lekcje, ćwiczenia i quizy. Aplikacja powinna umożliwiać śledzenie postępów użytkownika oraz dostosowywanie poziomu trudności.|false|false|1733933568135|1738363488308|25|16|20|32|65|12|12|5
                17|System rezerwacji online|Stwórz system rezerwacji online, który umożliwia użytkownikom rezerwowanie usług (np. hoteli, restauracji, wydarzeń). Zaimplementuj kalendarz dostępności, integrację z płatnościami online oraz możliwość otrzymywania potwierdzeń rezerwacji przez e-mail.|false|true|1733937222625|1735751688898|100|100|100|100|100|100|100|5
                18|System analityczny dla firm|Rozwijaj system analityczny dla firm, który gromadzi i analizuje dane operacyjne, pomagając w podejmowaniu decyzji strategicznych. Powinna być możliwa integracja z istniejącymi systemami ERP, CRM oraz import danych z zewnętrznych źródeł.|true|true|1733930210263|1740615104010|100|100|100|100|100|100|100|5
                19|Aplikacja do zarządzania projektami|Stwórz aplikację webową, która umożliwia zarządzanie projektami, przydzielanie zadań do członków zespołu, śledzenie postępów oraz generowanie raportów. Implementacja systemu powiadomień oraz uprawnień użytkowników jest kluczowa.|true|true|1733931332415|1743761190704|100|100|100|100|100|100|100|5
                20|Kampania reklamowa online|Zaprojektuj platformę do tworzenia, zarządzania i monitorowania kampanii reklamowych w Internecie. Umożliw użytkownikom tworzenie reklam, targetowanie grup docelowych, śledzenie wyników oraz analizowanie skuteczności kampanii.|false|true|1733929714076|1738154303356|100|100|100|100|100|100|100|5
                21|Aplikacja do zarządzania zadaniami|Stwórz aplikację do zarządzania zadaniami, która pozwala użytkownikom tworzyć listy zadań, przypisywać je do innych osób oraz monitorować postępy. Zaimplementuj system przypomnień i integrację z kalendarzem.|false|false|1733937460216|1738828448209|25|16|20|32|65|12|12|5
                22|Firma doradcza|Stwórz stronę internetową dla firmy doradczej, która oferuje usługi konsultingowe w różnych branżach. Strona powinna zawierać informacje o oferowanych usługach, zespole ekspertów, referencjach oraz formularz kontaktowy.|false|false|1733931103783|1737462816284|25|16|20|32|65|12|12|5""";

        Path pathProjects = Paths.get("projects.csv");
        try {
            Files.writeString(pathProjects, sampleProjects);
        } catch (IOException e) {
            logger.severe("Nie udało się utworzyć pliku projektów! " + e.getMessage());
            return false;
        }

        String[][] comments = new String[][]{
                {
                        "Zakończyłem implementację funkcji przeglądania produktów. Teraz pracuję nad integracją z bramkami płatności. Wkrótce push na GitHub!",
                        "Potrzebuję pomocy w integracji z systemem dostawy – ktoś zna API, które mogłoby to uprościć?",
                        "Dodałem sekcję opinii o produktach. Wkrótce dodam jeszcze opcję sortowania według oceny.",
                        "Zajmuję się teraz responsywnym designem dla wersji mobilnej, żeby strona działała płynnie na telefonach.",
                        "Zrobiłem mały refaktoring kodu. Teraz wszystko działa sprawniej. Jeśli ktoś ma sugestie, chętnie je wysłucham."},
                {
                        "Zakończyłem część dotyczącą tworzenia projektów. Teraz dodaję formularz przypisywania zadań do członków zespołu.",
                        "Czy ktoś zna dobrą bibliotekę do wyświetlania kalendarza? Muszę dodać harmonogramy zadań do aplikacji.",
                        "Push na GitHub! Dodałem funkcję powiadomień o terminach projektów.",
                        "Zajmuję się teraz uprawnieniami użytkowników. Ktoś zna prosty sposób na zabezpieczenie dostępu do danych w projekcie?",
                        "Potrzebuję pomocy z integracją aplikacji z Google Calendar API, żeby synchronizować terminy."},
                {
                        "Dodałem funkcję logowania z autentykacją dwuskładnikową. Push na GitHub wykonany!",
                        "Zajmuję się teraz integracją z systemem przelewów. Czy ktoś zna API, które obsługuje bankowe przelewy?",
                        "Pracuję nad bezpieczeństwem aplikacji – dodam szyfrowanie danych. Jeśli ktoś ma doświadczenie z AES, chętnie przyjmę porady.",
                        "Dodałem widok konta i salda. Potrzebuję pomocy z wyświetlaniem historii transakcji.",
                        "Zrobiłem push na GitHub. Aplikacja działa teraz płynniej. Ktoś chciałby sprawdzić wydajność?"},
                {
                        "Zakończyłem dodawanie produktów do koszyka. Teraz muszę dodać opcję płatności online. Push na GitHub za chwilę.",
                        "Potrzebuję pomocy w integracji z systemem śledzenia zamówień. Ktoś zna API do tego?",
                        "Zajmuję się teraz backendem. Jeśli ktoś zna dobrą bazę danych do przechowywania historii zamówień, chętnie wysłucham propozycji.",
                        "Dodałem możliwość filtrowania produktów według kategorii. Wkrótce dodam system recenzji.",
                        "Czy ktoś zna dobrą bibliotekę do integracji płatności online w PHP?"},
                {
                        "Zakończyłem formularz rejestracji uczestników. Teraz pracuję nad sekcją informacji o wydarzeniu. Push na GitHub za chwilę!",
                        "Czy ktoś zna API do sprzedaży biletów online? Potrzebuję go do implementacji płatności.",
                        "Zajmuję się teraz responsywnością strony, żeby dobrze wyglądała na telefonach. Ktoś chce rzucić okiem?",
                        "Dodałem animację do sekcji programu wydarzenia. Jeśli ktoś ma pomysł na bardziej dynamiczną prezentację, chętnie posłucham!",
                        "Zajmuję się teraz integracją z Google Maps, żeby dodać mapę lokalizacji wydarzenia."},
                {
                        "Zakończyłem implementację systemu logowania. Teraz dodaję funkcję powiadomień push o promocjach.",
                        "Zajmuję się teraz integracją z systemem płatności. Ktoś zna dobre API do tego?",
                        "Push na GitHub! Dodałem funkcję przeglądania produktów. Teraz planuję dodać opcję filtrowania po kategoriach.",
                        "Potrzebuję pomocy w synchronizacji aplikacji z systemem zarządzania zamówieniami na backendzie.",
                        "Dodałem możliwość śledzenia statusu zamówienia. Wkrótce dodam sekcję z historią zamówień."},
                {
                        "Zakończyłem implementację funkcji przesyłania filmów. Teraz dodaję możliwość komentowania.",
                        "Czy ktoś zna API do przesyłania wideo w czasie rzeczywistym? Chciałbym dodać tę funkcję.",
                        "Zajmuję się teraz implementacją systemu oceniania filmów. Ktoś może podpowiedzieć, jak to zaimplementować w bazie danych?",
                        "Zrobiłem push na GitHub! Dodałem opcję tworzenia kont użytkowników.",
                        "Potrzebuję pomocy w optymalizacji ładowania wideo. Jakie biblioteki do tego wykorzystujecie?"},
                {
                        "Zajmuję się teraz systemem logowania i rejestracji użytkowników. Wkrótce push na GitHub.",
                        "Zakończyłem część dotyczącą przesyłania filmów. Potrzebuję pomocy z tagowaniem filmów w bazie danych.",
                        "Zrobiłem refaktoring kodu dla filtrów wideo. Teraz wszystko działa szybciej. Jeśli ktoś ma sugestie na temat optymalizacji, chętnie posłucham.",
                        "Dodałem system powiadomień o nowych filmach. Zajmuję się teraz filtrowaniem treści według kategorii.",
                        "Potrzebuję pomocy przy integracji z YouTube API, żeby umożliwić użytkownikom dodawanie filmów z YouTube."},
                {
                        "Zakończyłem implementację sekcji o działalności organizacji. Teraz pracuję nad formularzem kontaktowym.",
                        "Dodałem integrację z mediami społecznościowymi. Ktoś zna jakieś dobre biblioteki do integracji z Twitterem i Instagramem?",
                        "Zrobiłem push na GitHub! Strona jest już responsywna, teraz zajmuję się sekcją projektów.",
                        "Potrzebuję pomocy przy implementacji systemu zarządzania projektami – ktoś ma doświadczenie w tym temacie?",
                        "Dodałem animacje do strony głównej, aby była bardziej interaktywna. Wkrótce dodam też sekcję blogową."},
                {
                        "Zakończyłem część dotyczącą dodawania i edytowania artykułów. Teraz zajmuję się systemem komentarzy.",
                        "Dodałem funkcję subskrypcji nowych wpisów. Ktoś zna bibliotekę do łatwego zarządzania subskrypcjami?",
                        "Zrobiłem push na GitHub! Strona jest już responsywna i łatwa w nawigacji.",
                        "Potrzebuję pomocy z optymalizowaniem wyszukiwania artykułów – kto zna dobre techniki do tego?",
                        "Zajmuję się teraz dodawaniem kategorii artykułów. Chciałbym, aby użytkownicy mogli je łatwo przeglądać."},
                {
                        "Zakończyłem implementację importu danych z plików CSV. Teraz pracuję nad wizualizacją danych.",
                        "Zajmuję się teraz tworzeniem raportów. Ktoś zna najlepsze biblioteki do generowania wykresów?",
                        "Zrobiłem push na GitHub! Dodałem opcję importu danych z API. Teraz dane są dynamicznie pobierane.",
                        "Potrzebuję pomocy przy optymalizacji zapytań do bazy danych, żeby system działał szybciej przy większych zbiorach danych.",
                        "Zajmuję się teraz generowaniem tabel z wynikami analiz. Jeśli ktoś zna techniki optymalizacji w tym zakresie, chętnie posłucham."},
                {
                        "Zakończyłem implementację profili użytkowników. Teraz dodaję możliwość tworzenia postów.",
                        "Potrzebuję pomocy przy systemie prywatności. Jakie techniki stosujecie, żeby zapewnić bezpieczne ustawienia prywatności?",
                        "Zrobiłem push na GitHub! Dodałem możliwość tworzenia grup i zapraszania do nich użytkowników.",
                        "Zajmuję się teraz systemem powiadomień o nowych postach. Ktoś ma pomysł na optymalizację tego procesu?",
                        "Zajmuję się teraz filtrowaniem treści według tagów. Ktoś może podpowiedzieć najlepszą metodę w tym przypadku?"},
                {
                        "Zakończyłem implementację odtwarzacza wideo. Teraz zajmuję się obsługą subskrypcji użytkowników.",
                        "Dodałem system rekomendacji na podstawie oglądanych treści. Ktoś zna algorytmy, które mogę zastosować, żeby były bardziej trafne?",
                        "Zrobiłem push na GitHub! Strona działa już stabilnie, teraz zajmuję się optymalizacją ładowania treści.",
                        "Potrzebuję pomocy w integracji z różnymi formatami wideo. Ktoś zna sprawdzone narzędzia do tego?",
                        "Zajmuję się teraz systemem oceniania treści wideo. Jeśli ktoś ma pomysł na dodanie bardziej zaawansowanego systemu oceny, chętnie wysłucham!"},
                {
                        "Zakończyłem sekcję tworzenia kursów. Teraz dodaję możliwość śledzenia postępów użytkowników.",
                        "Potrzebuję pomocy w implementacji testów dla kursów. Jakie narzędzia do tego polecacie?",
                        "Zrobiłem push na GitHub! Dodałem możliwość zdobywania certyfikatów po ukończeniu kursów.",
                        "Zajmuję się teraz integracją interaktywnych materiałów edukacyjnych. Ktoś zna ciekawe rozwiązania w tej kwestii?",
                        "Zajmuję się teraz dodawaniem opcji oceniania kursów. Jeśli ktoś zna dobre rozwiązania do tego, chętnie je poznam."},
                {
                        "Zakończyłem dodawanie funkcji śledzenia treningów. Teraz dodaję integrację z urządzeniami do aktywności.",
                        "Potrzebuję pomocy w integracji z bazą danych do przechowywania historii treningów. Ktoś zna dobrą bibliotekę?",
                        "Zrobiłem push na GitHub! Aplikacja działa płynnie, teraz zajmuję się interfejsem do monitorowania postępów.",
                        "Zajmuję się teraz tworzeniem przypomnień o treningach. Ktoś zna bibliotekę, która mogłaby to uprościć?",
                        "Zajmuję się teraz sekcją celów fitness. Chciałbym, żeby użytkownicy mogli ustawiać cele i monitorować postępy."
                },
                {
                        "Zakończyłem część dotyczącą przeglądania produktów. Teraz dodaję filtrowanie według ceny i opinii użytkowników.",
                        "Zajmuję się teraz integracją z systemem płatności online. Jeśli ktoś zna dobre API do tego, będę wdzięczny za sugestie!",
                        "Zrobiłem push na GitHub! Dodałem możliwość śledzenia zamówień.",
                        "Potrzebuję pomocy w optymalizacji wyszukiwania produktów po kategoriach. Ktoś ma sprawdzone rozwiązanie?",
                        "Zajmuję się teraz systemem recenzji produktów. Jeśli ktoś ma pomysły na poprawienie tego, chętnie je przyjmę."
                },
                {
                        "Zakończyłem implementację interaktywnych lekcji. Teraz dodaję funkcję śledzenia postępów użytkownika.",
                        "Potrzebuję pomocy przy integracji z API tłumaczeń. Ktoś ma doświadczenie z tym tematem?",
                        "Zrobiłem push na GitHub! Dodałem system quizów, teraz zajmuję się integracją z bazą danych.",
                        "Zajmuję się teraz implementacją możliwości dostosowywania poziomu trudności. Ktoś ma pomysł na łatwe zarządzanie tym?",
                        "Zajmuję się teraz funkcją nagród za postępy. Jakie mechanizmy motywacyjne proponujecie do takich aplikacji?"
                },
                {
                        "Zakończyłem integrację z kalendarzem dostępności. Teraz zajmuję się integracją z płatnościami online.",
                        "Potrzebuję pomocy w implementacji powiadomień e-mailowych o potwierdzeniu rezerwacji. Ktoś zna dobre narzędzia do tego?",
                        "Zrobiłem push na GitHub! Zaimplementowałem rezerwację usług, teraz zajmuję się testowaniem na różnych urządzeniach.",
                        "Zajmuję się teraz integracją z systemami zarządzania hotelami/restauracjami. Ktoś ma doświadczenie w tej integracji?",
                        "Potrzebuję pomocy przy obsłudze błędów przy rezerwacjach. Jakie techniki stosujecie, aby takie problemy były łatwe do zarządzania?"
                },
                {
                        "Zakończyłem część dotyczącą importu danych z systemów ERP i CRM. Teraz pracuję nad wizualizacją danych.",
                        "Dodałem możliwość generowania wykresów i raportów. Ktoś zna jakieś fajne narzędzia do generowania interaktywnych wykresów?",
                        "Zrobiłem push na GitHub! Dodałem możliwość tworzenia zapytań do danych z różnych źródeł.",
                        "Zajmuję się teraz poprawą wydajności zapytań w bazie danych. Ktoś ma pomysł na optymalizację w tym zakresie?",
                        "Potrzebuję pomocy przy integracji z narzędziami do analizy trendów rynkowych. Ktoś może polecić coś sprawdzonego?"
                },
                {
                        "Zakończyłem część dotyczącą przypisywania zadań do członków zespołu. Teraz dodaję powiadomienia o nowych zadaniach.",
                        "Zajmuję się teraz generowaniem raportów o postępach w projektach. Ktoś zna dobre biblioteki do generowania raportów?",
                        "Zrobiłem push na GitHub! Dodałem system powiadomień, teraz zajmuję się uprawnieniami użytkowników.",
                        "Potrzebuję pomocy przy implementacji systemu powiadomień o nowych zadaniach. Jakie rozwiązania możecie polecić?",
                        "Zajmuję się teraz sekcją generowania raportów z postępów w projekcie. Ktoś zna sprawdzone rozwiązania do tego?"
                },
                {
                        "Zakończyłem część dotyczącą tworzenia reklam. Teraz dodaję możliwość targetowania grup docelowych.",
                        "Potrzebuję pomocy przy analizie skuteczności kampanii. Jakie narzędzia do analizy wyników kampanii internetowych polecacie?",
                        "Zrobiłem push na GitHub! Dodałem integrację z Google Analytics do śledzenia wyników kampanii.",
                        "Zajmuję się teraz możliwością śledzenia efektywności reklam. Jeśli ktoś ma pomysł na rozbudowanie tej funkcji, chętnie wysłucham!",
                        "Zajmuję się teraz integracją z różnymi platformami reklamowymi. Ktoś ma doświadczenie w pracy z API Facebook Ads?"
                },
                {
                        "Zakończyłem implementację systemu tworzenia list zadań. Teraz zajmuję się systemem przypomnień.",
                        "Potrzebuję pomocy w integracji aplikacji z kalendarzem. Ktoś zna jakąś bibliotekę, która to ułatwi?",
                        "Zrobiłem push na GitHub! Dodałem możliwość przypisywania zadań do osób, teraz zajmuję się interfejsem użytkownika.",
                        "Zajmuję się teraz powiadomieniami o nadchodzących terminach. Ktoś zna dobrą bibliotekę do wysyłania powiadomień?",
                        "Zajmuję się teraz sekcją generowania raportów z postępów w zadaniach. Jeśli ktoś ma pomysł na optymalizację tego procesu, chętnie wysłucham!"
                },
                {
                        "Zakończyłem projektowanie sekcji usług doradczych. Teraz zajmuję się dodawaniem formularza kontaktowego.",
                        "Potrzebuję pomocy w integracji z systemem CRM. Ktoś zna jakieś popularne narzędzia?",
                        "Zrobiłem push na GitHub! Dodałem sekcję zespół ekspertów, teraz pracuję nad integracją z mediami społecznościowymi.",
                        "Zajmuję się teraz implementacją strony kontaktowej. Jeśli ktoś zna dobre rozwiązania dla formularzy kontaktowych, chętnie je poznam.",
                        "Zajmuję się teraz sekcją referencji i opinii klientów. Ktoś ma doświadczenie w implementacji takiej funkcji?"
                }
        };

        Path path;
        StringBuilder sb;
        for (int i = 0; i < comments.length; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < comments[i].length; j++) {
                Random random = new Random();
                sb.append(comments[i][j]).append("|").append("1733933568135").append("|").append(random.nextInt(50)).append("\n");
            }
            path = Paths.get("comments_" + i + ".csv");
            try {
                Files.writeString(path, sb, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            } catch (IOException e) {
                logger.severe("Tworzenie pliku z opisem projektu zakończyło się niepowodzeniem! " + e.getMessage());
            }
        }

        String sampleUsers = """
                \n1,m.kowalski,$2a$10$J9h9Oz9F1d1xlc7/m1MGEygPbkSBc93tDb/ISdN.ZyfmGeFhGlkCO,Marcin,Kowalski,false
                2,p.nowak,$2a$10$wz8q5d2CwJ6pPpNkBCdKr8obVne66zFeCUPcJfjfHV7zVznvD4g8O,Piotr,Nowak,true
                3,j.kaczmarek,$2a$10$czQmIxyjp9Svm6T1dU0X9f2jRMiRuErkQQ0oiW2A7ay30Xb11sjs2,Jan,Kaczmarek,false
                4,s.mazur,$2a$10$wJ4dF6H/wYckVGjkg1GpPqN54OYZwGj9yQWhLRPZT/G67c/4unmUO,Stefan,Mazur,true
                5,d.wisniewski,$2a$10$h/P11pZjOdbUs5k55UuuyC3KrJdHEPpkqgTRp.AmZTnlfEYppBv5p,Dawid,Wisniewski,false
                6,k.kalinski,$2a$10$eWErBdQn1Tzgr1bV9KhPS/OxVvTRrDECKuWaQDoMQK2h/oDSEbpnK,Kamil,Kalinski,true
                7,r.zielinski,$2a$10$K9eT7nQczJZnBwXYmfG9B6Vbi93BBU8r.Gg1aY93OGvUB9pkeqAem,Radoslaw,Zielinski,false
                8,l.jankowski,$2a$10$A9gOWJZXYtIHHMAsUJ8Uxxzyknn2VrDNORe4L7X8D5u7fuA7F3cqa,Lukasz,Jankowski,true
                9,t.dudek,$2a$10$G4Kzx9DhxhknUNvWQbpG5ZC5VURmOSUzJ4IjX2ZYBD3weUu5XW1gk,Tomasz,Dudek,false
                10,w.pawlowski,$2a$10$D3/yGdYy1ysYMnPruIGv9mc2Fl0hYt.y6lTkFdl2K6T9K1AovB6qF2,Wojciech,Pawlowski,true
                11,j.wozniak,$2a$10$OxM7rfkqX1eQk7Bo7Bq2P0FZ6A1V28gpNKrqVj2EGpy2RgW7iAg1S,Jakub,Wozniak,false
                12,m.jablonski,$2a$10$kFf5v7YiwV3RReTeYf4jjEV6HlAF9Z.z4sKzdUp7xZbxJY12t8yFu,Maciej,Jablonski,true
                13,a.walczak,$2a$10$cm7UmiAcOCtAdS7Bhxu.1rgf5e7TUlHNmTZlLXqvD5RP5gW8U8mFTi,Agnieszka,Walczak,false
                14,o.stanek,$2a$10$BvIu.B1NkK2LhsYMc9wne4aWT3CV1c4lHtS2ah.EH6nFcXwmBcBtwq,Oliwia,Stanek,true
                15,b.czerwinski,$2a$10$4OZtDFBx2MmxEm1nJthUJe4WhCxkfiMmuEXwAYpz1LBJ3xTehkcZYq,Bartosz,Czerwinski,false
                16,p.krol,$2a$10$ebj20xOhoNHsmS4cDlK8qVAnVe.sEij4gi4V5N8RsK2OXY3pY50geq,Piotr,Krol,true
                17,a.szymanski,$2a$10$ZKZCw.A42.MzPO0Wg1QmWsCIHhf0Izg3XwlCzAydovh8SO7BjlZ8dO,Anna,Szymanski,false
                18,d.szewczyk,$2a$10$uJj70zTkmbymQPo1DL6Q8hChhxMF0h4RkYFQfiX5Hi95.3LtSx4jK,Dawid,Szewczyk,true
                19,s.bartosz,$2a$10$7uAOVkIKb2rBCX0p3M1FzUMGozpzjV18K98d5DehPOubQxPTgt0iA,Sebastian,Bartosz,false
                20,l.niemczyk,$2a$10$cz9MhzBw6i6XrZ4b8UgFNVbxGGdH2bbFzDztpOt9idL4k9cZCZp4V,Lukasz,Niemczyk,true
                21,j.gorski,$2a$10$XnVUnZGUl5OFIYBHTTx8ZYmOnqa09goZWjv2BQiv7eCgyPYe9s5Du,Jaroslaw,Gorski,false
                22,t.mikolajczyk,$2a$10$ldXxzrlxzL3z3iAnM7zy6kTX94mnNeIcH2FhyAWw2oxLfL9c48liM,Tomasz,Mikolajczyk,true
                23,m.krawczyk,$2a$10$58XmsXI7dYjwKmcVrFZ5/Fkrw2TxReNlKf4IRpS2R6OjqPoMbm8VjQ,Monika,Krawczyk,false
                24,p.rutkowski,$2a$10$JvBceETUtnPQ70l.ZSKJJeRlF9pHkge8t/WjT2mxaQuwCp5w9nLhM,Piotr,Rutkowski,true
                25,l.wasilewski,$2a$10$SgsYQXkZqF37FwSH7wwgn49b72F6V1p2gA.BtbkFJITXh.c7l1ksM,Lukasz,Wasilewski,false
                26,k.budzyn,$2a$10$SckMlQIBzz.xcPy1C8aE2gFcwr.xkZj0rV.aF16wpwOQU24wY/jT6g,Kamil,Budzyn,true
                27,t.rosinski,$2a$10$UV0uTpTLG6ZVhys2.dffBF9tI/ccaq9o.GzFfeP32ch6doF8TtrnO,Tomasz,Rosinski,false
                28,l.kaminski,$2a$10$guv2zXYpdZ5w5vB94fVuOj/O0eTxKMssry72sF46NK.MuovZY0KcK,Lukasz,Kaminski,true
                29,j.pawlak,$2a$10$8VZ9I3Phrwh1LHoICfW9h6LMzp1HFJ1Tb2xGG.cEZo4Lgklcq5sVS,Jan,Pawlak,false
                30,m.duda,$2a$10$z0mf9Xa76dwqAX2RfjmLpqbbhRe6rKvZ4fD3V9lvNRYO.ZHzd5Y0z,Michal,Duda,true
                31,a.kaczmarek,$2a$10$RDApJITX21HlxtwkbkGx4PBGq5VYlRt0gYoW9fF9kvSpLR8RHN7iyu,Aleksandra,Kaczmarek,false
                32,j.piotrowski,$2a$10$aFZJnK7FvHl6P27mKOYa1gdwpZdMyIhT2CV9drqrSk5NUyyRnl7R8o,Jakub,Piotrowski,true
                33,p.baran,$2a$10$uNwqdbwRtczsznniilsm06t3zx0LfFVAY0QsGpEkm7F1mpGT0dyN6,Paweł,Baran,false
                34,l.kubik,$2a$10$Z7FbXouTtizxoQQjHo0PlB2t./K7kWzq7gfZm5eGRh3Gh0gfu9t66,Lukasz,Kubik,true
                35,m.zuraw,$2a$10$EBpOQ2a7C9smNG1ImLCwYH.c4aTx6poV06dH0fD4IrS3mNr8JDFuI,Maciej,Zuraw,false
                36,t.kozlowski,$2a$10$hR7zndxA0T5l1o4hsApgFcCO6wbRjq3A1O0rtczbx1g54QcoTjNSi,Tomasz,Kozlowski,true
                37,j.kwiatkowski,$2a$10$PciQ2VZqg1JGbD.Py8wBoIkyxTbmn9WlwQnS7iy03Yrj55o1LUNgQ,Jakub,Kwiatkowski,false
                38,a.daniel,$2a$10$32tbGRsbtt9qGhZbBfxw/Pa5uAzvAK9dHGoUR.HP8W30rF9rGpMyu,Adam,Daniel,true
                39,k.sienkiewicz,$2a$10$Lqozs8nrzNKINdHFxhLkzLR5kW9zOV3U5F29k5wQpP55HWlxAq7gqG,Katarzyna,Sienkiewicz,false
                40,p.sienkiewicz,$2a$10$F9rAq2Mvj2gq5JvXOqFgTwX44KNBcu8gI0n8Vx46HXIbNwZsh1ldk,Pawel,Sienkiewicz,true
                41,j.michalski,$2a$10$g76yhz9OeOCpAnrM8JgWeXyy9e1FjymJ9YoOG0kmL9.AKgL9WyWsi,Jacek,Michalski,false
                42,a.zawisza,$2a$10$2F5ew8v/mhr6lsD/R8kfeAW7nLy6H60V1htlxH8aLMrs99Hi3tU6ti,Andrzej,Zawisza,true
                43,t.szczepanski,$2a$10$QZ1vlwFe1k9VfRlt7T9acKBj6H2OVlV1XHeHkzTYit4gK1uoADZ3Hq,Tomasz,Szczepanski,false
                44,p.olejnik,$2a$10$SXh8fXXhPE0k0bHVx8n15cNKbgwLrk0NDO0QjZjC4yZd35lm5OxyS,Paweł,Olejnik,true
                45,l.borkowski,$2a$10$NY7rGvqa6hAKxXwddwmp2T3DQBGfhIVtHTKaaBz7zmlvZX7eKkwY1i,Lukasz,Borkowski,false
                46,j.kos,$2a$10$4xq7kQ9jwdujrsc5PvIHeKzlm5Blwx7.tU3MS7ljc5KHqmy3wvv6q,Jacek,Kos,true
                47,a.rogowski,$2a$10$7JYtZtVqU3SKPTK5Er5Xtq2oW8vgqfAd1eXuPyyXeQNW7tnNdp5G6,Andrzej,Rogowski,false
                48,d.wozniak,$2a$10$VKOD9ZZ3VXoEbfTll0S9zDa7QYY/JUkWaMGqBbmHLmksmGMGo7N16,Dariusz,Wozniak,true
                49,p.jablonski,$2a$10$g39DLcLl4/d0mrP4OBEl4X4RzMPB1ru1y3HlgDfv.bzUwv2VZpebS,Piotr,Jablonski,false""";

        Path pathUsers = Paths.get("users.csv");
        try {
            Files.writeString(pathUsers, sampleUsers, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Nie udało się utworzyć pliku użytkowników! " + e.getMessage());
            return false;
        }
        return true;
    }

    public static int fileLines(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lineCount++;
                }
            }
            return lineCount;
        } catch (IOException e) {
            logger.warning("Błąd podczas odczytu pliku " + file + ": " + e.getMessage());
            return -1;
        }
    }
}
