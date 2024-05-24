import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class ATM
{
    public static void main(String args[])
    {
        System.out.println("\n\n\t\t\t\t--------------------- Welcome ! --------------------- \n\n");

        userOfATM u=new userOfATM();
        u.general();
    }
}

class userOfATM
{
    private String holderName;
    private int accNum,balance=0;
    private int age,pin;
    boolean isValidate=true;
    ArrayList<String> info=new ArrayList<String>();

    public void general()
    {
        String str;
        File f=new File("Accounts.txt");
        if(f.exists())
        {
            try
            {
                Scanner scan=new Scanner(f);
                while(scan.hasNextLine())
                {
                    str=scan.nextLine();
                    info.add(str);
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

        System.out.print("You are new user ?\n1: Yes\n2: No\n--->");
        Scanner scanUser=new Scanner(System.in);
        int ans = scanUser.nextInt();
        if(ans==2)
        {
            do{
                try
                {
                    System.out.print("Enter Account Number : ");
                    Scanner scanInt=new Scanner(System.in);
                    accNum=scanInt.nextInt();
                    isValidate=false;
                    
                    
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

            
            do{
                try
                {
                    System.out.print("Enter Account PIN : ");
                    Scanner scanInt=new Scanner(System.in);
                    pin=scanInt.nextInt();
                    isValidate=false;
                    
                    
                    int ac=pin;
                    int length=0;
                    while(ac!=0)
                    {
                        ac/=10;
                        length++;
                    }

                    if(length<4 || length>4)
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
            checkUserAvilabel();
        }
        else
        {
            newUser();
        }
    }

    public void checkUserAvilabel()
    {
        int i=check();
        int pin1=Integer.parseInt(info.get(i+2));
        if(pin1==pin)
        {
            balance=Integer.parseInt(info.get(i+3));
            //System.out.println(balance);
            opesen(i);
        }
        else
        {
            System.out.println("Pin Does not match.");
            general();
        }
    }

    public int check()
    {
       int i;
       int val=0;
       // User name
        // Account number
        // ATM Code
        // $$$
        // User name
        // Account number
        // ATM Code
        // $$$
                for(i=1;i<info.size();i++)
                {
                    //System.out.println(i);
                    String ac=String.valueOf(accNum);
                    if(info.get(i).equalsIgnoreCase(ac))
                    {
                        //System.out.println("YEs Check");
                        val=i;
                        break;
                    }
                }
        if(val==i)
        {
            return i;
        }
        else
        {
            return 0;
        }
    }

    public void opesen(int i)
    {
        holderName=info.get(i-1);
        System.out.println("\n\n\t\t\t\t\t\t--------------------- Welcome "+holderName+"--------------------- \n\n");
        int op;
        do
        {
            System.out.print("1: Show Balance\n2: Depocite\n3: Withdraw\n4: Exit\n---->");
            Scanner scan=new Scanner(System.in);
            op=scan.nextInt();
            switch(op)
            {
                case 1:
                    System.out.println("Balance : "+balance);
                    break;
                case 2:
                    System.out.print("How many money do like to depocite :");
                    int depocite=scan.nextInt();
                    balance+=depocite;
                    info.set(i+3,String.valueOf(balance));
                    //info.add(i+4,"$$$");
                    updateFile();
                    System.out.println("Money Depocite Successfully.");
                    break;
                case 3:
                    System.out.println("How many do you liketo withdraw ?");
                    int withdraw=scan.nextInt();
                    if((balance-withdraw)<0)
                    {
                        System.out.println("Money out of bound.");
                    }
                    else
                    {
                        System.out.println("Money Withdraw Succefully.");
                        balance-=withdraw;
                        info.set(i+3,String.valueOf(balance));
                        updateFile();
                        System.out.println("Remaining balancse is : "+(balance));
                    }
                    break;
                case 4:
                    break;
            }
        }while(op!=4);

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n\n\t\t\t\t\t\t--------------------- Welcome ! --------------------- \n\n");
        general();
    }

    public void newUser()
    {
        int i=0;
        isValidate=true;
        do
        {
            try
            {
                System.out.print("Enter Account Number : ");
                Scanner scanInt=new Scanner(System.in);
                accNum=scanInt.nextInt();
                isValidate=false;
                    
                    
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
                i=check();
                if(i==0)
                {
                    isValidate=true;
                }
                else
                {
                    isValidate=false;
                }
                System.out.print("Do you want to exit ?\n1: Yes\n2: No\n--->");
                Scanner scan=new Scanner(System.in);
                int y=scan.nextInt();
                if(y==1)
                {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } 
                    catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\n\n\t\t\t\t\t\t--------------------- Welcome ! --------------------- \n\n");
                    general();
                }
            }
            catch(Exception e)
            {
                System.out.println("Enter valid account number.");
                isValidate=true;
            }
        }while(isValidate);
        setPin(i);
    }

    public void setPin(int i)
    {
        int val=0;
        do{
                try
                {
                    System.out.print("Enter ATM Code : ");
                    Scanner scanInt=new Scanner(System.in);
                    pin=scanInt.nextInt();
                    isValidate=false;
                    
                    
                    int ac=pin;
                    int length=0;
                    while(ac!=0)
                    {
                        ac/=10;
                        length++;
                    }

                    if(length<4 || length>4)
                    {
                        System.out.println("Enter valid ATM Code.");
                        isValidate=true;
                    }

                    String acc=String.valueOf(pin);
                    if(info.get(i+1).equalsIgnoreCase(acc))
                    {
                        val=1;
                        //System.out.println("Yes");
                    }
                    System.out.print("Do you want to exit ?\n1: Yes\n2: No\n---->");
                    Scanner scan=new Scanner(System.in);
                    int y=scan.nextInt();
                    if(y==1)
                    {
                        try {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } 
                        catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
            
                        System.out.println("\n\n\t\t\t\t\t\t--------------------- Welcome ! --------------------- \n\n");
                        general();
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Enter valid ATM Code.");
                    isValidate=true;
                }
            }while(isValidate);

            if(val==1)
            {
                isValidate=true;
                do{
                    try
                    {
                        System.out.print("Enter ATM PIN : ");
                        Scanner scanInt=new Scanner(System.in);
                        pin=scanInt.nextInt();
                        isValidate=false;
                        
                        
                        int ac=pin;
                        int length=0;
                        while(ac!=0)
                        {
                            ac/=10;
                            length++;
                        }

                        if(length<4 || length>4)
                        {
                            System.out.println("Enter valid ATM PIN.");
                            isValidate=true;
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Enter valid ATM Pin.");
                        isValidate=true;
                    }
                }while(isValidate);
                String acc=String.valueOf(pin);
                info.add(i+2,acc);
                // for(String s:info)
                // {
                //     System.out.println(s);
                // }
                boolean res=updateFile();
                if(res)
                {
                    opesen(i);
                }
            }
    }

    public boolean updateFile()
    {
                boolean res=false;
                try
                {
                    FileWriter f=new FileWriter("Accounts.txt");
                    f.close();
                    FileWriter fw=new FileWriter("Accounts.txt",true);
                    for(String str1 : info)
                    {
                        //System.out.println("Yes Write");
                        fw.write(str1+"\n");
                        res=true;
                    }
                    fw.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                return res;
            }
}