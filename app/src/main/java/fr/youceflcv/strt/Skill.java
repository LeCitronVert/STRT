package fr.youceflcv.strt;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by youcef.kadarabah on 08/01/20.
 */

public class Skill implements Parcelable, Serializable {
    String name;
    String desc;
    String triptyque;
    int img;
    String imgname;
    boolean actif;
    boolean temporary;
    String type;
    int value;
    String effect;
    String stat;
    String cibletype;
    int tier;
    int level;
    int dure;
    int cost;


    public Skill(String nom, String description, String triptyc, int image, String imagename, boolean activable,boolean temporaire, String typeskill, int valeur, String stats, String effetadditionnel, String cible, int cout, int palier, int niveau, int duree){
        name = nom;
        desc = description;
        triptyque = triptyc;
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
        temporary = temporaire;
    }

    protected Skill(Parcel in) {
        name = in.readString();
        desc = in.readString();
        triptyque = in.readString();
        img = in.readInt();
        imgname = in.readString();
        actif = in.readByte() != 0;
        temporary = in.readByte() != 0;
        type = in.readString();
        value = in.readInt();
        effect = in.readString();
        stat = in.readString();
        cibletype = in.readString();
        tier = in.readInt();
        level = in.readInt();
        dure = in.readInt();
        cost = in.readInt();
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    public void useSkill(Personnage attaquant, Personnage cible){
        attaquant.actions = attaquant.actions - this.cost;
        if(this.type.equals("attack")){
            Log.d("test attaque","oui");
            int dps = attaquant.attack*this.value;
            int critic=1;
            critic=(int)( Math.random()*100);
            if(critic <= attaquant.critical){
                dps = dps*2;
            }
            cible.takeDamage(dps);
        } else if(this.type.equals("heal")){
            cible.heal(this.value);
        } else if(this.type.equals("buff")){
            cible.buff(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(triptyque);
        parcel.writeInt(img);
        parcel.writeString(imgname);
        parcel.writeByte((byte) (actif ? 1 : 0));
        parcel.writeByte((byte) (temporary ? 1 : 0));
        parcel.writeString(type);
        parcel.writeInt(value);
        parcel.writeString(effect);
        parcel.writeString(stat);
        parcel.writeString(cibletype);
        parcel.writeInt(tier);
        parcel.writeInt(level);
        parcel.writeInt(dure);
        parcel.writeInt(cost);
    }
}
