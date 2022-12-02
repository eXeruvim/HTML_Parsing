package org.example;

import java.sql.*;
import java.util.List;

public class DAO {
    public static void insertDB () {
        List<Product> productList = DOM.getDOM();
        int posts = 0;
        int skip = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exeruvim", "root", "")) {
            Statement statement = connection.createStatement();

            for (Product product : productList){
                ResultSet getPost = statement.executeQuery("Select count(*) from wp_exeruvimposts where post_title like '" + product.getTitle() + "';");
                getPost.next();
                if (getPost.getLong(1) == 0) {
                    ResultSet getID = statement.executeQuery("SELECT ID+1 FROM wp_exeruvimposts ORDER BY ID DESC LIMIT 1;");
                    getID.next();
                    long id = getID.getLong(1);
                    String html = "'<!-- wp:paragraph -->\n <img src=\"" + product.getPicture() + "\" </img> \n <p> <b> Товар: " + product.getTitle() + "</b> </p> \n <p> <b> " + product.getOption() + " </b>" + product.getVariety() + "</p> \n <p> <b> Цена по акции: </b> " + product.getSalePriceRUB() + " руб. (" + product.getSalePriceUSD() + " $)" + "</p> \n <p> <b> Цена: </b>" + product.getRub() + " руб. (" + product.getUsd() + " $)" + "</p> \n <p> <b> Рейтинг: </b>" + product.getRating() + "</p> \n <p> <b> Количество отзывов: </b>" + product.getReviews() + "</p> \n <a href=\"" + product.getUrl() + "\"> <b> Купить </b> </a> \n <!-- /wp:paragraph -->'";
                    String sql = "INSERT INTO `wp_exeruvimposts` (`ID`, `post_author`, `post_date`, `post_date_gmt`, `post_content`, `post_title`, `post_excerpt`, `post_status`, `comment_status`, `ping_status`, `post_password`, `post_name`, `to_ping`, `pinged`, `post_modified`, `post_modified_gmt`, `post_content_filtered`, `post_parent`, `guid`, `menu_order`, `post_type`, `post_mime_type`, `comment_count`) VALUES (" + id + ", 1, NOW(), UTC_TIMESTAMP(), " + html + ", '" + product.getTitle() + "', '', 'publish','open','open', '', 'order" + id + "', '', '', NOW(), UTC_TIMESTAMP(), '', 0, 'http://exeruvim.su/?p=" + id + "', 0,'post', '', 0)";
                    statement.executeUpdate(sql);
                    posts ++;
                }
                else skip++;

            }
            if (skip > 0) System.out.println("Некоторые товары уже находятся в базе данных. \t товаров пропущено: " + skip);
            if (posts > 0) System.out.println("Данные успешно добавлены." + "\t товаров загружено: " + posts);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

