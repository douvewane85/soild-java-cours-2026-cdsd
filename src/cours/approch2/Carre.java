package cours.approch2;

public class Carre  extends Figure implements ICarre{
   private  double  cote;
   
   @Override
    public double getCote() {
    return cote;
   }
    @Override
    public double surface() {
         return cote*cote;
    }
 
}
