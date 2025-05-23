import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    private char rowLetter;
    private int seatNum;
    private double price;
    private Person person;

    //creating a Constructor using parameters
    public Ticket(char rowLetter, int seatNum, double price, Person person){
        this.rowLetter = rowLetter;
        this.seatNum = seatNum;
        this.price = price;
        this.person = person;

    }
    //Getters
    public char getRowLetter() {
        return rowLetter;
    }

    public int getSeatNum() {
        return seatNum;
    }
    public double getPrice() {
        return price;
    }
    public Person getPerson() {
        return person;
    }

    //setters
    public void setRowLetter(char rowLetter) {
        this.rowLetter = rowLetter;
    }

    public void setSeat(int seatNum) {
        this.seatNum = seatNum;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    public void ticketInfo(){                             //using to print ticket
        System.out.println("rowLetter : " + rowLetter);
        System.out.println("seat : " + seatNum);
        System.out.println("price :€ " + price);


        if (person != null){
            System.out.println("Person Information");
            person.personInfo();
        }else{
            System.out.println("Person Information not available");
        }

    }

    public void save() {                                   //write in the tet file
        String TicketInformation = rowLetter + seatNum + ".txt";
        try {
            FileWriter myWriter = new FileWriter(TicketInformation);
            myWriter.write("Ticket Information");
            myWriter.write("\nRow: " + rowLetter);
            myWriter.write("\nSeat Number: " + seatNum);
            myWriter.write("\nThe Price: " + price);
            myWriter.write("\n");
            myWriter.write("\nPerson Information");
            myWriter.write("\nName: " + person.getName());
            myWriter.write("\nSurname: " + person.getSurname());
            myWriter.write("\nEmail: " + person.getEmail());
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error while writing in a file");

        }
    }


}
