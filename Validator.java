// CS2336 TermProject Store.java
// Jin Chen  12/6/2018

// Validator.java
public class Validator implements Acceptable
{
// isNonEmptyString() accepts a string and returns
// a boolean value
public boolean isNonEmptyString(String s)
{
// TODO Auto-generated method stub
return !s.isEmpty();
}
// isPositive() accepts a double and returns
// a boolean value
public boolean isPositive(double d)
{
if (d>0)
return true;
else
return false;
}

// isPositive() overload method accepts an int and returns
// a boolean value
public boolean isPositive(int n)
{
if (n>0)
return true;
else
return false;
}
}
