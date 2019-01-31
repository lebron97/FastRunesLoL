package my.lebron.rafa_.facilrunaslol.POJOs;

/**
 * Created by rafa_ on 23/02/2018.
 */

public class Campeon {

    private int id_campeon;
    private String nombre_campeon;

    public Campeon(int id_campeon, String nombre_campeon) {
        this.id_campeon = id_campeon;
        this.nombre_campeon = nombre_campeon;
    }

    public int getId_campeon() {
        return id_campeon;
    }

    public void setId_campeon(int id_campeon) {
        this.id_campeon = id_campeon;
    }

    public String getNombre_campeon() {
        return nombre_campeon;
    }

    public void setNombre_campeon(String nombre_campeon) {
        this.nombre_campeon = nombre_campeon;
    }


    @Override
    public String toString() {
        return
                "\n"/*+id_campeon +
                        " | */+" Campeon: " + nombre_campeon + "\n";
    }
}
