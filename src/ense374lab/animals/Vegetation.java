/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ense374lab.animals;

/**
 *
 * @author BenBen
 */
public class Vegetation extends consumable {
    
private TypeVegetation type;
public Vegetation (TypeVegetation inType) 
{
this.type = inType;
}
TypeVegetation getType() {
    return this.type;
}
      
}
