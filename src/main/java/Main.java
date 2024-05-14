import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Main {

    public static void main(String[] args) throws Exception {
        String dirPath = "data/images/";
        String url = "https://skillbox.ru/";
        Document doc = Jsoup.connect(url).get();
        Elements images = doc.select("img");

        int number = 0;
        for (Element l : images) {
            String link = l.attr("abs:src");
            String extension = l.attr("abs:src")
                    .replaceAll("^.+\\.", "")
                    .replace("?.+$", "");

            String filePath = dirPath + (number += 1) + "." + extension;
            System.out.println("Link: " + link + "\nFile Path: " + filePath);
            downlonder(link, filePath);
        }
    }

    public static void downlonder(String link, String filePath) throws Exception {
        URLConnection connection = new URL(link).openConnection();
        try {
            InputStream inStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(filePath);
            int charRead;

            while ((charRead = inStream.read()) != -1) {
                outputStream.write(charRead);
            }
            outputStream.flush();
            outputStream.close();
            inStream.close();

        } catch (Exception e) {
            e.getMessage();
        }
    }
}


