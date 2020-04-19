import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/*
Code Written by - Nishant Pandey
git : https://github.com/unexh
*/
abstract class Food{
    HashMap<String, Double> FoodItems;

    Food(){
        FoodItems = new HashMap<>();
    }

    List<String> returnFoodName(){
        return new ArrayList<>(FoodItems.keySet());
    }
    void printItemList(){
        System.out.println("\n**************************");
        System.out.format("Item:\t\t\tPrice:\n");
        System.out.println("**************************");
        FoodItems.forEach((k, v) -> System.out.format("%-16s%-2.2f\n",k,v));
        System.out.println("**************************");
    }
}

class BreakFast extends Food{
    BreakFast(){
        FoodItems.put("Idli Sambhar",40.0);
        FoodItems.put("Kanda Poha",30.0);
        FoodItems.put("Aalu Paratha",12.0);
        FoodItems.put("Dhokla",70.0);
    }
}
class Lunch extends Food{

    Lunch(){
        FoodItems.put("Rajma Chawal",100.0);
        FoodItems.put("Kadhi Chawal",120.0);
        FoodItems.put("Daal Fry",70.0);
        FoodItems.put("Khichdi",90.0);
    }
}
class Dinner extends Food{

    Dinner(){
        FoodItems.put("Matar Paneer",150.0);
        FoodItems.put("Kadhai Paneer",170.0);
        FoodItems.put("Dum Aloo",100.0);
        FoodItems.put("Veg Biryani",130.0);
    }
}
class FastFood extends Food{
    FastFood(){
        FoodItems.put("Margherita Pizza",100.0);
        FoodItems.put("Cheese Burger",70.0);
        FoodItems.put("Spicy Noodles",120.0);
        FoodItems.put("French Fries",50.0);
    }
}

class Order{
    static int orderSrNumber =1;//increment on each order
    int orderNumber;//set its value as current orderSrNumber
    String userName;//take users name
    boolean bookingTable;
    double totalBill;
    LocalDateTime OrderTime;
    BreakFast vBreakfast;
    Lunch vLunch;
    Dinner vDinner;
    FastFood vFastFood;

    HashMap<String, Integer> FoodCount;
    static HashMap<String,Double> AllFoodPrices;


    Order(){
        this.FoodCount = new HashMap<>();
        this.orderNumber = orderSrNumber;
        orderSrNumber++;
        this.OrderTime = LocalDateTime.now();
        AllFoodPrices = new HashMap<>();
        AllFoodPrices.put("Idli Sambhar",40.0);
        AllFoodPrices.put("Kanda Poha",30.0);
        AllFoodPrices.put("Aalu Paratha",12.0);
        AllFoodPrices.put("Dhokla",70.0);
        AllFoodPrices.put("Rajma Chawal",100.0);
        AllFoodPrices.put("Kadhi Chawal",120.0);
        AllFoodPrices.put("Daal Fry",70.0);
        AllFoodPrices.put("Khichdi",90.0);
        AllFoodPrices.put("Matar Paneer",40.0);
        AllFoodPrices.put("Kadhai Paneer",30.0);
        AllFoodPrices.put("Dum Aloo",12.0);
        AllFoodPrices.put("Veg Biryani",70.0);
        AllFoodPrices.put("Margherita Pizza",100.0);
        AllFoodPrices.put("Cheese Burger",70.0);
        AllFoodPrices.put("Spicy Noodles",120.0);
        AllFoodPrices.put("French Fries",50.0);

    }
    boolean createOrder(){
        Scanner sc = new Scanner(System.in);
        int switchMenu=0;
        boolean Response = true;
        System.out.println("\nOrder Number : "+ this.orderNumber);
        System.out.print("Enter Your Name : ");
        this.userName = sc.nextLine().trim();
        System.out.printf("Hey %s !,What would you like to have Today?\n",this.userName);

        do {
            System.out.println("Choose from the list :");
            System.out.println("********************");
            System.out.println("1. Breakfast");
            System.out.println("2. Lunch");
            System.out.println("3. Dinner");
            System.out.println("4. Fast Food");
            System.out.println("5. Back");
            System.out.println("********************");
            System.out.print("\nResponse (Number): ");
            switchMenu = sc.nextInt();
            System.out.println("********************");
            switch (switchMenu) {
                case 1:
                    System.out.println("Breakfast selected");
                    this.orderBreakfast();
                    break;
                case 2:
                    System.out.println("Lunch selected");
                    this.orderLunch();
                    break;
                case 3:
                    System.out.println("Dinner selected");
                    this.orderDinner();
                    break;
                case 4:
                    System.out.println("Fast Food selected");
                    this.orderFastFood();
                    break;
                case 5:
                    System.out.println("Reverting");
                    Response = false;
                    break;
                default:
                    System.out.println("Invalid selection");

            }
            if(Response){
                System.out.println("Would you like to order food from different menu ? (Y or N)");
                Response = this.checkResponse(sc.next());
            }
        }while (Response);
        //Generate Bill
        System.out.println("Do you want to book a table ? (Y or N)");
        this.bookingTable = checkResponse(sc.next(),"noPrint");
        System.out.println("\n***********************************");
        System.out.println("\t\t\t\tBill");
        System.out.println("***********************************");
        this.createOrderBill();
        System.out.println("Press b for back. e for exit" );
        String newResponse;
        newResponse = sc.next();
        if(newResponse.equals('b'|'B')){
            System.out.println("Reverting");
        }
        else if(newResponse.equals('e'|'E')){
            System.out.println("Quit!");
            System.exit(0);
        }

        return true;
        //return false if error use try catch
    }

    boolean orderBreakfast() {
        boolean response=false;
        String FoodName;
        int FoodCount;

        this.vBreakfast = new BreakFast();
        System.out.println("We have some Refreshing Breakfast to serve you:");
        do {
            Scanner sc = new Scanner(System.in);
            this.vBreakfast.printItemList();
            System.out.println("\nResponse :\nName (Exact String): ");
            FoodName = sc.nextLine().trim();
            System.out.println("Quantity (int): ");
            FoodCount = sc.nextInt();

            if (this.vBreakfast.returnFoodName().contains(FoodName)) {
                System.out.printf("Your, %d %s are waiting in the queue\n", FoodCount, FoodName);
                this.FoodCount.put(FoodName, FoodCount);
            }
            else{
                System.out.printf("Sorry %s , That is an invalid response !!",FoodName);
            }

            System.out.println("Would you like to order more ? (Y or N)");
            response = this.checkResponse(sc.next());
        }while(response);

        this.printOrderCount();
        return true;
    }

    boolean orderLunch() {
        boolean response=false;
        String FoodName;
        int FoodCount;

        this.vLunch = new Lunch();
        System.out.println("We have got some Filling Lunch to serve you:");
        do {
            Scanner sc = new Scanner(System.in);
            this.vLunch.printItemList();
            System.out.println("\nResponse :\nName (Exact String): ");
            FoodName = sc.nextLine().trim();
            System.out.println("Quantity (int): ");
            FoodCount = sc.nextInt();

            if (this.vLunch.returnFoodName().contains(FoodName)) {
                System.out.printf("Your, %d %s are waiting in the queue\n", FoodCount, FoodName);
                this.FoodCount.put(FoodName, FoodCount);
            }
            else{
                System.out.printf("Sorry %s , That is an invalid response !!",FoodName);
            }

            System.out.println("Would you like to order more ? (Y or N)");
            response = this.checkResponse(sc.next());
        }while(response);

        this.printOrderCount();
        return true;
    }
    boolean orderDinner() {
        boolean response=false;
        String FoodName;
        int FoodCount;

        this.vDinner = new Dinner();
        System.out.println("We have some Lovely Dinner to serve you:");
        do {
            Scanner sc = new Scanner(System.in);
            this.vDinner.printItemList();
            System.out.println("\nResponse :\nName (Exact string): ");
            FoodName = sc.nextLine().trim();
            System.out.println("Quantity (int): ");
            FoodCount = sc.nextInt();

            if (this.vDinner.returnFoodName().contains(FoodName)) {
                System.out.printf("Your, %d %s are waiting in the queue\n", FoodCount, FoodName);
                this.FoodCount.put(FoodName, FoodCount);
            }
            else{
                System.out.printf("Sorry %s , That is an invalid response !!",FoodName);
            }

            System.out.println("Would you like to order more ? (Y or N)");
            response = this.checkResponse(sc.next());
        }while(response);

        this.printOrderCount();
        return true;
    }

    boolean orderFastFood() {
        boolean response=false;
        String FoodName;
        int FoodCount;

        this.vFastFood = new FastFood();
        System.out.println("We have some Exotic Fastfood to offer you:");
        do {
            Scanner sc = new Scanner(System.in);
            this.vFastFood.printItemList();
            System.out.println("\nResponse :\nName (Exact string): ");
            FoodName = sc.nextLine().trim();
            System.out.println("Quantity (int): ");
            FoodCount = sc.nextInt();

            if (this.vFastFood.returnFoodName().contains(FoodName)) {
                System.out.printf("Your, %d %s are waiting in the queue\n", FoodCount, FoodName);
                this.FoodCount.put(FoodName, FoodCount);
            }
            else{
                System.out.printf("Sorry %s , That is an invalid response !!",FoodName);
            }

            System.out.println("Would you like to order more ? (Y or N)");
            response = this.checkResponse(sc.next());
        }while(response);

        this.printOrderCount();
        return true;
    }

    boolean checkResponse(String getResponse){
        if(getResponse.equals("Y") | getResponse.equals("Yes") | getResponse.equals("y") | getResponse.equals("yes")){
            System.out.println("\nWhat else would like to order");
            return true;
        }else {
            return false;
        }
    }

    boolean checkResponse(String getResponse,String noPrint){ // Method Overloading
        return getResponse.equals("Y") | getResponse.equals("Yes") | getResponse.equals("y") | getResponse.equals("yes");
    }
    void printOrderCount(){
        System.out.println("\nTill now you have ordered :");
        System.out.println("**************************");
        System.out.format("Item:\t\t\tQuantity:\n");
        System.out.println("**************************");
        this.FoodCount.forEach((k, v) -> System.out.format("%-16s%-2d\n",k,v));
        System.out.println("**************************\n");
    }

    void createOrderBill(){
        DateTimeFormatter formatOrderTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.printf("Order Number : %d\nName \t\t : %s\nBooking time : %s\nTable Booked : %s\n",this.orderNumber,this.userName,this.OrderTime.format(formatOrderTime),this.bookingTable);
        System.out.println("***********************************");
        System.out.println("Inventory");
        System.out.format("Item%-10sQuantity%5sPrice:\n"," "," ");
        System.out.println("***********************************");
        this.FoodCount.forEach((k, v) -> {
            //System.out.println(k + "\t\t"+ v +"\t\t"+ v*(this.AllFoodPrices.get(k)));
            //System.out.println("New Way of list bill\n");
            System.out.format("%-15s %-10d %.2f\n",k,v,v*(AllFoodPrices.get(k)));
            this.totalBill += v*(AllFoodPrices.get(k));
        });
        System.out.println("***********************************");
        System.out.format("Total Bill : %18.1f\n",this.totalBill);
        System.out.println("***********************************");

    }

}

public class CodeImplementionRMS {
    ArrayList<Order> OrderArray;
    CodeImplementionRMS(){
        this.OrderArray = new ArrayList<>();
    }
    static void initialScreen(){
        System.out.println("\n\n************************************************************");
        System.out.println("*******         Restaurant Management System         *******");
        System.out.println("************************************************************");
        System.out.println("What would you like to do ?");
        System.out.println("1. Order Food");
        System.out.println("2. Cancel Order");
        System.out.println("3. Check Waiting Queue");
        System.out.println("4. Exit");
        System.out.println("************************************************************");
        System.out.print("\nResponse (int): ");
    }
    void orderFood(){
        Order newOrder = new Order();
        if(newOrder.createOrder()){
            System.out.println("\n***********************************");
            System.out.println("**   Order placed successfully   **");
            System.out.println("***********************************\n");
            OrderArray.add(newOrder);
        }
        else{
            System.out.println("\n*******************************************");
            System.out.printf("Sorry %s, can't place your order right now !!",newOrder.userName);
            System.out.println("*********************************************\n");
        }
    }

    void orderQueue(){
        System.out.println("\n***********************************");
        System.out.println("\t\t\tTotal Orders");
        System.out.println("***********************************");
        if(OrderArray.isEmpty()){
            System.out.println("\nNo Orders Arrived Yet\n");
        }
        else {
            System.out.println("\nTotal Orders in the Queue : " + OrderArray.size());
            System.out.println("***********************************");
            System.out.println("OrderNo\t\tName\t\tTotal Bill");
            System.out.println("***********************************");
            OrderArray.forEach((Order obj) -> System.out.format("%-11d%s\t\t%.2f\n",obj.orderNumber,obj.userName,obj.totalBill));
        }
        System.out.println("***********************************");
    }
    boolean orderCancel(){
        System.out.println("***********************************");
        System.out.println("\t\t\tCancel Order");
        System.out.println("***********************************");
        if(OrderArray.isEmpty()){
            System.out.println("\nNo Orders Arrived Yet");
            System.out.println("\n***********************************");
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Order Number (int) : ");
            int currentOrderNumber = sc.nextInt();

            System.out.print("Enter Customer Name (str): ");
            String currentUserName = sc.next();
            Order removeOrder = null;
            for(Order obj: this.OrderArray){
                if (obj.userName.equals(currentUserName)  && obj.orderNumber == currentOrderNumber) {
                    removeOrder = obj;
                }
            }
            this.OrderArray.remove(removeOrder);
            System.out.println("***********************************");
            this.orderQueue();
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        CodeImplementionRMS CurrentSessionObject = new CodeImplementionRMS();
        int MainMenuResponse;
        boolean response = true;
        do {
            initialScreen();
            try {
                Scanner scNew = new Scanner(System.in);
                MainMenuResponse = scNew.nextInt();
                switch (MainMenuResponse) {
                    case 1:
                        //Order food Selected
                        CurrentSessionObject.orderFood();
                        break;
                    case 2:
                        //Cancel Order Selected
                        if (CurrentSessionObject.orderCancel()) {
                            System.out.println("Order Cancelled Successfully");
                        }
                        break;
                    case 3:
                        //Waiting Queue Selected
                        System.out.println("Orders Waiting Queue : ");
                        CurrentSessionObject.orderQueue();
                        break;
                    case 4:
                        System.out.println("Exiting");
                        response = false;
                        break;
                    default:
                        System.out.println("Invalid response");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Response,Please Try Again !");
            }
        }while (response);
    }
}

/*
Code Written by - Nishant Pandey
git : https://github.com/unexh
*/