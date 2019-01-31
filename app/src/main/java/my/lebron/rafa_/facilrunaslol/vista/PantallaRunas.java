package my.lebron.rafa_.facilrunaslol.vista;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import my.lebron.rafa_.facilrunaslol.R;
import my.lebron.rafa_.facilrunaslol.sqlitedatabase.GestorBD;


public class PantallaRunas extends AppCompatActivity {

    private int tipoRuna, tipoRunaSecundaria;
    private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12;
    private int contador = 0, auxContador, idIntent;
    private boolean activado = true, auxActivado, auxActivado2, auxActivado3, auxActivado4;
    //private static int Declarador.desactivador;
    private Intent i;
    private GestorBD gBD;
    private String id_campeon, id_runas, auxRuna;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_runas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        id_campeon = getIntent().getExtras().getString("id_campeon");
        id_runas = getIntent().getExtras().getString("id_runas");

        gBD = new GestorBD(getBaseContext());


        tipoRuna = getIntent().getExtras().getInt("tipo_runa");
        tipoRunaSecundaria = getIntent().getExtras().getInt("tipo_runa_secundaria");

        auxRuna = id_runas;

       /* Toast t = Toast.makeText(getBaseContext(), id_runas, Toast.LENGTH_SHORT);
        t.show();*/

        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);
        checkBox11 = findViewById(R.id.checkBox11);
        checkBox12 = findViewById(R.id.checkBox12);

        c = gBD.cargarCursorRunaPPagRunas(id_runas);

        //Toast t = Toast.makeText(getBaseContext(), Declarador.desactivador + "", Toast.LENGTH_SHORT);
        //t.show();

        if (Declarador.desactivador == 1) {

            checkBox.setEnabled(false);
            checkBox2.setEnabled(false);
            checkBox3.setEnabled(false);
            contador = 2;
            idIntent = 1;
            cargaRunas();
        }

        c = gBD.cargarCursorRunaPPagRunas(id_runas);

        if (idIntent != 1)
            tiposDeRuna(tipoRuna);
        else
            tiposDeRuna(tipoRunaSecundaria);

    }

    public void seleccionados(View view) {

        auxContador = contador;
        auxActivado = activado;
        auxActivado2 = activado;
        auxActivado3 = activado;
        auxActivado4 = activado;
        Declarador.desactivador = 1;

        if (checkBox.isChecked()) {

            gBD.eliminarRunaP("1");
            gBD.eliminarRunaP("2");
            gBD.eliminarRunaP("3");
            gBD.insertarRunaP(id_runas, 1, R.drawable.pa, "1");

            auxContador++;
            auxActivado = false;
            checkBox2.setEnabled(auxActivado);
            checkBox3.setEnabled(auxActivado);

            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    i = new Intent(getBaseContext(), PantallaRunas.class);

                } else {
                    i = new Intent(getBaseContext(), PantallaRunasPrecision.class);

                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);

                if (idIntent == 1)
                    finish();
            }

        } else if (checkBox2.isChecked()) {
            gBD.eliminarRunaP("1");
            gBD.eliminarRunaP("2");
            gBD.eliminarRunaP("3");
            gBD.insertarRunaP(id_runas, 2, R.drawable.pa, "2");
            auxContador++;
            auxActivado = false;
            checkBox.setEnabled(auxActivado);
            checkBox3.setEnabled(auxActivado);
            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    i = new Intent(getBaseContext(), PantallaRunas.class);

                } else {
                    i = new Intent(getBaseContext(), PantallaRunasPrecision.class);

                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }
        } else if (checkBox3.isChecked()) {
            gBD.eliminarRunaP("1");
            gBD.eliminarRunaP("2");
            gBD.eliminarRunaP("3");
            gBD.insertarRunaP(id_runas, 3, R.drawable.pa, "3");
            auxContador++;
            auxActivado = false;
            checkBox2.setEnabled(auxActivado);
            checkBox.setEnabled(auxActivado);
            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    i = new Intent(getBaseContext(), PantallaRunas.class);

                } else {
                    i = new Intent(getBaseContext(), PantallaRunasPrecision.class);

                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }
        } else {

            if (idIntent == 1) {
                checkBox.setEnabled(false);
                checkBox2.setEnabled(false);
                checkBox3.setEnabled(false);
            } else {
                checkBox.setEnabled(auxActivado);
                checkBox2.setEnabled(auxActivado);
                checkBox3.setEnabled(auxActivado);
            }

           /* gBD.eliminarRunaS("4");
            gBD.eliminarRunaS("5");
            gBD.eliminarRunaS("6");
            gBD.eliminarRunaS("7");
            gBD.eliminarRunaS("8");
            gBD.eliminarRunaS("9");
            gBD.eliminarRunaS("10");
            gBD.eliminarRunaS("11");
            gBD.eliminarRunaS("12");*/

        }


        if (checkBox4.isChecked()) {
            if (idIntent == 0) {
                gBD.eliminarRunaP("4");
                gBD.eliminarRunaP("5");
                gBD.eliminarRunaP("6");
                gBD.insertarRunaP(id_runas, 4, R.drawable.pa, "4");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("4");
                gBD.eliminarRunaS("5");
                gBD.eliminarRunaS("6");
                gBD.insertarRunaS(id_runas, 4, R.drawable.pa, "4");
            }
            auxContador++;
            auxActivado2 = false;
            checkBox5.setEnabled(auxActivado2);
            checkBox6.setEnabled(auxActivado2);
            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }
        } else if (checkBox5.isChecked()) {
            if (idIntent == 0) {
                gBD.eliminarRunaP("4");
                gBD.eliminarRunaP("5");
                gBD.eliminarRunaP("6");
                gBD.insertarRunaP(id_runas, 5, R.drawable.pa, "5");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("4");
                gBD.eliminarRunaS("5");
                gBD.eliminarRunaS("6");
                gBD.insertarRunaS(id_runas, 5, R.drawable.pa, "5");
            }
            auxContador++;
            auxActivado2 = false;
            checkBox4.setEnabled(auxActivado2);
            checkBox6.setEnabled(auxActivado2);
            if (auxContador == 4) {

                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }

        } else if (checkBox6.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("4");
                gBD.eliminarRunaP("5");
                gBD.eliminarRunaP("6");
                gBD.insertarRunaP(id_runas, 6, R.drawable.pa, "6");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("4");
                gBD.eliminarRunaS("5");
                gBD.eliminarRunaS("6");
                gBD.insertarRunaS(id_runas, 6, R.drawable.pa, "6");
            }
            auxContador++;
            auxActivado2 = false;
            checkBox5.setEnabled(auxActivado2);
            checkBox4.setEnabled(auxActivado2);
            if (auxContador == 4) {

                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }

        } else {
            checkBox5.setEnabled(auxActivado2);
            checkBox4.setEnabled(auxActivado2);
            checkBox6.setEnabled(auxActivado2);

            gBD.eliminarRunaS("4");
            gBD.eliminarRunaS("5");
            gBD.eliminarRunaS("6");
            gBD.eliminarRunaS("7");
            gBD.eliminarRunaS("8");
            gBD.eliminarRunaS("9");
            gBD.eliminarRunaS("10");
            gBD.eliminarRunaS("11");
            gBD.eliminarRunaS("12");
        }

        if (checkBox7.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("7");
                gBD.eliminarRunaP("8");
                gBD.eliminarRunaP("9");
                gBD.insertarRunaP(id_runas, 7, R.drawable.pa, "7");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("7");
                gBD.eliminarRunaS("8");
                gBD.eliminarRunaS("9");
                gBD.insertarRunaS(id_runas, 7, R.drawable.pa, "7");
            }
            auxContador++;
            auxActivado3 = false;
            checkBox9.setEnabled(auxActivado3);
            checkBox8.setEnabled(auxActivado3);
            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }
        } else if (checkBox8.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("7");
                gBD.eliminarRunaP("8");
                gBD.eliminarRunaP("9");
                gBD.insertarRunaP(id_runas, 8, R.drawable.pa, "8");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("7");
                gBD.eliminarRunaS("8");
                gBD.eliminarRunaS("9");
                gBD.insertarRunaS(id_runas, 8, R.drawable.pa, "8");
            }
            auxContador++;
            auxActivado3 = false;
            checkBox7.setEnabled(auxActivado3);
            checkBox9.setEnabled(auxActivado3);
            if (auxContador == 4) {

                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }

        } else if (checkBox9.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("7");
                gBD.eliminarRunaP("8");
                gBD.eliminarRunaP("9");
                gBD.insertarRunaP(id_runas, 9, R.drawable.pa, "9");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("7");
                gBD.eliminarRunaS("8");
                gBD.eliminarRunaS("9");
                gBD.insertarRunaS(id_runas, 9, R.drawable.pa, "9");
            }
            auxContador++;
            auxActivado3 = false;
            checkBox7.setEnabled(auxActivado3);
            checkBox8.setEnabled(auxActivado3);
            if (auxContador == 4) {

                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }

        } else {
            checkBox9.setEnabled(auxActivado3);
            checkBox7.setEnabled(auxActivado3);
            checkBox8.setEnabled(auxActivado3);
            if (!checkBox4.isChecked() && !checkBox5.isChecked() && !checkBox6.isChecked()) {
                gBD.eliminarRunaS("4");
                gBD.eliminarRunaS("5");
                gBD.eliminarRunaS("6");
                gBD.eliminarRunaS("7");
                gBD.eliminarRunaS("8");
                gBD.eliminarRunaS("9");
                gBD.eliminarRunaS("10");
                gBD.eliminarRunaS("11");
                gBD.eliminarRunaS("12");
            }
        }

        if (checkBox10.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("10");
                gBD.eliminarRunaP("11");
                gBD.eliminarRunaP("12");
                gBD.insertarRunaP(id_runas, 10, R.drawable.pa, "10");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("10");
                gBD.eliminarRunaS("11");
                gBD.eliminarRunaS("12");
                gBD.insertarRunaS(id_runas, 10, R.drawable.pa, "10");
            }
            auxContador++;
            auxActivado4 = false;
            checkBox11.setEnabled(auxActivado4);
            checkBox12.setEnabled(auxActivado4);
            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }
        } else if (checkBox11.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("10");
                gBD.eliminarRunaP("11");
                gBD.eliminarRunaP("12");
                gBD.insertarRunaP(id_runas, 11, R.drawable.pa, "11");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("10");
                gBD.eliminarRunaS("11");
                gBD.eliminarRunaS("12");
                gBD.insertarRunaS(id_runas, 11, R.drawable.pa, "11");
            }
            auxContador++;
            auxActivado4 = false;
            checkBox10.setEnabled(auxActivado4);
            checkBox12.setEnabled(auxActivado4);
            if (auxContador == 4) {

                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }

        } else if (checkBox12.isChecked()) {
            if (idIntent != 1) {
                gBD.eliminarRunaP("10");
                gBD.eliminarRunaP("11");
                gBD.eliminarRunaP("12");
                gBD.insertarRunaP(id_runas, 12, R.drawable.pa, "12");
            } else if (idIntent == 1) {
                gBD.eliminarRunaS("10");
                gBD.eliminarRunaS("11");
                gBD.eliminarRunaS("12");
                gBD.insertarRunaS(id_runas, 12, R.drawable.pa, "12");
            }
            auxContador++;
            auxActivado4 = false;
            checkBox11.setEnabled(auxActivado4);
            checkBox10.setEnabled(auxActivado4);
            if (auxContador == 4) {
                if (tipoRunaSecundaria != 1) {

                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunas.class);
                    }
                } else {
                    if (idIntent == 1) {
                        i = new Intent(getBaseContext(), MisRunas.class);
                        Declarador.desactivador = 0;
                    } else {
                        i = new Intent(getBaseContext(), PantallaRunasPrecision.class);
                    }
                }

                i.putExtra("tipo_runa", tipoRuna);
                i.putExtra("tipo_runa_secundaria", tipoRunaSecundaria);
                i.putExtra("Declarador.desactivador", Declarador.desactivador);
                i.putExtra("id_runas", id_runas);
                startActivity(i);
                if (idIntent == 1)
                    finish();
            }
        } else {
            checkBox10.setEnabled(auxActivado4);
            checkBox11.setEnabled(auxActivado4);
            checkBox12.setEnabled(auxActivado4);


            if (!checkBox4.isChecked() && !checkBox5.isChecked() && !checkBox6.isChecked()) {
                gBD.eliminarRunaS("4");
                gBD.eliminarRunaS("5");
                gBD.eliminarRunaS("6");
                gBD.eliminarRunaS("7");
                gBD.eliminarRunaS("8");
                gBD.eliminarRunaS("9");
                gBD.eliminarRunaS("10");
                gBD.eliminarRunaS("11");
                gBD.eliminarRunaS("12");
            }
        }


    }

    public void tiposDeRuna(int num) {

        switch (num) {
            case 1:
                checkBox.setBackgroundResource(R.drawable.pa);
                checkBox2.setBackgroundResource(R.drawable.pb);
                checkBox3.setBackgroundResource(R.drawable.pc);
                checkBox4.setBackgroundResource(R.drawable.pd);
                checkBox5.setBackgroundResource(R.drawable.pe);
                checkBox6.setBackgroundResource(R.drawable.pf);
                checkBox7.setBackgroundResource(R.drawable.pg);
                checkBox8.setBackgroundResource(R.drawable.ph);
                checkBox9.setBackgroundResource(R.drawable.pi);
                checkBox10.setBackgroundResource(R.drawable.pj);
                checkBox11.setBackgroundResource(R.drawable.pk);
                checkBox12.setBackgroundResource(R.drawable.pl);
                break;

            case 2:
                checkBox.setBackgroundResource(R.drawable.da);
                checkBox2.setBackgroundResource(R.drawable.db);
                checkBox3.setBackgroundResource(R.drawable.dc);
                checkBox4.setBackgroundResource(R.drawable.dd);
                checkBox5.setBackgroundResource(R.drawable.de);
                checkBox6.setBackgroundResource(R.drawable.df);
                checkBox7.setBackgroundResource(R.drawable.dg);
                checkBox8.setBackgroundResource(R.drawable.dh);
                checkBox9.setBackgroundResource(R.drawable.di);
                checkBox10.setBackgroundResource(R.drawable.dj);
                checkBox11.setBackgroundResource(R.drawable.dk);
                checkBox12.setBackgroundResource(R.drawable.dl);

                break;

            case 3:

                checkBox.setBackgroundResource(R.drawable.bba);
                checkBox2.setBackgroundResource(R.drawable.bbb);
                checkBox3.setBackgroundResource(R.drawable.bbc);
                checkBox4.setBackgroundResource(R.drawable.ba);
                checkBox5.setBackgroundResource(R.drawable.bb);
                checkBox6.setBackgroundResource(R.drawable.bc);
                checkBox7.setBackgroundResource(R.drawable.bd);
                checkBox8.setBackgroundResource(R.drawable.be);
                checkBox9.setBackgroundResource(R.drawable.bf);
                checkBox10.setBackgroundResource(R.drawable.bg);
                checkBox11.setBackgroundResource(R.drawable.bh);
                checkBox12.setBackgroundResource(R.drawable.bi);

                break;


            case 4:

                checkBox.setBackgroundResource(R.drawable.vva);
                checkBox2.setBackgroundResource(R.drawable.vvb);
                checkBox3.setBackgroundResource(R.drawable.vvc);
                checkBox4.setBackgroundResource(R.drawable.va);
                checkBox5.setBackgroundResource(R.drawable.vb);
                checkBox6.setBackgroundResource(R.drawable.vc);
                checkBox7.setBackgroundResource(R.drawable.vd);
                checkBox8.setBackgroundResource(R.drawable.ve);
                checkBox9.setBackgroundResource(R.drawable.vg);
                checkBox10.setBackgroundResource(R.drawable.vh);
                checkBox11.setBackgroundResource(R.drawable.vi);
                checkBox12.setBackgroundResource(R.drawable.vj);

                break;

            case 5:
                checkBox.setBackgroundResource(R.drawable.iia);
                checkBox2.setBackgroundResource(R.drawable.iib);
                checkBox3.setBackgroundResource(R.drawable.iic);
                checkBox4.setBackgroundResource(R.drawable.ia);
                checkBox5.setBackgroundResource(R.drawable.ib);
                checkBox6.setBackgroundResource(R.drawable.ic);
                checkBox7.setBackgroundResource(R.drawable.id);
                checkBox8.setBackgroundResource(R.drawable.ie);
                checkBox9.setBackgroundResource(R.drawable.ifi);
                checkBox10.setBackgroundResource(R.drawable.ig);
                checkBox11.setBackgroundResource(R.drawable.ih);
                checkBox12.setBackgroundResource(R.drawable.ii);

                break;
        }

    }

    public void cargaRunas() {

        if (c.moveToFirst()) {
            do {
                //Toast t = Toast.makeText(getBaseContext(), c.getInt(0) + " " + c.getInt(1), Toast.LENGTH_SHORT);
                //t.show();
            } while (c.moveToNext());
        }

        c.close();

    }

    private class carga extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (idIntent == 0) {
            checkBox.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);

            checkBox.setEnabled(true);
            checkBox2.setEnabled(true);
            checkBox3.setEnabled(true);

            gBD.eliminarRunaS("1");
            gBD.eliminarRunaS("2");
            gBD.eliminarRunaS("3");
        }


        checkBox4.setChecked(false);
        checkBox5.setChecked(false);
        checkBox6.setChecked(false);
        checkBox7.setChecked(false);
        checkBox8.setChecked(false);
        checkBox9.setChecked(false);
        checkBox10.setChecked(false);
        checkBox11.setChecked(false);
        checkBox12.setChecked(false);

        checkBox4.setEnabled(true);
        checkBox5.setEnabled(true);
        checkBox6.setEnabled(true);
        checkBox7.setEnabled(true);
        checkBox8.setEnabled(true);
        checkBox9.setEnabled(true);
        checkBox10.setEnabled(true);
        checkBox11.setEnabled(true);
        checkBox12.setEnabled(true);

        gBD.eliminarRunaS("1");
        gBD.eliminarRunaS("2");
        gBD.eliminarRunaS("3");
        gBD.eliminarRunaS("4");
        gBD.eliminarRunaS("5");
        gBD.eliminarRunaS("6");
        gBD.eliminarRunaS("7");
        gBD.eliminarRunaS("8");
        gBD.eliminarRunaS("9");
        gBD.eliminarRunaS("10");
        gBD.eliminarRunaS("11");
        gBD.eliminarRunaS("12");

    }

    @Override
    public void onBackPressed() {

        if (idIntent != 0)
            super.onBackPressed();

    }
}