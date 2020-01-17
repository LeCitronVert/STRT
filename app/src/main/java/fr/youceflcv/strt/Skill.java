package fr.youceflcv.strt;

import android.media.Image;

import java.lang.reflect.Method;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Skill {
    String name;
    String desc;
    // Image img;
    String type;
    int value;
    String effect;
    String stat;


    public Skill(String nom, String description, String typeskill, int valeur, String stats, String effetadditionnel){
        name = nom;
        desc = description;
        type = typeskill;
        value = valeur;
        effect = effetadditionnel;
        stat = stats;
    }

    public void useSkill(Personnage attaquant, Personnage cible){
        if(this.type == "attack"){
            cible.takeDamage(this.value);
        } else if(this.type == "heal"){
            cible.heal(this.value);
        } else if(this.type == "buff"){
            cible.buff(this.value, this.stat);
        }
    }
}
