package my.lebron.rafa_.facilrunaslol.vista;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import my.lebron.rafa_.facilrunaslol.R;
import my.lebron.rafa_.facilrunaslol.sqlitedatabase.GestorBD;


public class RamaDeRunas extends AppCompatActivity {

    private Button btnPrecision, btnDominacion, btnBrujeria, btnValor, btnInspiracion, btnInvisible;
    private int contador = 0, valorRuna, valorRunaSecundaria;
    private String id_runas;
    private String rama = "";
    private EventosRamaRunas evento = new EventosRamaRunas(this);
    private GestorBD gBD;
    private Activity act = this;

    public Button getBtnPrecision() {
        return btnPrecision;
    }

    public Button getBtnDominacion() {
        return btnDominacion;
    }

    public Button getBtnBrujeria() {
        return btnBrujeria;
    }

    public Button getBtnValor() {
        return btnValor;
    }

    public Button getBtnInspiracion() {
        return btnInspiracion;
    }

    public String getId_runas() {
        return id_runas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rama_de_runas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gBD = new GestorBD(getBaseContext());

        id_runas = getIntent().getExtras().getString("id_runas");

        //Toast t = Toast.makeText(getBaseContext(), id_runas, Toast.LENGTH_SHORT);
        //t.show();

        btnPrecision = findViewById(R.id.btnPrecision);
        btnPrecision.setOnClickListener(evento);



        /*btnPrecision.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 1;
                    btnPrecision.setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 1;
                    if(valorRuna==4){
                        Intent i = new Intent(getBaseContext(),PantallaRunasPrecision.class);
                        i.putExtra("id_runas", id_runas);
                        startActivity(i);
                    }else {
                        Intent i = new Intent(getBaseContext(), PantallaRunas.class);
                        i.putExtra("id_runas", id_runas);
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getBackground().clearColorFilter();
                        startActivity(i);
                    }
                }

                //Toast t = Toast.makeText(getBaseContext(), "Has elegido Precision como rama " + rama, Toast.LENGTH_SHORT);
                //t.show();

                contador++;
            }});*/


        btnDominacion = findViewById(R.id.btnDominacion);
        btnDominacion.setOnClickListener(evento);

       /* btnDominacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 2;
                    btnDominacion.setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 2;
                    if (valorRuna == 4) {
                        Intent i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                        //i.putExtra("tipo_runa", valorRuna);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getBaseContext(), PantallaRunas.class);
                        i.putExtra("id_runas", id_runas);
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getBackground().clearColorFilter();
                        startActivity(i);
                    }
                }

                Toast t = Toast.makeText(getBaseContext(), "Has elegido Dominacion como rama " + rama, Toast.LENGTH_SHORT);
                t.show();

                contador++;
            }
        });*/

        btnBrujeria = findViewById(R.id.btnBrujeria);
        btnBrujeria.setOnClickListener(evento);

        /*btnBrujeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 3;
                    btnBrujeria.setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 3;
                    if (valorRuna == 4) {
                        Intent i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                        //i.putExtra("tipo_runa", valorRuna);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getBaseContext(), PantallaRunas.class);
                        i.putExtra("id_runas", id_runas);
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getBackground().clearColorFilter();
                        startActivity(i);
                    }
                }

                Toast t = Toast.makeText(getBaseContext(), "Has elegido Brujeria como rama " + rama, Toast.LENGTH_SHORT);
                t.show();

                contador++;
            }
        });*/

        btnValor = findViewById(R.id.btnValor);
        btnValor.setOnClickListener(evento);

        /*btnValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 4;
                    btnValor.setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 4;
                    Intent i = new Intent(getBaseContext(), PantallaRunas.class);
                    i.putExtra("id_runas", id_runas);
                    i.putExtra("tipo_runa", valorRuna);
                    i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                    v.getBackground().clearColorFilter();
                    startActivity(i);
                }

                Toast t = Toast.makeText(getBaseContext(), "Has elegido Valor como rama " + rama, Toast.LENGTH_SHORT);
                t.show();

                contador++;
            }
        });*/

        btnInspiracion = findViewById(R.id.btnInspiracion);
        btnInspiracion.setOnClickListener(evento);

        /*btnInspiracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
                v.invalidate();
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 5;
                    btnInspiracion.setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 5;
                    if (valorRuna == 4) {
                        Intent i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                        //i.putExtra("tipo_runa", valorRuna);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getBaseContext(), PantallaRunas.class);
                        i.putExtra("id_runas", id_runas);
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getBackground().clearColorFilter();
                        startActivity(i);
                    }

                }

               Toast t = Toast.makeText(getBaseContext(), "Has elegido Inspiracion como rama " + rama, Toast.LENGTH_SHORT);
                t.show();

                contador++;
            }
        });*/

        btnInvisible = findViewById(R.id.btnInvisible);
        btnInvisible.setOnClickListener(evento);

    }




    @Override
    protected void onResume() {
        super.onResume();

        contador = 0;
    }

    @Override
    public void onBackPressed() {

        btnInvisible.performClick();
        gBD.eliminarRuna(id_runas);
        gBD.eliminarRunaPPorPag(id_runas);
        gBD.eliminarRunaSPorPag(id_runas);
        finish();
        //super.onBackPressed();
    }
}
