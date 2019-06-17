package Pizzeria;
import com.sun.xml.internal.bind.v2.TODO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

interface Item {
    int getPrice();
}

//Exceptions start
class InvalidExtraTypeException extends Exception {
    InvalidExtraTypeException(String message) {
        super(message);
    }
}

class InvalidPizzaTypeException extends Exception {
    public InvalidPizzaTypeException(String message) {
        super(message);
    }
}

class ItemOutOfStockException extends Exception {
    public ItemOutOfStockException(String message) {
        super(message);
    }
}
//Exceptions end

class ExtraItem implements Item  {
    private String type;

    ExtraItem(String type) throws InvalidExtraTypeException {
        List<String> allowedItems = Arrays.asList("Coke", "Ketchup");
        if (allowedItems.contains(type))
            this.type = type;
        else
            throw new InvalidExtraTypeException(type + " is invalid ExtraItem");
    }

    @Override
    public int getPrice() {
        switch (type){
            case "Coke": return 5;
            case "Ketchup": return 3;
            default: return 0;
        }
    }

    //TODO
    //override equals() and hashCode() methods

    @Override
    public String toString() {
        return "ExtraItem: " + type;
    }
}

class PizzaItem implements Item {
    private String type;

    PizzaItem(String type) throws InvalidPizzaTypeException {
        List<String> allowedItems = Arrays.asList("Standard", "Pepperoni", "Vegetarian");
        if (allowedItems.contains(type))
            this.type = type;
        else
            throw new InvalidPizzaTypeException(type + " is invalid PizzaItem");
    }

    @Override
    public int getPrice() {
        switch (type){
            case "Standard": return 10;
            case "Pepperoni": return 12;
            case "Vegetarian": return 8;
            default: return 0;
        }
    }

    //TODO
    //override equals() and hashCode() methods

    @Override
    public String toString() {
        return "PizzaItem: " + type;
    }
}

class Order {
    private ArrayList<Item> items;
    private ArrayList<Integer> counts;

    Order() {
        items = new ArrayList<>();
        counts = new ArrayList<>();
    }

    void addItem(Item item, int count) throws ItemOutOfStockException{
        if (count > 10)
            throw new ItemOutOfStockException(item + " is out of stock");

        if(items.contains(item)){
            int index = items.indexOf(item);
            items.remove(index);
            counts.remove(index);

            items.add(index, item);
            counts.add(index, count);
        } else {
            items.add(item);
            counts.add(count);
        }
    }

    int getPrice() {
        int price = 0;
        for (int i = 0; i < items.size(); i++) {
            price += getTotalPrice(i);
        }
        return price;
    }

    int getTotalPrice(int i){
        return items.get(i).getPrice() * counts.get(i);
    }
}

public class PizzaOrderTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}
