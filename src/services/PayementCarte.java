package services;

public class PayementCarte implements IPayement{

    @Override
    public double calculFrais(double montant) {
           return montant*0.02;
    }
    
}
