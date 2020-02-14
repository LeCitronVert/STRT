package fr.youceflcv.strt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class create_team extends AppCompatActivity {

    private List<Skill> listskills;
    private List<Triptyque> listtriptycs;
    private List<Personnage> team1 = new ArrayList<Personnage>();
    private List<Personnage> team2 = new ArrayList<Personnage>();
    private List<Personnage> team3 = new ArrayList<Personnage>();
    private List<Personnage> team4 = new ArrayList<Personnage>();
    private List<Personnage> team5 = new ArrayList<Personnage>();
    private List<Personnage> team6 = new ArrayList<Personnage>();
    private List<Personnage> team7 = new ArrayList<Personnage>();
    private List<Personnage> team8 = new ArrayList<Personnage>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        Intent intent = getIntent();
        if (intent != null){
            listskills = (List<Skill>) getIntent().getSerializableExtra("skills");
            listtriptycs = (List<Triptyque>) getIntent().getSerializableExtra("triptycs");
            for(Skill skill : listskills) {
                skill.img = (getResources().getIdentifier(skill.imgname, "drawable", getPackageName()));
                for (Triptyque triptyque : listtriptycs) {
                    if(skill.triptyque.equals(triptyque.name)){
                        triptyque.skills.add(skill);
                    }
                }
            }
        }
        for(int i = 1;i<=4;i++) {
            for (int r = 1;r<=6;r++){
                String curr_layout = "layout" + i + r;
                int resID = getResources().getIdentifier(curr_layout, "id", getPackageName());
                LinearLayout layout = findViewById(resID);
                layout.setVisibility(View.GONE);
            }
        }
        String listname[] = {"Ruby","Weiss","Blake","Yang","Jaune","Nora","Pyrrha","Lie","Penny","Ciel","Ozpin","Glynda","James","Qrow","Raven","Summer","Taiyang","Jacques","Winter","Whitley"};
        ProgressBar atk_healthbar1 = findViewById(R.id.healthbar_1);
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team1.add(curr_personnage);
        }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team2.add(curr_personnage);        }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team3.add(curr_personnage);        }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team4.add(curr_personnage);         }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team5.add(curr_personnage);         }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team6.add(curr_personnage);         }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team7.add(curr_personnage);         }
        for(int i = 0;i<=3;i++){
            int randomnamenumber = (int) (Math.random() * ( listname.length - 0 ));
            int randomweaponnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randompowernumber = (int) (Math.random() * (listtriptycs.size() - 0));
            int randomclassesnumber = (int) (Math.random() * (listtriptycs.size() - 0));
            Personnage curr_personnage = new Personnage(listname[randomnamenumber],listtriptycs.get(randomweaponnumber),listtriptycs.get(randompowernumber),listtriptycs.get(1),atk_healthbar1,false,i+1);
            team8.add(curr_personnage);         }
        TextView name1 =findViewById(R.id.NomPerso1);
        TextView name2 =findViewById(R.id.NomPerso2);
        TextView name3 =findViewById(R.id.NomPerso3);
        TextView name4 =findViewById(R.id.NomPerso4);
        name1.setText(team1.get(0).name);
        name2.setText(team1.get(1).name);
        name3.setText(team1.get(2).name);
        name4.setText(team1.get(3).name);
        ImageView visuperso1 = findViewById(R.id.visuPerso1);
        ImageView visuperso2 = findViewById(R.id.visuPerso2);
        ImageView visuperso3 = findViewById(R.id.visuPerso3);
        ImageView visuperso4 = findViewById(R.id.visuPerso4);
        visuperso1.setImageResource(team1.get(0).classes.imgvisu);
        visuperso2.setImageResource(team1.get(1).classes.imgvisu);
        visuperso3.setImageResource(team1.get(2).classes.imgvisu);
        visuperso4.setImageResource(team1.get(3).classes.imgvisu);
        ImageView classes1 = findViewById(R.id.classes1);
        ImageView classes2 = findViewById(R.id.classes2);
        ImageView classes3 = findViewById(R.id.classes3);
        ImageView classes4 = findViewById(R.id.classes4);
        classes1.setImageResource(team1.get(0).classes.img);
        classes2.setImageResource(team1.get(1).classes.img);
        classes3.setImageResource(team1.get(2).classes.img);
        classes4.setImageResource(team1.get(3).classes.img);
        ImageView weapon1 = findViewById(R.id.weapon1);
        ImageView weapon2 = findViewById(R.id.weapon2);
        ImageView weapon3 = findViewById(R.id.weapon3);
        ImageView weapon4 = findViewById(R.id.weapon4);
        weapon1.setImageResource(team1.get(0).weapon.img);
        weapon2.setImageResource(team1.get(1).weapon.img);
        weapon3.setImageResource(team1.get(2).weapon.img);
        weapon4.setImageResource(team1.get(3).weapon.img);
        ImageView power1 = findViewById(R.id.power1);
        ImageView power2 = findViewById(R.id.power2);
        ImageView power3 = findViewById(R.id.power3);
        ImageView power4 = findViewById(R.id.power4);
        power1.setImageResource(team1.get(0).power.img);
        power2.setImageResource(team1.get(1).power.img);
        power3.setImageResource(team1.get(2).power.img);
        power4.setImageResource(team1.get(3).power.img);
        TextView life1 = findViewById(R.id.life1);
        TextView life2 = findViewById(R.id.life2);
        TextView life3 = findViewById(R.id.life3);
        TextView life4 = findViewById(R.id.life4);
        life1.setText(String.valueOf(team1.get(0).maxhealth));
        life2.setText(String.valueOf(team1.get(1).maxhealth));
        life3.setText(String.valueOf(team1.get(2).maxhealth));
        life4.setText(String.valueOf(team1.get(3).maxhealth));
        TextView accuracy1 = findViewById(R.id.accuracy1);
        TextView accuracy2 = findViewById(R.id.accuracy2);
        TextView accuracy3 = findViewById(R.id.accuracy3);
        TextView accuracy4 = findViewById(R.id.accuracy4);
        accuracy1.setText(String.valueOf(team1.get(0).precision));
        accuracy2.setText(String.valueOf(team1.get(1).precision));
        accuracy3.setText(String.valueOf(team1.get(2).precision));
        accuracy4.setText(String.valueOf(team1.get(3).precision));
        TextView attack1 = findViewById(R.id.attack1);
        TextView attack2 = findViewById(R.id.attack2);
        TextView attack3 = findViewById(R.id.attack3);
        TextView attack4 = findViewById(R.id.attack4);
        attack1.setText(String.valueOf(team1.get(0).attack));
        attack2.setText(String.valueOf(team1.get(1).attack));
        attack3.setText(String.valueOf(team1.get(2).attack));
        attack4.setText(String.valueOf(team1.get(3).attack));
        TextView defense1 = findViewById(R.id.defense1);
        TextView defense2 = findViewById(R.id.defense2);
        TextView defense3 = findViewById(R.id.defense3);
        TextView defense4 = findViewById(R.id.defense4);
        defense1.setText(String.valueOf(team1.get(0).defense));
        defense2.setText(String.valueOf(team1.get(1).defense));
        defense3.setText(String.valueOf(team1.get(2).defense));
        defense4.setText(String.valueOf(team1.get(3).defense));
        TextView agility1 = findViewById(R.id.agility1);
        TextView agility2 = findViewById(R.id.agility2);
        TextView agility3 = findViewById(R.id.agility3);
        TextView agility4 = findViewById(R.id.agility4);
        agility1.setText(String.valueOf(team1.get(0).esquive));
        agility2.setText(String.valueOf(team1.get(1).esquive));
        agility3.setText(String.valueOf(team1.get(2).esquive));
        agility4.setText(String.valueOf(team1.get(3).esquive));
        TextView critical1 = findViewById(R.id.critical1);
        TextView critical2 = findViewById(R.id.critical2);
        TextView critical3 = findViewById(R.id.critical3);
        TextView critical4 = findViewById(R.id.critical4);
        critical1.setText(String.valueOf(team1.get(0).critical));
        critical2.setText(String.valueOf(team1.get(1).critical));
        critical3.setText(String.valueOf(team1.get(2).critical));
        critical4.setText(String.valueOf(team1.get(3).critical));
        TextView speed1 = findViewById(R.id.speed1);
        TextView speed2 = findViewById(R.id.speed2);
        TextView speed3 = findViewById(R.id.speed3);
        TextView speed4 = findViewById(R.id.speed4);
        speed1.setText(String.valueOf(team1.get(0).speed));
        speed2.setText(String.valueOf(team1.get(1).speed));
        speed3.setText(String.valueOf(team1.get(2).speed));
        speed4.setText(String.valueOf(team1.get(3).speed));
        for(int i = 0;i<=3;i++) {
            int secondnumber = 1;
            int realnumber = i+1;
            for (Skill skill: team1.get(i).skills){
                String curr_skill = "skills" + realnumber + secondnumber;
                int resID = getResources().getIdentifier(curr_skill, "id", getPackageName());
                ImageView skillimage = findViewById(resID);
                skillimage.setImageResource(skill.img);
                String curr_name = "nameskill" + realnumber + secondnumber;
                resID = getResources().getIdentifier(curr_name, "id", getPackageName());
                TextView skillname = findViewById(resID);
                skillname.setText(skill.name);
                String curr_type = "typeskill" + realnumber + secondnumber;
                resID = getResources().getIdentifier(curr_type, "id", getPackageName());
                TextView skilltype = findViewById(resID);
                skilltype.setText(skill.type);
                String curr_desc = "description" + realnumber + secondnumber;
                resID = getResources().getIdentifier(curr_desc, "id", getPackageName());
                TextView description = findViewById(resID);
                description.setText(skill.desc);
                secondnumber = secondnumber+1;
            }
        }
    }

    public void afficheskill(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        for(int i = 1;i<=4;i++) {
            for (int r = 1;r<=6;r++){
                String curr_layout = "layout" + i + r;
                Log.d("nomlayout", curr_layout);
                int resID = getResources().getIdentifier(curr_layout, "id", getPackageName());
                LinearLayout layout = findViewById(resID);
                if((i*10)+r == nombre){
                    Log.d("nombrelayout", String.valueOf(nombre));
                    if(layout.getVisibility() == View.GONE){
                        layout.setVisibility(View.VISIBLE);
                    }
                    else if(layout.getVisibility() == View.VISIBLE){
                        layout.setVisibility(View.GONE);
                    }
                }
                else {
                    layout.setVisibility(View.GONE);
                }
            }
        }
    }
    public void launchfight(View view){
        Intent gameActivity = new Intent(create_team.this, MainActivity.class);
        gameActivity.putExtra("team1", (Serializable) team1);
        gameActivity.putExtra("team2", (Serializable) team2);
        gameActivity.putExtra("team3", (Serializable) team3);
        gameActivity.putExtra("team4", (Serializable) team4);
        gameActivity.putExtra("team5", (Serializable) team5);
        gameActivity.putExtra("team6", (Serializable) team6);
        gameActivity.putExtra("team7", (Serializable) team7);
        gameActivity.putExtra("team8", (Serializable) team8);
        gameActivity.putExtra("teamAlly", (Serializable) team1);
        gameActivity.putExtra("teamEnnemy", (Serializable) team2);
        gameActivity.putExtra("skills", (Serializable) listskills);
        gameActivity.putExtra("triptycs", (Serializable) listtriptycs);
        startActivity(gameActivity);
    }
    public void launchteam(View view){
        Intent gameActivity = new Intent(create_team.this, level_up.class);
        gameActivity.putExtra("team1", (Serializable) team1);
        gameActivity.putExtra("team2", (Serializable) team2);
        gameActivity.putExtra("team3", (Serializable) team3);
        gameActivity.putExtra("team4", (Serializable) team4);
        gameActivity.putExtra("team5", (Serializable) team5);
        gameActivity.putExtra("team6", (Serializable) team6);
        gameActivity.putExtra("team7", (Serializable) team7);
        gameActivity.putExtra("team8", (Serializable) team8);
        gameActivity.putExtra("teamAlly", (Serializable) team1);
        gameActivity.putExtra("teamEnnemy", (Serializable) team2);
        gameActivity.putExtra("skills", (Serializable) listskills);
        gameActivity.putExtra("triptycs", (Serializable) listtriptycs);
        startActivity(gameActivity);
    }
}
