// CS2336 TermProject Store.java
// Jin Chen  12/6/2018

// AudioBook.java
public class AudioBook extends Book
{
// instance variables
private double runningTime;
private double discount;

// Constructor
AudioBook(String title, double price, String author, int ISBN, double runtime)
{
super(title, price, author, ISBN);
this.runningTime = runtime;
discount = 0.5;
}
// getter and setter methods
public double getRunningTime()
{
return this.runningTime;
}

public double getDiscount()
{
return discount;
}
public void setDiscount(double discount)
{
this.discount = discount;
}
public void setRunningTime(double runningTime)
{
this.runningTime = runningTime;
}

// toString() method
public String toString()
{
return "Title: " + this.title + " | Author: " + this.author + " | Price: " + this.price + " | ISBN: "
+ this.ISBN + " | Runtime: " + this.runningTime;
}
}
