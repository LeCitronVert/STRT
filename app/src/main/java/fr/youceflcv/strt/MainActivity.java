package fr.youceflcv.strt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Personnage attaquant1;
    private Personnage attaquant2;
    private Personnage attaquant3;
    private Personnage attaquant4;
    private Personnage defenseur1;
    private Personnage defenseur2;
    private Personnage defenseur3;
    private Personnage defenseur4;
    private int action_state;
    Personnage attaquant;
    Skill competence;
    Personnage cible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        action_state = 0;
        ProgressBar atk_healthbar1 = findViewById(R.id.healthbar_1);
        ProgressBar atk_healthbar2 = findViewById(R.id.healthbar_2);
        ProgressBar atk_healthbar3 = findViewById(R.id.healthbar_3);
        ProgressBar atk_healthbar4 = findViewById(R.id.healthbar_4);
        ProgressBar def_heahlthbar1 = findViewById(R.id.healthbar_5);
        ProgressBar def_heahlthbar2 = findViewById(R.id.healthbar_6);
        ProgressBar def_heahlthbar3 = findViewById(R.id.healthbar_7);
        ProgressBar def_heahlthbar4 = findViewById(R.id.healthbar_8);
        attaquant1 = new Personnage(10,2,3, atk_healthbar1);
        attaquant2 = new Personnage(10,2,3, atk_healthbar2);
        attaquant3 = new Personnage(10,2,3, atk_healthbar3);
        attaquant4 = new Personnage(10,2,3, atk_healthbar4);
        defenseur1 = new Personnage(10,1,1, def_heahlthbar1);
        defenseur2 = new Personnage(10,1,1, def_heahlthbar2);
        defenseur3 = new Personnage(10,1,1, def_heahlthbar3);
        defenseur4 = new Personnage(10,1,1, def_heahlthbar4);
        ViewGroup skillbar = findViewById(R.id.skillsbar);






        //atk_healthbar.setMax(attaquant.health);
        //def_heahlthbar.setMax(defenseur.health);

        // def_heahlthbar.setProgress(25);



        // mael.basicattack(citron);
        Skill glaire = new Skill("Crachat de glaire","Crache un glaire infligeant 5 de dégâts.",R.drawable.skill3,"attack",5, "", "none","ennemi");
        Skill swun = new Skill("Swun", "Un soin peu efficace rendant 1 PV à son utilisateur.",R.drawable.skill2, "heal", 1, "", "none","allie");
        Skill berserk = new Skill("Rage", "Sous la colère, augmente l'attaque de l'utilisateur",R.drawable.skill1, "buff", -9, "attack", "none","ennemi");

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
        resetcible(view);
        resetAlly(view);
        resetEnnemi(view);
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
        TextView description = findViewById(R.id.description_skill);
        TextView titre = findViewById(R.id.title_skill);
        titre.setText("");
        description.setText("");
        if(nombre == 1){
            attaquant = attaquant1;
            selector1.setVisibility(view.VISIBLE);
            attaquant1.displayskill(actif1, actif2, actif3, actif4);
        }
        if(nombre == 2){
            attaquant = attaquant2;
            selector2.setVisibility(view.VISIBLE);
            attaquant2.displayskill(actif1, actif2, actif3, actif4);
        }
        if(nombre == 3){
            attaquant = attaquant3;
            selector3.setVisibility(view.VISIBLE);
            attaquant3.displayskill(actif1, actif2, actif3, actif4);
        }
        if(nombre == 4){
            attaquant = attaquant4;
            selector4.setVisibility(view.VISIBLE);
            attaquant4.displayskill(actif1, actif2, actif3, actif4);
        }
        action_state = 1;
        activefinal(view);
    }
    public void setskill(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        resetcible(view);
        resetAlly(view);
        resetEnnemi(view);
        TextView description = findViewById(R.id.description_skill);
        TextView titre = findViewById(R.id.title_skill);
        titre.setText(attaquant.skills.get(nombre).name);
        description.setText(attaquant.skills.get(nombre).desc);
        competence = attaquant.skills.get(nombre);
        if(attaquant.skills.get(nombre).cibletype == "allie"){
            setAlly(view);
        }
        if(attaquant.skills.get(nombre).cibletype == "ennemi"){
            setEnnemi(view);
        }
        action_state = 2;
        activefinal(view);
    }
    public void setCible(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        resetcible(view);
        ImageView selector1 = findViewById(R.id.SelectCible1);
        ImageView selector2 = findViewById(R.id.SelectCible2);
        ImageView selector3 = findViewById(R.id.SelectCible3);
        ImageView selector4 = findViewById(R.id.SelectCible4);
        ImageView selector5 = findViewById(R.id.SelectCible5);
        ImageView selector6 = findViewById(R.id.SelectCible6);
        ImageView selector7 = findViewById(R.id.SelectCible7);
        ImageView selector8 = findViewById(R.id.SelectCible8);
        if (action_state == 2 || action_state == 3){
            if(competence.cibletype == "allie"){
                if (nombre == 1){
                    selector1.setVisibility(view.VISIBLE);
                    cible = attaquant1;
                }
                if (nombre == 2){
                    selector2.setVisibility(view.VISIBLE);
                    cible = attaquant2;
                }
                if (nombre == 3){
                    selector3.setVisibility(view.VISIBLE);
                    cible = attaquant3;
                }
                if (nombre == 4){
                    selector4.setVisibility(view.VISIBLE);
                    cible = attaquant4;
                }
            }
            if(competence.cibletype == "ennemi"){
                if (nombre == 5){
                    selector5.setVisibility(view.VISIBLE);
                    cible = defenseur1;
                }
                if (nombre == 6){
                    selector6.setVisibility(view.VISIBLE);
                    cible = defenseur2;
                }
                if (nombre == 7){
                    selector7.setVisibility(view.VISIBLE);
                    cible = defenseur3;
                }
                if (nombre == 8){
                    selector8.setVisibility(view.VISIBLE);
                    cible = defenseur4;
                }
            }
        }
        action_state = 3;
        activefinal(view);
    }
    public void resetcible(View view){
        ImageView selector1 = findViewById(R.id.SelectCible1);
        ImageView selector2 = findViewById(R.id.SelectCible2);
        ImageView selector3 = findViewById(R.id.SelectCible3);
        ImageView selector4 = findViewById(R.id.SelectCible4);
        ImageView selector5 = findViewById(R.id.SelectCible5);
        ImageView selector6 = findViewById(R.id.SelectCible6);
        ImageView selector7 = findViewById(R.id.SelectCible7);
        ImageView selector8 = findViewById(R.id.SelectCible8);
        selector1.setVisibility(view.GONE);
        selector2.setVisibility(view.GONE);
        selector3.setVisibility(view.GONE);
        selector4.setVisibility(view.GONE);
        selector5.setVisibility(view.GONE);
        selector6.setVisibility(view.GONE);
        selector7.setVisibility(view.GONE);
        selector8.setVisibility(view.GONE);
    }
    public void resetattaquant(View view){
        ImageView selector1 = findViewById(R.id.SelectAttaquant1);
        ImageView selector2 = findViewById(R.id.SelectAttaquant2);
        ImageView selector3 = findViewById(R.id.SelectAttaquant3);
        ImageView selector4 = findViewById(R.id.SelectAttaquant4);
        selector1.setVisibility(view.GONE);
        selector2.setVisibility(view.GONE);
        selector3.setVisibility(view.GONE);
        selector4.setVisibility(view.GONE);
    }
    public void setAlly(View view){
        Button ally1 = findViewById(R.id.cible1);
        Button ally2 = findViewById(R.id.cible2);
        Button ally3 = findViewById(R.id.cible3);
        Button ally4 = findViewById(R.id.cible4);
        ally1.setVisibility(view.VISIBLE);
        ally2.setVisibility(view.VISIBLE);
        ally3.setVisibility(view.VISIBLE);
        ally4.setVisibility(view.VISIBLE);
    }
    public void setEnnemi(View view){
        ImageButton defenseur1 = findViewById(R.id.defenseur1);
        ImageButton defenseur2 = findViewById(R.id.defenseur2);
        ImageButton defenseur3 = findViewById(R.id.defenseur3);
        ImageButton defenseur4 = findViewById(R.id.defenseur4);
        defenseur1.setVisibility(view.VISIBLE);
        defenseur2.setVisibility(view.VISIBLE);
        defenseur3.setVisibility(view.VISIBLE);
        defenseur4.setVisibility(view.VISIBLE);
    }
    public void resetEnnemi(View view){
        ImageButton defenseur1 = findViewById(R.id.defenseur1);
        ImageButton defenseur2 = findViewById(R.id.defenseur2);
        ImageButton defenseur3 = findViewById(R.id.defenseur3);
        ImageButton defenseur4 = findViewById(R.id.defenseur4);
        defenseur1.setVisibility(view.GONE);
        defenseur2.setVisibility(view.GONE);
        defenseur3.setVisibility(view.GONE);
        defenseur4.setVisibility(view.GONE);
    }
    public void resetAlly(View view){
        Button ally1 = findViewById(R.id.cible1);
        Button ally2 = findViewById(R.id.cible2);
        Button ally3 = findViewById(R.id.cible3);
        Button ally4 = findViewById(R.id.cible4);
        ally1.setVisibility(view.GONE);
        ally2.setVisibility(view.GONE);
        ally3.setVisibility(view.GONE);
        ally4.setVisibility(view.GONE);
    }
    public void resetAction(View view){
        resetcible(view);
        resetAlly(view);
        resetattaquant(view);
        TextView description = findViewById(R.id.description_skill);
        TextView titre = findViewById(R.id.title_skill);
        titre.setText("");
        description.setText("");
        ImageView actif1 = findViewById(R.id.actif1);
        ImageView actif2 = findViewById(R.id.actif2);
        ImageView actif3 = findViewById(R.id.actif3);
        ImageView actif4 = findViewById(R.id.actif4);
        actif1.setVisibility(View.GONE);
        actif2.setVisibility(View.GONE);
        actif3.setVisibility(View.GONE);
        actif4.setVisibility(View.GONE);
        Personnage attaquant;
        Skill competence;
        Personnage cible;
        action_state = 0;
        activefinal(view);
    }
    public void activefinal(View view){
        Button finalisation = findViewById(R.id.confirm);
        if(action_state == 3){
            finalisation.setVisibility(view.VISIBLE);
        }
        else{
            finalisation.setVisibility(view.GONE);
        }
    }
}

