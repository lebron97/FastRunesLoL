package my.lebron.rafa_.facilrunaslol.vista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


import java.util.ArrayList;
import java.util.Random;

import my.lebron.rafa_.facilrunaslol.POJOs.Campeon;
import my.lebron.rafa_.facilrunaslol.R;
import my.lebron.rafa_.facilrunaslol.sqlitedatabase.GestorBD;

public class PantallaCampeon extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private ListView ltCampeon;
    private ArrayList<Campeon> datosCampeon;
    private GestorBD gBD;
    private Cursor c;
    //private ListView ltDatosCampeon;
    private Button btnInsertarCampeon, btnBorrarCampeon, btnBuscarCampeon;
    private AlertDialog.Builder builder, builderBorrar;
    private TextView txtBuscarCampeon;
    private ArrayAdapter<Campeon> arrayAdapter;
    private final String[] ordenacion = {"Sin orden", "Ascendente", "Descendente"};
    private Spinner sOrdenarCampeon;
    private Activity act = this;
    private Random r;
    private int aleatorio;
    private CoordinatorLayout coorLayout;
    private AdView adView; //publicidad tipo banner
    private InterstitialAd interstitialAd; // Publicidad Pantalla Completa
    private Context mContext= PantallaCampeon.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_campeon);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



       /* CoordinatorLayout container = findViewById(R.id.coorLayout);

        View viewInflate = View.inflate(this, R.layout.listrow, container);

        TextView t = viewInflate.findViewById(R.id.textView2);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.ffftusj);
        t.setTypeface(typeface);*/


        /*ProgressDialog dialog=new ProgressDialog(act);
        dialog.setMessage("message");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();*/


        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
            } else {
                //do here
            }
        } else {
            //do here
        }

        /*adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(AdSize.BANNER);

        coorLayout = findViewById(R.id.coorLayout);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        coorLayout.addView(adView);*/

        MobileAds.initialize(this, "ca-app-pub-9194517647213506~6553184034");

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        gBD = new GestorBD(getBaseContext());

        final EditText input = new EditText(getBaseContext());

        ltCampeon = findViewById(R.id.ltCampeon);

        ltCampeon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                new carga().execute(position);
                //Toast t = Toast.makeText(view.getContext(), datosCampeon.get(position).getId_campeon()+"", Toast.LENGTH_SHORT);
                //t.show();
            }
        });



        FloatingActionButton btnInsertarCampeon = findViewById(R.id.btnInsertarCampeon);
        btnInsertarCampeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LinearLayout layout = new LinearLayout(act);
                layout.setOrientation(LinearLayout.VERTICAL);

                builder = new AlertDialog.Builder(act);
                builder.setMessage("Insertar nuevo Campeon");

                builder.setPositiveButton("Insertar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        gBD.insertarCampeon(input.getText().toString());

                        arrayAdapter.clear();
                        c = gBD.cargarCursorCampeon();
                        cargaCampeones();

                        input.setText("");

                        layout.removeAllViews();

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        input.setText("");

                        layout.removeAllViews();
                        dialog.cancel();
                    }
                });

                if (input.getParent() != null)
                    ((ViewGroup) input.getParent()).removeAllViews();
                input.setTextColor(Color.BLACK);
                input.setHintTextColor(Color.GRAY);
                input.setHint("Nombre");
                layout.addView(input);


                builder.setView(layout);
                AlertDialog ventanita = builder.create();
                ventanita.setCancelable(false);
                ventanita.show();

            }


        });

        FloatingActionButton btnVerRunas = findViewById(R.id.btnVerRunas);
        btnVerRunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), TabsRunas.class);
                startActivity(i);
                finish();
            }
        });

        ltCampeon.setLongClickable(true);

        ltCampeon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {

                builder = new AlertDialog.Builder(act);
                builder.setMessage("Borrar Campeon");

                builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        gBD.eliminarCampeon(datosCampeon.get(pos).getId_campeon() +"");
                        gBD.eliminarRunaPorCampeon(datosCampeon.get(pos).getId_campeon() +"");
                        //gBD.eliminarRunaPPorPag(id_runa);
                        //gBD.eliminarRunaSPorPag(id_runa);

                        arrayAdapter.clear();
                        c = gBD.cargarCursorCampeon();
                        cargaCampeones();

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                AlertDialog ventanita = builder.create();
                ventanita.setCancelable(false);
                ventanita.show();

                return true;
            }
        });

        c = gBD.cargarCursorCampeon();

        datosCampeon = new ArrayList<>();

        cargaCampeones();


    }

    public void rellenaCampeon(Cursor c) {
        datosCampeon.add(new Campeon(c.getInt(0), c.getString(1)));
    }

    public void cargaCampeones() {

        if (c.moveToFirst()) {
            do {
                rellenaCampeon(c);
            } while (c.moveToNext());
        }


        arrayAdapter = new ArrayAdapter<Campeon>(
                getBaseContext(),
                R.layout.listrow, R.id.textView2,
                datosCampeon);

        ltCampeon.setAdapter(arrayAdapter);

        c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_campeon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getBaseContext(), PanelInfo.class);
            startActivity(i);
            return true;
        }else if (id == R.id.action_settings_p){
            Intent i = new Intent(getBaseContext(), Politica.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class carga extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            r = new Random();
            aleatorio = r.nextInt(5);

            Intent i = new Intent(getBaseContext(), PantallaListaRunas.class);
            i.putExtra("aleatorio", aleatorio);
            i.putExtra("id_campeon", datosCampeon.get(integers[0].intValue()).getId_campeon() + "");
            startActivity(i);
            finish();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    private static final int REQUEST = 112;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(mContext, "Debes conceder los permisos para que la app funcione correctamente", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //check permissions for marshmallow

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }




}
