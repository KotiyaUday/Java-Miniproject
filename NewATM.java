import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class User
{
    private String holderName;
    private int accNum;
    private int age;
    private int ATMCode;
    public int validate=1;
    boolean isValidate=true;

    public void getInformation()
    {
        // Creating a Scanner class object.
        Scanner scan=new Scanner(System.in);

        // Getting a User name as input from user name and validate the user enter data
        do
        {
            System.out.print("Enter Account Holder Name : ");
            holderName=scan.nextLine();
            for (int i = 0; i < holderName.length(); i++) 
            {
                if(holderName.charAt(i)>'0' && holderName.charAt(i)<'9')
                {
                    System.out.println("Please Enter valid User Name.");
                    isValidate=true;
                    break;
                }
                else
                {
                    isValidate=false;
                }
            }
        }while(isValidate);

        isValidate=true;
        // Getting a User account number as input from user and validate the user enter data
        do{
            try
            {
                System.out.print("Enter Account Number : ");
                Scanner scanInt=new Scanner(System.in);
                accNum=scanInt.nextInt();
                isValidate=false;
                 
                // checking account number not a duplicate.
                isValidate=check();

                int ac=accNum;
                int length=0;
                while(ac!=0)
                {
                    ac/=10;
                    length++;
                }

                if(length<6 || length>6)
                {
                    System.out.println("Enter valid account number.");
                    isValidate=true;
                }
            }
            catch(Exception e)
            {
                System.out.println("Enter valid account number.");
                isValidate=true;
            }
        }while(isValidate);
        
        // Getting a user age as input from user and validate the user enter data
        do{
            System.out.print("Enter Age : ");
            try
            {
                Scanner scanInt=new Scanner(System.in);
                age=scanInt.nextInt();
                isValidate=false;
            }
            catch(Exception e)
            {
                System.out.println("Enter valid Age.");
                isValidate=true;
            }
        }while(isValidate);
        
        // Checking Age of user for check is able or not for ATM Card
        if(age<18)
        {
            System.out.println("You are under age please Registera After : "+(18-age)+" Years.");
            validate=0;
        }
        else
        {
            ATMCode=(int)(Math.random()*10000);
            if(ATMCode<1000)
            {
                ATMCode *=10;
            }
        }
    }

    public void storeIntofile()
    {
        try
        {
            FileWriter fw=new FileWriter("Accounts.txt",true);
            String str=holderName+"\n"+accNum+"\n"+ATMCode+"\n"+"0\n"+"$$$"+"\n";
            fw.append(str);

            System.out.println("\n\n\t\t\t\t\t\t--------------------- ATM Registeration Successfully --------------------- \n\n");

            System.out.println("Name : "+holderName+"\nAccount Number : "+accNum+"\nAGE : "+age+"\nATM Code : "+ATMCode+" (Please Note This Code For First Time use ATM Card.)");

            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public boolean check()
    {
        // User name
        // Account number
        // ATM Code
        // $$$
        // User name
        // Account number
        // ATM Code
        // $$$
        String str;
        boolean val=false;
        File f=new File("Accounts.txt");
        if(f.exists())
        {
            try
            {
                Scanner scan=new Scanner(f);
                ArrayList<String> info=new ArrayList<String>();
                while(scan.hasNextLine())
                {
                    str=scan.nextLine();
                    info.add(str);
                }
                for(String str1:info)
                {
                    String ac=String.valueOf(accNum);
                    if(str1.equalsIgnoreCase(ac))
                    {
                        val=true;
                        break;
                    }        
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else
        {
            System.out.println("File not Found.");
        }
        if(val)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

 
// This is a Main Class.
class NewATM
{
    public static void main(String args[])
    {
        System.out.println("\n\n\t\t\t\t\t\t--------------------- ATM Registeration Form ---------------------\n\n");

        // Creating a User Class Object
        User u=new User();

        // Calling a getInformation() method of class user.
        u.getInformation();

        // It's Check a user is able to get ATM Card.
        if(u.validate!=0)
        {
            u.storeIntofile();
        }
    }
}