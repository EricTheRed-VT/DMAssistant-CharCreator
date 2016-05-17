import javafx.beans.property.IntegerProperty;

/**
 * Created by Eric the Red on 4/18/2016.
 */


//Interface for any race/class/etc which provides a creature's spell-casting ability
public interface SpellCaster{

    int getCasterLevel();
    IntegerProperty casterLevelProperty();

    Spell[] getCantripsPossible();
    int[] getCantripsKnownPerLevel();
    Spell[] getCantripsKnown();


    Spell[][] getSpellsPossible();
    int[][] getSpellsKnownPerLevel();
    Spell[][] getSpellsKnown();
    Spell[][] getSpellsPrepared();






















}