package my.lebron.rafa_.facilrunaslol.vista;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import my.lebron.rafa_.facilrunaslol.R;

public class PanelInfo extends Activity {

    private TextView txtInfo, txtInfo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_panel_info);

        txtInfo = findViewById(R.id.txtInfo);
        txtInfo2 = findViewById(R.id.txtInfo2);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.ffftusj);

        txtInfo.setTypeface(typeface);
        txtInfo2.setTypeface(typeface);


    }
}
