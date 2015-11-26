package Cards;

public class RoleCard{

    String name;
    int roleID;


    /**
     * Sets the argument to class variable
     * @param name Argument from initiation
     * @param roleID Used to identify role on client
     */
    public RoleCard(String name,int roleID) {

        this.name = name;
        this.roleID = roleID;
    }

    /**
     * Returns the name of the role
     * @return a string
     */
    public String getName() {
        return name;
    }

    public int getRoleID(){return roleID;}
}
