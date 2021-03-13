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
 * Product class which represents the type that all objects in the tree will be
 */
public class Product implements ProductInterface
{
    double ID;
    String name;
    double quantity_sold;
    double quantity_available;
    double cost;
    double retail_price;

    // please make sure you add in comments
    public Product(double id, String name, double qs, double qa, double c, double rp)
    {
        this.ID = id;
        this.name = name;
        this.quantity_sold = qs;
        this.quantity_available = qa;
        this.cost = c;
        this.retail_price = rp;
    }
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
    public double getQuantityAvailable() {
        return this.quantity_available;
    }
    @Override
    public double getQuantitySold()
    {
        return this.quantity_sold;
    }
    @Override
    public String getName()
    {
        return this.name;
    }
    @Override
    public double getID()
    {
        return this.ID;
    }
}