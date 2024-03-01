package peggame;

public class Move {
    

    private Location from;
    public Location getFrom() {
        return from;
    }

    private Location to;
    public Location getTo() {
        return to;
    }



    public Move(Location from, Location to){
        this.from=from;
        this.to=to;
    }

    
}
