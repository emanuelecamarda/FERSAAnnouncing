/**
 * Edit by EC.
 */

package dao;

import entity.RoomResearch;
import exception.EntityNotExistException;
import factory.ResearchFactory;
import utils.Database;
import utils.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomResearchDao {

    private DataSource ds = new DataSource();
    private UserDao userDao = new UserDao();

    /**
     * create method. Do by EC.
     *
     * @param roomResearch
     * @return
     */
    public RoomResearch create(RoomResearch roomResearch) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Database database = Database.getInstance();
        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("insert into \"public\".\"RoomResearch\" " +
                            "(\"ID\", \"city\", \"priceMin\", \"priceMax\", \"size\", \"favorite\", \"user\", " +
                            "\"sorting\", \"roomersNumberMax\", \"privateBathroom\", \"onlyFemale\", \"onlyMale\", " +
                            "\"date\") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Integer ID = database.getID();
            stmt.setInt(1, ID);
            stmt.setString(2, roomResearch.getCity());
            stmt.setDouble(3, roomResearch.getPriceMin());
            stmt.setDouble(4, roomResearch.getPriceMax());
            stmt.setDouble(5, roomResearch.getSize());
            stmt.setBoolean(6, roomResearch.getFavorite());
            stmt.setString(7, roomResearch.getUser().getNickname());
            stmt.setString(8, roomResearch.getSorting().toString());
            stmt.setInt(9, roomResearch.getRoomersNumberMax());
            stmt.setBoolean(10, roomResearch.getPrivateBathroom());
            stmt.setBoolean(11, roomResearch.getOnlyFemale());
            stmt.setBoolean(12, roomResearch.getOnlyMale());
            stmt.setString(13, Date.gregorianCalendarToString(roomResearch.getDate()));

            stmt.executeUpdate();

            roomResearch.setID(ID);

            stmt.close();
            conn.close();
            return roomResearch;
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
     *
     * @param ID
     * @return RoomResearch or null if not exists.
     */
    public RoomResearch findByID(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        RoomResearch roomResearch = null;

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"RoomResearch\" where \"ID\" = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, ID);
            ResultSet result = stmt.executeQuery();

            if (result.next() && roomResearch == null) {
                roomResearch = (RoomResearch) ResearchFactory.getRommResearch(result.getInt("ID"),
                        result.getString("city"), result.getDouble("priceMin"), result.getDouble("priceMax"),
                        result.getDouble("size"), Date.stringToGregorianCalendar(result.getString("date")),
                        result.getBoolean("favorite"), userDao.findByNickname(result.getString("user")),
                        result.getString("sorting"), result.getInt("roomersNumberMax"),
                        result.getBoolean("privateBathroom"), result.getBoolean("onlyFemale"),
                        result.getBoolean("onlyMale"));
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

        return roomResearch;
    }

    /**
     * Edit by EC.
     *
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

            stmt = conn.prepareStatement("delete from \"public\".\"RoomResearch\" where \"ID\" = ?;",
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
