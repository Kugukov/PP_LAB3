package kdk10_lab3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class TvDAO implements ITvDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Tv customer) { // Реализация вставки новой записи

        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO TV (Name, Manufacturer) VALUES(?,?)",
                new Object[]{customer.getName(), customer.getManufacturer()});
    }

    @Override
    public void append(String Name, String Manufacturer) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO TV (Name, Manufacturer) VALUES(?,?)", new Object[]{Name, Manufacturer});
    }

    @Override
    public void deleteByName(String Name) {  // Реализация удаления записей по производителю
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM TV WHERE Name LIKE ?", new Object[]{'%' + Name + '%'});
    }

    @Override
    public void delete(final String Name, final String Manufacturer) {  // Реализация удаления записей с указанными моделью и производителем
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from TV where Name= ? AND Manufacturer = ?", new Object[]{Name, Manufacturer});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from TV");
    }

    @Override
    public void update(String oldManufacturer, String newManufacturer) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE TV SET Manufacturer = ? WHERE Manufacturer = ?", new Object[]{newManufacturer, oldManufacturer});
    }

    @Override
    public List<Tv> findByName(String Name) {  // Реализация поиска записей по модели
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM TV WHERE Name LIKE ?";
        List<Tv> televisions = select.query(sql, new Object[]{'%' + Name + '%'}, new TvRowMapper());
        return televisions;
    }

    @Override
    public List<Tv> select(String Name, String Manufacturer) {  // Реализация получения записей с заданными моделью и производителем
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from TV where Name = ? AND Manufacturer= ?",
                new Object[]{Name, Manufacturer}, new TvRowMapper());
    }

    @Override
    public List<Tv> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from TV", new TvRowMapper());
    }
}
