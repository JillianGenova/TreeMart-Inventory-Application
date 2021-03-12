/*
this is the Product class which represents the type that all objects in the tree will be
 */
public class Product
{
    double ID;
    String name;
    double quantity_sold;
    double quantity_available;
    double cost;
    double retail_price;

    public Product(double id, String name, double qs, double qa, double c, double rp)
    {
        this.ID = id;
        this.name = name;
        this.quantity_sold = qs;
        this.quantity_available = qa;
        this.cost = c;
        this.retail_price = rp;
    }

    public double getRetailPrice()
    {
        return retail_price;
    }

    public double getCost()
    {
        return this.cost;
    }

    public double getQuantityAvailable() {
        return this.quantity_available;
    }

    public double getQuantitySold()
    {
        return this.quantity_sold;
    }

    public String getName()
    {
        return this.name;
    }

    public double getID()
    {
        return this.ID;
    }
}