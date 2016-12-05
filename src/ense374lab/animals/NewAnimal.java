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
public class NewAnimal extends World {
   protected int movementRange;
    protected int hunger;

    // all of these could be bundled into a struct called "diet".
    protected ArrayList<TypeVegetation>      canEatPlants;
    protected ArrayList<AnimalType> canEatAnimals;
    protected ArrayList<InsectType>     canEatInsects;
    protected ArrayList<BirdType>       canEatBirds;

    protected int remainingMoves;
    
    public NewAnimal () {
        this.canEatPlants      = new ArrayList <TypeVegetation>();
        this.canEatAnimals = new ArrayList <AnimalType>();
        this.canEatBirds       = new ArrayList <BirdType>();
        this.canEatInsects     = new ArrayList <InsectType>();
        this.hunger            = 2;
    }
        
    public int getHunger () {
        return hunger;
    }
    
    public void becomeFull () {
        this.hunger = 2;
    }
    
    public int getRemainingMoves () {
        return remainingMoves;
    }
    
    public void wakeUp () {
        this.remainingMoves = movementRange;
        this.hunger -- ;
    }
    
    public boolean dietIncludesBirds(){
        return !canEatBirds.isEmpty();
    }

    public ArrayList<BirdType> getDietOfBirds() {
        return this.canEatBirds;
    }
    
    public boolean dietIncludesAnimals(){
        return !canEatAnimals.isEmpty();
    }
    
    public ArrayList<AnimalType> getDietOfAnimals() {
        return this.canEatAnimals;
    }
    
    public boolean dietIncludesInsects(){
        return !canEatInsects.isEmpty();
    }

    public ArrayList<InsectType> getDietOfInsects() {
        return this.canEatInsects;
    }
    
    public boolean dietIncludesPlants(){
        return !canEatPlants.isEmpty();
    }    
    
    public ArrayList<TypeVegetation> getDietOfPlants() {
        return this.canEatPlants;
    }
    
    public void decrementMoves () {
        this.remainingMoves --;
    }
    
    public void setDoneMoving () {
        this.remainingMoves = 0;
    }  
}
