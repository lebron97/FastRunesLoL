package my.lebron.rafa_.facilrunaslol.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import my.lebron.rafa_.facilrunaslol.R;

public class SplashWelcome extends AppCompatActivity {
    private ImageView im, im2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);

        im = findViewById(R.id.im);
        im2 = findViewById(R.id.im2);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        im.startAnimation(myanim);
        im2.startAnimation(myanim);

        final Intent i = new Intent(this, PantallaCampeon.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();


    }
}
