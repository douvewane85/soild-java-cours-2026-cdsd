import java.util.ArrayList;
import java.util.List;

public class WalletService {
   private List<String> transactions =new ArrayList<>();


   private void saveTransactionToDatabase(Wallet wallet,double montant,String type) {
        transactions.add("Transaction  sur le wallet de" + wallet.getTitulaire() + "telephone" + wallet.getTelephone() + "montant" +montant + "type" +type);
   }
   private boolean sendSms(String message){
      return true;
   }
    public void retraitFonds(Wallet wallet,double montant,String methode) throws RuntimeException{
        //1- Retrait
         double frais=calculFrais(montant,methode);
         retrait(wallet,montant+frais);
         //3- Enregistrer la tranasaction dans la BD
        var result= sendSms("Valider la transaction de retrait de "+montant+"sur le wallet de "+wallet.getTelephone()+"frais de transaction : "+frais);
         if(!result)
            throw new RuntimeException("La transaction n'a pas ete enregistrer dans la base de donnee");

    }

    public void ajouterFonds(Wallet wallet,double montant,String methode) throws IllegalArgumentException{
     //1-Depot
       double frais= calculFrais(montant,methode);
       depot(wallet,montant-frais); 
     //2-Enregistre dans BD
        saveTransactionToDatabase(wallet,montant,"DEPOT");
     //3-Envoie un sms information
        sendSms("Valider la transaction de depot de "+(montant-frais)+"sur le wallet de "+wallet.getTelephone()+"frais de transaction : "+frais);
    }

   public double calculFrais(double montant,String methode){
    if(methode.equals("ORANGE_MONEY"))  {
        return montant*0.01;
        }
        else if(methode.equals("WAVE")){
            return montant*0.015;
        }else if(methode.equals("CARTE_BANCAIRE")){
            return montant*0.02;
        }
        return 0;
    }

     private void retrait(Wallet wallet,double montant){
        if(montant>wallet.getSolde()){
            throw new RuntimeException("Solde insuffisant");
        }
            wallet.setSolde(wallet.getSolde()-montant);

    }
    private void depot(Wallet wallet,double montant){
        if(montant<=0)
        throw new IllegalArgumentException("Le montant doit etre superieur a 0");
        wallet.setSolde(wallet.getSolde()+montant);
    }


     
}

    
    

