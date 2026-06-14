package repository;

import java.util.ArrayList;
import java.util.List;

import entity.Wallet;

public class TransactionRepository {
     private List<String> transactions =new ArrayList<>();
     public void save(Wallet wallet,double montant,String type) {
        transactions.add("Transaction  sur le wallet de" + wallet.getTitulaire().getTitulaire() + "telephone" + wallet.getTitulaire().getTelephone() + "montant" +montant + "type" +type);
   }
}
