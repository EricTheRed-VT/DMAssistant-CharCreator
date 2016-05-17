import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Eric the Red on 4/18/2016.
 */

//Implementation of the Wizard player class
public class Wizard extends PlayerClass implements SpellCaster {

    private int[][] spellsKnownPerLevel = new int[][]{  //FIXME: add number of spells as commented,
            {3, 6, 0, 0, 0, 0, 0, 0, 0, 0},             //FIXME: placement must be user-chosen
            {3, 6, 0, 0, 0, 0, 0, 0, 0, 0},//+2         //FIXME: Probably will need a method using multiple fields
            {3, 6, 1, 0, 0, 0, 0, 0, 0, 0},//+3
            {4, 6, 1, 0, 0, 0, 0, 0, 0, 0},//+5
            {4, 6, 1, 1, 0, 0, 0, 0, 0, 0},//+6
            {4, 6, 1, 1, 0, 0, 0, 0, 0, 0},//+8
            {4, 6, 1, 1, 1, 0, 0, 0, 0, 0},//+9
            {4, 6, 1, 1, 1, 0, 0, 0, 0, 0},//+11
            {4, 6, 1, 1, 1, 1, 0, 0, 0, 0},//+12
            {5, 6, 1, 1, 1, 1, 0, 0, 0, 0},//+14
            {5, 6, 1, 1, 1, 1, 1, 0, 0, 0},//+15
            {5, 6, 1, 1, 1, 1, 1, 0, 0, 0},//+17
            {5, 6, 1, 1, 1, 1, 1, 1, 0, 0},//+18
            {5, 6, 1, 1, 1, 1, 1, 1, 0, 0},//+20
            {5, 6, 1, 1, 1, 1, 1, 1, 1, 0},//+21
            {5, 6, 1, 1, 1, 1, 1, 1, 1, 0},//+23
            {5, 6, 1, 1, 1, 1, 1, 1, 1, 1},//+24
            {5, 6, 1, 1, 1, 1, 1, 1, 1, 1},//+26
            {5, 6, 1, 1, 1, 1, 1, 1, 1, 1},//+28
            {5, 6, 1, 1, 1, 1, 1, 1, 1, 1} //+30
    };

    private IntegerProperty casterLevel = new SimpleIntegerProperty(0);




    //Constructor
    public Wizard(PlayerCharacter me, boolean primaryClass) {

        super(me, primaryClass);
        this.name.set("Wizard");

    }

    @Override
    public int getCasterLevel() {
        return this.casterLevel.get();
    }

    @Override
    public IntegerProperty casterLevelProperty() {
        return this.casterLevel;
    }


    //FIXME: setup cantrip methods
    @Override
    public Spell[] getCantripsPossible() {
        return new Spell[0];
    }

    @Override
    public int[] getCantripsKnownPerLevel() {
        return new int[0];
    }

    @Override
    public Spell[] getCantripsKnown() {
        return new Spell[0];
    }


    //FIXME: setup spell methods
    @Override
    public Spell[][] getSpellsPossible() {
        return new Spell[0][];
    }

    @Override
    public int[][] getSpellsKnownPerLevel() {
        return spellsKnownPerLevel;
    }

    @Override
    public Spell[][] getSpellsKnown() {
        return new Spell[0][];
    }

    @Override
    public Spell[][] getSpellsPrepared() {
        return new Spell[0][];
    }
}
