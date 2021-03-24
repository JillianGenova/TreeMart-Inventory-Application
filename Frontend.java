// --== CS400 File Header Information ==--
// Name: <Leonardo Alfaro>
// Email: <lalfaro2@wisc.edu>
// Team: <BLUE>
// Group: <KE>
// TA: <Keren>
// Lecturer: <Heimerl>
// Notes to Grader: <None>

import java.util.ArrayList;
import java.util.Scanner;
public class Frontend {
  
  private ArrayList<Product> changed;
  private ArrayList<Product> lowProducts;
  private ArrayList<Product> allProducts;
  private Scanner sc = new Scanner(System.in);
  private Backend backend;
  private float netProfit;
  
  public static void main(String[] args) {
    args = new String[] {"listdata.csv"};
    //
    //
    //
    //
    Backend backend = new Backend(args[0]);
    //
    //
    //
    //
    Frontend frontend = new Frontend();
    //
    //
    //
    frontend.netProfit = backend.dailyProfit();
    //
    //
    //
    frontend.run(backend);
  }
  
  public void run(Backend back) {
    backend = back;
    changed = new ArrayList<Product>();
    boolean done = false;
    while(!done) {
      clearScreen();
      String toDo = printHeader();
      printMode(toDo);
      if(toDo.equals("x")) 
        done = true;   
    }
    return;
  }
  
  public String printHeader() {
    System.out.println("+--------------------------------------------+");
    System.out.println("|              Welcome to Our                |");
    System.out.println("|      TreeMart™ Inventory Application       |");
    System.out.println("+--------------------------------------------+");
    System.out.println("What Would You Like To Do?\n------------------------------------");
    System.out.println("Update Invetory(u)\nPrint Menu(p)\nExit(x)");
    String input = sc.next();
    input = valid(input, "Main", sc);
    return input;
  }
  
  public void printMode(String i) {
    clearScreen();
    if(i.equals("u")) {
      System.out.println("+--------------------------------------------+");
      System.out.println("|                  Update                    |");
      System.out.println("|             Inventory  Menu                |");
      System.out.println("+--------------------------------------------+");
      this.updateInventoryMenu();
    }
    if(i.equals("p")) {
      System.out.println("+--------------------------------------------+");
      System.out.println("|                Print Menu                  |");
      System.out.println("+--------------------------------------------+");
      this.printMenu();
    }
    if(i.equals("x")) { 
        System.out.println("+--------------------------------------------+");
        System.out.println("|              Have a Good Day!              |");
        System.out.println("+--------------------------------------------+");
        if(changed == null) {
          System.out.println("Products Changed In This Session: NONE");
        } else {
          System.out.println("Products Changed In This Session: ");
          for(int j = 0; j < changed.size(); j++) 
            System.out.println(changed.get(j).getName());          
        }
        System.out.println("Net Profit: " + netProfit);
    }
  }
  
  public void clearScreen() {
    for (int i = 0; i < 50; ++i)
      System.out.println();
  }
  
  public String valid(String input, String menu, Scanner sc) {    
    if(menu.equals("Main")) {
      while(!input.equals("u") && !input.equals("p") && !input.equals("x")) {
        System.out.println("Please Enter a Valid Option");
        input = sc.next();
      }

    }
    if(menu.equals("Update")) {
      while(!input.equals("i") && !input.equals("d") && !input.equals("a") && !input.equals("x")) {
        System.out.println("Please Enter a Valid Option");
        input = sc.next();
      }
    }
    if(menu.equals("Adding")) {
      while(!input.equals("y") && !input.equals("n")) {
        System.out.println("Please Enter a Valid Option");
        input = sc.next();
      }
    }
    if(menu.equals("Print")) {
      while(!input.equals("l") && !input.equals("f") && !input.equals("s") && !input.equals("x")) {
        System.out.println("Please Enter a Valid Option");
        input = sc.next();
      }
    }
    return input; 
  }
  
  public void updateInventoryMenu() {
    System.out.println("What Would You Like To Do?\n------------------------------------");
    System.out.println("Restock(i)\nDestock(d)\nAdd New Item(a)\nReturn To Main Menu(x)");
    String input = sc.next();
    input = valid(input, "Update", sc);
    if(input.equals("i")) {
      clearScreen();
      //
      //
      //
      for(int i = 0; i < 3; i++) {
        lowProducts = backend.needsRestock(3);
      }
      System.out.println("Low Products:" + lowProducts);
      //
      //
      //
      System.out.println("Which Existing Item Would You Like Increase?\n------------------------------------");
      String input1 = sc.next();
      clearScreen();
      System.out.println("How Much Would You Like To Increase(Integer) " + input1 + " by?\n------------------------------------");
      int input2 = sc.nextInt();
      //
      //
      //
      boolean worked = backend.restock(input1, input2);
      //
      //
      //
      if(worked) {
        clearScreen();
        //
        //
        //
        changed.add(backend.getProduct(input1));
        //
        //
        //
        System.out.println(input1 + " Was Successfully Restocked by " + input2 + "\n------------------------------------");
        System.out.println("------------------------------------\nType Anything To Continue");
        String anything = sc.next();
        printMode("u");
      } else {
        clearScreen();
        System.out.println(input1 + " Was Unsuccessfully Restocked As The Item In Not Within The System" + "\n------------------------------------");
        System.out.println("Type Anything To Continue");
        String anything = sc.next();
        printMode("u");
      }
    }
    if(input.equals("d")) {
      clearScreen();
      System.out.println("Which Existing Item Would You Like to Decrease(Integer)?\n------------------------------------");
      String input1 = sc.next();
      System.out.println("How Much Would You Like To Sell?\n------------------------------------");
      int input2 = sc.nextInt();
      //
      //
      //
      if(backend.getProduct(input1) != null) {
        try {
        	backend.sell(input1, input2);
        	changed.add(backend.getProduct(input1));
        } catch (IllegalArgumentException e) {
        	System.out.print("There was not enough " + input1 + " available to sell");
        }
        
      }
      //
      //
      //
      else {
        clearScreen();
        System.out.println(input1 + " Was Unsuccessfully Destocked As The Item In Not Within The System" + "\n------------------------------------");
        System.out.println("Type Anything To Continue");
        String anything = sc.next();
        printMode("u");
      }
    }
    if(input.equals("a")) {
      clearScreen();
      System.out.println("What Is The Name of The New Product You Would Like to Add?\n------------------------------------");
      String name = sc.next();
      clearScreen();
      System.out.println("How Many " + name + "(s) Are You Adding?\n------------------------------------");
      int amount = sc.nextInt();
      clearScreen();
      System.out.println("How Much Is Each " + name + " Worth?\n------------------------------------");
      Double cost = sc.nextDouble();
      clearScreen();
      System.out.println("At What Retail Price Will " + name +" Be Sold For?\n------------------------------------");      
      Double retail = sc.nextDouble();
      //
      //
      //
      Product fresh = new Product(backend.newProductID(), name, 0, amount, cost, retail);
      //
      //
      //
      System.out.println("Are You Sure You Want To Add " + fresh.toString() + "?(y or n)\n------------------------------------");
      String answer = sc.next();
      answer = valid(answer, "Adding", sc);
      if(answer.equals("y")) {
        //
        //
        System.out.println(fresh);
    	backend.addProduct(fresh);
        changed.add(fresh); // null pointer!!
        this.allProducts = backend.getAllProducts();
        //
        //
        //
      }
      if(answer.equals("n")) 
        printMode("u");
      
    }
    if(input.equals("x")) 
      return;   
  }
  public void printMenu() {
    System.out.println("What would you like to do?\n------------------------------------");
    System.out.println("Look Up Item(l)\nPrint All Products to CSV file(f)\nSummary of Session(s)\nReturn To Main Menu(x)");
    String input = sc.next();
    input = valid(input, "Print", sc);
    if(input.equals("l")) {
      clearScreen();
      System.out.println("Please Insert the Name(String) of the Product You Are Looking For\n------------------------------------");
      String input1 = sc.next();
      //
      //
      //
      while(backend.getProduct(input1) == null) {
        System.out.println("Please Enter The Name of a Product That Exists\n------------------------------------");
        input1 = sc.next();
      }
      //
      //
      //
      //
      ProductInterface got = backend.getProduct(input1);
      //
      //
      //
      System.out.println(got.toString());
      System.out.println("------------------------------------\nType Anything To Continue");
      input = sc.next();
      printMode("p");
    }
    if(input.equals("f")) {
      clearScreen();
      System.out.println("Print Inventory To .csv File\n");
      System.out.println(
          "\nWould You Like To Update The .csv File To Reflect Your New Inventory?\n(Type 'y' or 'n')");
      if (sc.hasNext()) {
        char response = sc.next().charAt(0);
        while (response != 'y' && response != 'n') {
          System.out.println("Invalid Response. (Type 'y' or 'n')");
          response = sc.next().charAt(0);
        }
        if (response == 'y') {
          boolean wrote = filePrinter();
          if(wrote) {
            for(int i = 0; i < allProducts.size(); i++) {
              System.out.println(allProducts.get(i).toString());
            }
            System.out.println("File Successfully Updated. Type Anything To Continue");
            String in = sc.next(); 
          } else {
            System.out.println("File Unsuccessfully Updated. Type Anything To Continue");
            String in = sc.next();  
          }
        }
        printMode("p");
        return;        
      }
    }
    if(input.equals("s")) {
      clearScreen();
      System.out.println("During This Session, The Following Products Were Changed:\n------------------------------------");
      if(changed == null) {
        System.out.println("Products Changed In This Session: NONE");
      } else { 
        for(int i = 0; i < changed.size(); i++) 
          System.out.println(changed.get(i).getName());      
      }
      //
      //
      //      
      netProfit = backend.dailyProfit();      
      //
      //
      //
      System.out.println("The Current Net Profit is: " + netProfit);
      System.out.println("------------------------------------\nType Anything To Continue");
      String anything = sc.next();
      printMode("p");
    }
    if(input.equals("x")) 
      return;   
  }
  public boolean filePrinter() {
    //
    //
    //
    //
    boolean wrote = backend.printToCSV();
    //
    //
    //
    String[] args = new String[] {"listdata.csv"};
    //
    //
    //
    backend = new Backend(args[0]);
    allProducts = backend.getAllProducts();
    //
    //
    return wrote;
  }
  
}