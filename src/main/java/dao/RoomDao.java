package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import entity.*;
import exception.EntityNotExistException;
import factory.RoomFactory;
import utils.Database;
import utils.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {


    private DataSource ds = new DataSource();
    private UserDao userDao = new UserDao();

    public synchronized RoomAnnounce create(RoomAnnounce roomAnnounce/*int roomersNumber, boolean privateBathroom , Array roomers*/) {
        PreparedStatement stmt = null;
        Connection conn = null;
        RoomAnnounce z = null;
        Database database = Database.getInstance();
        try {
            conn = this.ds.getConnection();



            stmt = conn.prepareStatement("insert into \"public\".\"Room\" "+" (\"ID\", \"city\", \"address\", \"price\", \"description\", \"size\", \"available\", \"date\", \"user\" , \"roomersNumber\" , \"privateBathroom\") " +
                    "values (?,?,?,?,?,?,?,?,?,?,?);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            Integer ID = database.getID();
            stmt.setInt(1, ID);
            stmt.setString(2, roomAnnounce.getCity());
            stmt.setString(3 , roomAnnounce.getAddress());
            stmt.setDouble(4,roomAnnounce.getprice());
            stmt.setString(5,roomAnnounce.getDescription());
            stmt.setDouble(6 , roomAnnounce.getSize());
            stmt.setBoolean(7 , roomAnnounce.getavailable());
            stmt.setString(8, Date.gregorianCalendarToString(roomAnnounce.getDate()));
            stmt.setString(9, roomAnnounce.getUser().getNickname());
            stmt.setInt(10 , roomAnnounce.getRoomersNumber());
            stmt.setBoolean(11 , roomAnnounce.isPrivateBathroom());
            //stmt.setArray(12 , roomAnnounce.getRoomers());

            stmt.executeUpdate();



            z = new RoomAnnounce();

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

        return z;
    }

    public RoomAnnounce findByID(Integer ID) {
        PreparedStatement stmt = null;
        Connection conn = null;
        RoomAnnounce roomAnnounce = null;

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"RoomDao\" where \"ID\" = ?;",
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
            result.getInt("roomersNumber");
            result.getBoolean("privateBathroom");

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

        return roomAnnounce;
    }

    public synchronized Boolean delete(Integer ID)throws EntityNotExistException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = this.ds.getConnection();

            if (findByID(ID) == null)
                throw new EntityNotExistException();

            stmt = conn.prepareStatement("delete from \"public\".\"RoomDao\" where \"ID\" = ?;",
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

            stmt = conn.prepareStatement("select MAX(\"ID\") from \"public\".\"Room\";",
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
     * @param roomResearch
     * @return
     */
    public List<RoomAnnounce> findByCondition(RoomResearch roomResearch) {
        PreparedStatement stmt = null;
        Connection conn = null;
        List<RoomAnnounce> roomAnnounces = new ArrayList<RoomAnnounce>();

        String query = "select * from \"public\".\"Room\" where \"available\" = true and \"city\" = ? " +
                "and \"price\" >= ? and \"price\" <= ? and \"size\" >= ? and \"privateBathroom\" = ?";
        if (roomResearch.getRoomersNumberMax() != null)
            query += " and \"roomersNumber\" <= ?";
        if (roomResearch.getSorting().equals(Sorting.moreBig))
            query += " order by \"size\" desc";
        if (roomResearch.getSorting().equals(Sorting.lessBig))
            query += " order by \"size\" asc";
        if (roomResearch.getSorting().equals(Sorting.moreExpensive))
            query += " order by \"price\" desc";
        if (roomResearch.getSorting().equals(Sorting.lessExpensive))
            query += " order by \"price\" asc";
        query += ";";

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, roomResearch.getCity());
            stmt.setDouble(2, roomResearch.getPriceMin());
            stmt.setDouble(3, roomResearch.getPriceMax());
            stmt.setDouble(4, roomResearch.getSize());
            stmt.setBoolean(5, roomResearch.getPrivateBathroom());
            if (roomResearch.getRoomersNumberMax() != null) {
                stmt.setInt(6, roomResearch.getRoomersNumberMax());
            }

            ResultSet result = stmt.executeQuery();

            if (!result.first()) // rs empty
                return null;

            result.first();

            roomAnnounces.add((RoomAnnounce) RoomFactory.getRoom(result.getInt("ID"), result.getString("city"),
                    result.getString("address"), result.getDouble("price"), result.getString("description"),
                    result.getDouble("size"), result.getBoolean("available"),
                    utils.Date.stringToGregorianCalendar(result.getString("date")),
                    userDao.findByNickname(result.getString("user")), result.getInt("roomersNumber"),
                    result.getBoolean("privateBathroom"), null));

            while (result.next()) {
                roomAnnounces.add((RoomAnnounce) RoomFactory.getRoom(result.getInt("ID"), result.getString("city"),
                        result.getString("address"), result.getDouble("price"), result.getString("description"),
                        result.getDouble("size"), result.getBoolean("available"),
                        utils.Date.stringToGregorianCalendar(result.getString("date")),
                        userDao.findByNickname(result.getString("user")), result.getInt("roomersNumber"),
                        result.getBoolean("privateBathroom"), null));
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

        return roomAnnounces;
    }

    /**
     * Edit by EC.
     * @return
     */
    public List<RoomAnnounce> findAll() {
        Statement stmt = null;
        Connection conn = null;
        List<RoomAnnounce> roomAnnounces = new ArrayList<RoomAnnounce>();

        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"Room\";",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = ((PreparedStatement) stmt).executeQuery();

            if (!result.first()) // rs empty
                return null;

            result.first();

            roomAnnounces.add((RoomAnnounce) RoomFactory.getRoom(result.getInt("ID"), result.getString("city"),
                    result.getString("address"), result.getDouble("price"), result.getString("description"),
                    result.getDouble("size"), result.getBoolean("available"),
                    utils.Date.stringToGregorianCalendar(result.getString("date")),
                    userDao.findByNickname(result.getString("user")), result.getInt("roomersNumber"),
                    result.getBoolean("privateBathroom"), null));

            while (result.next()) {
                roomAnnounces.add((RoomAnnounce) RoomFactory.getRoom(result.getInt("ID"), result.getString("city"),
                        result.getString("address"), result.getDouble("price"), result.getString("description"),
                        result.getDouble("size"), result.getBoolean("available"),
                        utils.Date.stringToGregorianCalendar(result.getString("date")),
                        userDao.findByNickname(result.getString("user")), result.getInt("roomersNumber"),
                        result.getBoolean("privateBathroom"), null));
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

        return roomAnnounces;
    }



}