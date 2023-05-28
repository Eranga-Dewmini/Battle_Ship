import jdk.nashorn.internal.ir.ContinueNode;
import java.util.Scanner;

public class Battle_Ship {
    static char[][] OceanMap = new char[10][10];             //10*10= Array - used to crate the map
    static final char NULL_CHAR = '\u0000';                   //Maintain the spaces
    static int PlayerShipCount = 5;                           //Define Player's ship count
    static int ComputerShipCount = 5;                         //Define Computer ship count
    static int x;
    static int y;
    static int z;
    static int m;                                              //Player and Computer co-ordinations
    static int PlayerleftShips = PlayerShipCount;              //Player's remain ships
    static int ComputerleftShips = ComputerShipCount;         //Computer's remaining ships
    static int attackpShips = 0;                               //Number of ships attacked
    static int attackcShips = 0;                               //Number of ships attacked
    static int round = 1;

    public static void main(String[] args) {
        System.out.println("**** Welcome to Battleship Game ****");
        System.out.println("...Right Now The Sea Is Empty...");

        printOceanMap();                                      //Display the empty map,step 1
        deployPlayerShip();                                   //Deploy the player ships,step 2
        OceanMap[x][y] = '1';
        printOceanMap();                                     //Display the map with player ships,locations
        deployComputerShip();                                //Deploy the Computer ships,step 3
        OceanMap[x][y] = '2';
        printOceanMap();                                     //Display the map with Computer ship,locations
        startBattle();                                       //Start Battle
        System.out.println(" ");

    if(ComputerleftShips == 0){
        System.out.println("Hooray!! You won the Battle");
    }
    else if (PlayerleftShips == 0)

    {
        System.out.println("You LOST...!! Computer Won the Battle....!!");
    }
    }
    private static void printOceanMap() {
        printRow();                                           //Display the upper 0-9

        for (int row = 0; row < OceanMap.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < OceanMap[row].length; col++) {
                if (OceanMap[row][col] == NULL_CHAR) {        //Check the null character of the map
                    System.out.print(" ");
                } else if (OceanMap[row][col] == '1') {       //Assign player ships as @
                    System.out.print("@");
                } else if (OceanMap[row][col] == '2') {      //Assign Computer ships as 2 and inivisible
                    System.out.print(" ");
                } else if (OceanMap[row][col] == '3') {      //Assign Computer ships as !
                    System.out.print("!");
                } else if (OceanMap[row][col] == '4') {      //Assign Computer ships as -
                    System.out.print("-");
                } else if (OceanMap[row][col] == '5') {      //Assign Computer ships as X
                    System.out.print("X");
                } else {
                    System.out.print(OceanMap[row][col]);  //Maintaining the row and columns & Printing
                }
            }
            System.out.println("|" + row);
        }
        printRow();                                       //Display the below 0-9 row
    }
    private static void printRow(){
        System.out.print("  ");
        for(int col=0; col<OceanMap[0].length; col++) {
            System.out.print(col);
        }
        System.out.println("  ");
        }

    private static void deployPlayerShip() {
        for (int i = 1; i <= PlayerShipCount; i++) {           //Iterate 5 times to take the inputs
            Scanner xCoordinates = new Scanner(System.in);     //Create scanner object to take user inputs
            System.out.print("Enter X coordinate for your " + i + " ship:- ");
            x = xCoordinates.nextInt();                        //Assign inputs to X co-ordinate
            System.out.print("Enter Y coordinate for your " + i + " ship:- ");
            y = xCoordinates.nextInt();                        //Assign inputs to Y co-ordinate
            if ((x > (OceanMap.length - 1)) || (x < 0) || (y > (OceanMap.length - 1)) || (y < 0)) { //validates user inputs with 0-9
                System.out.println("Out Of Map.");
                i--;                                           //Decrement the iterator, taking inputs again correctly
            } else {
                if (OceanMap[x][y] == NULL_CHAR) {             //Check whether the entered co-ordinates are free or not
                    System.out.println("Entered Location is Free.");
                    OceanMap[x][y] = '1';                      //Display the user co-ordinations in the map
                } else {
                    System.out.println("Entered Location is not Free.");
                    i--;                                       //Decrement the iterator, taking inputs again correctly
                }
            }
        }
    }

    private static void deployComputerShip() {
        System.out.println("Computer is deploying Ships...");
        for (int i = 1; i <= ComputerShipCount; i++) {
            x = (int) (Math.random() * 10);      //Generate random numbers within 0-9
            y = (int) (Math.random() * 10);      //Generate random numbers within 0-9
            System.out.print("Ship " + i + "deployed");
            if ((x > (OceanMap.length - 1)) || (x < 0) || (y > (OceanMap.length - 1)) || (y < 0)) { //validate user inputs with 0-9
                System.out.println("Out of Map");
                i--;                    //Decrement the integrator,taking inputs again correctly
            } else {
                if (OceanMap[x][y] == NULL_CHAR) {        //Check whether the entered co-ordinates are free or not
                    System.out.println("Location is Free...");
                    OceanMap[x][y] = '2';              //Display the computer given co-ordinations in the map
                } else {
                    System.out.println("Location is not Free...");
                    i--;                              //Decrement the iterator,taking again correctly
                }
            }
        }
    }
    private static void startBattle() {
        System.out.println(" ");
        System.out.println("*~~ START BATTLE ~~*");
        while (PlayerleftShips > 0 && ComputerleftShips > 0) {
            System.out.println(" ");

            System.out.println("--YOUR TURN--");
            Scanner xCoordinates = new Scanner(System.in); //Create scanner object to take user inputs
            System.out.print("Enter X coordinate:- ");
            x = xCoordinates.nextInt();                     //Assign inputs to X co-ordinate
            System.out.print("Enter Y coordinate:- ");
            y = xCoordinates.nextInt();                    //Assign inputs to Y co-ordinate
            if ((x > (OceanMap.length - 1)) || (x < 0) || (y > (OceanMap.length - 1)) || (y < 0)) { //Validate user inputs with 0-9
                System.out.println("Out of Map.");
                continue; //Continue the while loop form the beginning again
            } else {
                if (OceanMap[x][y] == '1') {              //Check the owner ship of the ship in the guessed co-ordinates
                    OceanMap[x][y] = '5';                 //Update the new ship status of the map
                    System.out.println("Oh No, You Sunk Your Own Ship.");
                    PlayerleftShips = PlayerleftShips - 1;          //Decrement the player's left ship count
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count: " + ComputerleftShips);
                } else if (OceanMap[x][y] == '2') {       //Check the owner ship of the ship in the guessed co-ordinates
                    OceanMap[x][y] = '3'; //Update the new ship status of the map
                    System.out.println("Boom...! You sunk the Ship!...");
                    ComputerleftShips = ComputerleftShips - 1;          //Decrement the computer's left ship count
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count: " + ComputerleftShips);
                } else if (OceanMap[x][y] == NULL_CHAR) { //Check whether the co-ordinates are empty or not
                    OceanMap[x][y] = '4';                 //Update the new ship status of the map
                    System.out.println("Sorry, You Missed...");
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count: " + ComputerleftShips);
                } else {
                    System.out.println("Already Attacked, Choose Another One.");
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count: " + ComputerleftShips);
                    continue;
                }
            }
            System.out.println(" ");
            System.out.println("--COMPUTER'S TURN--");

            z = (int) (Math.random() * 10); //Generate random numbers within 0-9
            m = (int) (Math.random() * 10); //Generate random numbers within 0-9
            if ((z > (OceanMap.length - 1)) || (z < 0) || (m > (OceanMap.length - 1)) || (m < 0)) { // Validate user inputs with 0 - 9
                System.out.println("Out of Map.");
                continue;                      //Continue the while loop form the beginning again
            } else {
                if (OceanMap[z][m] == '2') {    //Check the owner ship of the ship in the guessed co-ordinates
                    OceanMap[z][m] = '3';       //Update the new ship status of the map
                    System.out.println("The Computer Sunk One of its Own Ships.");
                    ComputerleftShips = ComputerleftShips - 1; //Decrement the computer's left ship count
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count: " + ComputerleftShips);
                } else if (OceanMap[z][m] == '1') { //Check the owner ship of the ship in the guessed co-ordinates
                    OceanMap [z][m] = '2';          //Update the new ship status of the map
                    System.out.println("The Computer Sunk One of Your Ships!!!");
                    PlayerleftShips = PlayerleftShips - 1;   //Decrement the player's left ship count
                    ComputerleftShips = ComputerleftShips + 1;   //Increment the computer's left ship count
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count:- " + ComputerleftShips);
                } else if (OceanMap[z][m] == NULL_CHAR) { //Check whether the co-ordinates are empty or not
                    OceanMap[z][m] = '4';                //Update the new ship status of the map
                    System.out.println("Sorry, Computer Missed...");
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count:- " + ComputerleftShips);
                } else {
                    System.out.println("Already Attacked, Choose Another One.");
                    System.out.println("Your Ship Count:- " + PlayerleftShips + " | Computer's Ship Count: " + ComputerleftShips);
                    continue;
                }
                System.out.println(" ");
                System.out.println("The Ocean Map after the Round Number:- " + round);
                round++;
                printOceanMap();
            }
        }
    }
}











