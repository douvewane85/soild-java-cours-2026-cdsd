package cours.approch1;

public class Rectangle extends Figure {
   private double longeur;


   
    public Rectangle() {
        super(TypeFigure.Rectangle);
}


    public double getLongeur() {
    return longeur;
}

      private double largeur;
    @Override
    public double surface() {
        return longeur*largeur;
    }
    
}
