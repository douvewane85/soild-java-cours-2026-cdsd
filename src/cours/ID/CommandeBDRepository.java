package cours.ID;

public class CommandeBDRepository  implements IRepository{
    private final String tableName="commandes";

    @Override
    public int insert(Commande commande){
         String sql="select ....";
        return 1;
    }
}
