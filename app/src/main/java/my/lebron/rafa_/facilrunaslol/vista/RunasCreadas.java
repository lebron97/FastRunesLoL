package my.lebron.rafa_.facilrunaslol.vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdView;

import java.io.File;

import my.lebron.rafa_.facilrunaslol.R;

public class RunasCreadas extends AppCompatActivity {

    private String id_runas;
    private CoordinatorLayout cl;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runas_creadas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cl = findViewById(R.id.cl);

        /*adView = findViewById(R.id.adView8);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

        id_runas = getIntent().getExtras().getString("id_runas");

        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "FastRunesLOL" + "/" + id_runas + ".jpg";
        File imageFile = new File(mPath);

        Bitmap bitmap = BitmapFactory.decodeFile(mPath);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getBaseContext().getResources(), bitmap);

        cl.setBackground(bitmapDrawable);

        //openScreenshot(imageFile);

    }

    public void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
