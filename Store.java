// CS2336 TermProject Store.java
// Jin Chen  12/6/2018

// Store.java
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.text.DecimalFormat;
public class Store
{
// define and declare the required items
public static Validator validate;
public static Scanner usrInput = new Scanner(System.in);
static ArrayList<Book> booksList = StoreManager.getBooksList();
static ArrayList<DVD> dvdList = StoreManager.getDVDList();
static ArrayList<String> cartItems = new ArrayList<String>();
public static double totalCartItemsCost = 0;
public static StoreManager storeManager;
// main method
public static void main(String[] args)
{
// initialize the Validator object and
// StoreManager object
validate = new Validator();
storeManager = new StoreManager();
char input = ' ';
// loop to perform the shopping operations by store manager and customer
do
{
// display the menu
System.out.println("**Welcome to the Comets Books and DVDs Store**");
System.out.println("Please select your role: ");
System.out.println("A ¨C store manager");
System.out.println("B ¨C customer");
System.out.println("C ¨C exit store");
// validating the input
do
{
System.out.print("\nEnter your option: ");
input = usrInput.next().charAt(0);
} while (input < 'A' || input > 'C');
// check for the cases to be performed
// as per the user choice
switch (input)
{
// to perform the storemanager process
case 'A':
processManager(storeManager);
break;
// to perform customer process
case 'B':
processCustomer();
break;
// to exit from shopping
case 'C':
System.out.println("See you next time!");
System.exit(0);
break;
// default option
default:
System.out.println("This option is not acceptable");
}
System.out.println();
} while (true);
}
// processManager() to process action by the store manager
public static void processManager(StoreManager storeManager)
{
String userName = "";
String password = "";
Scanner fileInput = null;
boolean flag = false;
// read user name and password
do
{
System.out.print("Please enter your username: ");
userName = usrInput.nextLine();
} while (!validate.isNonEmptyString(userName));
do
{
System.out.print("Please enter your password: ");
password = usrInput.nextLine();
} while (!validate.isNonEmptyString(password));
// check for the credentials from the credentials.txt file
try
{
fileInput = new Scanner(new File("credentials.txt"));
while (fileInput.hasNextLine())
{
String lines[] = fileInput.nextLine().split(",");
// check for validity
if (lines[0].equals(userName) && lines[1].equals(password))
{
// if valid, set flag to true and break the loop
flag = true;
break;
}
}
// using is method which if true, then close the input file and call the processStoreManager of the StoreManager
if (flag)
{
fileInput.close();
storeManager.processStoreManager();
}
// otherwise display an information to the user
else
{
System.out.println("Sorry! Credintials does not match.\n Try again!");
}
}
catch (Exception e)
{
System.out.println("Unable to process with the file.");
}
}
// processCustomer() to process access for the customer
public static void processCustomer()
{
String itemTitle = "";
int index = 0;
int menuOp;
do
{
displayMenu();
menuOp = getMenuInput();
switch (menuOp)
{
case 1:
// browse for books
displayBooks(booksList);
break;
case 2:
// Browse for DVDs
displayDVDs(dvdList);
break;
case 3:
// Add book to cart
itemTitle = getBooksInventory();
cartItems.add(booksList.get(index).getTitle());
break;
case 4:
// Add DVD to cart
itemTitle = getDVDInventory();
cartItems.add(itemTitle);
break;
case 5:
// removebook from cart
RemoveBookFromCart();
break;
case 6:
RemoveDVDFromCart();
break;
case 7:
// View cart
displayCart();
break;
case 8:
// Checkout
displayTotalCostOfItems();
break;
case 9:
System.out.println("Exiting store. Have a nice day!");
break;
default:
System.out.println("This option is not acceptable");
}
} while (menuOp != 8);
}
// to display the menu for the customer about the cart
public static void displayMenu()
{
System.out.print("\n**Welcome to the Comets Books and DVDs Store**\n\n"
+ "Choose from the following options:\n" + "1 - Browse books inventory (price low to high)\n"
+ "2 - Browse DVDs inventory (price low to high)\n" + "3 - Add a book to the cart\n"
+ "4 - Add a DVD to the cart\n" + "5 - Delete a book from cart\n" + "6 - Delete a DVD from cart\n"
+ "7 - View cart\n" + "8 - Checkout\n" + "7 - Cancel Order\n" + "8 - Exit store\n\n"
+ "Please select a menu option: ");
}
// read input from the user
public static int getMenuInput()
{
int input;
try
{
input = usrInput.nextInt();
}
catch (InputMismatchException ex)
{
input = -1;
System.out.println("You did not enter a whole number menu option!");
}
if (0 > input || 9 < input)
{
System.out.println("Your input was not a valid menu option!");
input = -1;
}
return input;
}
// to display the books list
public static void displayBooks(ArrayList<Book> books)
{
for (int i = 0; i < books.size(); i++)
{
System.out.println((i + 1) + " - " + books.get(i).toString());
}
}
// to display the dvd list
public static void displayDVDs(ArrayList<DVD> dvds)
{
for (int i = 0; i < dvds.size(); i++)
{
System.out.println((i + 1) + " - " + dvds.get(i).toString());
}
}
// to remove a book from the cart
public static void RemoveBookFromCart()
{
displayCart();
String name = "";
do
{
System.out.println("Enter the item name: ");
name = usrInput.nextLine();
} while (!validate.isNonEmptyString(name));
for (int i = 0; i < cartItems.size(); i++)
{
if (cartItems.get(i).equalsIgnoreCase(name))
{
cartItems.remove(i);
}
}
}
// to remove a dvd from the cart
public static void RemoveDVDFromCart()
{
displayCart();
String name = "";
do
{
System.out.println("Enter the item name: ");
name = usrInput.nextLine();
} while (!validate.isNonEmptyString(name));
for (int i = 0; i < cartItems.size(); i++)
{
if (cartItems.get(i).equalsIgnoreCase(name))
{
cartItems.remove(i);
}
}
}
// to display items in the cart
public static void displayCart()
{
System.out.println("Items in the cart are: ");
for (int i = 0; i < cartItems.size(); i++)
{
for (int j = 0; j < booksList.size(); j++)
{
if (cartItems.get(i).equals(booksList.get(j).getTitle()))
System.out.println(booksList.get(j) + " | Discount: " + booksList.get(i).getDiscount() * 100 + "%");
}
for (int j = 0; j < dvdList.size(); j++)
{
if (cartItems.get(i).equals(dvdList.get(j).getTitle()))
System.out.println(dvdList.get(j) + " | Discount: " + dvdList.get(i).getDiscount() * 100 + "%");
}
}
}
// to get the name of the book from the books list
public static String getBooksInventory()
{
int invenNum = 0;
do
{
displayBooks(booksList);
System.out.println("Please enter an inventory number: ");
invenNum = usrInput.nextInt();
} while (!validate.isPositive(invenNum));
return booksList.get(invenNum - 1).getTitle();
}
// to get the name of the dvd from the dvd list
public static String getDVDInventory()
{
int invenNum = 0;
do
{
displayDVDs(dvdList);
System.out.println("Please enter an inventory number: ");
invenNum = usrInput.nextInt();
} while (!validate.isPositive(invenNum));
return dvdList.get(invenNum - 1).getTitle();
}
// to display the final amount to the user and to clear the cart list
// and remove items from the books or dvd arraylist when items have been
// checked out. also update the list of the store manager
public static void displayTotalCostOfItems()
{
DecimalFormat formatCost = new DecimalFormat("###.00");
System.out.println("Items in the cart are: ");
System.out.println("Item_Name \t Item_Price \t Item_Discount \t Price_After_Discount");
double amount = 0;
String price, discount, totalPrice;
double overallAmount = 0;
for (int i = 0; i < cartItems.size(); i++)
{
for (int j = 0; j < booksList.size(); j++)
{
if (cartItems.get(i).equals(booksList.get(j).getTitle()))
{
amount = booksList.get(i).getPrice() * booksList.get(i).getDiscount();
overallAmount += amount;
price = formatCost.format(booksList.get(i).getPrice());
discount = formatCost.format(booksList.get(i).getDiscount() * 100);
totalPrice = formatCost.format(amount);
System.out.println(
booksList.get(j).getTitle() + "\t" + price + "\t" + discount + "% \t $" + totalPrice);
booksList.remove(j);
}
}
for (int j = 0; j < dvdList.size(); j++)
{
if (cartItems.get(i).equals(dvdList.get(j).getTitle()))
{
amount = dvdList.get(i).getPrice() * dvdList.get(i).getDiscount();
overallAmount += amount;
price = formatCost.format(dvdList.get(i).getPrice());
discount = formatCost.format(dvdList.get(i).getDiscount() * 100);
totalPrice = formatCost.format(amount);
System.out.println(
dvdList.get(j).getTitle() + "\t" + price + "\t" + discount + "% \t $" + totalPrice);
dvdList.remove(j);
}
}
}
System.out.println("--------------------------------------------------------------------------");
System.out.println("Total Price:$" + formatCost.format(overallAmount));
System.out.println("--------------------------------------------------------------------------");
cartItems.clear();
System.out.println("Thank You For Shopping. Please Visit Again!");
StoreManager.bookCata = booksList;
StoreManager.dvdCata = dvdList;
}
}