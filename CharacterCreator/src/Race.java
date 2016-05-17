import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Eric the Red on 3/5/2016.
 */

//Pulls race stats from database and applies to PC/NPC
public class Race{

    private StringProperty name;

    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }

    private int[] abilAdjusts = new int[6];
    private Size size;

    private int baseHeight;
    private int numHeightDice;
    private int typeHeightDice;
    private int baseWeight;
    private int numWeightDice;
    private int typeWeightDice;

    private int[] height = new int[2];
    private int weight;
    private int speed;
    private String[] langs;

    //Calculates a random height and weight based on racial constraints
    public void calcHeightWeight(){

        int roll = Dice.roll(numHeightDice, typeHeightDice);
        int inches = roll + baseHeight;
        this.height = new int[]{inches / 12, inches % 12};
        this.weight = Dice.roll(numWeightDice, typeWeightDice, baseWeight);

    }

    //Constructor pulls stats from database
    //If race not found in database, asks for user-input
    public Race(Person me, String race){

        this.name = new SimpleStringProperty(race);

        Object[] stats = SQLController.getRecord("RACES","name", race);
        if (stats == null){stats = CharacterCreatorApp.getRaceStats(race);}

        for (int i = 1; i < 6; i++) {abilAdjusts[i-1] = (int) stats[i];}

        switch ((String) stats[7]){
            case "s": this.size = Size.SMALL;
            case "m": this.size = Size.MEDIUM;
            case "l": this.size = Size.LARGE;
        }

        this.baseHeight = (int) stats[8];
        this.numHeightDice = (int) stats[9];
        this.typeHeightDice = (int) stats[10];
        this.baseWeight = (int) stats[11];
        this.numWeightDice = (int) stats[12];
        this.typeWeightDice = (int) stats[13];
        this.calcHeightWeight();

        this.speed = (int) stats[14];

        this.langs = ((String) stats[15]).split(",");

        me.getStr().setRaceAdj(abilAdjusts[0]);
        me.getDex().setRaceAdj(abilAdjusts[1]);
        me.getCon().setRaceAdj(abilAdjusts[2]);
        me.getInt().setRaceAdj(abilAdjusts[3]);
        me.getWis().setRaceAdj(abilAdjusts[4]);
        me.getCha().setRaceAdj(abilAdjusts[5]);

        me.setSize(size);
        me.setHeight(height);
        me.setWeight(weight);
        me.setSpeed(speed);
        me.addLang(langs);
    }







}

