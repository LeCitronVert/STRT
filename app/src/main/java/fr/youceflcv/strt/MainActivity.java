package fr.youceflcv.strt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Personnage attaquant1;
    private Personnage attaquant2;
    private Personnage attaquant3;
    private Personnage attaquant4;
    private Personnage defenseur1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar atk_healthbar1 = findViewById(R.id.healthbar_1);
        ProgressBar atk_healthbar2 = findViewById(R.id.healthbar_2);
        ProgressBar atk_healthbar3 = findViewById(R.id.healthbar_3);
        ProgressBar atk_healthbar4 = findViewById(R.id.healthbar_4);
        ProgressBar def_heahlthbar5 = findViewById(R.id.healthbar_5);
        attaquant1 = new Personnage(10,2,3, atk_healthbar1);
        attaquant2 = new Personnage(10,2,3, atk_healthbar2);
        attaquant3 = new Personnage(10,2,3, atk_healthbar3);
        attaquant4 = new Personnage(10,2,3, atk_healthbar4);
        defenseur1 = new Personnage(10,1,1, def_heahlthbar5);
        Button atkbutton = (Button) findViewById(R.id.button);
        ViewGroup skillbar = findViewById(R.id.skillsbar);



        Personnage attaquant;
        Skill competence;
        Personnage cible;


        //atk_healthbar.setMax(attaquant.health);
        //def_heahlthbar.setMax(defenseur.health);

        // def_heahlthbar.setProgress(25);



        // mael.basicattack(citron);
        Skill glaire = new Skill("Crachat de glaire","Crache un glaire infligeant 5 de dégâts.",R.drawable.skill3,"attack",5, "", "none");
        Skill swun = new Skill("Swun", "Un soin peu efficace rendant 1 PV à son utilisateur.",R.drawable.skill2, "heal", 1, "", "none");
        Skill berserk = new Skill("Rage", "Sous la colère, augmente l'attaque de l'utilisateur",R.drawable.skill1, "buff", -9, "attack", "none");

        attaquant1.skills.add(glaire);
        attaquant2.skills.add(berserk);
        attaquant2.skills.add(swun);
        attaquant3.skills.add(glaire);
        defenseur1.skills.add(swun);


        // citron.skill1.useSkill(citron, citron);
        // mael.skill1.useSkill(mael, citron);

        // attaquant = mael;
        // cible = citron;
        // competence = attaquant.skills.get(0);

        // competence.useSkill(attaquant, cible);
        // attaquant.displayskill(actif1, actif2, actif3, actif4);

    }
    public void setattaquant(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        ImageView actif1 = findViewById(R.id.actif1);
        ImageView actif2 = findViewById(R.id.actif2);
        ImageView actif3 = findViewById(R.id.actif3);
        ImageView actif4 = findViewById(R.id.actif4);
        ImageView selector1 = findViewById(R.id.SelectAttaquant1);
        ImageView selector2 = findViewById(R.id.SelectAttaquant2);
        ImageView selector3 = findViewById(R.id.SelectAttaquant3);
        ImageView selector4 = findViewById(R.id.SelectAttaquant4);
        selector1.setVisibility(view.GONE);
        selector2.setVisibility(view.GONE);
        selector3.setVisibility(view.GONE);
        selector4.setVisibility(view.GONE);
        if(nombre == 1){
            Personnage attaquant = attaquant1;
            selector1.setVisibility(view.VISIBLE);
            attaquant1.displayskill(actif1, actif2, actif3, actif4);
        }
        if(nombre == 2){
            Personnage attaquant = attaquant2;
            selector2.setVisibility(view.VISIBLE);
            attaquant2.displayskill(actif1, actif2, actif3, actif4);
        }
        if(nombre == 3){
            Personnage attaquant = attaquant3;
            selector3.setVisibility(view.VISIBLE);
            attaquant3.displayskill(actif1, actif2, actif3, actif4);
        }
        if(nombre == 4){
            Personnage attaquant = attaquant4;
            selector4.setVisibility(view.VISIBLE);
            attaquant4.displayskill(actif1, actif2, actif3, actif4);
        }
    }



}

