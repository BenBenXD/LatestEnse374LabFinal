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
public class Statistics {
        private ArrayList<Integer> animalStatsByDay;
    private ArrayList<Integer> birdStatsByDay;
    private ArrayList<Integer> insectStatsByDay;
    private ArrayList<Integer> plantStatsByDay;
    
    public Statistics() {
        System.out.println("I am constructing.");
        animalStatsByDay = new ArrayList<Integer>();
        birdStatsByDay   = new ArrayList<Integer>();
        insectStatsByDay = new ArrayList<Integer>();
        plantStatsByDay  = new ArrayList<Integer>();
        animalStatsByDay.add(new Integer(0));
        birdStatsByDay.add(new Integer(0));
        insectStatsByDay.add(new Integer(0));
        plantStatsByDay.add(new Integer(0));

    }
    
    public void printDay (Integer day) {
        if (day > animalStatsByDay.size()) {
            System.out.print ("This day is beyond the scope of the simulation.");
        }else{
            System.out.print ("Statistics for Day " + day + ": ");
            System.out.print ("Animals: " + animalStatsByDay.get(day) + " ");
            System.out.print ("Birds: " + birdStatsByDay.get(day) + " ");            
            System.out.print ("Insects: " + insectStatsByDay.get(day) + " ");
            System.out.print ("Plants: " + plantStatsByDay.get(day) + "\n");
        }
        
    }
    
    public void printAllStats () {
        
        for (Integer day = 0; day < animalStatsByDay.size(); day++) {
            printDay(day);
        }
    }
    
    public void newDay () {
        animalStatsByDay.add(animalStatsByDay.get(animalStatsByDay.size()-1));
        birdStatsByDay.add(birdStatsByDay.get(birdStatsByDay.size()-1));
        insectStatsByDay.add(insectStatsByDay.get(insectStatsByDay.size()-1));
        plantStatsByDay.add(plantStatsByDay.get(plantStatsByDay.size()-1));

    }
    
    public void incrementAnimals() {
        Integer tempint = animalStatsByDay.remove((animalStatsByDay.size()-1));
        tempint++;
        animalStatsByDay.add(tempint);
    }
    public void incrementBirds() {
        Integer tempint = birdStatsByDay.remove((birdStatsByDay.size()-1));
        tempint++;
        birdStatsByDay.add(tempint);
    }
    public void incrementPlants() {
       Integer tempint = plantStatsByDay.remove((plantStatsByDay.size()-1));
       tempint++;
       plantStatsByDay.add(tempint);
    }
    public void incrementInsects() {    
        Integer tempint = insectStatsByDay.remove((insectStatsByDay.size()-1));
        tempint++;
        insectStatsByDay.add(tempint);
    }
    public void decrementAnimals() {
        Integer tempint = animalStatsByDay.remove((animalStatsByDay.size()-1));
        tempint--;
        animalStatsByDay.add(tempint);
    }
    public void decrementBirds() {
        Integer tempint = birdStatsByDay.remove((birdStatsByDay.size()-1));
        tempint--;
        birdStatsByDay.add(tempint);
    }
    public void decrementPlants() {
        Integer tempint = plantStatsByDay.remove((plantStatsByDay.size()-1));
        tempint--;
        plantStatsByDay.add(tempint);
    }
    public void decrementInsects() {    
        Integer tempint = insectStatsByDay.remove((insectStatsByDay.size()-1));
        tempint--;
        insectStatsByDay.add(tempint);
    }
    
}
