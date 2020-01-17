package fr.youceflcv.strt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar atk_healthbar = findViewById(R.id.healthbar_1);
        ProgressBar def_heahlthbar = findViewById(R.id.healthbar_2);

        final Personnage mael = new Personnage(10,2,3, atk_healthbar);
        final Personnage citron = new Personnage(10,1,1, def_heahlthbar);

        //atk_healthbar.setMax(attaquant.health);
        //def_heahlthbar.setMax(defenseur.health);

        // def_heahlthbar.setProgress(25);



        // mael.basicattack(citron);
        Skill glaire = new Skill("Crachat de glaire","Crache un glaire infligeant 5 de dégâts.","attack",5, "", "none");
        Skill swun = new Skill("Swun", "Un soin peu efficace rendant 1 PV à son utilisateur.", "heal", 1, "", "none");
        Skill berserk = new Skill("Rage", "Sous la colère, augmente l'attaque de l'utilisateur", "buff", -9, "attack", "none");
        mael.skill1 = glaire;
        citron.skill1 = swun;
        mael.skill2 = berserk;
        // citron.skill1.useSkill(citron, citron);
        // mael.skill1.useSkill(mael, citron);

        Button atkbutton = (Button) findViewById(R.id.button);




    }



}

