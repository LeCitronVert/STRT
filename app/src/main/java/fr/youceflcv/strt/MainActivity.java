package fr.youceflcv.strt;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private List<Skill> listskills;
    private List<Triptyque> listtriptycs;

    public Personnage attaquant1;
    public Personnage attaquant2;
    public Personnage attaquant3;
    public Personnage attaquant4;
    public Personnage defenseur1;
    public Personnage defenseur2;
    public Personnage defenseur3;
    public Personnage defenseur4;
    public int action_state;
    public int turnnumber;
    public String turntype;
    Personnage attaquant;
    public int attaquantnbr;
    Skill competence;
    Personnage cible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        Intent intent = getIntent();
        if (intent != null){
            listskills = (List<Skill>) getIntent().getSerializableExtra("skills");
            listtriptycs = (List<Triptyque>) getIntent().getSerializableExtra("triptycs");
            for(Skill skill : listskills) {
                skill.img = (getResources().getIdentifier(skill.imgname, "drawable", getPackageName()));
                Log.d("img", skill.imgname + String.valueOf(skill.img));
                for (Triptyque triptyque : listtriptycs) {
                    if(skill.triptyque.equals(triptyque.name)){
                        triptyque.skills.add(skill);
                        Log.d("liste de skill",skill.name);
                        Log.d("liste de skill", String.valueOf(skill.tier));
                        Log.d("liste de skill", String.valueOf(skill.actif));
                    }
                    Log.d("nom triptyque", triptyque.name);
                }
            }
        }
        action_state = 0;
        turnnumber = 1;
        turntype= "allie";
        ProgressBar atk_healthbar1 = findViewById(R.id.healthbar_1);
        ProgressBar atk_healthbar2 = findViewById(R.id.healthbar_2);
        ProgressBar atk_healthbar3 = findViewById(R.id.healthbar_3);
        ProgressBar atk_healthbar4 = findViewById(R.id.healthbar_4);
        ProgressBar def_heahlthbar1 = findViewById(R.id.healthbar_5);
        ProgressBar def_heahlthbar2 = findViewById(R.id.healthbar_6);
        ProgressBar def_heahlthbar3 = findViewById(R.id.healthbar_7);
        ProgressBar def_heahlthbar4 = findViewById(R.id.healthbar_8);
        Triptyque epee_longue = listtriptycs.get(0);
        Triptyque guerrier = listtriptycs.get(1);
        attaquant1 = new Personnage("paul",epee_longue,guerrier,guerrier,atk_healthbar1,false,1);
        attaquant2 = new Personnage("paul",epee_longue,guerrier,guerrier, atk_healthbar2,false,2);
        attaquant3 = new Personnage("paul",epee_longue,guerrier,guerrier, atk_healthbar3,false,3);
        attaquant4 = new Personnage("paul",epee_longue,guerrier,guerrier, atk_healthbar4,false,4);
        defenseur1 = new Personnage("paul",epee_longue,guerrier,guerrier, def_heahlthbar1,true,1);
        defenseur2 = new Personnage("paul",epee_longue,guerrier,guerrier, def_heahlthbar2,true,2);
        defenseur3 = new Personnage("paul",epee_longue,guerrier,guerrier, def_heahlthbar3,true,3);
        defenseur4 = new Personnage("paul",epee_longue,guerrier,guerrier, def_heahlthbar4,true,4);
        Log.d("skill du personnage", attaquant1.actifskill[0].name+attaquant1.actifskill[0].imgname);
        Personnage listattaquant[] = new Personnage[4];
        listattaquant[0] = attaquant1;
        listattaquant[1] = attaquant2;
        listattaquant[2] = attaquant3;
        listattaquant[3] = attaquant4;
        for(int r = 0 ; r<=3;r++){
            int realr = r+1;
            for(int i = 1; i<=listattaquant[r].maxaction;i++){
                String curraction = "action"+realr+i;
                int resID = getResources().getIdentifier(curraction, "id", getPackageName());
                ImageView action = findViewById(resID);
                action.setVisibility(View.VISIBLE);
            }
        }

        ViewGroup skillbar = findViewById(R.id.skillsbar);


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
        attaquantnbr = nombre;
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
        titre.setText(attaquant.actifskill[nombre].name);
        description.setText(attaquant.actifskill[nombre].desc);
        competence = attaquant.actifskill[nombre];
        Log.d("cible",attaquant.actifskill[nombre].cibletype);
        resetdesignskill();
        designskill(nombre, competence);
        if(attaquant.actifskill[nombre].cibletype.equals("allie")){
            setAlly(view);
        }
        if(attaquant.actifskill[nombre].cibletype.equals("ennemi")){
            Log.d("test ennemi", "pouet");
            setEnnemi(view);
        }
        action_state = 2;
        if(attaquant.actifskill[nombre].cibletype.equals("luimeme")){
            cible = attaquant;
            action_state = 3;
            if(attaquantnbr == 1){
                ImageView selector = findViewById(R.id.SelectCible1);
                selector.setVisibility(view.VISIBLE);
            }
            if(attaquantnbr == 2){
                ImageView selector = findViewById(R.id.SelectCible2);
                selector.setVisibility(view.VISIBLE);
            }
            if(attaquantnbr == 3){
                ImageView selector = findViewById(R.id.SelectCible3);
                selector.setVisibility(view.VISIBLE);
            }
            if(attaquantnbr == 4){
                ImageView selector = findViewById(R.id.SelectCible4);
                selector.setVisibility(view.VISIBLE);
            }
        }
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
            if(competence.cibletype.equals("allie")){
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
            if(competence.cibletype.equals("ennemi")){
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
        resetEnnemi(view);
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
    public void Action(View view){
        if(competence.cost <= attaquant.actions) {
            competence.useSkill(attaquant, cible);
            updateActionBar();
            resetAction(view);
        }
        if (cible.health <= 0){
            if (cible.ennemy == true){
                String curr_character = "skindef"+cible.position;
                int resID = getResources().getIdentifier(curr_character, "id", getPackageName());
                ImageView character = findViewById(resID);
                character.setVisibility(View.GONE);
            }
            else{
                String curr_character = "attaquant"+cible.position;
                int resID = getResources().getIdentifier(curr_character, "id", getPackageName());
                ImageView character = findViewById(resID);
                character.setVisibility(View.GONE);
            }
        }
        if(defenseur1.health <= 0 && defenseur2.health <= 0 &&defenseur3.health <= 0 &&defenseur4.health <= 0){
            victory();
        }
        if(attaquant1.health <= 0 && attaquant2.health <= 0 &&attaquant3.health <= 0 &&attaquant4.health <= 0){
            defeat();
        }
    }
    public void endTurn(View view){
        //effets passifs comme les poisons ou les saignements
        if(defenseur1.health <= 0 && defenseur2.health <= 0 &&defenseur3.health <= 0 &&defenseur4.health <= 0){
            victory();
        }
        if(attaquant1.health <= 0 && attaquant2.health <= 0 &&attaquant3.health <= 0 &&attaquant4.health <= 0){
            defeat();
        }
        updateAllActionBar();
        if(turntype.equals("allie")){
            defenseur1.actions = defenseur1.maxaction;
            defenseur2.actions = defenseur2.maxaction;
            defenseur3.actions = defenseur3.maxaction;
            defenseur4.actions = defenseur4.maxaction;
            for(Skill skill : defenseur1.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        defenseur1.buff(skill);
                        defenseur1.skills.remove(skill);
                    }
                }
            }
            for(Skill skill : defenseur2.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        defenseur2.buff(skill);
                        defenseur2.skills.remove(skill);
                    }
                }
            }
            for(Skill skill : defenseur3.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        defenseur3.buff(skill);
                        defenseur3.skills.remove(skill);
                    }
                }
            }
            for(Skill skill : defenseur4.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        defenseur3.buff(skill);
                        defenseur4.skills.remove(skill);
                    }
                }
            }
        }
        if(turntype.equals("ennemi")){
            attaquant1.actions = attaquant1.maxaction;
            attaquant2.actions = attaquant2.maxaction;
            attaquant3.actions = attaquant3.maxaction;
            attaquant4.actions = attaquant4.maxaction;
            for(Skill skill : attaquant1.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        attaquant1.buff(skill);
                        attaquant1.skills.remove(skill);
                    }
                }
            }
            for(Skill skill : attaquant2.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        attaquant2.buff(skill);
                        attaquant2.skills.remove(skill);
                    }
                }
            }
            for(Skill skill : attaquant3.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        attaquant3.buff(skill);
                        attaquant3.skills.remove(skill);
                    }
                }
            }
            for(Skill skill : attaquant4.skills){
                if(skill.temporary == true){
                    skill.dure = skill.dure - 1;
                    if(skill.dure <= 0){
                        skill.value = -(skill.value);
                        attaquant4.buff(skill);
                        attaquant4.skills.remove(skill);
                    }
                }
            }
        }
        Log.d("tour nombre", String.valueOf(turnnumber));
        if(turntype.equals("allie")){
            turntype = "ennemi";
            turnnumber++;
        }
        else{
            turntype = "allie";
        }
        Log.d("type tour",turntype);
    }
    public void victory(){
        Intent gameActivity = new Intent(MainActivity.this, Victory.class);
        startActivity(gameActivity);
    }
    public void defeat(){

    }
    public void updateActionBar(){
        for(int i = attaquant.maxaction; i> attaquant.actions;i--){
            String curraction = "action"+attaquantnbr+i;
            int resID = getResources().getIdentifier(curraction, "id", getPackageName());
            ImageView action = findViewById(resID);
            action.setImageResource(R.drawable.action_vide);
        }
        for(int i = 1;i<=attaquant.actions;i++){
            String curraction = "action"+attaquantnbr+i;
            int resID = getResources().getIdentifier(curraction, "id", getPackageName());
            ImageView action = findViewById(resID);
            action.setImageResource(R.drawable.action_pleine);
        }
    }
    public void updateAllActionBar(){
        for(int r= 1;r<=4;r++){
            for(int i = attaquant.maxaction; i> attaquant.actions;i--){
                String curraction = "action"+r+i;
                int resID = getResources().getIdentifier(curraction, "id", getPackageName());
                ImageView action = findViewById(resID);
                action.setImageResource(R.drawable.action_vide);
            }
            for(int i = 1;i<=attaquant.actions;i++){
                String curraction = "action"+r+i;
                int resID = getResources().getIdentifier(curraction, "id", getPackageName());
                ImageView action = findViewById(resID);
                action.setImageResource(R.drawable.action_pleine);
            }
        }
    }
    public void designskill(int nombre, Skill skill){
        int realnumber = nombre+1;
        String currskill = "actif"+realnumber;
        int resID = getResources().getIdentifier(currskill, "id", getPackageName());
        ImageButton select = findViewById(resID);
        String variableValue = skill.imgname+"_clicked";
        select.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
    }
    public void resetdesignskill(){
        ImageView actif1 = findViewById(R.id.actif1);
        ImageView actif2 = findViewById(R.id.actif2);
        ImageView actif3 = findViewById(R.id.actif3);
        ImageView actif4 = findViewById(R.id.actif4);
        attaquant.displayskill(actif1, actif2, actif3, actif4);
    }
}

