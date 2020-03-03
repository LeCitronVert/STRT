package fr.youceflcv.strt;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class level_up extends AppCompatActivity {
    private List<Skill> listskills;
    private List<Triptyque> listtriptycs;
    private List<Personnage> team1;
    private List<Personnage> team2;
    private List<Personnage> team3;
    private List<Personnage> team4;
    private List<Personnage> team5;
    private View view;
    private Skill[] Skill;
    private int Curr_skill;
    private String[] SkillNames;
    private String[] SkillTypes;
    private String[] SkillDescs;
    private String[] SkillCosts;
    private int[] LevelupPoints;
    private int cible;
    private int roundnumber;
    private String tryptiqueup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        Intent intent = getIntent();
        if (intent != null){
            roundnumber = intent.getIntExtra("roundnumber",0);
            listskills = (List<Skill>) getIntent().getSerializableExtra("skills");
            listtriptycs = (List<Triptyque>) getIntent().getSerializableExtra("triptycs");
            team1 = (List<Personnage>) getIntent().getSerializableExtra("team1");
            team2 = (List<Personnage>) getIntent().getSerializableExtra("team2");
            team3 = (List<Personnage>) getIntent().getSerializableExtra("team3");
            team4 = (List<Personnage>) getIntent().getSerializableExtra("team4");
            team5 = (List<Personnage>) getIntent().getSerializableExtra("team5");
        }
        roundnumber++;
        SkillNames = new String[343];
        SkillTypes = new String[343];
        SkillDescs = new String[343];
        SkillCosts = new String[343];
        Skill = new Skill[343];
        LevelupPoints = new int[4];
        for(int i=0;i<=3;i++){
            LevelupPoints[i] = 2;
        }
        SetName();
        ResetBackground(view);
        ResetSkill(view);
        ResetLevelupButton(view);
    }
    public void SetName(){
        TextView button0 = findViewById(R.id.button_nom0);
        TextView button1 = findViewById(R.id.button_nom1);
        TextView button2 = findViewById(R.id.button_nom2);
        TextView button3 = findViewById(R.id.button_nom3);
        button0.setText(team1.get(0).name);
        button1.setText(team1.get(1).name);
        button2.setText(team1.get(2).name);
        button3.setText(team1.get(3).name);
    }
    public void SetBackground(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        cible = nombre;
        ResetBackground(view);
        SetSkills(view, nombre);
        SetStats(view);
        SetActiveSkill(view);
        String curr_button = "button_nom"+nombre;
        int resID = getResources().getIdentifier(curr_button, "id", getPackageName());
        TextView current_button = findViewById(resID);
        current_button.setBackgroundResource(R.drawable.shape);
    }
    public void ResetBackground(View view){
        TextView button0 = findViewById(R.id.button_nom0);
        TextView button1 = findViewById(R.id.button_nom1);
        TextView button2 = findViewById(R.id.button_nom2);
        TextView button3 = findViewById(R.id.button_nom3);
        button0.setBackgroundResource(R.drawable.shapeblack);
        button1.setBackgroundResource(R.drawable.shapeblack);
        button2.setBackgroundResource(R.drawable.shapeblack);
        button3.setBackgroundResource(R.drawable.shapeblack);
    }
    public void SetSkills(View view, int lequel){
        ResetSkill(view);
        ResetLevelupButton(view);
        Personnage curr_character = team1.get(lequel);
        TextView classe_name = findViewById(R.id.classe_name);
        classe_name.setText("Classe: "+curr_character.classes.name);
        TextView power_name = findViewById(R.id.power_name);
        power_name.setText("Power: "+curr_character.power.name);
        TextView weapon_name = findViewById(R.id.weapon_name);
        weapon_name.setText("Weapon: "+curr_character.weapon.name);
        TextView NbrPoint = findViewById(R.id.ViewNbrPoints);
        NbrPoint.setText("Points de compétences restants: "+LevelupPoints[cible]);
        //CLASSES PART
        if (LevelupPoints[cible] > 0) {
            if(curr_character.classes_level <4){
                String curr_button = "LevelUpClasse"+curr_character.classes_level;
                int resID2 = getResources().getIdentifier(curr_button, "id", getPackageName());
                ImageView current_levelupbutton = findViewById(resID2);
                current_levelupbutton.setVisibility(view.VISIBLE);
            }
        }
        int cmpt = 1;
        int tier_test = 1;
        for(Skill curr_skill : curr_character.classes.skills){
            if(tier_test == curr_skill.tier){
                cmpt++;
            }
            else{
                tier_test = curr_skill.tier;
                cmpt = 1;
            }
            SkillNames[100+curr_skill.tier*10+cmpt] = curr_skill.name;
            SkillTypes[100+curr_skill.tier*10+cmpt] = curr_skill.type;
            SkillDescs[100+curr_skill.tier*10+cmpt] = curr_skill.desc;
            SkillCosts[100+curr_skill.tier*10+cmpt] = String.valueOf(curr_skill.cost);
            Skill[100+curr_skill.tier*10+cmpt] = curr_skill;
            String curr_visuel = "classes"+curr_skill.tier+cmpt;
            int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
            ImageView current_button = findViewById(resID);
            current_button.setImageResource(curr_skill.img);
            current_button.setVisibility(View.VISIBLE);
            if(curr_skill.tier > curr_character.classes_level){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                current_button.setColorFilter(filter);
            }
            else{
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(1);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                current_button.setColorFilter(filter);
            }
        }
            for(int i = 1;i<=4;i++){
                if(i<=curr_character.classes_level){
                    String curr_visuel= "classe_palier_back"+i;
                    int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
                    ImageView current_button = findViewById(resID);
                    current_button.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }
        //POWER PART
        if (LevelupPoints[cible] > 0) {
            if(curr_character.power_level <4){
                String curr_button1 = "levelUpPower"+curr_character.power_level;
                int resID21 = getResources().getIdentifier(curr_button1, "id", getPackageName());
                ImageView current_levelupbutton1 = findViewById(resID21);
                current_levelupbutton1.setVisibility(view.VISIBLE);
            }
        }

        cmpt = 1;
        tier_test = 1;
        for(Skill curr_skill : curr_character.power.skills){
            Log.d("nom skill",curr_skill.name);
            if(tier_test == curr_skill.tier){
                cmpt++;
            }
            else{
                tier_test = curr_skill.tier;
                cmpt = 1;
            }
            SkillNames[200+curr_skill.tier*10+cmpt] = curr_skill.name;
            SkillTypes[200+curr_skill.tier*10+cmpt] = curr_skill.type;
            SkillDescs[200+curr_skill.tier*10+cmpt] = curr_skill.desc;
            SkillCosts[200+curr_skill.tier*10+cmpt] = String.valueOf(curr_skill.cost);
            Skill[200+curr_skill.tier*10+cmpt] = curr_skill;
            String curr_visuel = "power"+curr_skill.tier+cmpt;
            int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
            ImageView current_button = findViewById(resID);
            current_button.setImageResource(curr_skill.img);
            current_button.setVisibility(View.VISIBLE);
            if(curr_skill.tier > curr_character.power_level){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                current_button.setColorFilter(filter);
            }
            else{
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(1);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                current_button.setColorFilter(filter);
            }
        }
            for(int i = 1;i<=4;i++){
                if(i<=curr_character.power_level){
                    String curr_visuel= "power_palier_back"+i;
                    int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
                    ImageView current_button = findViewById(resID);
                    current_button.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }

        //WEAPON PART
        if (LevelupPoints[cible] > 0) {
            if(curr_character.weapon_level <4){
                String curr_button2 = "levelUpWeapon"+curr_character.weapon_level;
                int resID22 = getResources().getIdentifier(curr_button2, "id", getPackageName());
                ImageView current_levelupbutton2 = findViewById(resID22);
                current_levelupbutton2.setVisibility(view.VISIBLE);
            }
        }
        cmpt = 1;
        tier_test = 1;
        for(Skill curr_skill : curr_character.weapon.skills){
            Log.d("nom skill",curr_skill.name);
            if(tier_test == curr_skill.tier){
                cmpt++;
            }
            else{
                tier_test = curr_skill.tier;
                cmpt = 1;
            }
            SkillNames[300+curr_skill.tier*10+cmpt] = curr_skill.name;
            SkillTypes[300+curr_skill.tier*10+cmpt] = curr_skill.type;
            SkillDescs[300+curr_skill.tier*10+cmpt] = curr_skill.desc;
            SkillCosts[300+curr_skill.tier*10+cmpt] = String.valueOf(curr_skill.cost);
            Skill[300+curr_skill.tier*10+cmpt] = curr_skill;
            String curr_visuel = "weapon"+curr_skill.tier+cmpt;
            int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
            ImageView current_button = findViewById(resID);
            current_button.setImageResource(curr_skill.img);
            current_button.setVisibility(View.VISIBLE);
            if(curr_skill.tier > curr_character.weapon_level){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                current_button.setColorFilter(filter);
            }
            else{
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(1);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                current_button.setColorFilter(filter);
            }
        }
            for(int i = 1;i<=4;i++){
                if(i<=curr_character.weapon_level){
                    String curr_visuel= "weapon_palier_back"+i;
                    int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
                    ImageView current_button = findViewById(resID);
                    current_button.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            }
    }
    public void ResetSkill(View view){
        ImageView backgroundskill1 = findViewById(R.id.classe_palier_back1);
        ImageView backgroundskill2 = findViewById(R.id.classe_palier_back2);
        ImageView backgroundskill3 = findViewById(R.id.classe_palier_back3);
        ImageView backgroundskill4 = findViewById(R.id.classe_palier_back4);
        backgroundskill1.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill2.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill3.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill4.setBackgroundColor(Color.parseColor("#AAAAAA"));
        ImageView backgroundskill5 = findViewById(R.id.power_palier_back1);
        ImageView backgroundskill6 = findViewById(R.id.power_palier_back2);
        ImageView backgroundskill7 = findViewById(R.id.power_palier_back3);
        ImageView backgroundskill8 = findViewById(R.id.power_palier_back4);
        backgroundskill5.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill6.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill7.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill8.setBackgroundColor(Color.parseColor("#AAAAAA"));
        ImageView backgroundskill9 = findViewById(R.id.weapon_palier_back1);
        ImageView backgroundskill10 = findViewById(R.id.weapon_palier_back2);
        ImageView backgroundskill11 = findViewById(R.id.weapon_palier_back3);
        ImageView backgroundskill12 = findViewById(R.id.weapon_palier_back4);
        backgroundskill9.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill10.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill11.setBackgroundColor(Color.parseColor("#AAAAAA"));
        backgroundskill12.setBackgroundColor(Color.parseColor("#AAAAAA"));
        for(int i = 1;i<=4;i++){
            for(int r = 1;r<=3;r++){
                String curr_visuel = "classes"+i+r;
                int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
                ImageView current_button = findViewById(resID);
                current_button.setVisibility(View.GONE);
            }
        }
        for(int i = 1;i<=4;i++){
            for(int r = 1;r<=3;r++){
                String curr_visuel = "power"+i+r;
                int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
                ImageView current_button = findViewById(resID);
                current_button.setVisibility(View.GONE);
            }
        }
        for(int i = 1;i<=4;i++){
            for(int r = 1;r<=3;r++){
                String curr_visuel = "weapon"+i+r;
                int resID = getResources().getIdentifier(curr_visuel, "id", getPackageName());
                ImageView current_button = findViewById(resID);
                current_button.setVisibility(View.GONE);
            }
        }
    }
    public void SetActiveSkill(View view){
        ImageView icon1 = findViewById(R.id.ActiveButton1);
        ImageView icon2 = findViewById(R.id.ActiveButton2);
        ImageView icon3 = findViewById(R.id.ActiveButton3);
        ImageView icon4 = findViewById(R.id.ActiveButton4);
        icon1.setVisibility(view.VISIBLE);
        icon2.setVisibility(view.VISIBLE);
        icon3.setVisibility(view.VISIBLE);
        icon4.setVisibility(view.VISIBLE);
        if (team1.get(cible).actifskill[0] != null) {
            Skill skill1 = (Skill) team1.get(cible).actifskill[0];
            icon1.setImageResource(skill1.img);
            SkillNames[1] = skill1.name;
            SkillTypes[1] = skill1.type;
            SkillDescs[1] = skill1.desc;
            SkillCosts[1] = String.valueOf(skill1.cost);
            Skill[1] = skill1;
        }
        else{
            icon1.setImageResource(R.drawable.plus);
        }

        if (team1.get(cible).actifskill[1] != null) {
            Skill skill2 = (Skill) team1.get(cible).actifskill[1];
            icon2.setImageResource(skill2.img);
            SkillNames[2] = skill2.name;
            SkillTypes[2] = skill2.type;
            SkillDescs[2] = skill2.desc;
            SkillCosts[2] = String.valueOf(skill2.cost);
            Skill[2] = skill2;
        }
        else{
            icon2.setImageResource(R.drawable.plus);
        }

        if (team1.get(cible).actifskill[2] != null) {
            Skill skill3 = (Skill) team1.get(cible).actifskill[2];
            icon3.setImageResource(skill3.img);
            SkillNames[3] = skill3.name;
            SkillTypes[3] = skill3.type;
            SkillDescs[3] = skill3.desc;
            SkillCosts[3] = String.valueOf(skill3.cost);
            Skill[3] = skill3;
        }
        else{
            icon3.setImageResource(R.drawable.plus);
        }

        if (team1.get(cible).actifskill[3] != null) {
            Skill skill4 = (Skill) team1.get(cible).actifskill[3];
            icon4.setImageResource(skill4.img);
            SkillNames[4] = skill4.name;
            SkillTypes[4] = skill4.type;
            SkillDescs[4] = skill4.desc;
            SkillCosts[4] = String.valueOf(skill4.cost);
            Skill[4] = skill4;
        }
        else{
            icon4.setImageResource(R.drawable.plus);
        }
    }
    public void PutActiveSkill(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        if(Skill[Curr_skill].actif == true){
            team1.get(cible).actifskill[nombre] = Skill[Curr_skill];
        }
        SetActiveSkill(view);
    }
    public void SetSkillBox(View view){
        int nombre = Integer.parseInt((String) view.getTag());
        Curr_skill = nombre;
        LinearLayout SkillBox = findViewById(R.id.skillbox);
        TextView skillname = findViewById(R.id.SkillName);
        TextView skilltype = findViewById(R.id.SkillType);
        TextView skilldesc = findViewById(R.id.SkillDesc);
        TextView skillcost = findViewById(R.id.SkillCout);
        SkillBox.setVisibility(view.VISIBLE);
        skillname.setText(SkillNames[nombre]);
        skilltype.setText(SkillTypes[nombre]);
        skilldesc.setText(SkillDescs[nombre]);
        skillcost.setText(SkillCosts[nombre]);
        TextView Uptext = findViewById(R.id.UpactifText);
        LinearLayout UpBar = findViewById(R.id.UpActifBar);
        if(Skill[Curr_skill].actif == true){
            if(Curr_skill>100 && Curr_skill<200){
                if(Skill[Curr_skill].tier <= team1.get(cible).classes_level){
                    Uptext.setVisibility(view.VISIBLE);
                    UpBar.setVisibility(view.VISIBLE);
                }
                else{
                    Uptext.setVisibility(view.GONE);
                    UpBar.setVisibility(view.GONE);
                }
            }
            if(Curr_skill>200 && Curr_skill<300){
                if(Skill[Curr_skill].tier <= team1.get(cible).power_level){
                    Uptext.setVisibility(view.VISIBLE);
                    UpBar.setVisibility(view.VISIBLE);
                }
                else{
                    Uptext.setVisibility(view.GONE);
                    UpBar.setVisibility(view.GONE);
                }
            }
            if(Curr_skill>300){
                if(Skill[Curr_skill].tier <= team1.get(cible).weapon_level){
                    Uptext.setVisibility(view.VISIBLE);
                    UpBar.setVisibility(view.VISIBLE);
                }
                else{
                    Uptext.setVisibility(view.GONE);
                    UpBar.setVisibility(view.GONE);
                }
            }
        }
        else{
            Uptext.setVisibility(view.GONE);
            UpBar.setVisibility(view.GONE);
        }
    }
    public void CloseSkillBox(View view){
        Curr_skill = 0;
        LinearLayout SkillBox = findViewById(R.id.skillbox);
        SkillBox.setVisibility(view.GONE);
    }
    public void ResetLevelupButton(View view){
        ImageView upclasse1 = findViewById(R.id.LevelUpClasse1);
        ImageView upclasse2 = findViewById(R.id.LevelUpClasse2);
        ImageView upclasse3 = findViewById(R.id.LevelUpClasse3);
        ImageView uppower1 = findViewById(R.id.levelUpPower1);
        ImageView uppower2 = findViewById(R.id.levelUpPower2);
        ImageView uppower3 = findViewById(R.id.levelUpPower3);
        ImageView upweapon1 = findViewById(R.id.levelUpWeapon1);
        ImageView upweapon2 = findViewById(R.id.levelUpWeapon2);
        ImageView upweapon3 = findViewById(R.id.levelUpWeapon3);
        upclasse1.setVisibility(view.GONE);
        upclasse2.setVisibility(view.GONE);
        upclasse3.setVisibility(view.GONE);
        uppower1.setVisibility(view.GONE);
        uppower2.setVisibility(view.GONE);
        uppower3.setVisibility(view.GONE);
        upweapon1.setVisibility(view.GONE);
        upweapon2.setVisibility(view.GONE);
        upweapon3.setVisibility(view.GONE);
    }
    public void SetlevelUp(View view){
        tryptiqueup = (String) view.getTag();
        Log.d("queltriptyque",tryptiqueup);
        TextView descupbox = findViewById(R.id.DescUpBox);
        if(tryptiqueup.equals("classes")){
            int next_palier = team1.get(cible).classes_level+1;
            descupbox.setText("Voulez vous vraiment mettre "+team1.get(cible).classes.name+" de "+team1.get(cible).name+ " au niveau " +next_palier+" ?");
        }
        if(tryptiqueup.equals("power")){
            int next_palier = team1.get(cible).power_level+1;
            descupbox.setText("Voulez vous vraiment mettre "+team1.get(cible).power.name+" de "+team1.get(cible).name+ " au niveau " +next_palier+" ?");
        }
        if(tryptiqueup.equals("weapon")){
            int next_palier = team1.get(cible).weapon_level+1;
            descupbox.setText("Voulez vous vraiment mettre "+team1.get(cible).weapon.name+" de "+team1.get(cible).name+ " au niveau " +next_palier+" ?");
        }
        LinearLayout ConfirmUpBox = findViewById(R.id.confirmupbox);
        ConfirmUpBox.setVisibility(view.VISIBLE);
    }
    public void ResetLevelUp(View view){
        tryptiqueup = "";
        LinearLayout ConfirmUpBox = findViewById(R.id.confirmupbox);
        ConfirmUpBox.setVisibility(view.GONE);
    }
    public void LevelUp(View view){
        LevelupPoints[cible] = LevelupPoints[cible]-1;
        int Oldlife = team1.get(cible).maxhealth;
        int Oldaccuracy = team1.get(cible).precision;
        int Oldattack = team1.get(cible).attack;
        int Olddefense = team1.get(cible).defense;
        int Oldcritical = team1.get(cible).critical;
        int Oldagility = team1.get(cible).esquive;
        int Oldspeed = team1.get(cible).speed;
        if(tryptiqueup.equals("classes")){
            team1.get(cible).classes_level++;
            for(Skill curr_skill : team1.get(cible).classes.skills){
                if(curr_skill.tier == team1.get(cible).classes_level){
                    if(curr_skill.actif == true){
                       team1.get(cible).skills.add(curr_skill);
                    }
                    else{
                        team1.get(cible).buff(curr_skill);
                    }
                }
            }
            SetSkills(view,cible);
        }
        if(tryptiqueup.equals("power")){
            team1.get(cible).power_level++;
            for(Skill curr_skill : team1.get(cible).power.skills){
                if(curr_skill.tier == team1.get(cible).power_level){
                    if(curr_skill.actif == true){
                        team1.get(cible).skills.add(curr_skill);
                    }
                    else{
                        team1.get(cible).buff(curr_skill);
                    }
                }
            }
            SetSkills(view,cible);
        }
        if(tryptiqueup.equals("weapon")){
            team1.get(cible).weapon_level++;
            for(Skill curr_skill : team1.get(cible).weapon.skills){
                if(curr_skill.tier == team1.get(cible).weapon_level){
                    if(curr_skill.actif == true){
                        team1.get(cible).skills.add(curr_skill);
                    }
                    else{
                        team1.get(cible).buff(curr_skill);
                    }
                }
            }
            SetSkills(view,cible);
        }
        TextView life = findViewById(R.id.lifeUnique);
        TextView accuracy = findViewById(R.id.accuracyUnique);
        TextView attack = findViewById(R.id.attackUnique);
        TextView defense = findViewById(R.id.defenseUnique);
        TextView critical = findViewById(R.id.criticalUnique);
        TextView agility = findViewById(R.id.agilityUnique);
        TextView speed = findViewById(R.id.speedUnique);
        if(Oldlife != team1.get(cible).maxhealth){
            SetAnimationStats(view, life, Oldlife, team1.get(cible).maxhealth);
        }
        if(Oldaccuracy != team1.get(cible).precision){
            SetAnimationStats(view, accuracy, Oldaccuracy, team1.get(cible).precision);
        }
        if(Oldattack != team1.get(cible).attack){
            SetAnimationStats(view, attack, Oldattack, team1.get(cible).attack);
        }
        if(Olddefense != team1.get(cible).defense){
            SetAnimationStats(view, defense, Olddefense, team1.get(cible).defense);
        }
        if(Oldcritical != team1.get(cible).critical){
            SetAnimationStats(view, critical, Oldcritical, team1.get(cible).critical);
        }
        if(Oldagility != team1.get(cible).esquive){
            SetAnimationStats(view, agility, Oldagility, team1.get(cible).esquive);
        }
        if(Oldspeed != team1.get(cible).speed){
            SetAnimationStats(view, speed, Oldspeed, team1.get(cible).speed);
        }
        ResetLevelUp(view);
    }
    public void SetStats(View view){
        TextView life = findViewById(R.id.lifeUnique);
        TextView accuracy = findViewById(R.id.accuracyUnique);
        TextView attack = findViewById(R.id.attackUnique);
        TextView defense = findViewById(R.id.defenseUnique);
        TextView critical = findViewById(R.id.criticalUnique);
        TextView agility = findViewById(R.id.agilityUnique);
        TextView speed = findViewById(R.id.speedUnique);
        life.setText(team1.get(cible).maxhealth+"");
        accuracy.setText(team1.get(cible).precision+"");
        attack.setText(team1.get(cible).attack+"");
        defense.setText(team1.get(cible).defense+"");
        critical.setText(team1.get(cible).critical+"");
        agility.setText(team1.get(cible).esquive+"");
        speed.setText(team1.get(cible).speed+"");
    }
    public void SetAnimationStats (View view, final TextView cible, int ValeurBase, int ValeurFinal){
        ValueAnimator animator = ValueAnimator.ofInt(ValeurBase, ValeurFinal);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
               cible.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
    public void launchfight(View view){
        Intent gameActivity = new Intent(level_up.this, MainActivity.class);
        gameActivity.putExtra("team1", (Serializable) team1);
        gameActivity.putExtra("team2", (Serializable) team2);
        gameActivity.putExtra("team3", (Serializable) team3);
        gameActivity.putExtra("team4", (Serializable) team4);
        gameActivity.putExtra("team5", (Serializable) team5);
        gameActivity.putExtra("roundnumber",roundnumber);
        gameActivity.putExtra("teamAlly", (Serializable) team1);
        if(roundnumber == 2){
            gameActivity.putExtra("teamEnnemy", (Serializable) team3);
        }
        if(roundnumber == 3){
            gameActivity.putExtra("teamEnnemy", (Serializable) team4);
        }
        if(roundnumber == 4){
            gameActivity.putExtra("teamEnnemy", (Serializable) team5);
        }
        gameActivity.putExtra("skills", (Serializable) listskills);
        gameActivity.putExtra("triptycs", (Serializable) listtriptycs);
        startActivity(gameActivity);
    }
    public void IaLevelUp(){

    }
}
