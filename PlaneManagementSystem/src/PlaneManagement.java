import org.w3c.dom.ls.LSOutput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
    public static char[] rowA = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    public static char[] rowB = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    public static char[] rowC = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    public static char[] rowD = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',};

    public static Ticket[] ticketsArray = new Ticket[60];


    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");

        boolean options = true;

        while (options) {
            int option = 0;
            System.out.println(""" 
                    ********************************************************
                    *                   MENU OPTIONS                       *
                    ********************************************************
                         1) Buy a seat
                         2) Cancel a seat
                         3) Find first available seat
                         4) Show seating plan
                         5) Print tickets information and total sales
                         6) Search ticket
                         0) Quit
                    ********************************************************
                                    
                    """);
            System.out.println("Please select an option:");
            try {
                Scanner scanner = new Scanner(System.in);
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        Show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        options = false;

                }

            } catch (Exception e) {
                System.out.println("Enter a valid number");
            }
        }
    }
    public static void buy_seat() {
        Scanner input = new Scanner(System.in);
        boolean valid = true;

        while (valid) {
            try {
                System.out.println("Enter row letter :");
                char rowLetter = input.next().toUpperCase().charAt(0);
                if (rowLetter < 'A' || rowLetter > 'D') {                           //validate row letter
                    System.out.println("Invalid row letter. Please enter a valid letter.");
                    continue;
                }


                char[] rowArray = null;
                switch (rowLetter) {
                    case 'A':
                        rowArray = rowA;
                        break;
                    case 'B':
                        rowArray = rowB;
                        break;
                    case 'C':
                        rowArray = rowC;
                        break;
                    case 'D':
                        rowArray = rowD;
                        break;
                }
                System.out.println("Enter seat number: ");
                int seatNum = input.nextInt();
                int seatIndex = seatNum - 1;
                if (seatIndex < rowArray.length) {                  //validate seat number

                    if (rowArray[seatIndex] == 'O') {
                        System.out.println("Seat is available");
                        System.out.println("Enter your name: ");
                        String name = input.next();

                        System.out.println("Enter your surname: ");
                        String surname = input.next();

                        //Validating email
                        String email = "";

                        while (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$ ")) {
                            System.out.println("Enter your Email: ");
                            email = input.next();
                            break;
                        }

                        double price = 0;
                        //creating an object using person class
                        Person person = new Person(name, surname, email);
                        //creating an object using Ticket class
                        Ticket ticket = new Ticket(rowLetter, seatNum, price, person);

                        // Set ticket price based on seat number
                        if (seatNum <= 5) {
                            ticket.setPrice(200);
                        } else if (seatNum >= 6 && seatNum <= 9) {
                            ticket.setPrice(150);
                        } else {
                            ticket.setPrice(180);
                        }

                        // Add values to ticketsArray array
                        for (int sindex = 0; sindex < ticketsArray.length; sindex++) {
                            if (ticketsArray[sindex] == null) {
                                ticketsArray[sindex] = ticket;
                                rowArray[seatIndex] = 'X';                  // Mark the seat as booked
                                System.out.println("Seat has been successfully booked.");
                                ticket.save();                           //Calling the method from the Ticket class
                                return;
                            }
                        }
                        System.out.println("Failed to book seat. Sold out.");
                    } else {
                        System.out.println("Seat is already booked. Please choose another seat.");
                    }
                } else {
                    System.out.println("Invalid seat number.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number.");
                input.next();
            }
        }
    }
    public static void cancel_seat() {
        Scanner input = new Scanner(System.in);
        boolean valid = true;

        while (valid) {
            try {
                System.out.println("Enter row letter: ");
                char rowLetter = input.next().toUpperCase().charAt(0);

                if (rowLetter < 'A' || rowLetter > 'D') {
                    System.out.println("Invalid row letter. Please enter a valid letter.");
                    continue;
                }


                char[] rowArray = null;
                switch (rowLetter) {
                    case 'A':
                        rowArray = rowA;
                        break;
                    case 'B':
                        rowArray = rowB;
                        break;
                    case 'C':
                        rowArray = rowC;
                        break;
                    case 'D':
                        rowArray = rowD;
                        break;
                }
                System.out.println("Enter seat number: ");
                int seatNum = input.nextInt();
                int seatIndex = seatNum - 1;

                if (seatIndex < rowArray.length) {
                    if (rowArray[seatIndex] == 'X') {
                        rowArray[seatIndex] = 'O';
                        for (int sindex = 0; sindex < ticketsArray.length; sindex++) {    //removing values from the ticketsArray
                            Ticket ticket = ticketsArray[sindex];
                            if (ticket != null && ticket.getRowLetter() == rowLetter && ticket.getSeatNum() == seatNum) {
                                ticketsArray[sindex] = null;
                                System.out.println("Seat has been successfully cancelled.");
                                return;
                            }
                        }
                    } else {
                        System.out.println("Seat is not booked.");
                    }
                    break;
                } else {
                    System.out.println("Invalid seat number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number.");
                input.next(); // Consume the invalid input
            }
        }
    }

    public static void find_first_available() {

        int condition = 0;     //a variable use when checking empty seat

        for (int number = 0; number <  rowA.length; number++) {
            if (rowA[number] == 'O') {
                int seatNumber = number + 1;
                System.out.println("first available seat is in row A" + seatNumber);
                condition = 1;
                break;
            }
        }

        if (condition == 0) {

            for (int number = 0; number < rowB.length; number++) {
                if (rowB[number] == 'O') {
                    int seatNumber = number + 1;
                    System.out.println("first available seat is in row B" + seatNumber);
                    condition = 1;
                    break;
                }
            }
        }
        if (condition == 0) {


            for (int number = 0; number < rowC.length; number++) {
                if (rowC[number] == 'O') {
                    int seatNumber = number + 1;
                    System.out.println("first available seat is in row C" + seatNumber);
                    condition = 1;
                    break;
                }
            }
        }
        if (condition == 0) {
            for (int number = 0; number < rowD.length; number++) {
                if (rowD[number] == 'O') {
                    int seatNumber = number + 1;
                    System.out.println("first available seat is in row D" + seatNumber);
                    break;
                }
            }
        }


    }
    public static void Show_seating_plan() {
        System.out.println("Seating Plan");
        System.out.println(rowA);
        System.out.println(rowB);
        System.out.println(rowC);
        System.out.println(rowD);

    }
    public static void print_tickets_info(){
        int totAmount =0;
        System.out.println("Tickets Information");
        for(Ticket ticket: ticketsArray){
            if(ticket != null){                     //to check whether ticket is available
                ticket.ticketInfo();               //calling ticketInfo method
                totAmount += ticket.getPrice();
            }
        }
        System.out.println("Total Amount : € " + totAmount);
    }

    public static void search_ticket() {
        Scanner input = new Scanner(System.in);
        boolean valid = true;

        while (valid) {
            try {
                System.out.println("Enter the row letter that you want to booked");
                char rowLetter = input.next().toUpperCase().charAt(0);

                if (rowLetter < 'A' || rowLetter > 'D') {
                    System.out.println("Invalid row letter. Please enter a valid letter ");
                    continue;
                }
                valid=false;

                System.out.println("Enter seat number:");
                int seatNum = input.nextInt();
                int seatIndex = seatNum - 1;

                char[] rowArray;
                switch (rowLetter) {
                    case 'A':
                        rowArray = rowA;
                        break;
                    case 'B':
                        rowArray = rowB;
                        break;
                    case 'C':
                        rowArray = rowC;
                        break;
                    case 'D':
                        rowArray = rowD;
                        break;
                    default:
                        System.out.println("Invalid row letter. Please enter a valid letter ");
                        return;
                }

                if (seatIndex < rowArray.length) {
                    if (rowArray[seatIndex] == 'X') {
                        //  seat is sold
                        for (Ticket ticket : ticketsArray) {
                            if (ticket != null && ticket.getRowLetter() == rowLetter && ticket.getSeatNum() == seatNum) {
                                // Print Ticket and Person information
                                System.out.println("Ticket details for " + rowLetter + seatNum);
                                System.out.println();
                                ticket.ticketInfo();
                                return;
                            }
                        }
                    } else {
                        // Seat is available
                        System.out.println("This seat is available.");
                    }
                } else {
                    System.out.println("Invalid seat number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid row letter and seat number.");
            }
        }
    }
}

