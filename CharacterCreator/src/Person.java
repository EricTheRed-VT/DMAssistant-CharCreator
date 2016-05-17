import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Eric the Red on 5/8/2016.
 */


//Abstract class implementing common player character and NPC traits.
public abstract class Person extends Creature {

    //Setup basic stats
    protected StringProperty name = new SimpleStringProperty("name");

    protected int charAge = 0;
    protected int[] height = {0,0};
    protected int weight = 0;
    protected String hairColor = "";
    protected String eyeColor = "";
    protected String skinColor = "";



    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public int getCharAge() {
        return charAge;
    }
    public void setCharAge(int charAge) {
        this.charAge = charAge;
    }

    public int[] getHeight() {
        return height;
    }
    public void setHeight(int[] height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHairColor() {
        return hairColor;
    }
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getSkinColor() {
        return skinColor;
    }
    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }



    //Setup Languages
    protected ArrayList<String> charLangs = new ArrayList<>();

    public ArrayList<String> getCharLangs() {
        return this.charLangs;
    }
    public void addLang(String... langs) {

        int countEmpty = 0;

        for(String lang : langs){
            if (lang.equals("any")) {countEmpty++;}
            else {this.charLangs.add(lang);}
        }
        if (countEmpty != 0){
            String[] inputs = CharacterCreatorApp.getLangInput(countEmpty);
            Collections.addAll(this.charLangs, inputs);
        }

    }



    // Setup Race
    private Race race = new Race(this, "human");

    public Race getRace() {
        return race;
    }
    public void setRace() {
        race = new Race(this, CharacterCreatorApp.getRaceInput());
    }



    //Setup Spells
    protected IntegerBinding casterLevel;
    protected int[] spellSlots = new int[9];

    public Number getCasterLvl() {
        return casterLevel.get();
    }
    public IntegerBinding casterLvlProperty() {
        return casterLevel;
    }

    public int[] getSpellSlots() {
        return spellSlots;
    }


    //Constructor
    protected Person(){
        super();
    }


}
