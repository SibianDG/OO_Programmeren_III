package domein;

//VUL DE KLASSE VERDER AAN
public class Vat implements Runnable {

    private final Tafel tafel;
    private int inhoud;

    public Vat(int emmers, Tafel tafel) {
        inhoud = emmers;
        this.tafel = tafel;
    }

    @Override
    public void run() {
        while (inhoud > 0){
            tafel.vulEmmer();
            inhoud--;
            System.out.println("Emmer gevuld");
        }
        tafel.setVatIsLeeg();
    }

}
