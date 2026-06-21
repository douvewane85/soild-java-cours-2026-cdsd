package cours.ID;

public class Main {
    public static void main(String[] args) {
           CommandeListRepository repo=new CommandeListRepository();
           CommandeService commandeService=new CommandeService();
           commandeService.saveCommande(repo, new Commande());
    }
}
