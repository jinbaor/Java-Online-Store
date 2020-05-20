// CS2336 TermProject Store.java
// Jin Chen  12/6/2018

// Book.java
public class Book extends CatalogItem
{
// class instances
protected String author;
protected int ISBN;
private double discount;

// constructor
Book(String argTitle, double argPrice, String argAuthor, int argISBN)
{
this.title = argTitle;
this.price = argPrice;
this.author = argAuthor;
this.ISBN = argISBN;
this.discount = 0.8;
}
// getter and setter methods
public double getDiscount()
{
return discount;
}
public void setDiscount(double discount)
{
this.discount = discount;
}
public void setAuthor(String author)
{
this.author = author;
}
public void setISBN(int iSBN)
{
ISBN = iSBN;
}
public String getAuthor()
{
return this.author;
}
public int getISBN()
{
return this.ISBN;
}

// toString()
public String toString()
{
return "Title: " + this.title + " | Author: " + this.author + " | Price: " + this.price + " | ISBN: "
+ this.ISBN;
}
}
