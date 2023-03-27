package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Order class tests")
public class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
      order = new Order();
    }

    @Test
    @DisplayName("Add items with valid parameters")
    void testAddItemsValid() {
      Order.Item item1 = new Order.Item("apple", 0.5);
      Order.Item item2 = new Order.Item("banana", 0.75);
      order.addItems(item1, 3);
      order.addItems(item2, 2);
      assertEquals(2, order.getEntryCount());
      assertEquals(5, order.getItemCount());
      assertEquals(3 * 0.5 + 2 * 0.75, order.getTotalPrice());
    }
    

    @Test
    @DisplayName("Add items with invalid parameters")
    void testAddItemsInvalid() {
      Order.Item item = new Order.Item("apple", 0.5);
      try {
        order.addItems(item, -1);
        fail("Expected IllegalArgumentException was not thrown");
      } catch (IllegalArgumentException e) {
        // Pass
      }

      try {
        order.addItems(item, 2);
        order.addItems(new Order.Item("apple", 0.75), 3);
        fail("Expected an IllegalStateException to be thrown");
      } catch (IllegalStateException e) {
        // Pass
      }
    }

    @Test
    @DisplayName("Remove items with valid parameters")
    void testRemoveItemsValid() {
      Order.Item item = new Order.Item("apple", 0.5);
      order.addItems(item, 3);
      assertTrue(order.removeItems("apple", 1));
      assertEquals(1, order.getEntryCount());
      assertEquals(2, order.getItemCount());
      assertEquals(2 * 0.5, order.getTotalPrice());
    }

  
    @Test
    @DisplayName("Remove items with invalid parameters")
    void testRemoveItemsInvalid() {

    try {
      order.removeItems("apple", -1);
      fail("Expected IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException e) {
      // Pass
    }

    try {
      order.removeItems("apple", 1);
      fail("Expected NoSuchElementException to be thrown");
    } catch (NoSuchElementException e) {
      // Pass
    }
  
  }
    @Test
    @DisplayName("Add items by name with valid parameters")
    void testAddItemsByNameValid() {
      Order.Item item = new Order.Item("apple", 0.5);
      order.addItems(item, 3);
      assertTrue(order.addItems("apple", 2));
      assertEquals(1, order.getEntryCount());
      assertEquals(5, order.getItemCount());
      assertEquals(3 * 0.5 + 2 * 0.5, order.getTotalPrice());
    }

    @Test
    @DisplayName("Add items by name with invalid parameters")
    void testAddItemsByNameInvalid() {
      try {
        order.addItems("apple", -1);
        fail("Expected an IllegalArgumentException to be thrown");
      } catch (IllegalArgumentException e) {
        // Pass
      }
    
      try {
        order.addItems("apple", 1);
        fail("Expected a NoSuchElementException to be thrown");
      } catch (NoSuchElementException e) {
        // Pass
      }
    
    }

    @Test
    @DisplayName("Get entries in original adding order")
    void testGetEntries() {
      Order.Item item1 = new Order.Item("apple", 0.5);
      Order.Item item2 = new Order.Item("banana", 0.75);
      order.addItems(item1, 3);
      order.addItems(item2, 2);
      assertEquals(2, order.getEntries().size());
      assertEquals("apple", order.getEntries().get(0).getItemName());
      assertEquals("banana", order.getEntries().get(1).getItemName());
    }
    
    @Test
    public void testItemMethods() {
      Order.Item item = new Order.Item("apple", 0.5);

      // Test getName() method
      assertEquals("apple", item.getName());

      // Test getPrice() method
      assertEquals(0.5, item.getPrice(), 0.0);

      // Test toString() method
      assertEquals("Item(apple, 0.50)", item.toString());
    }

    @Test
    public void testEquals() {
      // Create two identical items
      Order.Item item1 = new Order.Item("apple", 0.75);
      Order.Item item2 = new Order.Item("apple", 0.75);
      
      // Create a different item
      Order.Item item3 = new Order.Item("orange", 1.25);
      
      // Test that item1 equals item2, and item2 equals item1
      assertTrue(item1.equals(item2));
      assertTrue(item2.equals(item1));
      
      // Test that item1 does not equal item3, and item3 does not equal item1
      assertFalse(item1.equals(item3));
      assertFalse(item3.equals(item1));
      
      // Test that item2 does not equal item3, and item3 does not equal item2
      assertFalse(item2.equals(item3));
      assertFalse(item3.equals(item2));
      
      // Test that an item equals itself
      assertTrue(item1.equals(item1));
    }

    @Test
    void testGetItemName() {
      Order.Item item = new Order.Item("apple", 0.75);
      Order.Entry entry = new Order.Entry(item, 2);
      assertEquals("apple", entry.getItemName());
    }

    @Test
    void testGetUnitPrice() {
      Order.Item item = new Order.Item("apple", 0.75);
      Order.Entry entry = new Order.Entry(item, 2);
      assertEquals(0.75, entry.getUnitPrice());
    }

    @Test
    void testGetItem() {
      Order.Item apple = new Order.Item("apple", 0.75);
      Order.Entry entry = new Order.Entry(apple, 2);
      assertEquals(apple, entry.getItem());
    }
  
    @Test
    void testGetCount() {
      Order.Item apple = new Order.Item("apple", 0.75);
      Order.Entry entry = new Order.Entry(apple, 2);
      assertEquals(2, entry.getCount());
    }

    /* 
    @Test
    public void testAddCount() {
      Order.Item item = new Order.Item("apple", 1.00);
      Order.Entry entry = new Order.Entry(item, 2);

      entry.addCount(3);
      assertEquals(5, entry.getCount());
    }

    @Test
    public void testRemoveCount() {
      Order.Item item = new Order.Item("apple", 1.00);
      Order.Entry entry = new Order.Entry(item, 5);

      entry.removeCount(2);
      assertEquals(3, entry.getCount());
    }

    @Test
    public void testGetTotalPrice() {
      Order.Item item = new Order.Item("apple", 1.00);
      Order.Entry entry = new Order.Entry(item, 3);

      double expectedTotalPrice = 3 * item.getPrice();
      assertEquals(expectedTotalPrice, entry.getTotalPrice(), 0.01);
    }*/



    @Test
    void testGetEntryCount() {
      Order order = new Order();
      assertEquals(0, order.getEntryCount());
        
      order.addItems(new Order.Item("apple", 0.75), 2);
      assertEquals(1, order.getEntryCount());
        
      order.addItems(new Order.Item("banana", 1.25), 1);
      assertEquals(2, order.getEntryCount());
    }

    @Test
    void testGetItemCount() {
      Order order = new Order();
      assertEquals(0, order.getItemCount());
        
      order.addItems(new Order.Item("apple", 0.75), 2);
      assertEquals(2, order.getItemCount());
        
      order.addItems(new Order.Item("banana", 1.25), 1);
      assertEquals(3, order.getItemCount());
    }

    @Test
    void testIsEmpty() {
      Order order = new Order();
      assertTrue(order.isEmpty());
        
      order.addItems(new Order.Item("apple", 0.75), 2);
      assertFalse(order.isEmpty());
        
      order.removeItems("apple", 2);
      assertTrue(order.isEmpty());
    }


}
