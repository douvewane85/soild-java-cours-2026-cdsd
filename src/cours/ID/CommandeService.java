package cours.ID;

public class CommandeService {
    public boolean saveCommande(IRepository repo,Commande commande){
       return repo.insert(commande)!=0;
    }
    
}
