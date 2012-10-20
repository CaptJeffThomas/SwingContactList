// Jeff Thomas
// CMPUT 305 Assn 5 - Swing and You!

package cmput305assn5;

public class Contact {
    
    
    // the fine properties that our contacts can possess
    private String fName, lName, email, homePhone, workPhone;
    
    public Contact(String fname, String lname, String email, String homephone, String workphone){
        this.fName = fname;
        this.lName = lname;
        this.email = email;
        this.homePhone = homephone;
        this.workPhone = workphone;
    }
    
    public String getFirstName(){
        return this.fName;
    }
    
    public String getLastName(){
        return this.lName;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getHomePhone(){
        return this.homePhone;
    }
    
    public String getWorkPhone(){
        return this.workPhone;
    }
    
}
