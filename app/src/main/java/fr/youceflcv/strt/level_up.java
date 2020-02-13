package fr.youceflcv.strt;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class level_up extends AppCompatActivity {
    private List<Skill> listskills;
    private List<Triptyque> listtriptycs;
    private List<Personnage> team1;
    private List<Personnage> team2;
    private View view;
    private String[] SkillNames;
    private String[] SkillTypes;
    private String[] SkillDescs;
    private String[] SkillCosts;
    private int cible;
    private String tryptiqueup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        Intent intent = getIntent();
        if (intent != null){
            listskills = (List<Skill>) getIntent().getSerializableExtra("skills");
            listtriptycs = (List<Triptyque>) getIntent().getSerializableExtra("triptycs");
            team1 = (List<Personnage>) getIntent().getSerializableExtra("team1");
            team2 = (List<Personnage>) getIntent().getSerializableExtra("team2");
        }
        SkillNames = new String[343];
        SkillTypes = new String[343];
        SkillDescs = new String[343];
        SkillCosts = new String[343];
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
        //CLASSES PART
        String curr_button = "LevelUpClasse"+curr_character.classes_level;
        int resID2 = getResources().getIdentifier(curr_button, "id", getPackageName());
        ImageView current_levelupbutton = findViewById(resID2);
        current_levelupbutton.setVisibility(view.VISIBLE);
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
    public void SetSkillBox(View view){
        int nombre = Integer.parseInt((String) view.getTag());
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
    }
    public void CloseSkillBox(View view){
        LinearLayout SkillBox = findViewById(R.id.skillbox);
        SkillBox.setVisibility(view.GONE);
    }
    public void ResetLevelupButton(View view){
        ImageView upclasse1 = findViewById(R.id.LevelUpClasse1);
        ImageView upclasse2 = findViewById(R.id.LevelUpClasse2);
        ImageView upclasse3 = findViewById(R.id.LevelUpClasse3);
        upclasse1.setVisibility(view.GONE);
        upclasse2.setVisibility(view.GONE);
        upclasse3.setVisibility(view.GONE);
    }
    public void SetlevelUp(View view){
        tryptiqueup = (String) view.getTag();
        if(tryptiqueup == "classes"){
            
        }
    }
    public void LevelUp(View view){

    }
}
