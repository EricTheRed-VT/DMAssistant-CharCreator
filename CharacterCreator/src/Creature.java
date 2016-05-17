import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Eric the Red on 5/8/2016.
 */

//Abstract class implementing basic stats for all monsters/characters.
public abstract class Creature {

    //Setup basic stats
    protected IntegerProperty hitPoints = new SimpleIntegerProperty();
    protected IntegerProperty armorClass = new SimpleIntegerProperty();
    protected IntegerProperty passiveWisdom = new SimpleIntegerProperty();
    protected IntegerProperty speed = new SimpleIntegerProperty();
    protected IntegerProperty profBonus = new SimpleIntegerProperty(0);


    public int getHitPoints() {
        return hitPoints.get();
    }
    public IntegerProperty hitPointsProperty() {
        return hitPoints;
    }
    public void setHitPoints(int hitPoints) {
        this.hitPoints.set(hitPoints);
    }

    public int getArmorClass() {
        return armorClass.get();
    }
    public IntegerProperty armorClassProperty() {
        return armorClass;
    }
    public void setArmorClass(int armorClass) {
        this.armorClass.set(armorClass);
    }

    public int getPassiveWisdom() {
        return passiveWisdom.get();
    }
    public IntegerProperty passiveWisdomProperty() {
        return passiveWisdom;
    }

    public int getSpeed() {
        return speed.get();
    }
    public IntegerProperty speedProperty() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public int getProfBonus() {
        return profBonus.get();
    }
    public IntegerProperty profBonusProperty() {
        return profBonus;
    }



    //Setup Abilities
    protected Ability str = new Ability("Str");
    protected Ability dex = new Ability("Dex");
    protected Ability con = new Ability("Con");
    protected Ability intel = new Ability("Int");
    protected Ability wis = new Ability("Wis");
    protected Ability cha = new Ability("Cha");
    protected ArrayList<Ability> abilities = new ArrayList<>(Arrays.asList(str, dex, con, intel, wis, cha));

    public void addAbility(String name){
        Ability newAbility = new Ability(name);
        abilities.add(newAbility);
    }

    public Ability getStr() {
        return str;
    }
    public Ability getDex() {
        return dex;
    }
    public Ability getCon() {
        return con;
    }
    public Ability getInt() {
        return intel;
    }
    public Ability getWis() {
        return wis;
    }
    public Ability getCha() {
        return cha;
    }
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public Ability getAbil(String name){

        for (Ability a:abilities) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        System.out.println("Ability " + name + " does not exist.");
        return getAbil(CharacterCreatorApp.getAbilInput());

    }



    //Setup Skills and Saves
    protected ArrayList<Skill> skills = new ArrayList<>();

    public void addSkill(String name, String abil){
        skills.add(new Skill(name, this, abil));
    }
    public void addSkills(String[][] input){
        for (String[] s : input) {
            addSkill(s[0], s[1]);
        }
    }

    public void addSkillProf(String skill) {

        Skill skillName = this.getSkill(skill);
        if (!skillName.getProf()) {skillName.setProf(true);}
        else {addSkillProf(1);}

    }
    public void addSkillProf(int i) {
        String[] inputs = CharacterCreatorApp.getSkillInput(i);
        for (int j=0; j <= i-1; j++) addSkillProf(inputs[j]);
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }
    public Skill getSkill(String name){

        for (Skill s:skills) {
            if (s.getName().equals(name)){
                return s;
            }
        }
        System.out.println("Ability " + name + " does not exist.");
        return getSkill(CharacterCreatorApp.getSkillInput());
    }



    //Setup Size
    protected Size size = Size.MEDIUM;

    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }



    //Setup Alignment
    protected LawfulChaotic lnc = LawfulChaotic.NEUTRAL;
    protected GoodEvil gne = GoodEvil.NEUTRAL;

    public LawfulChaotic getLawfulChaotic() {
        return lnc;
    }
    public GoodEvil getGoodEvil() {
        return gne;
    }
    public void setLawfulChaotic(LawfulChaotic lnc) {
        this.lnc = lnc;
    }
    public void setGoodEvil(GoodEvil gne) {
        this.gne = gne;
    }
    public void setCharAlign(LawfulChaotic lnc, GoodEvil gne) {
        this.setLawfulChaotic(lnc);
        this.setGoodEvil(gne);
    }



    //Constructor sets up skill list and passive wisdom score
    public Creature(){

        this.addSkills(new String[][]{
                {"athletics", "Str"},
                {"acrobatics", "Dex"}, {"sleightOfHand", "Dex"}, {"stealth", "Dex"},
                {"arcana", "Int"}, {"history", "Int"}, {"investigation", "Int"}, {"nature", "Int"}, {"religion", "Int"},
                {"animalHandling", "Wis"}, {"insight", "Wis"}, {"medicine", "Wis"}, {"perception", "Wis"}, {"survival", "Wis"},
                {"deception", "Cha"}, {"intimidation", "Cha"}, {"performance", "Cha"}, {"persuasion", "Cha"},
                {"strSave", "Str"}, {"dexSave", "Dex"}, {"conSave", "Con"}, {"intSave", "Int"}, {"wisSave", "Wis"}, {"chaSave", "Cha"}
        });

        this.passiveWisdom.bind(this.getSkill("perception").getMod().add(10));

    }




}
