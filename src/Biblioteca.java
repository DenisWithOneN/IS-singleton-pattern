public class Biblioteca {

    private static volatile Biblioteca instance; //private static Singleton instance;
    private int carti;
    private int reviste;
    private int volume;
    private String autoriCelebrii;

    private Biblioteca(int carti, int reviste, int volume, String autoriCelebrii) {
        //constructor private pentru ca avem nevoie de o alta metoda pentru a avea acces la acesta
        this.carti = carti;
        this.reviste = reviste;
        this.volume = volume;
        this.autoriCelebrii = autoriCelebrii;
    }

    public static Biblioteca getInstance(int carti, int reviste, int volume, String autoriCelebrii) {
        if (instance == null) { // double checked locking idiom
            synchronized (Biblioteca.class) { //de fiecare data cand un thread vrea sa creeze o noua instanta trebuie sa astepte sa-i vina randul
                if (instance == null) { //verifica daca instanta de mai sus este egala cu null
                    // daca este, de abia atunci creeaza o noua instanta apeland constructorul privat
                    instance = new Biblioteca(carti, reviste, volume, autoriCelebrii);
                }
            }
        }
        return instance; //returneaza o singura instanta

    }

    public void show(){
        System.out.println("In biblioteca sunt " + carti + " carti, " + reviste + " reviste, " + volume + " volume");
        System.out.println("Autorii celebri ai bibliotecii sunt " + autoriCelebrii);
    }
}

class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca1 = Biblioteca.getInstance(5,3,5, "Dale Carnegie, Daniel Goleman, Chris Voss");
        biblioteca1.show();
    }
}