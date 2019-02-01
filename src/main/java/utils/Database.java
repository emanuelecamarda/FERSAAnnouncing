/**
 * Edit by EC.
 */

package utils;

public class Database {

    private static Database instance;
    private static Integer countID;

    private Database(){}

    /**
     * Singleton pattern
     * @return
     */
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
            countID = 0;
        }
        return instance;
    }

    /**
     * Get a new ID for database
     * @return
     */
    public Integer getID() {
        increaseID();
        return countID;
    }

    public void increaseID() {
        countID++;
    }
}
