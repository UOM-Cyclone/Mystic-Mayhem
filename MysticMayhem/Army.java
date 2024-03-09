package MysticMayhem;

import MysticMayhem.Characters.*;
import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Equipments.Equipment;

import java.lang.Character;

public class Army {
    Archer archer;
    Knight knight;
    Mage mage;
    Healer healer;
    MythicalCreature mythicalCreature;

    public void addArcher(Archer archer){
        this.archer=archer;
    }
    public void addKnight(Knight knight){
        this.knight=knight;
    }
    public void addMage(Mage mage){
        this.mage=mage;
    }
    public void addHealer(Healer healer){
        this.healer=healer;
    }
    public void addMythicalCreature(MythicalCreature mythicalCreature){
        this.mythicalCreature=mythicalCreature;
    }


    //adding armours to the army
    public void archerArmourAdd(Armour equipment){
        this.archer.addArmour(equipment);
    }
    public void knightArmourAdd(Armour equipment){
        this.knight.addArmour(equipment);
    }
    public void mageArmourAdd(Armour equipment){
        this.mage.addArmour(equipment);
    }
    public void healerArmourAdd(Armour equipment){
        this.healer.addArmour(equipment);
    }
    public void mythicalCreatureArmourAdd(Armour equipment){
        this.mythicalCreature.addArmour(equipment);
    }

    //adding artefacts to the army
    public void archerArtefactsAdd(Artefacts equipment){
        this.archer.addArtefacts(equipment);
    }
    public void knightArtefactsAdd(Artefacts equipment){
        this.knight.addArtefacts(equipment);
    }
    public void mageArtefactsAdd(Artefacts equipment){
        this.mage.addArtefacts(equipment);
    }
    public void healerArtefactsAdd(Artefacts equipment){
        this.healer.addArtefacts(equipment);
    }
    public void mythicalCreatureArtefactsAdd(Artefacts equipment){
        this.mythicalCreature.addArtefacts(equipment);
    }

    //removing armours to the army
    public void archerArmourRemove(){
        this.archer.removeArmour();
    }
    public void knightArmourRemove(){
        this.knight.removeArmour();
    }
    public void mageArmourRemove(){
        this.mage.removeArmour();
    }
    public void healerArmourRemove(){
        this.healer.removeArmour();
    }
    public void mythicalCreatureArmourRemove(){
        this.mythicalCreature.removeArmour();
    }

    //removing artefacts to the army
    public void archerArtefactsRemove(){
        this.archer.removeArmour();
    }
    public void knightArtefactsRemove(){
        this.knight.removeArmour();
    }
    public void mageArtefactsRemove(){
        this.mage.removeArmour();
    }
    public void healerArtefactsRemove(){
        this.healer.removeArmour();
    }
    public void mythicalCreatureArtefactsRemove(){
        this.mythicalCreature.removeArmour();
    }
}
