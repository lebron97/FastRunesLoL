package my.lebron.rafa_.facilrunaslol.vista;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.View;

import my.lebron.rafa_.facilrunaslol.R;

/**
 * Created by rafa_ on 12/03/2018.
 */

public class EventosRamaRunas implements View.OnClickListener {

    private RamaDeRunas ramaRuna;
    private int contador = 0, valorRuna, valorRunaSecundaria;
    private String id_runas;
    private String rama = "";
    private View view;

    EventosRamaRunas(RamaDeRunas ramaRuna) {
        this.ramaRuna = ramaRuna;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnPrecision: {

                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 1;
                    ramaRuna.getBtnPrecision().setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 1;
                    /*if (valorRuna == 4) {
                        Intent i = new Intent(v.getContext(), PantallaRunasPrecision.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                    } else {*/
                    Intent i = new Intent(v.getContext(), PantallaRunas.class);
                    i.putExtra("id_runas", ramaRuna.getId_runas());
                    i.putExtra("tipo_runa", valorRuna);
                    i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                    v.getContext().startActivity(i);
                    ramaRuna.finish();

                    //}
                }

                //Toast t = Toast.makeText(v.getContext(), "Has elegido Precision como rama " + rama, Toast.LENGTH_SHORT);
                //t.show();

                contador++;
            }
            break;

            case R.id.btnDominacion: {
                //v.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
                //v.invalidate();
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 2;
                    ramaRuna.getBtnDominacion().setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 2;
                    if (valorRuna == 1) {
                        Intent i = new Intent(v.getContext(), PantallaRunasPrecision.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();
                    } else {
                        Intent i = new Intent(v.getContext(), PantallaRunas.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();

                    }
                }

               /* Toast t = Toast.makeText(v.getContext(), "Has elegido Dominacion como rama " + rama, Toast.LENGTH_SHORT);
                t.show();*/

                contador++;

            }
            break;

            case R.id.btnBrujeria: {
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 3;
                    ramaRuna.getBtnBrujeria().setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 3;
                    if (valorRuna == 1) {
                        Intent i = new Intent(v.getContext(), PantallaRunasPrecision.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();
                    } else {
                        Intent i = new Intent(v.getContext(), PantallaRunas.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();

                    }
                }

                /*Toast t = Toast.makeText(v.getContext(), "Has elegido Brujeria como rama " + rama, Toast.LENGTH_SHORT);
                t.show();*/

                contador++;

            }
            break;

            case R.id.btnValor: {

                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 4;
                    ramaRuna.getBtnValor().setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 4;
                    if (valorRuna == 1) {
                        Intent i = new Intent(v.getContext(), PantallaRunasPrecision.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();
                    } else {
                        Intent i = new Intent(v.getContext(), PantallaRunas.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();
                    }
                }

               /* Toast t = Toast.makeText(v.getContext(), "Has elegido Valor como rama " + rama, Toast.LENGTH_SHORT);
                t.show();*/

                contador++;

            }
            break;

            case R.id.btnInspiracion: {
                if (contador == 0) {
                    rama = "primaria";
                    valorRuna = 5;
                    ramaRuna.getBtnInspiracion().setEnabled(false);
                } else {
                    rama = "secundaria";
                    valorRunaSecundaria = 5;
                    if (valorRuna == 1) {
                        Intent i = new Intent(v.getContext(), PantallaRunasPrecision.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();
                    } else {
                        Intent i = new Intent(v.getContext(), PantallaRunas.class);
                        i.putExtra("id_runas", ramaRuna.getId_runas());
                        i.putExtra("tipo_runa", valorRuna);
                        i.putExtra("tipo_runa_secundaria", valorRunaSecundaria);
                        v.getContext().startActivity(i);
                        ramaRuna.finish();
                    }

                }


               /* Toast t = Toast.makeText(v.getContext(), "Has elegido Inspiracion como rama " + rama, Toast.LENGTH_SHORT);
                t.show();*/

                contador++;

            }
            break;

            case R.id.btnInvisible:
                if(contador == 1)
                contador = 2;
                break;

        }


        if (contador == 1) {
            view = v;
            view.getBackground().setColorFilter(0xe0ffff00, PorterDuff.Mode.SRC_ATOP);
        }

        if (contador == 2) {
            view.getBackground().clearColorFilter();
        }


    }

}

