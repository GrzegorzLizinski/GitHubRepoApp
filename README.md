# GitHubRepoApp [ENG]
Mobile application for Android [min SDK 21].
Application for downloading and displaying Allegro organization repositories located on GitHub.
The application also allows you to view the details of the selected repository.

The main assumption when designing the application was to create one activity with three fragments:
- a fragment with a loading animation, while getting the initial data;
- fragment displaying the list of repositories;
- a fragment of the details preview of the selected repository.

## Features
The application was created in the Android Studio environment in Kotlin. 
After receiving the data from the first query using the GET method, the application displays the first page of the downloaded data (the same as on the website - 1 page of the GitHub website = 30 elements). After scrolling to the bottom of the list, the application downloads the next page containing the repositories and adds them to the previously downloaded items.
The list is displayed by the Adapter in RecycleView. Clicking on the appropriate CardView of a given repository brings up the excerpt with the details of that repository.

## Technologies
The following tools and technologies were used to create the project:
- Retrofit
- Dagger2
- Hilt
- MVVM
- LiveData
- Glide
- Data Binding

The PostmanCanary program was used to test the REST API queries.
The jsonviewer.stack.hu website was used to preview and format the received data.

## Further development of the application
Further development of the created application is possible by implementing a search engine, e.g. after the name of the repository. Additionally, it is possible to create a repository sorting function, choose the sorting method and filter the repository list.

# GitHubRepoApp [PL]
Aplikacja mobilna na system Android [min SDK 21].
Służy do pobierania i wyświetlania repozytoriów organizacji Allegro znajdujących się w serwisie GitHub.
Aplikacja pozwala również na podgląd szczegółów wybranego repozytorium.

Głownym założeniem przy projektowaniu aplikacji było utworzenie jednej aktywności z trzema fragmentami: 
- fragment z animacją ładowania, podczas pobierania początkowych danych;
- fragment wyświetlający listę repozytorów;
- fragment podglądu szczegółów wybranego repozytorium.

## Cechy
Aplikacja została utworzona w środowisku Android Studio w języku Kotlin. 
Aplikacja po otrzymaniu danych z pierwszego zapytania metodą GET wyświetla pierwszą stronę pobranych danych (identycznie jak na serwisie www - 1 strona serwisu GitHub = 30 elementów). Po przewinięciu listy na sam dół, aplikacja pobiera kolejną stroną zawierającą repozytoria oraz dodaje je do poprzednio pobranych elementów. 
Lista wyświetlana jest za pomocą Adaptera w RecycleView. Kliknięcie na odpowiedni CardView danego repozytorium wywołuje fragment ze szczegółami tego repozytorium.

## Technologie
Do stworzenia projektu wykorzystano następujące narzędzia i technologie:
- Retrofit
- Dagger2
- Hilt
- MVVM
- LiveData
- Glide
- Data Binding

Do testowania zapytań REST API wykorzystano program PostmanCanary. 
Do podglądu oraz formatowania odebranych danych wykorzystano stronę jsonviewer.stack.hu

## Dalszy rozwój aplikacji
Dalszy rozwój stworzonej aplikacji możliwe jest poprzez implementację wyszukiwarki np. po nazwie repozytorium. Dodatkowo istnieje możliwość utworzenia funkcji sortowania repozytoriów, wyboru sposobu sortowania oraz filtrowanie listy repozytoriów.  
