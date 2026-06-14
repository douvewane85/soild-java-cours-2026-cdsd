public class Wallet {
    private double solde;
    private String titulaire;
    private String telephone;

    public Wallet(double solde, String titulaire, String telephone) {
        this.solde = solde;
        this.titulaire = titulaire;
        this.telephone = telephone;
    }

    public  Wallet(){
        
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Wallet [solde=" + solde + ", titulaire=" + titulaire + ", telephone=" + telephone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
        return result;
    }

    //on va comparer les wallets sur base de leur numero de telephone 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Wallet other = (Wallet) obj;
        if (telephone == null) {
            if (other.telephone != null)
                return false;
        } else if (!telephone.equals(other.telephone))
            return false;
        return true;
    }

   
    

    

    
}
