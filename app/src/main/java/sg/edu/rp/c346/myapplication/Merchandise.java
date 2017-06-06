package sg.edu.rp.c346.myapplication;

/**
 * Created by 15017103 on 6/6/2017.
 */

public class Merchandise {
    private int id;
    private String itemName;
    private int quantity;
    private Double price;

    public Merchandise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
