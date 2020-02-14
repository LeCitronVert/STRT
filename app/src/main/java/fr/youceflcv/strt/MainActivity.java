package fr.youceflcv.strt;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Skill> listskills;
    private List<Triptyque> listtriptycs;
    private List<Personnage> team1;
    private List<Personnage> team2;
    private List<Personnage> team3;
    private List<Personnage> team4;
    private List<Personnage> team5;
    private List<Personnage> team6;
    private List<Personnage> team7;
    private List<Personnage> team8;
    private List<Personnage> teamally;
    private List<Personnage> teamennemy;

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
    Animation lefttorightattack;
    Animation blinking;
    Animation fadeout;
    public int NumberAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        lefttorightattack = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_lefttoright_attack);
        blinking = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blinking);
        fadeout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        Intent intent = getIntent();
        if (intent != null){
            listskills = (List<Skill>) getIntent().getSerializableExtra("skills");
            listtriptycs = (List<Triptyque>) getIntent().getSerializableExtra("triptycs");
            team1 = (List<Personnage>) getIntent().getSerializableExtra("team1");
            team2 = (List<Personnage>) getIntent().getSerializableExtra("team2");
            team3 = (List<Personnage>) getIntent().getSerializableExtra("team3");
            team4 = (List<Personnage>) getIntent().getSerializableExtra("team4");
            team5 = (List<Personnage>) getIntent().getSerializableExtra("team5");
            team6 = (List<Personnage>) getIntent().getSerializableExtra("team6");
            team7 = (List<Personnage>) getIntent().getSerializableExtra("team7");
            team8 = (List<Personnage>) getIntent().getSerializableExtra("team8");
            teamally = (List<Personnage>) getIntent().getSerializableExtra("teamAlly");
            teamennemy = (List<Personnage>) getIntent().getSerializableExtra("teamEnnemy");
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
        attaquant1 = teamally.get(0);
        attaquant1.setHealthbar(atk_healthbar1);
        attaquant2 = teamally.get(1);
        attaquant2.setHealthbar(atk_healthbar2);
        attaquant3 = teamally.get(2);
        attaquant3.setHealthbar(atk_healthbar3);
        attaquant4 = teamally.get(3);
        attaquant4.setHealthbar(atk_healthbar4);
        defenseur1 = teamennemy.get(0);
        defenseur1.maxhealth = 1;
        defenseur1.health = 1;
        defenseur1.setHealthbar(def_heahlthbar1);
        defenseur2 = teamennemy.get(1);
        defenseur2.maxhealth = 1;
        defenseur2.health = 1;
        defenseur2.setHealthbar(def_heahlthbar2);
        defenseur3 = teamennemy.get(2);
        defenseur3.maxhealth = 1;
        defenseur3.health = 1;
        defenseur3.setHealthbar(def_heahlthbar3);
        defenseur4 = teamennemy.get(3);
        defenseur4.maxhealth = 1;
        defenseur4.health = 1;
        defenseur4.setHealthbar(def_heahlthbar4);
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
        resetActionSkill();
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
            for(int i=1;i<=4;i++){
                if(attaquant.actifskill[i-1] != null) {
                    for (int r = 1; r <= attaquant.actifskill[i - 1].cost; r++) {
                        String curr_cout = "coutskill" + i + r;
                        Log.d("curr_cout", curr_cout);
                        int resID = getResources().getIdentifier(curr_cout, "id", getPackageName());
                        ImageView cout = findViewById(resID);
                        cout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        if(nombre == 2){
            attaquant = attaquant2;
            selector2.setVisibility(view.VISIBLE);
            attaquant2.displayskill(actif1, actif2, actif3, actif4);
            for(int i=1;i<=4;i++){
                if(attaquant.actifskill[i-1] != null) {
                    for (int r = 1; r <= attaquant.actifskill[i - 1].cost; r++) {
                        String curr_cout = "coutskill" + i + r;
                        Log.d("curr_cout", curr_cout);
                        int resID = getResources().getIdentifier(curr_cout, "id", getPackageName());
                        ImageView cout = findViewById(resID);
                        cout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        if(nombre == 3){
            attaquant = attaquant3;
            selector3.setVisibility(view.VISIBLE);
            attaquant3.displayskill(actif1, actif2, actif3, actif4);
            for(int i=1;i<=4;i++){
                if(attaquant.actifskill[i-1] != null) {
                    for (int r = 1; r <= attaquant.actifskill[i - 1].cost; r++) {
                        String curr_cout = "coutskill" + i + r;
                        Log.d("curr_cout", curr_cout);
                        int resID = getResources().getIdentifier(curr_cout, "id", getPackageName());
                        ImageView cout = findViewById(resID);
                        cout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        if(nombre == 4){
            attaquant = attaquant4;
            selector4.setVisibility(view.VISIBLE);
            attaquant4.displayskill(actif1, actif2, actif3, actif4);
            for(int i=1;i<=4;i++){
                if(attaquant.actifskill[i-1] != null) {
                    for (int r = 1; r <= attaquant.actifskill[i - 1].cost; r++) {
                        String curr_cout = "coutskill" + i + r;
                        Log.d("curr_cout", curr_cout);
                        int resID = getResources().getIdentifier(curr_cout, "id", getPackageName());
                        ImageView cout = findViewById(resID);
                        cout.setVisibility(View.VISIBLE);
                    }
                }
            }
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
        resetActionSkill();
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
    public void Action(View view) throws InterruptedException {
        if(competence.cost <= attaquant.actions) {
            NumberAction = competence.useSkill(attaquant, cible);
            updateActionBar();
            resetAction(view);
            resetActionSkill();
            String curr_attaquant = "attaquant"+attaquant.position;
            int resID = getResources().getIdentifier(curr_attaquant, "id", getPackageName());
            ImageView attaquant_visuel= findViewById(resID);
            String curr_cible = "skindef"+cible.position;
            int resID2 = getResources().getIdentifier(curr_cible, "id", getPackageName());
            ImageView cible_visuel = findViewById(resID2);
            if(competence.type.equals("attack")){
                attaquant_visuel.startAnimation(lefttorightattack);
                cible_visuel.startAnimation(blinking);
                AnimationNumber();
            }
            if(competence.type.equals("buff")){

            }
            if(competence.type.equals("heal")){

            }
        }
        if (cible.health <= 0){
                String curr_character = "skindef"+cible.position;
                int resID = getResources().getIdentifier(curr_character, "id", getPackageName());
                ImageView character = findViewById(resID);
                character.startAnimation(fadeout);
                character.setVisibility(View.GONE);
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
        if(turntype.equals("allie")) {
            defenseur1.actions = defenseur1.maxaction;
            defenseur2.actions = defenseur2.maxaction;
            defenseur3.actions = defenseur3.maxaction;
            defenseur4.actions = defenseur4.maxaction;
            for (Skill skill : defenseur1.skills) {
                if (skill.temporary == true) {
                    skill.dure = skill.dure - 1;
                    if (skill.dure <= 0) {
                        skill.value = -(skill.value);
                        defenseur1.buff(skill);
                        defenseur1.skills.remove(skill);
                    }
                }
            }
            for (Skill skill : defenseur2.skills) {
                if (skill.temporary == true) {
                    skill.dure = skill.dure - 1;
                    if (skill.dure <= 0) {
                        skill.value = -(skill.value);
                        defenseur2.buff(skill);
                        defenseur2.skills.remove(skill);
                    }
                }
            }
            for (Skill skill : defenseur3.skills) {
                if (skill.temporary == true) {
                    skill.dure = skill.dure - 1;
                    if (skill.dure <= 0) {
                        skill.value = -(skill.value);
                        defenseur3.buff(skill);
                        defenseur3.skills.remove(skill);
                    }
                }
            }
            for (Skill skill : defenseur4.skills) {
                if (skill.temporary == true) {
                    skill.dure = skill.dure - 1;
                    if (skill.dure <= 0) {
                        skill.value = -(skill.value);
                        defenseur3.buff(skill);
                        defenseur4.skills.remove(skill);
                    }
                }
            }

            // IA ennemie
            Log.d("Début du tour IA ennemi", "Attaques ennemies");


            Personnage listattaquant[] = new Personnage[4];
            listattaquant[0] = attaquant1;
            listattaquant[1] = attaquant2;
            listattaquant[2] = attaquant3;
            listattaquant[3] = attaquant4;

            Personnage listdefenseur[] = new Personnage[4];
            listdefenseur[0] = defenseur1;
            listdefenseur[1] = defenseur2;
            listdefenseur[2] = defenseur3;
            listdefenseur[3] = defenseur4;

            defenseur1.AIRandom(listattaquant, listdefenseur);
            defenseur2.AIRandom(listattaquant, listdefenseur);
            defenseur3.AIRandom(listattaquant, listdefenseur);
            defenseur4.AIRandom(listattaquant, listdefenseur);
            for (Personnage character : listattaquant) {
                if (character.health <= 0) {
                    String curr_character = "attaquant" + character.position;
                    int resID = getResources().getIdentifier(curr_character, "id", getPackageName());
                    ImageView personnage = findViewById(resID);
                    personnage.setVisibility(View.GONE);
                    for(int i=1;i<=5;i++){
                        String curr_action = "action" + character.position + i;
                        Log.d("curr action",curr_action);
                        int resID2 = getResources().getIdentifier(curr_action, "id", getPackageName());
                        ImageView action = findViewById(resID2);
                        action.setVisibility(View.GONE);
                    }
                }
            }

            turntype = "ennemi";
            turnnumber++;
            endTurn(view);
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
            turntype = "allie";
        }
        Log.d("tour nombre", String.valueOf(turnnumber));
        updateAllActionBar();
    }
    public void victory(){
        for(Skill skill : attaquant1.skills){
            if(skill.temporary == true){
                    skill.value = -(skill.value);
                    attaquant1.buff(skill);
                    attaquant1.skills.remove(skill);
            }
        }
        for(Skill skill : attaquant2.skills){
            if(skill.temporary == true){
                    skill.value = -(skill.value);
                    attaquant2.buff(skill);
                    attaquant2.skills.remove(skill);
            }
        }
        for(Skill skill : attaquant3.skills){
            if(skill.temporary == true){
                    skill.value = -(skill.value);
                    attaquant3.buff(skill);
                    attaquant3.skills.remove(skill);
            }
        }
        for(Skill skill : attaquant4.skills){
            if(skill.temporary == true){
                    skill.value = -(skill.value);
                    attaquant4.buff(skill);
                    attaquant4.skills.remove(skill);
            }
        }
        Intent gameActivity = new Intent(MainActivity.this, level_up.class);
        gameActivity.putExtra("team1", (Serializable) team1);
        gameActivity.putExtra("team2", (Serializable) team2);
        gameActivity.putExtra("team3", (Serializable) team3);
        gameActivity.putExtra("team4", (Serializable) team4);
        gameActivity.putExtra("team5", (Serializable) team5);
        gameActivity.putExtra("team6", (Serializable) team6);
        gameActivity.putExtra("team7", (Serializable) team7);
        gameActivity.putExtra("team8", (Serializable) team8);
        gameActivity.putExtra("skills", (Serializable) listskills);
        gameActivity.putExtra("triptycs", (Serializable) listtriptycs);
        startActivity(gameActivity);
    }
    public void defeat(){
        Log.d("defaite","defaite");
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
    public void resetActionSkill(){
        for(int r= 1;r<=4;r++){
            for(int i= 1;i<=5;i++){
                String curraction = "coutskill"+r+i;
                int resID = getResources().getIdentifier(curraction, "id", getPackageName());
                ImageView cout = findViewById(resID);
                cout.setVisibility(View.GONE);
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
    public void AnimationNumber(){
        Log.d("numberaction",NumberAction+"");
        String curr_character = "";
        if(cible.ennemy == true){
            curr_character = "ZoneNumberAlly"+cible.position;
        }
        else{
            curr_character = "ZoneNumberEnnemy"+cible.position;
        }
        int resID = getResources().getIdentifier(curr_character, "id", getPackageName());
        final ConstraintLayout zone_degats = (ConstraintLayout) findViewById(resID);
        TextView textView = new TextView(MainActivity.this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setText(NumberAction+"");
        textView.setTextSize(18);
        zone_degats.addView(textView);
        textView.setTranslationY(80);
        textView.animate().setDuration(1500);
        textView.animate().translationX(0);
        textView.animate().translationY(0);
        textView.animate().alpha(0);
    }
}

