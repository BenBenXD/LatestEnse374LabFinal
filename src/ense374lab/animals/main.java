
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ense374lab.animals;
import java.util.Scanner;
/**
 *
 * @author BenBen
 */
public class main {
    
    
public static void main(String[] args) {
    
Scanner s = new Scanner(System.in);
String in = "a";
int    intIn = 0;
int    intX, intY;
int Vege, insect, bird, Animal;
System.out.println("integer between 0 - 100 as the density of plants:");
Vege = s.nextInt();
System.out.println("integer between 0 - 100 as density of insects:");
insect = s.nextInt();
System.out.println("integer between 0 - 100 as density of birds:");
bird = s.nextInt();
System.out.println("integer between 0 - 100 as density of animals:");
Animal = s.nextInt();
World env = new World(Vege, insect, bird,Animal);
env.printGrid();

while (!in.equals("x")) {
    System.out.println("Please select from the following menu:");
    System.out.println("Magnify a square: z");
    System.out.println("Run the simulation for a number of days: r");
    System.out.println("Print the grid of Alpha predators: p");
    System.out.println("Print out statistics of the simulation: s");
    System.out.println("Exit: x");
    in = s.next(".");
    if (in.equals("z")){
        System.out.println("Please enter the coordinates to magnify: 0-149, 0-149");
        intX = s.nextInt();
        intY = s.nextInt();
        if (intX > 149 || intY > 149 || intY <0 || intX < 0) {
            System.out.println("Invalid Coordinates");
        }else{
            env.printDetails (intX, intY);
        }
    }else if (in.equals("s")){
        env.printStats();
    }else if (in.equals("r")){
        System.out.println("enter the number of days to run for");
        intIn = s.nextInt();
        if (intIn < 0 ) {
            System.out.println("Invalid");
        }
        else{
            env.runForNDays(intIn);
        }
    }else if (in.equals("x")){
    }else if (in.equals("p")){
        env.printGrid();
    }else{
        System.out.println("its invalid input");
    }
}
s.close();
}
    
}
