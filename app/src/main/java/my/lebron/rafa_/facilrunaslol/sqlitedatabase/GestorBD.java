package my.lebron.rafa_.facilrunaslol.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rafa_ on 20/02/2018.
 */

public class GestorBD {


    public static final String CREATE_TABLE_CAMPEON = "CREATE TABLE campeon (campeon_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre_campeon TEXT NOT NULL);";
    public static final String CREATE_TABLE_RUNAS = "CREATE TABLE runas (runas_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre_runas TEXT NOT NULL, campeon_id INTEGER NOT NULL, " +
            "FOREIGN KEY (campeon_id) REFERENCES campeon (campeon_id) " +
            " ON DELETE CASCADE ON UPDATE NO ACTION);";
    public static final String CREATE_TABLE_RUNAS_PRINCIPALES = "CREATE TABLE runasp (runasp_id INTEGER PRIMARY KEY AUTOINCREMENT, runas_id INTEGER NOT NULL, posicion INTEGER NOT NULL, " +
            "tipo_runa INTEGER NOT NULL, " +
            "nombre_runasp TEXT NOT NULL, " +
            "FOREIGN KEY (runas_id) REFERENCES runas (runas_id) " +
            " ON DELETE CASCADE ON UPDATE NO ACTION);";
    public static final String CREATE_TABLE_RUNAS_SECUNDARIAS = "CREATE TABLE runass (runass_id INTEGER PRIMARY KEY AUTOINCREMENT, runas_id INTEGER NOT NULL, posicion INTEGER NOT NULL, " +
            "tipo_runa INTEGER NOT NULL, " +
            "nombre_runass TEXT NOT NULL, " +
            "FOREIGN KEY (runas_id) REFERENCES runas (runas_id) " +
            " ON DELETE CASCADE ON UPDATE NO ACTION);";

    private DataBaseHelper bd;
    private SQLiteDatabase db;

    public GestorBD(Context context) {
        bd = new DataBaseHelper(context);
        db = bd.getWritableDatabase();
    }

    //--------------------------------------CAMPEON--------------------------------------

    public ContentValues generarContentValuesCampeon(String nombre) {
        ContentValues valores = new ContentValues();

        valores.put("nombre_campeon", nombre);

        return valores;
    }

    public void insertarCampeon(String nombre) {

        db.insert("campeon", null, generarContentValuesCampeon(nombre));
    }

    public void eliminarCampeon(String id_campeon){

        db.delete("campeon", "campeon_id"+" =? ", new String[]{id_campeon});
    }

    public void modificarCampeon(int id, String nombre){
        db.update("campeon", generarContentValuesCampeon(nombre), "campeon_id"+"=?", new String[]{id+""});
    }


    public Cursor cargarCursorCampeon(){

        String[] columnas = new String[]{"campeon_id", "nombre_campeon"};

        return db.query("campeon", columnas, null, null, null, null, null);
    }

    public Cursor buscarCampeon(String nombre){
        String[] columnas = new String[]{"campeon_id", "nombre_campeon"};

        return db.query("campeon", columnas, "nombre_campeon"+" LIKE? ", new String[]{nombre}, null, null, null);
    }

    public Cursor ordenarCampeonAsc(){
        String[] columnas = new String[]{"campeon_id", "nombre_campeon"};

        return db.query("campeon", columnas, null, null, null, null, "nombre_campeon ASC");
    }

    public Cursor ordenarCampeonDesc(){
        String[] columnas = new String[]{"campeon_id", "nombre_campeon"};

        return db.query("campeon", columnas, null, null, null, null, "nombre_campeon DESC");
    }

    //--------------------------------------RUNAS--------------------------------------

    public ContentValues generarContentValuesRunas(String id_campeon, String nombre_runas) {
        ContentValues valores = new ContentValues();

        valores.put("campeon_id", id_campeon);
        valores.put("nombre_runas", nombre_runas);

        return valores;
    }

    public void insertarRuna(String id_campeon, String nombre_runas) {

        db.insert("runas", null, generarContentValuesRunas(id_campeon, nombre_runas));
    }

    public void eliminarRuna(String id_runas){

        db.delete("runas", "runas_id" + "=?" , new String[]{id_runas});
    }

    public void eliminarRunaPorCampeon(String id_campeon){

        db.delete("runas", "campeon_id" + "=?", new String[]{id_campeon});
    }

    public Cursor cargarCursorRuna(){

        String[] columnas = new String[]{"campeon_id","nombre_runas"};

        return db.query("runas", columnas, null, null, null, null, null);
    }

    public Cursor cargarCursorRunaCampeon(String id_campeon){

        String[] columnas = new String[]{"runas_id", "campeon_id","nombre_runas"};

        return db.query("runas", columnas, "campeon_id"+"=?", new String[]{id_campeon}, null, null, null);
    }
    //--------------------------------------RUNAS PRINCIPALES--------------------------------------


    public ContentValues generarContentValuesRunasP(String id_runas, int posicion, int tipo_runa, String nombre_runasp) {
        ContentValues valores = new ContentValues();

        valores.put("runas_id", id_runas);
        valores.put("posicion", posicion);
        valores.put("tipo_runa", tipo_runa);
        valores.put("nombre_runasp", nombre_runasp);

        return valores;
    }

    public void insertarRunaP(String id_runas, int posicion, int tipo_runa, String nombre_runasp) {

        db.insert("runasp", null, generarContentValuesRunasP(id_runas, posicion, tipo_runa, nombre_runasp));
    }

    public void eliminarRunaP(String nombre_runasp){

        db.delete("runasp", "nombre_runasp" + "=?", new String[]{nombre_runasp});
    }

    public void eliminarRunaPPorPag(String id_runas){

        db.delete("runasp", "runas_id" + "=?", new String[]{id_runas});
    }

    public Cursor cargarCursorRunaPPagRunas(String id_runas){

        String[] columnas = new String[]{"runas_id","posicion", "tipo_runa"};

        return db.query("runasp", columnas, "runas_id"+"=?", new String[]{id_runas}, null, null, null);
    }

    //--------------------------------------RUNAS SECUNDARIAS--------------------------------------

    public ContentValues generarContentValuesRunasS(String id_runas, int posicion, int tipo_runa, String nombre_runass) {
        ContentValues valores = new ContentValues();

        valores.put("runas_id", id_runas);
        valores.put("posicion", posicion);
        valores.put("tipo_runa", tipo_runa);
        valores.put("nombre_runass", nombre_runass);

        return valores;
    }

    public void insertarRunaS(String id_runas, int posicion, int tipo_runa, String nombre_runass) {

        db.insert("runass", null, generarContentValuesRunasS(id_runas, posicion, tipo_runa, nombre_runass));
    }

    public void eliminarRunaS(String nombre_runass){

        db.delete("runass", "nombre_runass" + "=?", new String[]{nombre_runass});
    }

    public void eliminarRunaSPorPag(String id_runas){

        db.delete("runass", "runas_id" + "=?", new String[]{id_runas});
    }


    public Cursor cargarCursorRunaSPagRunas(String id_runas){

        String[] columnas = new String[]{"runas_id","posicion", "tipo_runa"};

        return db.query("runass", columnas, "runas_id"+"=?", new String[]{id_runas}, null, null, null);
    }


}
