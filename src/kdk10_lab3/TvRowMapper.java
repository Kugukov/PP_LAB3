package kdk10_lab3;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class TvRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }
    
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Tv tv = new Tv();
            tv.setId(rs.getInt("id"));
            tv.setName(rs.getString("Name"));
            tv.setManufacturer(rs.getString("Manufacturer"));
            return tv;
        }
    }
}
