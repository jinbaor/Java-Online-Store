// CS2336 TermProject Store.java
// Jin Chen  12/6/2018

// StoreManager.java
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
public class StoreManager
{
// create a static arraylists
static ArrayList<Book> bookCata = new ArrayList<Book>();
static ArrayList<DVD> dvdCata = new ArrayList<DVD>();
// processStoreManager() will process the options as mentioned in the
// question like, add/remove book/AudioBook/Dvd
public static void processStoreManager()
{
int menuOption = 0;
Scanner userInput = new Scanner(System.in);
// Temporary variables to hold user input untill they are put into an object
int ISBN;
String title;
String author;
double price;
double runtime;
String director;
int year;
int dvdcode;
Validator validate = new Validator();
do
{
displayMenu();
menuOption = userInput.nextInt();
switch (menuOption)
{
case 1:
// Add Book and Gather user input
System.out.println("Please enter the Book details as prompted: ");
System.out.println("\nEnter book ISBN number: ");
ISBN = userInput.nextInt();
userInput.nextLine();
if (searchForBook(bookCata, ISBN) == -1)
{
System.out.println("Enter book title: ");
title = userInput.nextLine();
while (!validate.isNonEmptyString(title))
{
// Validate title
System.out.println("Enter book title: ");
title = userInput.nextLine();
}
System.out.println("Enter author of the book: ");
author = userInput.nextLine();
while (!validate.isNonEmptyString(author))
{
System.out.println("Enter author of the book: ");
author = userInput.nextLine();
}
System.out.println("Enter book price: ");
price = userInput.nextDouble();
while (!validate.isPositive(price))
{
System.out.println("Enter book price: ");
price = userInput.nextDouble();
}
// Add book to array list
bookCata.add(new Book(title, price, author, ISBN));
System.out.println("Book added!\n");
}
else
{
System.out.println("\nThis ISBN is already in the system.\n");
}
break;
case 2:
// Add AudioBook
System.out.println("Please enter the Audio Book details as prompted: ");
System.out.println("\nEnter audio book ISBN number: ");
ISBN = userInput.nextInt();
userInput.nextLine();
if (searchForBook(bookCata, ISBN) == -1)
{
System.out.println("Enter audio book title: ");
title = userInput.nextLine();
while (!validate.isNonEmptyString(title))
{
// Validate title
System.out.println("Enter audio book title: ");
title = userInput.nextLine();
}
System.out.println("Enter author of the audio book: ");
author = userInput.nextLine();
while (!validate.isNonEmptyString(author))
{
System.out.println("Enter author of the audio book: ");
author = userInput.nextLine();
}
System.out.println("Enter price of the audio book: ");
price = userInput.nextDouble();
while (!validate.isPositive(price))
{
System.out.println("Enter price of the audio book: ");
price = userInput.nextDouble();
}
System.out.println("Enter runtime of the audio book: ");
runtime = userInput.nextDouble();
while (!validate.isPositive(runtime))
{
System.out.println("Enter runtime of the audio book: ");
runtime = userInput.nextDouble();
}
// Add AudioBook to array list
bookCata.add(new AudioBook(title, price, author, ISBN, runtime));
System.out.println("AudioBook added!");
}
else
{
System.out.println("\nThis ISBN is already in the system.\n");
}
break;
case 3:
// Add DVD
System.out.println("Please enter the DVD details as prompted: ");
System.out.println("\nEnter DVD Code: ");
dvdcode = userInput.nextInt();
userInput.nextLine();
if (searchForDVD(dvdCata, dvdcode) == -1)
{
System.out.println("Enter title of the movie: ");
title = userInput.nextLine();
while (!validate.isNonEmptyString(title))
{
System.out.println("Enter title of the movie: ");
title = userInput.nextLine();
}
System.out.println("Enter director of the movie: ");
director = userInput.nextLine();
while (!validate.isNonEmptyString(director))
{
System.out.println("Enter the director of the movie: ");
director = userInput.nextLine();
}
System.out.println("Enter price of the movie: ");
price = userInput.nextDouble();
while (!validate.isPositive(price))
{
System.out.println("Enter the price of the movie: ");
price = userInput.nextDouble();
}
System.out.println("Enter release year of the movie: ");
year = userInput.nextInt();
while (!validate.isPositive(year))
{
System.out.println("Enter release year of the movie: ");
year = userInput.nextInt();
}
dvdCata.add(new DVD(title, price, director, year, dvdcode));
System.out.println("DVD added!");
}
else
{
System.out.println("\nThis DVD Code is alread in the system.\n");
}
break;
case 4:
// Remove Book
System.out.println("\nPlease provide the ISBN of the book you wish to remove: ");
ISBN = userInput.nextInt();
int bookIndex = searchForBook(bookCata, ISBN);
if (bookIndex == -1)
{
System.out.println("\nThis ISBN does not exist in the system.\n");
}
else
{
bookCata.remove(bookIndex);
System.out.println("\nBook removed!\n");
}
break;
case 5:
// Remove DVD
System.out.println("\nPlease provide the DVD Code of the movie you wish to remove: ");
dvdcode = userInput.nextInt();
int dvdIndex = searchForDVD(dvdCata, dvdcode);
if (dvdIndex == -1)
{
System.out.println("\nThis DVD Code does not exist in the system.\n");
}
else
{
dvdCata.remove(dvdIndex);
System.out.println("\nDVD removed!\n");
}
break;
case 6:
// Display Catalog
displayBooks();
System.out.println(
"--------------------------------------------------------------------------------------");
displayDVDs();
break;
case 7:
// Create backup of the store
createBackUp();
case 9:
// Exit Store
System.out.println("Exiting Store!");
break;
default:
System.out.println("Invalid menu option selected");
break;
}
Collections.sort(bookCata);
Collections.sort(dvdCata);
System.out.println();
} while (menuOption != 9);
}
// displayMenu() Displays menu options to the manager
public static void displayMenu()
{
System.out.println("\n**Welcome to the Comets Books and DVDs Store (Catalog Section)**\n");
System.out.println("Choose from the following options:");
System.out.println("1 - Add Book\n2 - Add AudioBook\n3 - Add DVD\n4 - Remove Book\n5 - Remove DVD\n"
+ "6 - Display Catalog\n7 - Create backup file\n9 - Exit Store\n");
System.out.print("\nPlease select a menu option: ");
}
// searchForBook() method searches for the book isbn and returns the index
// of the isbn of the bookList
public static int searchForBook(ArrayList<Book> bookList, int codeToSearchFor)
{
for (int i = 0; i < bookList.size(); i++)
{
if (bookList.get(i).getISBN() == codeToSearchFor)
{
return i;
}
}
return -1;
}
// searchForDVD() method searches for the dvd code and returns the index of the
// dvdcode of the dvdList
public static int searchForDVD(ArrayList<DVD> dvdList, int dvdCode)
{
for (int i = 0; i < dvdList.size(); i++)
{
if (dvdList.get(i).getDvdcode() == dvdCode)
{
return i;
}
}
return -1;
}
// to retrieve the arraylist for books
public static ArrayList<Book> getBooksList()
{
return bookCata;
}
// to retrieve the arraylist for the dvds
public static ArrayList<DVD> getDVDList()
{
return dvdCata;
}
// to display books
public static void displayBooks()
{
System.out.println("Books:\n");
for (int i = 0; i < bookCata.size(); i++)
{
System.out.println(bookCata.get(i).toString());
}
}
// to display dvds
public static void displayDVDs()
{
System.out.println("DVDs:\n");
for (int x = 0; x < dvdCata.size(); x++)
{
System.out.println(dvdCata.get(x).toString());
}
}
// to generate the backup copy of the current items list
public static void createBackUp()
{
String fileName = "catalog_backup_";
Date presentDate = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
fileName = fileName + dateFormat.format(presentDate);
PrintWriter pw = null;
try
{
pw = new PrintWriter(new File(fileName));
pw.println("Books: ");
for (int i = 0; i < bookCata.size(); i++)
{
if (bookCata.get(i) instanceof Book)
{
Book book = (Book) bookCata.get(i);
pw.println(book.toString());
}
}
pw.println("Audio Books: ");
for (int i = 0; i < bookCata.size(); i++)
{
if (bookCata.get(i) instanceof AudioBook)
{
AudioBook audiobook = (AudioBook) bookCata.get(i);
pw.println(audiobook.toString());
}
}
pw.println("DVDs: ");
for (int i = 0; i < dvdCata.size(); i++)
{
if (dvdCata.get(i) instanceof DVD)
{
DVD dvds = (DVD) dvdCata.get(i);
pw.println(dvds.toString());
}
}
pw.close();
}
catch (Exception e)
{
System.out.println(e.getMessage());
}
}
}