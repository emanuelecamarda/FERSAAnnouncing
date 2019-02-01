/**
 * Edit by EC.
 */

package dao;

import entity.ApartmentResearch;
import exception.EntityNotExistException;
import factory.ResearchFactory;
import utils.Database;
import utils.Date;

import java.sql.*;

public class ApartmentResearchDao {

    private DataSource ds = new DataSource();
    private UserDao userDao = new UserDao();

    /**
     * create method. Do by EC
     * @param apartmentResearch
     * @return
     */
    public ApartmentResearch create(ApartmentResearch apartmentResearch) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Database database = Database.getInstance();
        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("insert into \"public\".\"ApartmentResearch\" " +
                            "(\"ID\", \"city\", \"priceMin\", \"priceMax\", \"size\", \"favorite\", \"user\", " +
                            "\"sorting\", \"localsMin\", \"localsMax\", \"furnished\", \"bathroomNumberMin\", " +
                            "\"bedsNumberMin\", \"bedsNumberMax\", \"date\") " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer ID = database.getID();
            stmt.setInt(1, ID);
            stmt.setString(2, apartmentResearch.getCity());
            stmt.setDouble(3, apartmentResearch.getPriceMin());
            stmt.setDouble(4, apartmentResearch.getPriceMax());
            stmt.setDouble(5, apartmentResearch.getSize());
            stmt.setBoolean(6, apartmentResearch.getFavorite());
            stmt.setString(7, apartmentResearch.getUser().getNickname());
            stmt.setString(8, apartmentResearch.getSorting().toString());
            stmt.setInt(9, apartmentResearch.getLocalsMin());
            stmt.setInt(10, apartmentResearch.getLocalsMax());
            stmt.setBoolean(11, apartmentResearch.getFurnished());
            stmt.setInt(12, apartmentResearch.getBathroomNumberMin());
            stmt.setInt(13, apartmentResearch.getBedsNumberMin());
            stmt.setInt(14, apartmentResearch.getBedsNumberMax());
            stmt.setString(15, Date.gregorianCalendarToString(apartmentResearch.getDate()));

            stmt.executeUpdate();

            apartmentResearch.setID(ID);

            stmt.close();
            conn.close();
            return apartmentResearch;
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

        return null;
    }

    /**
     * Do by EC.
     * @param ID
     * @return ApartmentResearch or null if not exists.
     */
    public ApartmentResearch findByID(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ApartmentResearch apartmentResearch = null;

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"ApartmentResearch\" where \"ID\" = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, ID);
            ResultSet result = stmt.executeQuery();

            if (!result.first()) // rs empty
            return null;

            result.first();

            apartmentResearch = (ApartmentResearch) ResearchFactory.getApartmentResearch(result.getInt("ID"),
                    result.getString("city"), result.getDouble("priceMin"), result.getDouble("priceMax"),
                    result.getDouble("size"), Date.stringToGregorianCalendar(result.getString("date")),
                    result.getBoolean("favorite"), userDao.findByNickname(result.getString("user")),
                    result.getString("sorting"), result.getInt("localsMin"), result.getInt("localsMax"),
                    result.getBoolean("furnished"), result.getInt("bathroomNumberMin"),
                    result.getInt("bedsNumberMin"), result.getInt("bedsNumberMax"));

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

        return apartmentResearch;
    }

    /**
     * Edit by EC.
     * @param ID
     * @return
     */
    public Boolean delete(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = this.ds.getConnection();

            if (findByID(ID) == null)
                throw new EntityNotExistException();

            stmt = conn.prepareStatement("delete from \"public\".\"ApartmentResearch\" where \"ID\" = ?;",
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

}
