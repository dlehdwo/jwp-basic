package next.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(String sql, PreparedStatementSetter pss) {;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setParameters(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
    }

    public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);

            pss.setParameters(pstmt);
            rs = pstmt.executeQuery();
            return rm.mapRow(rs);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
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
