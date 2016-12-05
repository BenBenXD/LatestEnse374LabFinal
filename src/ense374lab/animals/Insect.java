/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ense374lab.animals;
import java.util.*;
/**
 *
 * @author BenBen
 */
public class Insect extends NewAnimal {
    private InsectType type;
    
    public Insect (InsectType inType) {
        super();
        this.type = inType;
        this.movementRange = 1;
        switch (this.type) {
            case CATERPILLAR:
                this.canEatPlants.add(TypeVegetation.TREE);
                this.canEatPlants.add(TypeVegetation.SHRUB);
                break;
            case GRASSHOPPER:
                this.canEatPlants.add(TypeVegetation.GRASS);
                break;
        }
    }
    
    InsectType getType() {
        return this.type;
    }   
}
