## Adapter selection category for Android App dzen.ru

The android program allows users to select their preferred categories. For this, a **RecyclerView** is used, which displays a list of categories. Each category is represented as an element with a name and a flag that indicates that the category is selected.

The user can select or deselect a category by tapping an item in the RecyclerView. Information about the selected categories is stored in the **Room** database and can be used in the future, depending on the logic of the program.

<img src="https://raw.githubusercontent.com/Khokhlinvladimir/Category_selection_dzen.ru/main/screens/screen_gif.gif" alt="" width="200px"></a>  <img src="https://github.com/Khokhlinvladimir/Category_selection_dzen.ru/blob/main/screens/screen_photo_2.jpg?raw=true" alt="" width="200px"></a> <img src="https://github.com/Khokhlinvladimir/Category_selection_dzen.ru/blob/main/screens/screen_photo_1.jpg?raw=true" alt="" width="200px"></a> 

The program uses the **MVVM** (Model-View-ViewModel) architecture to separate the application logic from its presentation. The Model stores the data, the View displays it, and the ViewModel provides the interaction between the Model and the View. This improves testability and segregation of duties.

The program also uses **coroutines** to manage asynchronous operations and avoid blocking the user interface. Coroutines allow you to run tasks in the background and perform some actions after these tasks are completed.

The program also uses several libraries from android jetpack, such as **LifeCycle**, **LiveData** and **ViewModel**, to improve the application lifecycle experience and simplify data handling.
