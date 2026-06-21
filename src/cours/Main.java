package cours;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
           int x=2;
    double y=(double)x;
    Carre carre=new Carre();
    Rectangle rectangle=new Rectangle();

       Figure fig=new Rectangle();
       Rectangle rect=(Rectangle)fig;

       Figure fig1=new Carre();
       Carre car=(Carre)fig1;


    //Liste de Carre et de Rectangle

     List<Figure> figures= new ArrayList<>();
       figures.add(new Rectangle());
       figures.add(new Carre());

       for (Figure figure : figures) {
             figure.surface();
              if (figure instanceof Carre) {
                   ((Carre)figure).getCote();
              }

              if (figure instanceof Rectangle) {
                    ((Rectangle)figure).getLongeur();
              }
       }
    }
    



}
