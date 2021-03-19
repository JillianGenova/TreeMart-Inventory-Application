// --== CS400 File Header Information ==--
// Name: Yash Butani
// Email: @wisc.edu
// Team: Blue
// Group: KE
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 *
 * Product class which represents the type that all objects in the tree
 */
public class Product implements ProductInterface,Comparable<Product>
{
    int ID;
    String name;
    int quantity_sold;
    int quantity_available;
    double cost;
    double retail_price;

    //constructor takes in an id, name, quantity sold, quanity available, cost and retail price
    public Product(int id, String name, int qs, int qa, double c, double rp)
    {
        this.ID = id;
        this.name = name;
        this.quantity_sold = qs;
        this.quantity_available = qa;
        this.cost = c;
        this.retail_price = rp;
    }

    public boolean addStock(int amount){
        if(quantity_available + amount >= 0)
        {
            quantity_available += amount;
        }
        else
            return false;
    }

    // getters for variables
    @Override
    public double getRetailPrice()
    {
        return retail_price;
    }
    @Override
    public double getCost()
    {
        return this.cost;
    }
    @Override
    public int getQuantityAvailable() {
        return this.quantity_available;
    }
    @Override
    public int getQuantitySold()
    {
        return this.quantity_sold;
    }
    @Override
    public String getName()
    {
        return this.name;
    }
    @Override
    public int getID()
    {
        return this.ID;
    }

    /**
     * @return a String that details the ID,name,quantity sold, quantity available, cost and retail price
     */
    @Override
    public java.lang.String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", quantity_sold=" + quantity_sold +
                ", quantity_available=" + quantity_available +
                ", cost=" + cost +
                ", retail_price=" + retail_price +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Product o) {
        if(this.ID > o.getID()){return 1;}
        else if(this.ID < o.getID()){return -1;}
        return 0;
    }
}
