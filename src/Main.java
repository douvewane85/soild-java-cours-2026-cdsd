import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Wallet wallet =new Wallet(100000,"Baila Wane","771001010");
        WalletService walletService =new WalletService();
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

               walletService.ajouterFonds(wallet, montant, methode);  
             } catch (IllegalArgumentException e) {
                   System.out.println(e.getMessage());
            }
               
           }else if(choix==2){
              
               try {
                  System.out.println("Entrez le montant a retirer");
                  double montant = scanner.nextDouble();
                    System.out.println("Methode(ORANGE_MONEY/WAVE/CARTE_BANCAIRE");
                   String methode=scanner.next();
                   walletService.retraitFonds(wallet, montant, methode);
               } catch (RuntimeException e) {
                 System.out.println(e.getMessage());
               }
              
           }else if(choix==3){
               running=false;
           }
       }
    }
}
