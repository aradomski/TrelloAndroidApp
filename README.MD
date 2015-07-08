##Wymagania:
1. Android Studio 1.3(beta) lub nowsze
1. Java 8
1. compileSdkVersion 22
1. buildToolsVersion 22.0.1



W przypadku wystąpienia błędu podobnego do:

    Error:(1, 0) Plugin is too old, please update to a more recent version,
    or set ANDROID_DAILY_OVERRIDE environment variable to "placeholder"
    <a href="fixGradleElements">Fix plugin version and sync project</a><br><a href="openFile">Open File</a>

proszę się stosować do poleceń IDE.


##Założenia:

1. Wszystkie klucze zostaną dostarczone przez osobę odbierającą aplikacje

    Plik build.gradle należy uzupełnić następująco:


    ```	
    buildConfigField "String", "API_URL", "\"https://api.trello.com/1\""
    buildConfigField "String", "BOARD_ID", "\"placeholder\"" //Krótkie ID tablicy My_Board
    buildConfigField "String", "BOARD_ID_API", "\"placeholder\""
    //Długie ID tablicy My_Board
    buildConfigField "String", "APP_KEY", "\"placeholder\""
    //Klucz aplikacji
    buildConfigField "String", "TOKEN", "\"placeholder\""
    // Token aplikacji otrzymany po wejściu na:
    // https://trello.com/1/authorize?key=[klucz     aplikacji]&name=TroelloApp&expiration=never&response_type=token&scope=read,write
    // gdzie [klucz aplikacji] zastępujemy kluczem aplikacji (APP_KEY)
    buildConfigField "String", "LIST_1", "\"placeholder\"" //  ID listy To do
    buildConfigField "String", "LIST_2", "\"placeholder\"" // ID listy Doing
    buildConfigField "String", "LIST_3", "\"placeholder\"" // ID listy Done
    ```
    
Należy zwrócić uwagę na wartości muszą się znajdować w takim: "\"WARTOŚĆ\"" ciągu znaków.

##Obsługa apliakcji
Prawidłowo skonfigurowana aplikacja uruchomiona na telefonie powinna wyświetlić listę "Doing" na pierwszym ekranie. Gestami swipe można przechodzić pomiędzy kolejnmi listami.

Krótkie dotknęcie karty powoduje otwarcie okna edycji karty.

Długie dotknięcie karty powoduje jej usunięcie.

Po naciśnięciu guzika "Create new card" otwiera się formularz tworzenia nowej karty.

