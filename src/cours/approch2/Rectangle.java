package cours.approch2;

public class Rectangle  implements IRectangle{
       private double longeur;
      private double largeur;
   
   
    @Override
    public double surface() {
        return longeur*largeur;
    }

  

  @Override
    public double getLongeur() {
    return longeur;
   }
    
}
