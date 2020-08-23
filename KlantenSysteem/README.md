# Client System
The client system for the Corendon FYS project. The project was built in the Netbeans IDE.

## Requirements
- Java 8
- JavaFX - **[Download here](https://gluonhq.com/products/javafx)**
- Maven
- MySQL

## Installation/Configuration
1. Open the project in **Intelij**
2. Wait for the indexing process and click build
3. You will see several errors
4. Extract the downloaded **JavaFX** zip somewhere
5. In Intelij, from the main menu, select **File | Project Structure**
6. Open the **Libraries** section, click the New Project Library, and select **Java**
7. Specify the path to the **lib** folder in the JavaFX SDK package, for example: ```/Users/jetbrains/Desktop/javafx-sdk-12/lib```
8. Apply the changes and click ok
9. Go to **File > Settings**
10. In Settings go to **Appearance & Behavior > System Settings > Path Variables**
11. Click on **+** and add new path variable name it **PATH_TO_FX** and in value field locate the **JavaFX-sdk lib** folder
12. Apply the changes and click ok
13. Then go to **Run > Add/Edit Configurations**
14. Select **Application**
15. Give it a name and select the main class
16. Apply the changes and click ok

## Database
1. Check the credentials of your MySQL database in MyJDBC.java. Change the username and password.
```
private static final String DB_DEFAULT_ACCOUNT = "root";
private static final String DB_DEFAULT_PASSWORD = "admin";
```
2. Create a database named **fys** in your MySQL
3. Import the SQL file, which can be found in the SQL folder
4. Go to Intelij and click run


## Sources
- [Jetbrains Help](https://www.jetbrains.com/help/idea/javafx.html#vm-options)
- [StackOverflow](https://stackoverflow.com/questions/53668630/how-to-run-javafx-applications-in-intellij-idea-ide)
