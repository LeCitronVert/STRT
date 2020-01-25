package fr.youceflcv.strt;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.sax.Element;
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
    Triptyque weapon;
    Triptyque power;
    Triptyque classes;
    List<Skill> skills = new ArrayList<Skill>();
    Skill[] actifskill = new Skill[4];


    ProgressBar healthbar;

    public Personnage(String nom,Triptyque arme,Triptyque pouvoir, Triptyque classe, ProgressBar barredevie){
        name = nom;
        health = arme.health + pouvoir.health + classe.health;
        maxhealth = arme.health + pouvoir.health + classe.health;
        maxaction = arme.speed + pouvoir.speed + classe.speed;
        actions = arme.speed + pouvoir.speed + classe.speed;
        attack = arme.attack + pouvoir.attack + classe.attack;
        defense = arme.defense + pouvoir.defense + classe.defense;
        esquive = arme.esquive + pouvoir.esquive + classe.esquive;
        precision = arme.precision + pouvoir.precision + classe.precision;
        speed = arme.speed + pouvoir.speed + classe.speed;
        critical = arme.critical + pouvoir.critical + pouvoir.critical;
        weapon = arme;
        power = pouvoir;
        classes = classe;
        healthbar = barredevie;
        barredevie.setMax(health);
        for(Skill curr_skill: this.weapon.skills){
            if(curr_skill.tier == 1){
                this.skills.add(curr_skill);
                if(curr_skill.actif == true){
                    this.actifskill[0] = curr_skill;
                }
            }
        }
        for(Skill curr_skill: this.power.skills){
            if(curr_skill.tier == 1){
                this.skills.add(curr_skill);
                if(curr_skill.actif == true){
                    this.actifskill[1] = curr_skill;
                }
            }
        }
        for(Skill curr_skill: this.classes.skills){
            if(curr_skill.tier == 1){
                this.skills.add(curr_skill);
                if(curr_skill.actif == true){
                    this.actifskill[2] = curr_skill;
                }
            }
        }
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

    public void buff(int value, String stat){
        if(stat == "actions"){
            this.actions = this.actions + value;
        } else if(stat == "attack"){
            this.attack = this.attack + value;
            this.capatk();
        } else if(stat == "maxhealth"){
            this.maxhealth = this.maxhealth + value;
            this.overheal();
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
}