package dao;

import entity.Apartment;
import entity.Room;
import exception.EntityNotExistException;

import java.sql.*;

public class ApartmentDao {

    private DataSource ds = new DataSource();

    public Apartment create(int locals, boolean furnished , int bathroomNumber , int bedsNumber) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Apartment a = null;
        try {
            conn = this.ds.getConnection();



            stmt = conn.prepareStatement("insert into \"public\".\"ApartmentDao\" (locals , furnished , bathroomNumber , bedsNumber) " +
                    "values (?,?,?);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, locals);
            stmt.setBoolean(2, furnished);
            stmt.setInt(3 , bathroomNumber);
            stmt.setInt(4,bedsNumber);
            stmt.executeUpdate();



            a = new Apartment(locals , furnished , bathroomNumber , bedsNumber);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return a;
    }

    public ApartmentDao findByID(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ApartmentDao apartmentDao = null;

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"ApartmentDao\" where \"ID\" = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, ID);
            ResultSet result = stmt.executeQuery();

            if (!result.first()) // rs empty
                return null;

            result.first();

                    result.getInt("ID");
                    result.getString("city");
                    result.getString("address");
                    result.getDouble("price");
                    result.getString("description");
                    result.getDouble("size");
                    result.getBoolean("available");
                    result.getInt("locals");
                    result.getBoolean("furnished");
                    result.getInt("bathroomNumbers");
                    result.getInt("bedsNumber");

            result.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return apartmentDao;
    }

    public Boolean delete(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = this.ds.getConnection();

            if (findByID(ID) == null)
                throw new EntityNotExistException();

            stmt = conn.prepareStatement("delete from \"public\".\"ApartmentDao\" where \"ID\" = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, ID);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            return Boolean.TRUE;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Edit by EC. Return the max ID for this entity. Use for initialize the ID generator.
     * @return
     */
    public Integer getMaxID() {
        PreparedStatement stmt = null;
        Connection conn = null;
        Integer maxID = null;

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select MAX(\"ID\") from \"public\".\"Apartment\";",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = stmt.executeQuery();

            if (result.next() && maxID == null) {
                maxID = result.getInt(1);
            }
            result.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return maxID;
    }

}
