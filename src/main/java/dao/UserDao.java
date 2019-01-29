package dao;

import entity.User;
import java.sql.*;

public class UserDao {

    private DataSource ds = new DataSource();

    public User findByNicknameAndPassword(String nickname, String password) {
        Statement stmt = null;
        Connection conn = null;
        User u = null;
        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"Users\" where \"nickname\" = ? " +
                    "and \"password\" = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ((PreparedStatement) stmt).setString(1, nickname);
            ((PreparedStatement) stmt).setString(2, password);
            ResultSet rs = ((PreparedStatement) stmt).executeQuery();

            if (!rs.first()) // rs empty
                return null;

            rs.first();

            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            String nicknameLoaded = rs.getString("nickname");
            String email = rs.getString("email");
            Character gender = rs.getString("gender").toCharArray()[0];

            u = new User(nicknameLoaded, nome, cognome, email, "");
            u.setGender(gender);

            rs.close();
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
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return u;
    }

    public User create(String nickname, String nome , String cognome , String email , String password , Character gender) {
        Statement stmt = null;
        Connection conn = null;
        User v = null;
        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("insert into \"public\".\"Users\" (nickname , nome , cognome , email , password , gender) " +
                    "values (?,?,?,?,?,?);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ((PreparedStatement) stmt).setString(1, nickname);
            ((PreparedStatement) stmt).setString(2, nome);
            ((PreparedStatement) stmt).setString(3, cognome);
            ((PreparedStatement) stmt).setString(4, email);
            ((PreparedStatement) stmt).setString(5, password);
            ((PreparedStatement) stmt).setString(6, String.valueOf(gender));
            ((PreparedStatement) stmt).executeUpdate();

            //problema : questa query funziona ovvero inserisce nella tabella ho controllato solo che non ritorna nessun risultato
            //ovvero rs è sempre vuoto quindi ho sempre un return null

//            if (!rs.first()) // rs empty
//                return null;

            //boolean moreThanOne = rs.first() && rs.next();


            //rs.first();

//            String nome = rs.getString("nome");
//            String cognome = rs.getString("cognome");
//            String nicknameLoaded = rs.getString("nickname");
//            String email = rs.getString("email");
//            Character gender = rs.getString("gender").toCharArray()[0];


            v = new User(nickname, nome, cognome, email, "");
            v.setGender(gender);

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

        return v;
    }

    public User findByNickname(String nickname) {
        Statement stmt = null;
        Connection conn = null;
        User u = null;
        try {
            conn = this.ds.getConnection();

            stmt = conn.prepareStatement("select * from \"public\".\"Users\" where \"nickname\" = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ((PreparedStatement) stmt).setString(1, nickname);
            ResultSet rs = ((PreparedStatement) stmt).executeQuery();

            if (!rs.first()) // rs empty
                return null;

            rs.first();

            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            String nicknameLoaded = rs.getString("nickname");
            String email = rs.getString("email");
            Character gender = rs.getString("gender").toCharArray()[0];

            u = new User(nicknameLoaded, nome, cognome, email, "");
            u.setGender(gender);

            rs.close();
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

        return u;
    }

}
