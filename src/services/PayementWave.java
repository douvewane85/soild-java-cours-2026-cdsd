package services;

public class PayementWave implements  IPayement{

    @Override
    public double calculFrais(double montant) {
          return montant*0.015;
    }
    
}
