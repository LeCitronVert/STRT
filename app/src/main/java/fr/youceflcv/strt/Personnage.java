package fr.youceflcv.strt;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.sax.Element;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Personnage{
    String name;
    int health;
    int maxaction;
    int actions;
    int attack;
    int defense;
    int esquive;
    int precision;
    int speed;
    int critical;
    int maxhealth;
    boolean ennemy;
    int position;
    Triptyque weapon;
    Triptyque power;
    Triptyque classes;
    List<Skill> skills = new ArrayList<Skill>();
    Skill[] actifskill = new Skill[4];


    ProgressBar healthbar;

    public Personnage(String nom,Triptyque arme,Triptyque pouvoir, Triptyque classe, ProgressBar barredevie,boolean ennemi,int ou){
        name = nom;
        maxhealth = arme.health + pouvoir.health + classe.health;
        health = maxhealth;
        speed = arme.speed + pouvoir.speed + classe.speed;
        maxaction = (Integer)this.speed/10;
        actions = maxaction;
        attack = arme.attack + pouvoir.attack + classe.attack;
        defense = arme.defense + pouvoir.defense + classe.defense;
        esquive = arme.esquive + pouvoir.esquive + classe.esquive;
        precision = arme.precision + pouvoir.precision + classe.precision;
        critical = arme.critical + pouvoir.critical + pouvoir.critical;
        ennemy = ennemi;
        position = ou;
        weapon = arme;
        power = pouvoir;
        classes = classe;
        healthbar = barredevie;
        barredevie.setMax(maxhealth);
        barredevie.setProgress(health);
        for(Skill curr_skill: this.weapon.skills){
            if(curr_skill.tier == 1){
                this.skills.add(curr_skill);
                if(curr_skill.actif == true){
                    if(this.actifskill[0] == null){
                        this.actifskill[0] = curr_skill;
                    } else if(this.actifskill[1] == null){
                        this.actifskill[1] = curr_skill;
                    } else if(this.actifskill[2] == null){
                        this.actifskill[2] = curr_skill;
                    } else if(this.actifskill[3] == null){
                        this.actifskill[3] = curr_skill;
                    }
                }
                else{
                    this.buff(curr_skill);
                }
            }
        }
        for(Skill curr_skill: this.power.skills){
            if(curr_skill.tier == 1){
                this.skills.add(curr_skill);
                if(curr_skill.actif == true){
                    if(this.actifskill[0] == null){
                        this.actifskill[0] = curr_skill;
                    } else if(this.actifskill[1] == null){
                        this.actifskill[1] = curr_skill;
                    } else if(this.actifskill[2] == null){
                        this.actifskill[2] = curr_skill;
                    } else if(this.actifskill[3] == null){
                        this.actifskill[3] = curr_skill;
                    }
                }
                else{
                    this.buff(curr_skill);
                }
            }
        }
        for(Skill curr_skill: this.classes.skills){
            if(curr_skill.tier == 1){
                this.skills.add(curr_skill);
                if(curr_skill.actif == true){
                    if(this.actifskill[0] == null){
                        this.actifskill[0] = curr_skill;
                    } else if(this.actifskill[1] == null){
                        this.actifskill[1] = curr_skill;
                    } else if(this.actifskill[2] == null){
                        this.actifskill[2] = curr_skill;
                    } else if(this.actifskill[3] == null){
                        this.actifskill[3] = curr_skill;
                    }
                }
                else{
                    this.buff(curr_skill);
                }
            }
        }
    }

    public Personnage(Personnage copy){
        this.health = copy.maxhealth;
        this.maxhealth = copy.maxhealth;
        this.maxaction = copy.maxaction;
        this.actions = copy.maxaction;
        this.attack = copy.attack;
    }

    public void updateHealthbar(){
        healthbar.setProgress(health);
    }


    public void basicattack(Personnage defenseur){
        defenseur.takeDamage(attack);
    }

    public void takeDamage(int value){
        this.health = this.health - value;
        this.updateHealthbar();
    }

    public void overheal(){
        if(this.health > this.maxhealth){
            this.health = this.maxhealth;
        }
        this.updateHealthbar();
    }

    public void capatk(){
        if(this.attack < 0){
            this.attack = 0;
        }
        this.updateHealthbar();
    }

    public void heal(int value){
        this.health = this.health + value;
        this.overheal();
    }

    public void buff(Skill parent_skill){
        if(parent_skill.dure != 999 && parent_skill.temporary == false){
            Skill tempo_skill = new Skill (parent_skill.name+"buff","","",1,"",false,true,parent_skill.type,parent_skill.value,parent_skill.stat,parent_skill.effect,parent_skill.cibletype,0,0,0,parent_skill.dure);
            this.skills.add(tempo_skill);
            if(tempo_skill.stat.equals("actions")){
                this.actions = this.actions + tempo_skill.value;
            } else if(tempo_skill.stat.equals("attack")){
                this.attack = this.attack + tempo_skill.value;
                this.capatk();
            } else if(tempo_skill.stat.equals("defense")){
                Log.d("test defense", String.valueOf(this.defense));
                this.defense = this.defense + tempo_skill.value;
                Log.d("test defense after", String.valueOf(this.defense));
            } else if(tempo_skill.stat.equals("esquive")){
                this.esquive = this.esquive + tempo_skill.value;
            } else if(tempo_skill.stat.equals("precision")){
                this.precision = this.precision + tempo_skill.value;
            } else if(tempo_skill.stat.equals("speed")){
                this.speed = this.speed + tempo_skill.value;
            } else if(tempo_skill.stat.equals("critical")){
                this.critical = this.critical + tempo_skill.value;
            } else if(tempo_skill.stat.equals("maxhealth")){
                this.maxhealth = this.maxhealth + tempo_skill.value;
                this.overheal();
            }
        }
        else{
            if(parent_skill.stat.equals("actions")){
                this.actions = this.actions + parent_skill.value;
            } else if(parent_skill.stat.equals("attack")){
                this.attack = this.attack + parent_skill.value;
                this.capatk();
            } else if(parent_skill.stat.equals("defense")){
                this.defense = this.defense + parent_skill.value;
            } else if(parent_skill.stat.equals("esquive")){
                this.esquive = this.esquive + parent_skill.value;
            } else if(parent_skill.stat.equals("precision")){
                this.precision = this.precision + parent_skill.value;
            } else if(parent_skill.stat.equals("speed")){
                this.speed = this.speed + parent_skill.value;
            } else if(parent_skill.stat.equals("critical")){
                this.critical = this.critical + parent_skill.value;
            } else if(parent_skill.stat.equals("maxhealth")){
                this.maxhealth = this.maxhealth + parent_skill.value;
                this.overheal();
            }
        }
    }

    public void displayskill(ImageView icon1, ImageView icon2, ImageView icon3, ImageView icon4){
        icon1.setVisibility(View.GONE);
        icon2.setVisibility(View.GONE);
        icon3.setVisibility(View.GONE);
        icon4.setVisibility(View.GONE);
        if (this.actifskill[0] != null) {
            Skill skill1 = (Skill) this.actifskill[0];
            icon1.setImageResource(skill1.img);
            icon1.setVisibility(View.VISIBLE);
        }

        if (this.actifskill[1] != null) {
            Skill skill2 = (Skill) this.actifskill[1];
            icon2.setImageResource(skill2.img);
            icon2.setVisibility(View.VISIBLE);
        }

        if (this.actifskill[2] != null) {
            Skill skill3 = (Skill) this.actifskill[2];
            icon3.setImageResource(skill3.img);
            icon3.setVisibility(View.VISIBLE);
        }

        if (this.actifskill[3] != null) {
            Skill skill4 = (Skill) this.actifskill[3];
            icon4.setImageResource(skill4.img);
            icon4.setVisibility(View.VISIBLE);
        }
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void understandSkills(){ // Cette fonction permet à l'IA de comprendre quel archétype de perso elle joue

    }

    public void AIattack(){ // Cette fonction permet à l'IA d'attaquer selon le meilleur choix estimé
        for(int i = 1; i> this.skills.size();i++){

        }
    }

    public void estimateEffect(Personnage cible, Skill test){ // Cette fonction permet d'estimer l'effect qu'aura un skill sur un personnage donné
        int effect;
        Personnage AIcopy = new Personnage(cible);
    }


}