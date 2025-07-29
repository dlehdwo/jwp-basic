package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(String sql, PreparedStatementSetter pss) {;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);){
            pss.setParameters(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);){
            pss.setParameters(pstmt);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rm.mapRow(rs); // 로직 실행 후 rs는 자동 close
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void executeUpdate(String sql, Object... parameters) {;
        PreparedStatementSetter pss = createPrepareStatementSetter(parameters);
        executeUpdate(sql, pss);
    }

    public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters) {
        PreparedStatementSetter pss = createPrepareStatementSetter(parameters);
        return executeQuery(sql, rm, pss);
    }

    private PreparedStatementSetter createPrepareStatementSetter(Object[] parameters) {
        return new PreparedStatementSetter() {

            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
        };
    }
}
