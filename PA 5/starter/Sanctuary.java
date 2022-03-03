
/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Lecture
 * 
 * This file contain a class that implement information about a sanctuary. It 
 * also implement methods to implements/ alter informations of the sanctuary
 * and the animals within that sanctuary.
 */

import java.util.HashMap;
import java.util.Set;

/**
 * This class contain initialized Sanctuary and also contain methods that return
 * information such as the number of animals of a specifc species, total number
 * of animals, total numbers of species. It also contain methods to add and
 * remove animals from the sanctuary.
 */
public class Sanctuary {

    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * This method Initialize the sanctuary’s information as a HashMap with no
     * element.
     * 
     * @param maxAnimals - the max amount of animals can be in the sanctuary
     * @param maxSpecies - the max amount of species can be in the sanctuary
     * @throws IllegalArgumentException - if any input is less than 0
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals < 0 || maxSpecies < 0) {
            throw new IllegalArgumentException();
        }
        this.sanctuary = new HashMap<String, Integer>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * This method return the number of animals of a specific species in the
     * sanctuary
     * 
     * @param species - the species of animals
     * @return - an integer that represent the total number of the animals from
     * that species
     * @throws IllegalArgumentException - if species is null.
     */
    public int getNum(String species) {
        // If species object is null, throw exception
        if (species == null) {
            throw new IllegalArgumentException();
        }
        // Otherwise,
        else {
            // If the species object is in the sanctuary then return the number
            // of that species currently in the sanctuary
            if (sanctuary.containsKey(species)) {
                return this.sanctuary.get(species);
            }
            // Otherwise, if the species is not there, then return 0
            else {
                return 0;
            }
        }
    }

    /**
     * This method return the total number of animals in the sanctuary
     * 
     * @return - an integer that represent the total number of the animals from
     * the sanctuary
     */
    public int getTotalAnimals() {
        int total = 0;

        // A for each loop that add up all the values contain in sanctuary's map
        for (int num : this.sanctuary.values()) {
            total += num;
        }
        // Return all the value added up which is the total animals in the
        // sanctuary
        return total;
    }

    /**
     * This method return the total number of species in the sanctuary. Also is
     * the size of the HashMap
     * 
     * @return - an integer that represent the total number of the species from
     * the sanctuary
     */
    public int getTotalSpecies() {
        return this.sanctuary.size();
    }

    /**
     * This method add animals into the sanctuary if space permit. If spaces is
     * run out, the method return the amount of animals were not added.
     * 
     * @param species - the species of animals
     * 
     * @return - an integer that represent the total number of the animals from
     * that species that were not able to be added
     * @throws IllegalArgumentException - if species is null or num is less than
     * or equal to 0
     */
    public int rescue(String species, int num) {
        int canAdd;
        // Throw an exception if num is less than 0 or species is null
        if (num <= 0 || species == null) {
            throw new IllegalArgumentException();
        }

        // If total animals with the number of adding animals is less than
        // max allowed animals in the sanctuary then,
        if (getTotalAnimals() + num <= this.maxAnimals) {
            // The number of animals can be added is num
            canAdd = num;
        }
        // Otherwise,
        else {
            // The number of animals can be added is the empty space in
            // sanctuary
            canAdd = this.maxAnimals - this.getTotalAnimals();
        }

        // Add animals that already in the sanctuary
        if (this.sanctuary.containsKey(species)) {
            this.sanctuary.put(species, getNum(species) + canAdd);
            // Should return 0 if canAdd is num and a positive integer
            // representing the animals that could not be added
        }

        // Adding animals that is not already in the sanctuary
        else {
            this.sanctuary.put(species, canAdd);
            // Should return 0 if canAdd is num and a positive integer
            // representing the animals that could not be added
        }
        // Return the number of animals that wasn't able to be added
        return num - canAdd;
    }

    /**
     * This method remove the animals from the sanctuary. If the animals no
     * longer exist then remove that species from the sanctuary.
     * 
     * @param species - the species of animals
     * @param num - the amount of animals from that species to be remove
     * @throws IllegalArgumentException - if species is null; num is less than
     * or equal to 0; num is more than the total animals of that species; if the
     * species does not exist in the sanctuary
     */
    public void release(String species, int num) {
        if (num <= 0 || num > sanctuary.get(species) || species == null
                || !this.sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        // Get the total amount of animals left in the sanctuary
        int remainAnimals = this.getNum(species) - num;

        // Update the remaining animals in the sanctuary
        this.sanctuary.put(species, remainAnimals);

        // remove the species if no more animals of that species exist
        if (this.getNum(species) == 0) {
            this.sanctuary.remove(species);
        }
    }
}
