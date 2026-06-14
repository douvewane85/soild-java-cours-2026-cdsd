package entity;

public class Titulaire {
        private String titulaire;
        private String telephone;
        public Titulaire(String titulaire, String telephone) {
            this.titulaire = titulaire;
            this.telephone = telephone;
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
            return "Titulaire [telephone=" + telephone + "]";
        }
}
