package fr.youceflcv.strt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar atk_healthbar = findViewById(R.id.healthbar_1);
        ProgressBar def_heahlthbar = findViewById(R.id.healthbar_2);

        Personnage mael = new Personnage(10,2,3, atk_healthbar);
        Personnage citron = new Personnage(10,1,1, def_heahlthbar);

        //atk_healthbar.setMax(attaquant.health);
        //def_heahlthbar.setMax(defenseur.health);

        // def_heahlthbar.setProgress(25);

        Button atkbutton = findViewById(R.id.button);

        mael.basicattack(citron);
    }
}

