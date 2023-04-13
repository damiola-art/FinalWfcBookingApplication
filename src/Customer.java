public class Customer {
    private String firstname;

    private String lastName;

    private String email;


    public Customer(String firstname, String lastName, String email){
        this.firstname =firstname;
        this.lastName = lastName;
        this.email =email;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}





