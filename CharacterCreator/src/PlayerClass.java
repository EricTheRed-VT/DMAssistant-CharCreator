import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Eric the Red on 3/20/2016.
 */

//Abstract class implementing common player-class traits
public abstract class PlayerClass {

    protected StringProperty name = new SimpleStringProperty();
    protected IntegerProperty classLevel = new SimpleIntegerProperty(0);

    protected int classHDType;
    protected int[] levelHP = new int[20];
    protected IntegerProperty totalClassHP = new SimpleIntegerProperty();

    protected String[] armorProfs;
    protected String[] weaponProfs;
    protected String[] toolProfs;

    protected String[] skillOptions;
    protected String[] skillProfs;


    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public int getClassLevel() {
        return classLevel.get();
    }
    public IntegerProperty classLevelProperty() {
        return classLevel;
    }
    public void setClassLevel(int classLevel) {
        this.classLevel.set(classLevel);
    }

    public int getClassHDType() {
        return classHDType;
    }

    public int getTotalClassHP() {
        return totalClassHP.get();
    }
    public IntegerProperty totalClassHPProperty() {
        return totalClassHP;
    }


    //Constructor
    public PlayerClass(PlayerCharacter me, boolean primaryClass){

        //Setup HP
        if (primaryClass) {levelHP[0] = classHDType;}
        else {levelHP[0] = Dice.roll(1, classHDType);}

        for (int i=1; i<20; i++){
            levelHP[i] = levelHP[i-1] + Dice.roll(1, classHDType);
        }

        totalClassHP.bind(new IntegerBinding(){
            {
                super.bind(classLevel);
            }
            @Override
            protected int computeValue() {
                return levelHP[classLevel.intValue()];
            }
        });




    }



}



