public class Person {
    private String name;
    private String surname;
    private String email;

    //creating a constructor using parameters
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    //create Getters
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getEmail(){
        return email;
    }
    //Create Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void personInfo() {
        System.out.println("Name: "+ name + "\nSurname: " + surname + "\nEmail: " + email);
    }
}
