//
// University of Texas at El Paso - Department of Computer Science
// Course: Advanced Object Oriented Programming - CS 3331
// Instructor: Daniel Mejia                     Period: Fall 2022
// Author: Ian Rogelio Flores Maynez            Assingment: Programming Assignment 0
// Date: 09/04/2022                             Student ID: 80718488
//
//
// This work was done individually and completely on my own. I did not share, reproduce, or alter
// any part of this assignment for any purpose. I did not share code, upload this assignment online 
// in any form, or view/received/modified code written from anyone else. All deliverables were 
// produced entirely on my own. This assignment is part of an academic course at The University 
// of Texas at El Paso and a grade will be assigned for the work I produced.
//

public class Sport {
    private int id;
    private String name;
    private String date;
    private String time;
    private double vipPrice;
    private double goldPrice;
    private double bronzePrice;
    private double gaPrice;

    // Constructors
    public Sport(){
    }
    
    // Basic construstuctor for our event:
    public Sport(int idIn, String nameIn, String dateIn, String timeIn, double vipPriceIn, double goldPriceIn, double bronzePriceIn, double gaPriceIn){
        this.id = idIn;
        this.name = nameIn;
        this.date = dateIn;
        this.time = timeIn;
        this.vipPrice = vipPriceIn;
        this.goldPrice = goldPriceIn;
        this.bronzePrice = bronzePriceIn;
        this.gaPrice = gaPriceIn;
    }
    
    // Prints all the info for the event from inside the object:
    public void printInfo(){
        System.out.println("You've requested the information for the following event: ");
        System.out.println("Name: " + name + " Event ID: " + id);
        System.out.println("Date: " + date + " Time: " + time);
        System.out.println("These are the prices for the event: ");
        System.out.println("VIP: " + vipPrice + " Gold: " + goldPrice);
        System.out.println("Bronze: " + bronzePrice + " General Admission: " + gaPrice);
    }

    // Getters: 

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }
    
    public double getVipPrice(){
        return this.vipPrice;
    }
    
    public double getGoldPrice(){
        return this.goldPrice;
    }

    public double getBronzePrice(){
        return this.bronzePrice;
    }
    
    public double getGaPrice(){
        return this.gaPrice;
    }

    // Setters:

    public void setId(int IdIn){
        this.id = IdIn;
    }

    public void setName(String nameIn){
        this.name = nameIn;
    }

    public void setDate(String dateIn){
        this.date = dateIn;
    }
    
    public void setTime(String timeIn){
        this.time = timeIn;
    }

    public void setVipPrice(double vipPriceIn){
        this.vipPrice = vipPriceIn;
    }

    public void setGoldPrice(double goldPriceIn){
        this.goldPrice = goldPriceIn;
    }

    public void setBronzePrice(double bronzePriceIn){
        this.bronzePrice = bronzePriceIn;
    }
    
    public void setGaPrice(double gaPriceIn){
        this.gaPrice = gaPriceIn;
    }
}
