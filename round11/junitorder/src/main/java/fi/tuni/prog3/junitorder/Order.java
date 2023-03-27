package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.text.DecimalFormat;


public class Order extends Object {


  public static class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) throws IllegalArgumentException {
      if(name == null || price < 0) {
        throw new IllegalArgumentException("Name must not be null and price greater than 0");
      } 
      this.name = name;
      this.price = price;
    }

    public String getName() {
      return name;
    }

    public double getPrice() {
      return price;
    }

    @Override
    public String toString() {
      String a = String.format("Item(%s, ", name);
      String b = String.format("%.2f)", price).replace(",", ".");

      //return String.format("Item(%s, %.2f)", name, price).replace(",", ".");
      //return String.format("Item(%s, %s)", name, new DecimalFormat("#.##0.00").format(price));
      return a+b;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Item) {
        Item other = (Item) obj;
        return name.equals(other.name);
      }
      return false;
    }
  }

  public static class Entry {
    private final Item item;
    private int count;

    public Entry(Item item, int count) throws IllegalArgumentException {
      if (count <= 0) {
        throw new IllegalArgumentException("Item count must be positive");
      }
      this.item = item;
      this.count = count;
    }

    public String getItemName() {
      return item.getName();
    }

    public double getUnitPrice() {
      return item.getPrice();
    }

    public Item getItem() {
      return item;
    }

    public int getCount() {
      return count;
    }
    
    public void addCount(int count) {
      this.count += count;
    }

    public void removeCount(int count) {
      this.count -= count;
    }

    public double getTotalPrice() {
      return item.getPrice() * count;
    }
    
    @Override
    public String toString() {
      return count + " units of " + item;
    }
  } 

  // The list of item entries in the order
  private final List<Entry> entries;

  public Order() {
    entries = new ArrayList<Entry>();
  }

  public boolean addItems(Item item, int count) throws IllegalArgumentException, IllegalStateException {
    if (count <= 0) {
      throw new IllegalArgumentException("Invalid item unit count: " + count);
    }
    for (Entry entry : entries) {

      if (entry.getItemName().equals(item.getName()) && entry.getUnitPrice() != item.getPrice()) {
        throw new IllegalStateException("An existing entry has the same item name but a different price");
      }
      if (entry.getItemName().equals(item.getName())) {
        entry.addCount(count);
        return true;
      }

    }
    entries.add(new Entry(item, count));
    return true;
  }

  public boolean addItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
    if (count <= 0) {
      throw new IllegalArgumentException("Item unit count to add must be positive.");
    }

    boolean added = false;

    for (Entry entry : entries) {
      if (entry.getItemName().equals(name)) {
        entry.addCount(count);
        added = true;
        break;
      }
    }

    if (!added) {
      throw new NoSuchElementException("Order does not contain an entry with the specified item name.");
    }

    return true;
}

  public List<Entry> getEntries() {
    List<Entry> copy = new ArrayList<Entry>(entries);
    return copy;
  }

  public int getEntryCount() {
    return entries.size();
  }

  public int getItemCount() {
    int count = 0;
    for (Entry entry : entries) {
      count += entry.getCount();
    }
    return count;
  }

  public double getTotalPrice() {
    double total = 0.0;
    for (Entry entry : entries) {
      total += entry.getTotalPrice();
    }
    return total;
  }

  public boolean isEmpty() {
    return entries.isEmpty();
  }

  public boolean removeItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
    if (count <= 0) {
      throw new IllegalArgumentException("Invalid item unit count: " + count);
    }
    for (Entry entry : entries) {
      if (entry.getItemName().equals(name)) {

        if (count > entry.getCount()) {
          throw new IllegalArgumentException("Item unit count to remove is larger than existing count: " + count + " > " + entry.getCount());
        }
        entry.removeCount(count);
        if (entry.getCount() == 0) {
          entries.remove(entry);
        }
        return true;
      }
    }
    throw new NoSuchElementException("No such item in order: " + name);
  }
}
