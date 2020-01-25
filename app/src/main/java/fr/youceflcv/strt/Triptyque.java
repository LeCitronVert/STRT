package fr.youceflcv.strt;

import java.util.ArrayList;
import java.util.List;

public class Triptyque {
    String name;
    String desc;
    int img;
    int health;
    int attack;
    int defense;
    int esquive;
    int precision;
    int speed;
    int critical;
    List<Skill> skills = new ArrayList<Skill>();

    public Triptyque(String nom,String description,int icon,int vie,int attaque,int defens,int esquiv, int precisions,int vitesse,int critique){
        name = nom;
        desc = description;
        img = icon;
        health = vie;
        attack = attaque;
        defense = defens;
        esquive = esquiv;
        precision = precisions;
        speed = vitesse;
        critical = critique;
    }
}
