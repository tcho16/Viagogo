
package viagogo;

public class Event implements Position {
    private String id;
    private double price;
    private int tickets;
    private int distanceFromLocation;
    private int xPos;
    private int yPos;
    
    Event(String id, double price, int tickets){
    this.id = id;
    this.price = price;
    this.tickets = tickets;
    }
    
    public void setDistance(int distance){
        this.distanceFromLocation = distance;
    }
    
    public int getDistance(){
        return distanceFromLocation;
    }
    
    public int getTickets(){
        
    return tickets;
    }
    
    public String getID(){
    return id;
    }
    
    public double getPrice(){
    return price;
    }
    
    public String toString(){
    return "Event " + this.id + " - $" +  this.price + ", Distance " + this.distanceFromLocation;
    }

    @Override
    public void setX(int x) {
        this.xPos = x;}

    @Override
    public void setY(int y) {
        this.yPos = y;
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
     return yPos;
    }
    
}
