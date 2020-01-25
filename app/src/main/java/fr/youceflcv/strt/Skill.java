package fr.youceflcv.strt;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Method;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Skill {
    String name;
    String desc;
    int img;
    String imgname;
    boolean actif;
    String type;
    int value;
    String effect;
    String stat;
    String cibletype;
    int tier;
    int level;
    int dure;
    int cost;


    public Skill(String nom, String description,int image, String imagename, boolean activable, String typeskill, int valeur, String stats, String effetadditionnel, String cible, int cout, int palier, int niveau, int duree){
        name = nom;
        desc = description;
        img = image;
        imgname = imagename;
        type = typeskill;
        value = valeur;
        effect = effetadditionnel;
        stat = stats;
        cibletype = cible;
        cost = cout;
        tier = palier;
        level = niveau;
        dure = duree;
        actif = activable;
    }

    public void useSkill(Personnage attaquant, Personnage cible){
        attaquant.actions = attaquant.actions - this.cost;
        if(this.type == "attack"){
            cible.takeDamage(this.value) ;
        } else if(this.type == "heal"){
            cible.heal(this.value);
        } else if(this.type == "buff"){
            cible.buff(this.value, this.stat);
        }
    }
}
