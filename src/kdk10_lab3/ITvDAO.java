package kdk10_lab3;

import java.util.List;
import javax.sql.DataSource;

public interface ITvDAO {
    void setDataSource(DataSource ds);                      // Установка связи с данныими
    void insert(Tv customer);                               // Вставка новой записи
    void append(String Name, String Manufacturer);          // Добавление новой записи
    void deleteByName(String Name);                         // Удаление записи по фамилии
    void delete(String Name, String Manufacturer);          // Удаление записи с указанным именем и фамилией
    void deleteAll();                                       // Удаление всех запией
    void update(String oldName, String newManufacturer);    // Изменение записей в таблице
    List<Tv> findByName(String Name);                       // Получение записей с заданным именем 
    List<Tv> select(String Name, String Manufacturer);      // Получение записей с заданным именем и фамилией
    List<Tv> selectAll();                                   // Получение всех записей
}
