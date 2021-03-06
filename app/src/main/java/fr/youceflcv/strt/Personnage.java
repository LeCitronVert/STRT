package fr.youceflcv.strt;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.sax.Element;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Personnage implements Parcelable, Serializable {
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
    ProgressBar healthbar;
    boolean ennemy;
    int position;
    Triptyque weapon;
    int weapon_level;
    Triptyque power;
    int power_level;
    Triptyque classes;
    int classes_level;
    List<Skill> skills = new ArrayList<Skill>();
    Skill[] actifskill = new Skill[4];



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
        weapon_level = 1;
        power = pouvoir;
        power_level = 1;
        classes = classe;
        classes_level = 1;
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

    public Personnage(Personnage cible){ // Ce constructeur permet de créer une copie d'un personnage, ce qui sert à l'IA pour créer une "fausse" copie d'un personnage pour calculer les dégâts infligés au vrai
        name = cible.name;
        maxhealth = cible.maxhealth;
        health = cible.maxhealth;
        speed = cible.speed;
        maxaction = cible.maxaction;
        actions = cible.maxaction;
        attack = cible.attack;
        defense = cible.defense;
        esquive = cible.esquive;
        precision = cible.precision;
        critical = cible.critical;
        ennemy = cible.ennemy;
        position = cible.position;
        weapon = cible.weapon;
        power = cible.power;
        classes = cible.classes;
        skills = cible.skills;
    }

    protected Personnage(Parcel in) {
        name = in.readString();
        health = in.readInt();
        maxaction = in.readInt();
        actions = in.readInt();
        attack = in.readInt();
        defense = in.readInt();
        esquive = in.readInt();
        precision = in.readInt();
        speed = in.readInt();
        critical = in.readInt();
        maxhealth = in.readInt();
        ennemy = in.readByte() != 0;
        position = in.readInt();
        weapon = in.readParcelable(Triptyque.class.getClassLoader());
        weapon_level = in.readInt();
        power = in.readParcelable(Triptyque.class.getClassLoader());
        power_level = in.readInt();
        classes = in.readParcelable(Triptyque.class.getClassLoader());
        classes_level = in.readInt();
        skills = in.createTypedArrayList(Skill.CREATOR);
        actifskill = in.createTypedArray(Skill.CREATOR);
    }

    public static final Creator<Personnage> CREATOR = new Creator<Personnage>() {
        @Override
        public Personnage createFromParcel(Parcel in) {
            return new Personnage(in);
        }

        @Override
        public Personnage[] newArray(int size) {
            return new Personnage[size];
        }
    };


    public void updateHealthbar(){
        healthbar.setProgress(health);
    }
    public void setHealthbar(ProgressBar healthbar){
        this.healthbar = healthbar;
        this.health = this.maxhealth;
        healthbar.setMax(this.maxhealth);
        healthbar.setProgress(this.health);
    }


    public void basicattack(Personnage defenseur){
        defenseur.takeDamage(attack);
    }

    public void takeDamage(int value){
        Log.d("degat",value+"");
        Log.d("vie avant degats", String.valueOf(this.health));
        this.health = this.health - value;
        this.updateHealthbar();
        Log.d("vie après degats", String.valueOf(this.health));
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
                this.actions =(int) this.actions + tempo_skill.value;
            } else if(tempo_skill.stat.equals("attack")){
                this.attack =(int) this.attack + tempo_skill.value;
                this.capatk();
            } else if(tempo_skill.stat.equals("defense")){
                Log.d("test defense", String.valueOf(this.defense));
                this.defense =(int) this.defense + tempo_skill.value;
                Log.d("test defense after", String.valueOf(this.defense));
            } else if(tempo_skill.stat.equals("esquive")){
                this.esquive =(int) this.esquive + tempo_skill.value;
            } else if(tempo_skill.stat.equals("precision")){
                this.precision =(int) this.precision + tempo_skill.value;
            } else if(tempo_skill.stat.equals("speed")){
                this.speed =(int) this.speed + tempo_skill.value;
            } else if(tempo_skill.stat.equals("critical")){
                this.critical =(int) this.critical + tempo_skill.value;
            } else if(tempo_skill.stat.equals("maxhealth")){
                this.maxhealth =(int) this.maxhealth + tempo_skill.value;
            }
        }
        else{
            if(parent_skill.stat.equals("actions")){
                this.actions =(int) this.actions + parent_skill.value;
            } else if(parent_skill.stat.equals("attack")){
                this.attack =(int) this.attack + parent_skill.value;
                this.capatk();
            } else if(parent_skill.stat.equals("defense")){
                this.defense =(int) this.defense + parent_skill.value;
            } else if(parent_skill.stat.equals("esquive")){
                this.esquive =(int) this.esquive + parent_skill.value;
            } else if(parent_skill.stat.equals("precision")){
                this.precision =(int) this.precision + parent_skill.value;
            } else if(parent_skill.stat.equals("speed")){
                this.speed =(int) this.speed + parent_skill.value;
            } else if(parent_skill.stat.equals("critical")){
                this.critical =(int) this.critical + parent_skill.value;
            } else if(parent_skill.stat.equals("maxhealth")){
                this.maxhealth =(int) this.maxhealth + parent_skill.value;
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

    public void AIRandom(Personnage[] attaquants, Personnage[] defenseurs){
        int alea;
        alea = new Random().nextInt(this.actifskill.length-1);
        Skill skill = this.actifskill[alea];
        if(skill.type.equals("attack")){
            //Log.d("Attaques IA","L'IA a lancé un skill offensif.");
            alea = new Random().nextInt(attaquants.length);
            Personnage cible = attaquants[alea];

            int dps = (int) this.attack*skill.value;
            int critic=1;
            critic=(int)( Math.random()*100);
            if(critic <= this.critical){
                dps = dps*2;
            }
            cible.takeDamage(dps);
            //Log.d("Attaques IA","L'IA a attaqué un ennemi.");
            //Log.d("Attaques IA",cible.name);
        } else if(skill.type.equals("heal")){
            //Log.d("Attaques IA","L'IA a lancé un skill de support.");
            alea = new Random().nextInt(defenseurs.length);
            Personnage cible = defenseurs[alea];

            cible.heal(skill.value);
            //Log.d("Attaques IA","L'IA a soigné un allié.");
            //Log.d("Attaques IA",cible.name);
        } else if(skill.type.equals("buff")){
            //Log.d("Attaques IA","L'IA a lancé un buff.");
            this.buff(skill);
            //Log.d("Attaques IA","L'IA s'est buffé.");
            //Log.d("Attaques IA",this.name);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(health);
        parcel.writeInt(maxaction);
        parcel.writeInt(actions);
        parcel.writeInt(attack);
        parcel.writeInt(defense);
        parcel.writeInt(esquive);
        parcel.writeInt(precision);
        parcel.writeInt(speed);
        parcel.writeInt(critical);
        parcel.writeInt(maxhealth);
        parcel.writeByte((byte) (ennemy ? 1 : 0));
        parcel.writeInt(position);
        parcel.writeParcelable(weapon, i);
        parcel.writeInt(weapon_level);
        parcel.writeParcelable(power, i);
        parcel.writeInt(power_level);
        parcel.writeParcelable(classes, i);
        parcel.writeInt(classes_level);
        parcel.writeTypedList(skills);
        parcel.writeTypedArray(actifskill, i);
    }
}