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

/*import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class TicketMiner {
    private HashMap<Integer,Sport> sportEvents = new HashMap<Integer,Sport>();
    private HashMap<Integer,String> eventChanges = new HashMap<Integer,String>();
    private int changeLog = 0;
    
    // Reading the csv file and populating the hashmap with the differenet
    // sports events
    public void readCsv(TicketMiner utepEvents){
        String eventListPA0 = "C:/Users/IanFl/Desktop/VSCode/events.csv";
        String line = "";

        // Reading the file with a buffered reader
        try{
            BufferedReader reader = new BufferedReader(new FileReader(eventListPA0));
            line = reader.readLine();
            String[] sportEvent = line.split(",");

            // Until we havent read the whole file, this loop will continue and create an
            // sport object out of the given information from each line
            while ((line = reader.readLine()) != null) {
                sportEvent = line.split(",");
                Sport event = new Sport(Integer.parseInt(sportEvent[0]), sportEvent[2], sportEvent[3], sportEvent[4], 
                                        Double.parseDouble(sportEvent[5]), Double.parseDouble(sportEvent[6]), 
                                        Double.parseDouble(sportEvent[7]), Double.parseDouble(sportEvent[8]));
                
                // If for some reason our csv file had non sport type of events:
                if (!sportEvent[1].equals("Sport")){
                    continue;
                }

                utepEvents.sportEvents.put(event.getId(), event);
            }
            reader.close();
            // Exceptions for our file reading:
            } catch(FileNotFoundException e){
                System.out.println("No File Found");
            } catch(IOException e){
                System.out.println("IO Exception");
        }
    }

    // This is the main menu where our user will be able to either pick to display the
    // information of an event, or change its information:
    public void mainMenu(TicketMiner utepEvents, Scanner userScan){
        Boolean properInp = false;

        // This loop basically just keeps the user on the menu until it decides to exit
        // TicketMiner
        while(properInp == false){
            System.out.println("\nIf you would like to see an Event's information enter '1'");
            System.out.println("If you would to like to change an Event's information enter '2'");
            System.out.println("If you would like to exit enter '3'");
            String //action = "";
            action = userScan.nextLine();  
    
            if (action.equals("1")){
                getEventInfo(utepEvents, userScan);
            } else if (action.equals("2")){
                changeEventInfo(utepEvents, userScan);
            } else if (action.equals("3")){
                System.out.println("\nThanks for using TicketMiner, goodbye!");
                utepEvents.fileLog(utepEvents);
                System.exit(0);
            }else{
                System.out.println("Please enter proper action number");
                mainMenu(utepEvents, userScan);
            }
        }
    }

    // This method just asks the user for the ID of the event they want to the
    // information of, once given a proper ID, it makes a call to the Sport method of
    // printInfo:
    public void getEventInfo(TicketMiner utepEvents, Scanner userScan){
        System.out.println("\nPlease enter event ID to show information");
        String eventId = userScan.nextLine();
        int id = 0;

        try {
            id = Integer.parseInt(eventId);        
        } catch (NumberFormatException e){
            System.out.println("Please enter proper event ID");
            getEventInfo(utepEvents, userScan);
        }

        if (utepEvents.sportEvents.containsKey(id)){
            utepEvents.sportEvents.get(id).printInfo();
        // I tried to make the code fool-proof as to say that if you are to 
        // enter the wrong ID number you'll be able to enter it again:
        } else{
            System.out.println("Please enter proper event ID");
            getEventInfo(utepEvents, userScan);
        }
    }


    // This is our main hub for changing information of the event:
    public void changeEventInfo(TicketMiner utepEvents, Scanner userScan){
        System.out.println("\nPlease enter event ID to change information");
        String eventId = userScan.nextLine();
        int curId = 0;

        // As our ID's in Sport are stored in int format, I have this try and catch's all
        // over the code every time I ask the user to enter ID, as we are turning the user 
        // input from String to int and trying to convert a non number character to int
        // gives us a exception, so this is meant to catch those cases:
        try{
            curId = Integer.parseInt(eventId);
        } catch (NumberFormatException e){
            System.out.println("Please enter proper event ID");
            changeEventInfo(utepEvents, userScan);
        }

        // I've separated the qualities of our events into 4 numbers
        // from which the user may choose to change:
        if (utepEvents.sportEvents.containsKey(curId)){
            System.out.println("Enter the number of the aspect you'd like to change");
            System.out.println("1.- Name  2.- Date");
            System.out.println("3.- Time  4.- Prices");
            String action = userScan.nextLine();
            boolean properInp = false;

            // User stays in this loop until they provide a proper action number
            while (properInp == false){
 
                // Changing Name:
                if (action.equals("1")){
                    System.out.println("Please enter new name for event:");
                    String newName = userScan.nextLine();
                    utepEvents.sportEvents.get(curId).setName(newName);
                    System.out.println("Event name updated succesfully!");
                    
                    // We now store this change:
                    utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + " updated name to " + newName);
                    utepEvents.changeLog++;
                    
                    // And we return to the main menu:
                    utepEvents.mainMenu(utepEvents, userScan);
                
                // The following changes I thought would be best to do in a seperate method:
                // 2.- Changing Date:
                } else if(action.equals("2")){
                    utepEvents.changeDate(utepEvents, userScan, curId);
                    utepEvents.mainMenu(utepEvents, userScan);
                    properInp = true;

                // 3.- Changing Time:    
                }else if (action.equals("3")){
                    utepEvents.changeTime(utepEvents, userScan, curId);
                    utepEvents.mainMenu(utepEvents, userScan);
                    properInp = true;

                // 4.- Changing Price                
                }else if (action.equals("4")){
                    utepEvents.changePrice(utepEvents, userScan, curId);
                    utepEvents.mainMenu(utepEvents, userScan);
                    properInp = true;
                } else{
                    System.out.println("Improper action number");
                    break;
                }
            }
        
        // The user falls on this else if they've given an invalid ID number:
        } else{
            System.out.println("Invalid ID number");
            changeEventInfo(utepEvents, userScan);
        }
    }

    // Changing methods:

    // Changing method for Date
    // I'm assuming the user will input a proper Date:
    public void changeDate(TicketMiner utepEvents, Scanner userScan, int curId){
        // Asking the user for the new date:
        System.out.println("\nPlease enter the date you wish to change your event to");
        System.out.println("Enter new date in the following format: MM/DD/YYYY");
        
        // User enters new date and we update it on the Sport object:
        String newDate = userScan.nextLine();
        utepEvents.sportEvents.get(curId).setDate(newDate);
        System.out.println("Event date updated succesfully!");

        // Storing the change:
        utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + " updated date to " + newDate);
        utepEvents.changeLog++;

        
    }

    // Changing method for Time
    // I'm assuming the user will input a proper Time:
    public void changeTime(TicketMiner utepEvents, Scanner userScan, int curId){
        //Asking the user for the new time:
        System.out.println("\nPlease enter the time you wish to change your event to");
        System.out.println("Enter new time in 12 hour format as follows: 00:00 AM/ PM");

        //User enters new date and we update it on the Sport object:
        String newTime = userScan.nextLine();
        utepEvents.sportEvents.get(curId).setTime(newTime);
        System.out.println("Event time updated succesfully!");

        //Storing the change:
        utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + " updated time to " + newTime);
        utepEvents.changeLog++;

    }

    // Changing method for Price:
    public void changePrice(TicketMiner utepEvents, Scanner userScan, int curId){
        boolean properInp = false;
        String newPrice = "";
        double newPriceDbl = 0;
        System.out.println("\nPlease enter the price you wish to change the VIP Price to");
        System.out.println("If you wish for it to remain as is enter: '-1'");

        while (properInp == false){
            newPrice = userScan.nextLine();
            try{
                newPriceDbl = Double.parseDouble(newPrice);
            } catch(NumberFormatException e){
                System.out.println("Please enter a proper price");
                continue;
            }

            if (newPriceDbl == -1){
                break;
            }
            utepEvents.sportEvents.get(curId).setVipPrice(newPriceDbl);
            System.out.println("Event VIP Price updated succesfully!");

            //Storing the change:
            utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + "updated VIP Price to " + newPriceDbl);
            utepEvents.changeLog++;
            break;
        }

        System.out.println("\nPlease enter the price you wish to change the Gold Price to");
        System.out.println("If you wish for it to remain as is enter: '-1'");

        while (properInp == false){
            newPrice = userScan.nextLine();
            try{
                newPriceDbl = Double.parseDouble(newPrice);
            } catch(NumberFormatException e){
                System.out.println("Please enter a proper price");
                continue;
            }

            if (newPriceDbl == -1){
                break;
            }
            utepEvents.sportEvents.get(curId).setGoldPrice(newPriceDbl);
            System.out.println("Event VIP Price updated succesfully!");

            //Storing the change:
            utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + "updated Gold Price to " + newPriceDbl);
            utepEvents.changeLog++;
            break;
        }

        System.out.println("\nPlease enter the price you wish to change the Bronze Price to");
        System.out.println("If you wish for it to remain as is enter: '-1'");
        
        while (properInp == false){
            newPrice = userScan.nextLine();
            try{
                newPriceDbl = Double.parseDouble(newPrice);
            } catch(NumberFormatException e){
                System.out.println("Please enter a proper price");
                continue;
            }

            if (newPriceDbl == -1){
                break;
            }
            utepEvents.sportEvents.get(curId).setBronzePrice(newPriceDbl);
            System.out.println("Event VIP Price updated succesfully!");

            //Storing the change:
            utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + "updated Bronze Price to " + newPriceDbl);
            utepEvents.changeLog++;
            break;
        }

        System.out.println("\nPlease enter the price you wish to change the General Admission Price to");
        System.out.println("If you wish for it to remain as is enter: '-1'");
        
        while (properInp == false){
            newPrice = userScan.nextLine();
            try{
                newPriceDbl = Double.parseDouble(newPrice);
            } catch(NumberFormatException e){
                System.out.println("Please enter a proper price");
                continue;
            }

            if (newPriceDbl == -1){
                break;
            }
            utepEvents.sportEvents.get(curId).setGaPrice(newPriceDbl);
            System.out.println("Event VIP Price updated succesfully!");

            //Storing the change:
            utepEvents.eventChanges.put(utepEvents.changeLog, "\nEvent ID " + curId + "updated General Admission to " + newPriceDbl);
            utepEvents.changeLog++;
            break;
        }
    }

    // This method will create the log where we will be able to see the changed 
    // the user made to the events:
    public void fileLog(TicketMiner utepEvents){
        int i = 0;
        try{
            FileWriter log = new FileWriter("ticketMinerChangeLog.txt");
            while (i != utepEvents.changeLog){
                log.write(utepEvents.eventChanges.get(i));
                i++;
            }
            log.close();
        } catch (IOException e){
            System.out.println("The writing of the change log failed");
        }
    }

    // We call the method that reads the csv file and populates our HashMap here
    // We also make a call to the main menu which is where our user can print and 
    // change an event's info
    public static void main(String [] args) {
        TicketMiner utepEvents = new TicketMiner();
        utepEvents.readCsv(utepEvents);
        Scanner userScan = new Scanner(System.in);

        // Starting the user terminal:
        System.out.println("\nWelcome to TicketMiner ");
        utepEvents.mainMenu(utepEvents, userScan);
    }
}

*/
