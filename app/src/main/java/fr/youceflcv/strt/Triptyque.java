package fr.youceflcv.strt;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Triptyque implements Parcelable, Serializable {
    String name;
    String desc;
    String type;
    int img;
    int imgvisu;
    int health;
    int attack;
    int defense;
    int esquive;
    int precision;
    int speed;
    int critical;
    List<Skill> skills = new ArrayList<Skill>();

    public Triptyque(String nom,String description,String what, int icon,int vie,int attaque,int defens,int esquiv, int precisions,int vitesse,int critique){
        name = nom;
        desc = description;
        type = what;
        img = icon;
        health = vie;
        attack = attaque;
        defense = defens;
        esquive = esquiv;
        precision = precisions;
        speed = vitesse;
        critical = critique;
    }

    protected Triptyque(Parcel in) {
        name = in.readString();
        desc = in.readString();
        type = in.readString();
        img = in.readInt();
        imgvisu = in.readInt();
        health = in.readInt();
        attack = in.readInt();
        defense = in.readInt();
        esquive = in.readInt();
        precision = in.readInt();
        speed = in.readInt();
        critical = in.readInt();
        skills = in.createTypedArrayList(Skill.CREATOR);

    }

    public static final Creator<Triptyque> CREATOR = new Creator<Triptyque>() {
        @Override
        public Triptyque createFromParcel(Parcel in) {
            return new Triptyque(in);
        }

        @Override
        public Triptyque[] newArray(int size) {
            return new Triptyque[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(type);
        parcel.writeInt(img);
        parcel.writeInt(imgvisu);
        parcel.writeInt(health);
        parcel.writeInt(attack);
        parcel.writeInt(defense);
        parcel.writeInt(esquive);
        parcel.writeInt(precision);
        parcel.writeInt(speed);
        parcel.writeInt(critical);
        parcel.writeTypedList(skills);
    }
}
