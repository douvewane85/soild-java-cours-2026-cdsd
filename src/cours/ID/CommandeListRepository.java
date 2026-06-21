package cours.ID;

import java.util.ArrayList;
import java.util.List;

public class CommandeListRepository implements IRepository{
    private List<Commande> commandes=new ArrayList<>();


    @Override
    public int insert(Commande commande){
        commandes.add(commande);
        return 1;
    }
}
