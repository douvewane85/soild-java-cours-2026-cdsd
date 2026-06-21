package cours.approch2;

public class Carre   implements ICarre{
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
