/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floopy;

import java.util.ArrayList;

/**
 *creates an iteration of one fight between two heroes
 * @author sunbe
 */
public class Battle {
    Hero a;
    Hero b;
    ArrayList<Fight> combat= new ArrayList();
    public Battle(Hero a,Hero b){
    this.a=a;
    this.b=b;
    
    }
}
