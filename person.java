
public class person {
    // protected fields to allow subclasses to directly access these values
    protected String id;  
    protected String name; 

    // initialize person
    public person(String id, String name) {
        this.id = id;   // Assign the ID
        this.name = name; // Assign the name
    }

    // getter method to get person's ID
    public String getId() { 
        return id; 
    }

    // getter method to get person's name
    public String getName() { 
        return name; 
    }
}
