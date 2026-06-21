import java.util.Scanner;

import entity.Titulaire;
import entity.Wallet;
import repository.TransactionRepository;
import services.IPayement;
import services.NotificationService;
import services.PayementCarte;
import services.PayementOM;
import services.PayementWave;
import services.WalletService;

public class Main {
    public static void main(String[] args) throws Exception {
          int x=12;
          double y=(double)x;

        Titulaire titulaire=new Titulaire("Baila Wane","771001010");
        Wallet wallet =new Wallet(100000,titulaire);
        NotificationService notificationService=new NotificationService();
        TransactionRepository transactionRepository=new TransactionRepository();
        WalletService walletService =new WalletService(notificationService, transactionRepository);
        boolean running=true;
        while(running){
           System.out.println("Solde actuel : "+wallet.getSolde());
           System.out.println("Voulez vous faire un depot ou un retrait?");
           System.out.println("1- Depot");
           System.out.println("2- Retrait");
           System.out.println("3- Quitter");
           Scanner scanner =new Scanner(System.in);
           int choix = scanner.nextInt();
           if(choix==1){
            try {
                System.out.println("Entrez le montant a deposer");
                double montant = scanner.nextDouble();
               System.out.println("Methode(ORANGE_MONEY/WAVE/CARTE_BANCAIRE)");
               String methode=scanner.next();
               IPayement payement;
               if (methode.equals("ORANGE_MONEY")) {
                  payement=new PayementOM();
               }else if(methode.equals("WAVE")){
                payement=new PayementWave();
               }else{
                    payement=new PayementCarte();
               }
               walletService.ajouterFonds(wallet, montant, payement);  
             } catch (IllegalArgumentException e) {
                   System.out.println(e.getMessage());
            }
               
           }else if(choix==2){
              
               try {
                  System.out.println("Entrez le montant a retirer");
                  double montant = scanner.nextDouble();
                    System.out.println("Methode(ORANGE_MONEY/WAVE/CARTE_BANCAIRE");
                   String methode=scanner.next();
                    IPayement payement;
                        if (methode.equals("ORANGE_MONEY")) {
                            payement=new PayementOM();
                        }else if(methode.equals("WAVE")){
                            payement=new PayementWave();
                        }else{
                                payement=new PayementCarte();
                        }
                   walletService.retraitFonds(wallet, montant, payement);
               } catch (RuntimeException e) {
                 System.out.println(e.getMessage());
               }
              
           }else if(choix==3){
               running=false;
           }
       }
    }
}
