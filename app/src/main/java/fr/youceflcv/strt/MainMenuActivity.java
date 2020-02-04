package fr.youceflcv.strt;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
public class MainMenuActivity extends AppCompatActivity {

    private Vector<Skill> list;
    private Vector<Triptyque> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        XmlResourceParser xpp = getApplicationContext().getResources().getXml(R.xml.skills);

        // début analyse
        try {

            Skill current_skill = null;
            String text= null;
            String path = "";
            list = new Vector<>();

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) { // Tant qu'on a pas atteint la fin du doc
                if(eventType == XmlPullParser.START_TAG) {
                    path = path + "." + xpp.getName();
                    if ("skill".equals(xpp.getName())) {// Init. nouveau perso
                        current_skill = new Skill("","","",1,"",true,false,"",1,"","","",1,1,1,1);
                    }
                } else  if(eventType == XmlPullParser.END_TAG) {

                    if (".set.skill.name".equals(path)) {// Nom
                        current_skill.name = text;
                    } else if (".set.skill.description".equals(path)){
                        current_skill.desc = text;
                    }  else if (".set.skill.triptyque".equals(path)){
                        current_skill.triptyque = text;
                    } else if (".set.skill.iconname".equals(path)){
                        current_skill.imgname = text;
                    } else if (".set.skill.actif".equals(path)){
                        current_skill.actif = Boolean.valueOf(text).booleanValue() ;
                    } else if (".set.skill.temporary".equals(path)){
                        current_skill.temporary = Boolean.valueOf(text).booleanValue() ;
                    } else if (".set.skill.type".equals(path)){
                        current_skill.type = text;
                    } else if (".set.skill.effet".equals(path)){
                        current_skill.effect = text;
                    } else if (".set.skill.stat".equals(path)){
                        current_skill.stat = text;
                    } else if (".set.skill.valeur".equals(path)){
                        current_skill.value = Integer.parseInt(text);
                    } else if (".set.skill.cibletype".equals(path)){
                        current_skill.cibletype = text;
                    } else if (".set.skill.tier".equals(path)){
                        current_skill.tier = Integer.parseInt(text);
                    } else if (".set.skill.level".equals(path)){
                        current_skill.level = Integer.parseInt(text);
                    } else if (".set.skill.duree".equals(path)){
                        current_skill.dure = Integer.parseInt(text);
                    } else if (".set.skill.cost".equals(path)){
                        current_skill.cost = Integer.parseInt(text);
                    } else if ("skill".equals(xpp.getName())) {// Ajouter perso à la liste
                        list.add(current_skill);
                    }

                    path = path.substring(0,path.lastIndexOf('.'));

                } else if(eventType == XmlPullParser.TEXT) {
                    text = xpp.getText(); // On met le text de coté pour la fin de la balise.
                }

                xpp.next();
                eventType = xpp.getEventType();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Skill perso : list){
            Log.d("xml",perso.triptyque);
        }




        XmlResourceParser xpp2 = getApplicationContext().getResources().getXml(R.xml.triptyque);

        // début analyse
        try {

            Triptyque current_triptyque = null;
            String text= null;
            String path = "";
            list2 = new Vector<>();

            xpp2.next();
            int eventType = xpp2.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) { // Tant qu'on a pas atteint la fin du doc
                if(eventType == XmlPullParser.START_TAG) {
                    path = path + "." + xpp2.getName();
                    if ("triptyque".equals(xpp2.getName())) {// Init. nouveau perso
                        current_triptyque = new Triptyque("","","",1,1,1,1,1,1,1,1);
                    }
                } else  if(eventType == XmlPullParser.END_TAG) {

                    if (".set.triptyque.name".equals(path)) {// Nom
                        current_triptyque.name = text;
                    } else if (".set.triptyque.description".equals(path)){
                        current_triptyque.desc = text;
                    } else if (".set.triptyque.icon".equals(path)){
                        current_triptyque.img = (getResources().getIdentifier(text, "drawable", getPackageName()));
                    } else if (".set.triptyque.imgvisu".equals(path)){
                        current_triptyque.imgvisu = (getResources().getIdentifier(text, "drawable", getPackageName()));
                    } else if (".set.triptyque.type".equals(path)){
                        current_triptyque.type = text;
                    } else if (".set.triptyque.health".equals(path)){
                        current_triptyque.health = Integer.parseInt(text);
                    } else if (".set.triptyque.attack".equals(path)){
                        current_triptyque.attack = Integer.parseInt(text);
                    } else if (".set.triptyque.defense".equals(path)){
                        current_triptyque.defense = Integer.parseInt(text);
                    } else if (".set.triptyque.speed".equals(path)){
                        current_triptyque.speed = Integer.parseInt(text);
                    } else if (".set.triptyque.precision".equals(path)){
                        current_triptyque.precision = Integer.parseInt(text);
                    } else if (".set.triptyque.esquive".equals(path)){
                        current_triptyque.esquive = Integer.parseInt(text);
                    } else if (".set.triptyque.critic".equals(path)){
                        current_triptyque.critical = Integer.parseInt(text);
                    } else if ("triptyque".equals(xpp2.getName())) {// Ajouter perso à la liste
                        list2.add(current_triptyque);
                    }

                    path = path.substring(0,path.lastIndexOf('.'));

                } else if(eventType == XmlPullParser.TEXT) {
                    text = xpp2.getText(); // On met le text de coté pour la fin de la balise.
                }

                xpp2.next();
                eventType = xpp2.getEventType();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Triptyque triptyc : list2){
            Log.d("nom du triptyc",triptyc.name);
            for(int i= 0; i<triptyc.skills.size();i++){
                Log.d("sa liste de skills", triptyc.skills.get(i).name);
            }
        }
    }
    public void launchteam(View view){
        Intent gameActivity = new Intent(MainMenuActivity.this, create_team.class);
        gameActivity.putExtra("skills",list);
        gameActivity.putExtra("triptycs",list2);
        startActivity(gameActivity);
    }
    public void launchfight(View view){
        Intent gameActivity = new Intent(MainMenuActivity.this, MainActivity.class);
        gameActivity.putExtra("skills",list);
        gameActivity.putExtra("triptycs",list2);
        startActivity(gameActivity);
    }
}
