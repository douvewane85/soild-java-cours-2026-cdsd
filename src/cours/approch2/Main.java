package cours.approch2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
           int x=2;
    double y=(double)x;
    Carre carre=new Carre();
    Rectangle rectangle=new Rectangle();

       IFigure fig=new Rectangle();
       Rectangle rect=(Rectangle)fig;

       IFigure fig1=new Carre();
       Carre car=(Carre)fig1;


    //Liste de Carre et de Rectangle

     List<IFigure> figures= new ArrayList<>();
       figures.add(new Rectangle());
       figures.add(new Carre());

       for (IFigure figure : figures) {
             figure.surface();
             if (figure instanceof Carre) {
                  ((Carre)figure).getCote();
             } else {
                     ((Rectangle)figure).getLongeur();
             }
       }
    }
    



}
