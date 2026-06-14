package entity;

public class Wallet {
      private double solde;
      private Titulaire titulaire;

      public Wallet(double solde,Titulaire titulaire) {
        this.solde = solde;
        this.titulaire = titulaire;
    }
      public double getSolde() {
          return solde;
      }
      public void setSolde(double solde) {
          this.solde = solde;
      }
      public Titulaire getTitulaire() {
          return titulaire;
      }
      public void affecterTitulaire(Titulaire titulaire) {
          this.titulaire = titulaire;
      }
       public void retrait(double montant){
        if(montant>this.getSolde()){
            throw new RuntimeException("Solde insuffisant");
        }
            this.setSolde(this.getSolde()-montant);

    }
     public void depot(double montant){
        if(montant<=0)
        throw new IllegalArgumentException("Le montant doit etre superieur a 0");
        this.setSolde(this.getSolde()+montant);
    }

}
