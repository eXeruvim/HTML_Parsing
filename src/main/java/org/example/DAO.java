package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DAO {
    public static void insertDB () throws IOException, InterruptedException {
        List<Product> productList = DOM.getDOM();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exeruvim", "root", "")) {
            Statement statement = connection.createStatement();

            for (Product product : productList){
                ResultSet getID = statement.executeQuery("SELECT ID+1 FROM wp_exeruvimposts ORDER BY ID DESC LIMIT 1;");
                getID.next();
                long id = getID.getLong(1);
                String html = "'<!-- wp:paragraph -->\n <img src=\"" + product.getPicture() + "\" </img> \n <p> <b> Товар: " + product.getTitle() + "</b> </p> \n <p> <b> " + product.getOption() + " </b>" + product.getVariety() + "</p> \n <p> <b> Цена по акции: </b> " + product.getSalePriceRUB() + " руб. (" + product.getSalePriceUSD() + " $)" + "</p> \n <p> <b> Цена: </b>" + product.getRub() + " руб. (" + product.getUsd() + " $)" +  "</p> \n <p> <b> Рейтинг: </b>" + product.getRating() + "</p> \n <p> <b> Количество отзывов: </b>" + product.getReviews() + "</p> \n <a href=\"" + product.getUrl() + "\"> <b> Купить </b> </a> \n <!-- /wp:paragraph -->'";
                String sql = "INSERT INTO `wp_exeruvimposts` (`ID`, `post_author`, `post_date`, `post_date_gmt`, `post_content`, `post_title`, `post_excerpt`, `post_status`, `comment_status`, `ping_status`, `post_password`, `post_name`, `to_ping`, `pinged`, `post_modified`, `post_modified_gmt`, `post_content_filtered`, `post_parent`, `guid`, `menu_order`, `post_type`, `post_mime_type`, `comment_count`) VALUES ("+id+", 1, NOW(), UTC_TIMESTAMP(), "+ html +", '"+product.getTitle()+"', '', 'publish','open','open', '', 'order"+id+"', '', '', NOW(), UTC_TIMESTAMP(), '', 0, 'http://exeruvim.su/?p="+id+"', 0,'post', '', 0)";
                statement.executeUpdate(sql);
            }
            System.out.println("Данные успешно добавлены");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

