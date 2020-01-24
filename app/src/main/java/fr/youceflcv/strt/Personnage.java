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
    int health;
    int actions;
    int attack;
    int maxhealth;
    List<Skill> skills = new ArrayList<Skill>();


    ProgressBar healthbar;

    public Personnage(int sante, int actionsdispo, int atk, ProgressBar barredevie){
        health = sante;
        maxhealth = sante;
        actions = actionsdispo;
        attack = atk;
        healthbar = barredevie;
        barredevie.setMax(health);
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
        int count = this.skills.size();

        if (count > 0) {
            Skill skill1 = (Skill) this.skills.get(0);
            icon1.setImageResource(skill1.img);
            icon1.setVisibility(View.VISIBLE);
        }

        if (count >1) {
            Skill skill2 = (Skill) this.skills.get(1);
            icon2.setImageResource(skill2.img);
            icon2.setVisibility(View.VISIBLE);
        }

        if (count >2) {
            Skill skill3 = (Skill) this.skills.get(2);
            icon3.setImageResource(skill3.img);
            icon3.setVisibility(View.VISIBLE);
        }

        if (count >3) {
            Skill skill4 = (Skill) this.skills.get(3);
            icon4.setImageResource(skill4.img);
            icon4.setVisibility(View.VISIBLE);
        }
    }
}