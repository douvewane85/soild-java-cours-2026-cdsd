package services;

public class PayementOM  implements IPayement{

    @Override
    public double calculFrais(double montant) {
           return montant*0.01;
    }
    
}
