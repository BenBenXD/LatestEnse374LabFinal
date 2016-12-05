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
public class Bird extends NewAnimal{
private BirdType type;
    
    public Bird (BirdType inType) {
        super();
        this.type = inType;
        this.movementRange = 5;
        switch (this.type) {
            case BLUEJAY:
                this.canEatInsects.add(InsectType.CATERPILLAR);
                this.canEatInsects.add(InsectType.GRASSHOPPER);
                break;
            case HAWK:
                this.canEatAnimals.add(AnimalType.SQUIRREL);
                this.canEatAnimals.add(AnimalType.MOUSE);
                break;
        }
    }
    
    BirdType getType() {
        return this.type;
    }    
}
