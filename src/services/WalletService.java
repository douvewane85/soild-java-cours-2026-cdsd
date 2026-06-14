package services;

import entity.Wallet;
import repository.TransactionRepository;

public class WalletService {
   private NotificationService notificationService;
   private TransactionRepository transactionRepository;
   

     public WalletService(NotificationService notificationService, TransactionRepository transactionRepository) {
        this.notificationService = notificationService;
        this.transactionRepository = transactionRepository;
     }



     public void retraitFonds(Wallet wallet,double montant,String methode) throws RuntimeException{
        //1- Retrait
         double frais=calculFrais(montant,methode);
          wallet.retrait(montant+frais);
         //3- Enregistrer la tranasaction dans la BD
        var result=    this.notificationService.sendSms("Valider la transaction de retrait de "+montant+"sur le wallet de "+wallet.getTitulaire().getTelephone()+"frais de transaction : "+frais);
         if(!result)
            throw new RuntimeException("La transaction n'a pas ete enregistrer dans la base de donnee");
         transactionRepository.save(wallet,montant,"DEPOT");


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



     public void ajouterFonds(Wallet wallet,double montant,String methode) throws IllegalArgumentException{
     //1-Depot
       double frais= calculFrais(montant,methode);
       wallet.depot(montant-frais); 
     //2-Enregistre dans BD
        transactionRepository.save(wallet,montant,"DEPOT");
     //3-Envoie un sms information
        this.notificationService.sendSms("Valider la transaction de depot de "+(montant-frais)+"sur le wallet de "+wallet.getTitulaire().getTelephone()+"frais de transaction : "+frais);
    }

}
