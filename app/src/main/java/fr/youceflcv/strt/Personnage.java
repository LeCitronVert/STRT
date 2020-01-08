package fr.youceflcv.strt;

import android.widget.ProgressBar;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Personnage {
    int health;
    int actions;
    int attack;



    ProgressBar healthbar;

    public Personnage(int sante, int actionsdispo, int atk, ProgressBar barredevie){
        health = sante;
        actions = actionsdispo;
        attack = atk;
        healthbar = barredevie;
        barredevie.setMax(health);
    }

    public void updateHealthbar(){
        healthbar.setProgress(health);
    }


    public void basicattack(Personnage defenseur){
        defenseur.health = defenseur.health - attack;
        defenseur.updateHealthbar();
    }



}