public class DVD extends CatalogItem
{
// class instances
protected String director;
protected int year;
protected int dvdcode;
private double discount;

// constructor
public DVD(String title, double price, String director, int year, int dvdcode)
{
this.title = title;
this.price = price;
this.director = director;
this.year = year;
this.dvdcode = dvdcode;
discount = 0.8;
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
public void setDirector(String director)
{
this.director = director;
}
public void setYear(int year)
{
this.year = year;
}
public void setDvdcode(int dvdcode)
{
this.dvdcode = dvdcode;
}
public String getDirector()
{
return this.director;
}
public int getYear()
{
return this.year;
}
public int getDvdcode()
{
return this.dvdcode;
}

// toString() method
public String toString()
{
return "Title: " + this.title + " | Director: " + this.director + " | Price: " + this.price + " | Year: " + this.year + " | DvdCode: " + this.dvdcode;
}
}

