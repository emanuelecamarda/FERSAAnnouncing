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

//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY);
//            String sql = "select * from \"public\".\"Users\" " +
//                    "where \"nickname\" = '" + nickname + "' and \"password\" = '" + password + "';";
//            ResultSet rs = stmt.executeQuery(sql);

            stmt = conn.prepareStatement("select * from \"public\".\"Users\" " +
                    "where \"nickname\" = ? and \"password\" = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ((PreparedStatement) stmt).setString(1, nickname);
            ((PreparedStatement) stmt).setString(2, password);
            ResultSet rs = ((PreparedStatement) stmt).executeQuery();

            if (!rs.first()) // rs empty
                return null;

            boolean moreThanOne = rs.first() && rs.next();
//            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
//            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
//            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione

            rs.first();

            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            String nicknameLoaded = rs.getString("nickname");
            String email = rs.getString("email");
            Character gender = rs.getString("gender").toCharArray()[0];

//            assert (nicknameLoaded.equals(nickname));

            u = new User(nicknameLoaded, nome, cognome, email, "", gender);

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
