package dao;

import entity.ApartmentResearch;
import entity.Research;
import entity.Room;
import entity.RoomResearch;
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
     * @param roomResearch
     * @return
     */
    public Boolean create(RoomResearch roomResearch) {
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
            stmt.setInt(1, database.getID());
            stmt.setString(2, roomResearch.getCity());
            stmt.setDouble(3, roomResearch.getPriceMin());
            stmt.setDouble(4, roomResearch.getPriceMax());
            stmt.setDouble(5, roomResearch.getSize());
            stmt.setBoolean(6, roomResearch.getFavorite());
            stmt.setString(7, roomResearch.getUser().getNickname());
            stmt.setString(8, roomResearch.getSorting().toString());
            stmt.setInt(9, roomResearch.getRoomersNumberMax());
            stmt.setBoolean(10, roomResearch.isPrivateBathroom());
            stmt.setBoolean(11, roomResearch.isOnlyFemale());
            stmt.setBoolean(12, roomResearch.isOnlyMale());
            stmt.setString(13, Date.gregorianCalendarToString(roomResearch.getDate()));

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
     * Do by EC.
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
                roomResearch = new RoomResearch();
                roomResearch.setID(result.getInt("ID"));
                roomResearch.setCity(result.getString("city"));
                roomResearch.setPriceMin(result.getDouble("priceMin"));
                roomResearch.setPriceMax(result.getDouble("priceMax"));
                roomResearch.setSize(result.getDouble("size"));
                roomResearch.setFavorite(result.getBoolean("favorite"));
                roomResearch.setUser(userDao.findByNickname(result.getString("user")));
                roomResearch.setSorting(result.getString("sorting"));
                roomResearch.setRoomersNumberMax(result.getInt("roomersNumberMax"));
                roomResearch.setPrivateBathroom(result.getBoolean("privateBathroom"));
                roomResearch.setOnlyFemale(result.getBoolean("onlyFemale"));
                roomResearch.setOnlyMale(result.getBoolean("onlyMale"));
                roomResearch.setDate(Date.stringToGregorianCalendar(result.getString("date")));
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
     * @param ID
     * @return
     */
    public Boolean delete(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = this.ds.getConnection();

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
