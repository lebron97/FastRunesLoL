package my.lebron.rafa_.facilrunaslol.POJOs;

/**
 * Created by rafa_ on 09/03/2018.
 */

public class RunasPrincipales {

    String id_runas;
    int posicion, tipo_runa;

    public RunasPrincipales(String id_runas, int posicion, int tipo_runa) {
        this.id_runas = id_runas;
        this.posicion = posicion;
        this.tipo_runa = tipo_runa;
    }

    public String getId_runas() {
        return id_runas;
    }

    public void setId_runas(String id_runas) {
        this.id_runas = id_runas;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getTipo_runa() {
        return tipo_runa;
    }

    public void setTipo_runa(int tipo_runa) {
        this.tipo_runa = tipo_runa;
    }

    @Override
    public String toString() {
        return "RunasPrincipales{" +
                "id_runas='" + id_runas + '\'' +
                ", posicion=" + posicion +
                ", tipo_runa=" + tipo_runa +
                '}';
    }
}
