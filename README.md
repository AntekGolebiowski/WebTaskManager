# WebTaskManager
Mój pierwszy program konsolowy - projekt realizowany w trakcie kursu JavaDeveloper w CodersLab. Autor programu - Antoni Gołębiowski. Program konsolowy stworzony do zarządzania projektami dla WebDeveleperów, jest modyfikacją polecenia z CodersLab - pierwotnie aplikacja miała pozwalać na zarządzanie pojedynczymi zadaniami. Modyfikacja programu wynikała z chęci pozyskania nowych umiejętności podczas tworzenia aplikacji oraz wykorzystania posiadanych już przeze mnie umiejętności - tworzenia stron internetowych.

## Zadanie z CodersLab
Program miał zawierać prosty menadżer zadań. Aplikacja miała ładować dane z pliku csv, po czym wyświetlać je na konsoli z możliwością dalszej edytcji, tworzenia nowych zadań i ponownego zapisania zmian do pliku tekstowego.

## Zrealizwaony projekt
Dokonałem modyfikacji polecenia, rozbudowując aplikację. Program nadal pozostaje aplikacją konsolową, dodatkowo pozwala na przejście do strony internetowej (wykorzystałem bibliotekę bootstrap), która wyświetla listę zadań. Przygotowana przeze mnie aplikacja spełnia założenia zadania, dodatkowo została rozbudowana o inne funkcjonalności.

## O programie
Program miał służyć do zarządzania projektami(zadaniami) realizowanymi przez webdeveloperów. Program, po zalogowaniu się przez użytkownika, pozwala na utworzenie, edycję oraz usunięcie projektów. Każdy projekt posiada nazwę oraz opis, a także datę utworzenia oraz termin realizacji. Każdy z projektów można oznaczyć jako "Pilny" lub "Zrealizowany". Tylko użytkownicy z uprawnieniami administratora posiadają możliwość tworzenia oraz edycji projektów.
Program posiada instalator, który uruchamia się wraz ze startem aplikacji w momencie, w którym plik "users.csv" nie został odnaleziony. Podczas instalacji można wypełnić program przykładowymi danymi.
W aplikacji skorzystałem między innymi z loggera, enumeracji, listy, wykorzystałem Thread aby aplikacja jednocześnie tworzyła socked oraz pozwalała na wprowadzanie komend do konsoli.

## Różnice w programie i zadaniu
* Wg. przykładu z Coders Lab aplikacja miała być w języku angielski - jest zmiana kosmetyczna
* Aplikacja wg. przykładu z Coders Lab miała zapisywać dane do pliku dopiero przy zamknięciu aplikacji. W moim programie dane są zapisywane po każdej modyfikacji (tak aby zmiany w przypadku przypadkowego zamknięcia programu nie przepadły).

## Z czego nie jestem zadowolony
* Podczas tworzenia aplikacji na bieżąco szukałem nowych rozwiązań, z powyższego wynika, że kod nie jest spójny - np. dane o użytkownikach są pobierane na bieżąco z pliku users.csv, z kolei projekty po uruchomieniu programu przechowywane są w liście z uwagii na łatwiejsze sortowanie danych.
* Podczas otwierania plików początkowo korzystałem z Files, jednak z biegiem czasu zacząłem wykorzystywać BufferedReader.
* Z uwagii na fakt, że nie planuję rozwijania aplikacji wiele linii kodu jest powielonych. W przyszłych aplikacjach planuję częściej tworzyć nowe metody aby uniknąć powielania kodu.
* Kod pliku http.java został utworzony przez chatgpt, jedynie kod HtML jest przygotowany przeze mnie. Nie modyfikowałem tego kodu, ponieważ na tym etapie kursu jest dla mnie nie zrozumiały, a z uwagii na brak czasu nie miałem okazji przeanalizować jego działania.
* Informacje o udanych i nieudanych operacjach zwracałem z wykorzystaniem Loggera, jednak pojawił się problem "rozjeżdżającego" się tekstu. Opóźnienie działania loggera sprawia, że czasem jego komunikat pojawia się w miejscu, w którym nie powinien się pojawić.

## Czego nie udało się zrealizować
Z uwagii na plany urlopowe i wynikający z nich brak czasu nie utworzyłem panelu administracyjnego, który pozwalał by na tworzenie nowych użytkowników oraz modyfikację/usuwanie już istniejących. Nie zdążyłem też przeprowadzić pełnych testów działania aplikacji, jednak aplikacja była testowana na bieżąco podczas pisania, więc powinna działać poprawnie.
