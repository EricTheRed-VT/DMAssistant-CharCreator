/**
 * Created by Eric the Red on 4/18/2016.
 */


//Holds spell stats, will provide more methods in full DM Assistant
public class Spell {
    private String name;
    private int level;
    private String school;
    private boolean ritual;
    private int castingTime;
    private int range;
    private String components;
    private boolean concentration;
    private int duration;
    private String savingThrow;
    private int numDamageDice;
    private int damageDieType;
    private String description;

    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public String getSchool() {
        return school;
    }
    public boolean isRitual() {
        return ritual;
    }
    public int getCastingTime() {
        return castingTime;
    }
    public int getRange() {
        return range;
    }
    public String getComponents() {
        return components;
    }
    public boolean isConcentration() {
        return concentration;
    }
    public int getDuration() {
        return duration;
    }
    public String getSavingThrow() {
        return savingThrow;
    }
    public int getNumDamageDice() {
        return numDamageDice;
    }
    public int getDamageDieType() {
        return damageDieType;
    }
    public String getDescription() {
        return description;
    }

    public void cast(){}    //placeholder for casting effects

    //Constructor pulls stats from database
    //If spell not found in database, asks for user-input
    public Spell(String spellName){
        this.name = spellName;

        Object[] stats = SQLController.getRecord("SPELLS","name", spellName);
        if (stats == null){stats = CharacterCreatorApp.getSpellStats(spellName);}

        this.level = (int) stats[1];
        this.school = (String) stats[2];
        this.ritual = ((int) stats[3] != 0);
        this.castingTime = (int) stats[4];
        this.range = (int) stats[5];
        this.components = (String) stats[6];
        this.concentration = ((int) stats[7] != 0);
        this.duration = (int) stats[8];
        this.savingThrow = (String) stats[9];
        this.numDamageDice = (int) stats[10];
        this.damageDieType = (int) stats[11];
        this.description = (String) stats[12];


    }



}
