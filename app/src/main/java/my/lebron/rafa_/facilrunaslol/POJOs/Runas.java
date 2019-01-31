package my.lebron.rafa_.facilrunaslol.POJOs;

/**
 * Created by rafa_ on 08/03/2018.
 */

public class Runas {

    private int id_runas;
    private int id_campeon;
    private String nombre_runas;

    public Runas(int id_runas, int id_campeon, String nombre_runas) {
        this.id_runas = id_runas;
        this.id_campeon = id_campeon;
        this.nombre_runas = nombre_runas;
    }

    public int getId_runas() {
        return id_runas;
    }

    public void setId_runas(int id_runas) {
        this.id_runas = id_runas;
    }

    public String getNombre_runas() {
        return nombre_runas;
    }

    public void setNombre_runas(String nombre_runas) {
        this.nombre_runas = nombre_runas;
    }

    @Override
    public String toString() {
        return "\n"/*+id_runas+
                " Campeon: " + id_campeon +
                " |*/+" Runas: " + nombre_runas + "\n";
    }
}
