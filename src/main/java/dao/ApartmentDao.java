package dao;

import entity.Apartment;
import entity.ApartmentResearch;
import entity.Room;
import exception.EntityNotExistException;
import utils.Date;

import java.sql.*;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class ApartmentDao {

    private DataSource ds = new DataSource();

    public Apartment create(int locals, boolean furnished , int bathroomNumber , int bedsNumber) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Apartment a = null;
        try {
            conn = this.ds.getConnection();



            stmt = conn.prepareStatement("insert into \"public\".\"ApartmentDao\" (locals , furnished , bathroomNumber , bedsNumber) " +
                    "values (?,?,?,?);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, locals);
            stmt.setBoolean(2, furnished);
            stmt.setInt(3 , bathroomNumber);
            stmt.setInt(4,bedsNumber);
            stmt.executeUpdate();



            a = new Apartment();

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

    public Apartment findByID(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Apartment apartment = null;

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"Apartment\" where \"ID\" = ?;",
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

        return apartment;
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

    /**
     * Edit by EC. Find all apartment with specific condition
     * @param Condition
     * @return
     */
    public List<Apartment> findByCondition(ApartmentResearch apartmentResearch) {
        PreparedStatement stmt = null;
        Connection conn = null;
        List<Apartment> apartments = null;
        int count = 6;

        String query = "select * from \"public\".\"Apartment\" where \"city\" = ? and \"price\" >= ? " +
                "and \"price\" <= ? and \"size\" >= ? and \"sorting\" = ?";
        if (apartmentResearch.getLocalsMin() != null)
            query += " and \"locals\" >= ?";
        if (apartmentResearch.getLocalsMax() != null)
            query += " and \"locals\" <= ?";
        if (apartmentResearch.getFurnished() != null)
            query += " and \"furnished\" = ?";
        if (apartmentResearch.getBathroomNumberMin() != null)
            query += " and \"bathroomNumber\" >= ?";
        if (apartmentResearch.getBedsNumberMin() != null)
            query += " and \"bedsNumber\" >= ?";
        if (apartmentResearch.getBedsNumberMax() != null)
            query += " and \"bedsNumber\" <= ?";
        query += ";";

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, apartmentResearch.getCity());
            stmt.setDouble(2, apartmentResearch.getPriceMin());
            stmt.setDouble(3, apartmentResearch.getPriceMax());
            stmt.setDouble(4, apartmentResearch.getSize());
            stmt.setString(5, apartmentResearch.getSorting().toString());
            if (apartmentResearch.getLocalsMin() != null) {
                stmt.setInt(count, apartmentResearch.getLocalsMin());
                count++;
            }
            if (apartmentResearch.getLocalsMax() != null) {
                stmt.setInt(count, apartmentResearch.getLocalsMax());
                count++;
            }
            if (apartmentResearch.getFurnished() != null) {
                stmt.setBoolean(count, apartmentResearch.getFurnished());
                count++;
            }
            if (apartmentResearch.getBathroomNumberMin() != null) {
                stmt.setInt(count, apartmentResearch.getBathroomNumberMin());
                count++;
            }
            if (apartmentResearch.getBedsNumberMin() != null) {
                stmt.setInt(count, apartmentResearch.getBedsNumberMin());
                count++;
            }
            if (apartmentResearch.getBedsNumberMax() != null) {
                stmt.setInt(count, apartmentResearch.getBedsNumberMax());
                count ++;
            }

            ResultSet result = stmt.executeQuery();

            if (!result.first()) // rs empty
                return null;

            result.first();

//            aggiungere factory method per creare un'istanza di appartamento e aggiungerlo alla lista
//            result.getInt("ID");
//            result.getString("city");
//            result.getString("address");
//            result.getDouble("price");
//            result.getString("description");
//            result.getDouble("size");
//            result.getBoolean("available");
//            result.getInt("locals");
//            result.getBoolean("furnished");
//            result.getInt("bathroomNumbers");
//            result.getInt("bedsNumber");

            while (result.next()) {
                // aggiungere factory method per creare un'istanza di appartamento e aggiungerlo alla lista
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

        return apartments;
    }

}
