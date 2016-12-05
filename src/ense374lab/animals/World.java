/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ense374lab.animals;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
/**
 *
 * @author BenBen
 */
public class World {
    private Location[][] grid = new Location [150][150];
    private Statistics ecoStats;
    
    public void printStats() {
        ecoStats.printAllStats();
    }
    
    public World () {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                grid[i][j] = new Location(i,j);
            }
        }
    }
    
    public World (int percentPlants,
                      int percentInsects,
                      int percentBirds,
                      int percentAnimals) {
        this.ecoStats = new Statistics();
        int SATURATION = 150 * 150 * 5;
        int noPlants = SATURATION * percentPlants / 100;
        int noInsects =  SATURATION * percentInsects / 100;
        int noBirds = SATURATION * percentBirds / 100;
        int noAnimals = SATURATION * percentAnimals / 100;
        int x, y;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                grid[i][j] = new Location(i,j);
            }
        }
        while (noPlants != 0) {
            // create a random Plant
            Vegetation tempPlant = new Vegetation (TypeVegetation.values()[ThreadLocalRandom.current().nextInt(0, TypeVegetation.values().length)]);
            ecoStats.incrementPlants();
            // choose a random Location
            x = ThreadLocalRandom.current().nextInt(0, 150);
            y = ThreadLocalRandom.current().nextInt(0, 150);
            grid[x][y].addConsumable(tempPlant);
            noPlants --;
        }
        while (noAnimals != 0) {
            NewAnimal tempAnimal = new NewAnimal (AnimalType.values()[ThreadLocalRandom.current().nextInt(0, AnimalType.values().length)]);
            ecoStats.incrementAnimals();
            x = ThreadLocalRandom.current().nextInt(0, 150);
            y = ThreadLocalRandom.current().nextInt(0, 150);
            grid[x][y].addConsumable(tempAnimal);
            noAnimals--;
        }
        while (noBirds != 0) {
            Bird tempBird = new Bird (BirdType.values()[ThreadLocalRandom.current().nextInt(0, BirdType.values().length)]);
            ecoStats.incrementBirds();
            x = ThreadLocalRandom.current().nextInt(0, 150);
            y = ThreadLocalRandom.current().nextInt(0, 150);
            grid[x][y].addConsumable(tempBird);
            noBirds--;
        }
        while (noInsects != 0) {
            Insect tempInsect = new Insect (InsectType.values()[ThreadLocalRandom.current().nextInt(0, InsectType.values().length)]);
            ecoStats.incrementInsects();
            x = ThreadLocalRandom.current().nextInt(0, 150);
            y = ThreadLocalRandom.current().nextInt(0, 150);
            grid[x][y].addConsumable(tempInsect);
            noInsects--;
        }
    }
    
    public void printGrid() {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                System.out.print(grid[i][j].getAlphaPredator());
            }
            System.out.print("\n");
        }
            System.out.print("\n");
            System.out.print("Legend:\n");
            System.out.print("'a': Top predator is an Animal.\n");
            System.out.print("'b': Top predator is a Bird.\n");
            System.out.print("'i': Top predator is an Insect.\n");
            System.out.print("'p': There are only plants in this location.\n");
            System.out.print("' ': This location is empty.\n");
        
    }
    
    public void printDetails (int x, int y) {
        grid[x][y].printDetails();
    }
    
    // big oh of (i can 't imagine this will ever finish)... and yet it does. :P
    public void runForNDays (int n) {
        int sumRemainingMoves;
        for (int day = 0; day < n; day++) {
            ecoStats.newDay();
            System.out.println (" This has been running for " + day + " days.");
            for (int i = 0; i < 150; i++){
                for (int j = 0; j < 150; j++) {
                    grid[i][j].awakenAll();
                }
            }
            for (int d = 0; d < n; d++) {
                while (getSumRemainingMoves()!=0) {
                    for (int i = 0; i < 150; i++){
                        for (int j = 0; j < 150; j++) {
                            grid[i][j].allFeed(ecoStats);
                        }
                    }
                    moveAllAnimals();
                }
                for (int i = 0; i < 150; i++){
                    for (int j = 0; j < 150; j++) {
                        grid[i][j].checkStarvations(ecoStats);
                    }
                }
            }
        }
    }
    
    public int getSumRemainingMoves () {
        int sumRemainingMoves = 0;
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++) {
                sumRemainingMoves += grid[i][j].getSumRemainingMoves();
            }
        }
        return sumRemainingMoves;
    }
    
    public void moveAllAnimals () {
        ArrayList<NewAnimal> tempAnimals = new ArrayList<NewAnimal>();
        ArrayList<Bird>       tempBirds       = new ArrayList<Bird>();
        ArrayList<Insect>     tempInsects     = new ArrayList<Insect>();
        Bird       currentBird;
        NewAnimal currentAnimal;
        Insect     currentInsect;
        int        totalBirds;
        int        totalInsects;
        int        totalAnimals;
        
        int xOffset = 0;
        int yOffset = 0;
        int newX, newY;
        // move all animals : pseudo
        // do for all x and y locations.
        
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++) {
            // 1. Get the list of all animals from the location    
            tempBirds = grid[i][j].getBirds();
            // 2. for each critter
            totalBirds = tempBirds.size();
            for (int v = 0; v < totalBirds; v++) {
                    
                currentBird = tempBirds.remove(0);
                // 2. Check to see if the animal has < 3 hunger
                // 3. Check to see if the animal has move points remaining
                if (currentBird.getHunger() < 2 && currentBird.getRemainingMoves() > 0){
                    // 5. decrement move points
                    currentBird.decrementMoves();
                    // 6. Select a random number for x between -1 and 1
                    // 7. Select a random number for y between -1 and 1
                    // 8. if 2, 2, continue
                    xOffset = 0;
                    yOffset = 0;
                    while (xOffset == 0 && yOffset == 0) {
                        xOffset = ThreadLocalRandom.current().nextInt(-1, 1);
                        yOffset = ThreadLocalRandom.current().nextInt(-1, 1);
                    }
                    // 9. Add these numbers to x and y
                    newX = i + xOffset;
                    newY = j + yOffset;
                    // 10. if out of bounds, fix it
                    if (newX < 0) {
                        newX += 2;
                    }
                    if (newY < 0) {
                        newY += 2;
                    }
                    if (newX > 149) {
                        newX -= 2;
                    }
                    if (newY > 149) {
                        newX -= 2;
                    }
                    // 11. add creature to that location x, y.
                    //System.out.println("("+i+","+j+")->("+newX+","+newY+")");
                    grid[newX][newY].addConsumable(currentBird);
                }else{
                    currentBird.setDoneMoving();
                    tempBirds.add(currentBird);
                }
            }
            // 12. return the list to that location.
            grid[i][j].setBirds(tempBirds);
            
            tempAnimals = grid[i][j].getAnimals();
            totalAnimals = tempAnimals.size();
            for (int v = 0; v < totalAnimals; v++) {
                currentAnimal = tempAnimals.remove(0);

                if (currentAnimal.getHunger() < 2 && currentAnimal.getRemainingMoves() > 0){
                    
                    currentAnimal.decrementMoves();
                    xOffset = 0;
                    yOffset = 0;
                    while (xOffset == 0 && yOffset == 0) {
                        xOffset = ThreadLocalRandom.current().nextInt(-1, 1);
                        yOffset = ThreadLocalRandom.current().nextInt(-1, 1);
                    }
                    newX = i + xOffset;
                    newY = j + yOffset;
                    if (newX < 0) {
                        newX += 2;
                    }
                    if (newY < 0) {
                        newY += 2;
                    }
                    if (newX > 149) {
                        newX -= 2;
                    }
                    if (newY > 149) {
                        newX -= 2;
                    }
                    grid[newX][newY].addConsumable(currentAnimal);
                }else{
                    currentAnimal.setDoneMoving();
                    tempAnimals.add(currentAnimal);
                }
            }
            grid[i][j].setAnimals(tempAnimals);
            
            tempInsects = grid[i][j].getInsects();
            totalInsects = tempInsects.size();
            for (int v = 0; v < totalInsects; v++) {
                currentInsect = tempInsects.remove(0);

                if (currentInsect.getHunger() < 2 && currentInsect.getRemainingMoves() > 0){
                    
                    currentInsect.decrementMoves();
                    xOffset = 0;
                    yOffset = 0;
                    while (xOffset == 0 && yOffset == 0) {
                        xOffset = ThreadLocalRandom.current().nextInt(-1, 1);
                        yOffset = ThreadLocalRandom.current().nextInt(-1, 1);
                    }
                    newX = i + xOffset;
                    newY = j + yOffset;
                    if (newX < 0) {
                        newX += 2;
                    }
                    if (newY < 0) {
                        newY += 2;
                    }
                    if (newX > 149) {
                        newX -= 2;
                    }
                    if (newY > 149) {
                        newX -= 2;
                    }
                    grid[newX][newY].addConsumable(currentInsect);
                }else{
                    currentInsect.setDoneMoving();
                    tempInsects.add(currentInsect);
                }
            }
            grid[i][j].setInsects(tempInsects);
            
            }
        }
    }

}
