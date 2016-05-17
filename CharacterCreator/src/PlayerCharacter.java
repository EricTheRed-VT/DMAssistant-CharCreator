import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Eric the Red on 3/4/2016.
 */

//Implements player-character-only stats, including classes, backgrounds, etc.
public class PlayerCharacter extends Person {

    //Setup basic properties
    private IntegerProperty expPoints = new SimpleIntegerProperty(0);
    private IntegerProperty charLevel = new SimpleIntegerProperty();
    private IntegerProperty carryCapacity = new SimpleIntegerProperty();
    private IntegerProperty pushCapacity = new SimpleIntegerProperty();
    private StringProperty background = new SimpleStringProperty();

    public int getExpPoints() {
        return expPoints.get();
    }
    public IntegerProperty expPointsProperty() {
        return expPoints;
    }
    public void setExpPoints(int expPoints) {
        this.expPoints.set(expPoints);
    }

    public int getCharLevel() {
        return charLevel.get();
    }
    public IntegerProperty charLevelProperty() {
        return charLevel;
    }

    public int getCarryCapacity() {
        return carryCapacity.get();
    }
    public IntegerProperty carryCapacityProperty() {
        return carryCapacity;
    }

    public String getBackground() {
        return background.get();
    }
    public StringProperty backgroundProperty() {
        return background;
    }
    public void setBackground(String background) {
        this.background.set(background);
    }



    //Setup Classes
    private ObservableList<PlayerClass> playerClasses = FXCollections.observableArrayList();

    public ObservableList<PlayerClass> getPlayerClasses() {
        return playerClasses;
    }
    public void addPlayerClass(PlayerClass newClass){

        if (!playerClasses.contains(newClass)) {
            playerClasses.add(newClass);

        } else {System.out.println("Person already has levels in " + newClass.getName() + ".");}

    }
    public void removePlayerClass(PlayerClass newClass){
        playerClasses.remove(newClass);}



    //Setup Spells
    private int[][] spellSlotsPerCasterLevel = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 2, 0, 0, 0, 0, 0, 0, 0},
            {4, 3, 0, 0, 0, 0, 0, 0, 0},
            {4, 3, 2, 0, 0, 0, 0, 0, 0},
            {4, 3, 3, 0, 0, 0, 0, 0, 0},
            {4, 3, 3, 1, 0, 0, 0, 0, 0},
            {4, 3, 3, 2, 0, 0, 0, 0, 0},
            {4, 3, 3, 3, 1, 0, 0, 0, 0},
            {4, 3, 3, 3, 2, 0, 0, 0, 0},
            {4, 3, 3, 3, 2, 1, 0, 0, 0},
            {4, 3, 3, 3, 2, 1, 0, 0, 0},
            {4, 3, 3, 3, 2, 1, 1, 0, 0},
            {4, 3, 3, 3, 2, 1, 1, 0, 0},
            {4, 3, 3, 3, 2, 1, 1, 1, 0},
            {4, 3, 3, 3, 2, 1, 1, 1, 0},
            {4, 3, 3, 3, 2, 1, 1, 1, 1},
            {4, 3, 3, 3, 3, 1, 1, 1, 1},
            {4, 3, 3, 3, 3, 2, 1, 1, 1},
            {4, 3, 3, 3, 3, 2, 2, 1, 1}
    };



    //Constructor including necessary property bindings
    public PlayerCharacter(){

        super();

        this.charLevel.bind(new IntegerBinding() {
            {
                super.bind(expPoints);
            }

            //calculate level by comparing XP to set values
            @Override
            protected int computeValue() {
                int lvl = 0;
                int xp = expPoints.intValue();
                int[] lvlUp = {300,900,2700,6500,14000,23000,34000,48000,64000,85000,
                        100000,120000,140000,165000,195000,225000,265000,305000,355000};

                while ((xp >= lvlUp[lvl]) && (lvl < 20)){lvl++;}
                return ++lvl;
            }
        });
        this.profBonus.bind((this.charLevel.divide(4)).add(2));

        this.charLangs.add("common");

        this.carryCapacity.bind(this.str.scoreProperty().multiply(15));
        this.pushCapacity.bind(this.str.scoreProperty().multiply(30));

        this.hitPoints.bind(new IntegerBinding(){
            {
                //updates hit points if constitution, total level, or classes change
                super.bind(
                        PlayerCharacter.this.getCon().modProperty(),
                        PlayerCharacter.this.charLevel,
                        PlayerCharacter.this.playerClasses
                );
            }

            //calculates hp from constitution and adds hp from classes
            @Override
            protected int computeValue() {
                int totalHP = PlayerCharacter.this.charLevel.intValue() * PlayerCharacter.this.getCon().getMod();
                for (PlayerClass c : PlayerCharacter.this.playerClasses) {
                    totalHP += c.getTotalClassHP();
                }

                return totalHP;
            }
        });

        this.casterLevel = new IntegerBinding(){
            {
                //updates caster level if total level or classes change
                super.bind(PlayerCharacter.this.charLevel, PlayerCharacter.this.playerClasses);
            }

            //totals CL from classes implementing spellcaster
            @Override
            protected int computeValue() {
                int totalCL = 0;
                for (PlayerClass c : PlayerCharacter.this.playerClasses) {
                    if (c instanceof SpellCaster){
                        SpellCaster s = (SpellCaster) c;
                        totalCL += s.getCasterLevel();
                    }
                }

                return totalCL;
            }
        };

        //updates spell slots when CL changes
        this.casterLevel.addListener(
                (o, oldVal, newVal) -> {
                    this.spellSlots = this.spellSlotsPerCasterLevel[this.casterLevel.intValue()];
                }
        );

    }















}