package fr.youceflcv.strt;

import android.widget.ProgressBar;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Personnage {
    int health;
    int actions;
    int attack;
    Skill skill1;
    Skill skill2;
    int maxhealth;


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


}