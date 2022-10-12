package kdk10_lab3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            TvDAO tvDAO = (TvDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            tvDAO.deleteAll(); // Удаление всех записей
            
            Tv tv = new Tv("43UQ76003LD", "LG"); // Создание нового объекта таблицы клиентов 
            tvDAO.insert(tv); // Вставить новый объект (запись) в таблицу клиентов

            tvDAO.insert(new Tv("UA43H1400", "AVA")); // Вставить новый объект (запись) в таблицу клиентов
            tvDAO.insert(new Tv("UE55AU7100UXCE", "Samsung")); // Вставить новый объект (запись) в таблицу клиентов

            tvDAO.deleteByName("55AU71"); // Удаление записей по фрагменту модели
            tvDAO.delete("UA43H1400", "AVA"); // Удаление записи по модели и производителю

            List<Tv> televisions = tvDAO.findByName("Q760"); // Поиск записей по фрагменту модели
            System.out.println("Поиск по фрагменту модели - Q760");
            System.out.println(televisions != null ? televisions : "Нет данных");

            tvDAO.append("40S65A", "TCL"); // Добавлние записей
            tvDAO.append("32F710KB", "KIVI");
            tvDAO.append("UE65AU7100UXCE", "Samsung");
            tvDAO.append("32AH90G", "AVA");

            tvDAO.update("LG", "LG568"); // Изменение записей в таблице

            System.out.println("Данные в таблице БД:");

            List<Tv> list = tvDAO.selectAll();
            for (Tv myTelevisions : list) {
                System.out.println(myTelevisions.getName() + " " + myTelevisions.getManufacturer());
            }

            System.out.println("Вывод записей с моделью 40S65A и производителем TCL:");

            list = tvDAO.select("40S65A", "TCL");
            for (Tv myTelevisions : list) {
                System.out.println(myTelevisions.getName() + " " + myTelevisions.getManufacturer());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
    
}
