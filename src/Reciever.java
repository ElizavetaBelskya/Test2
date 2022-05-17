import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reciever {
    File propFile = new File("C:\\Users\\Elizaveta\\OneDrive\\Рабочий стол\\Прога\\Java 2 сем\\ControlWork\\src\\App.properties");
    Properties properties = new Properties();
    public String getData(String domen, String zone) throws IOException {
        try {
            properties.load(new FileReader(propFile));
        } catch (IOException e) {
            throw new IOException("It is impossible to read the properties");
        }
        String link = properties.getProperty("link") + domen + "&zone=" + zone;
        System.out.println(link);
        URL url = new URL(link);
        URLConnection connection = url.openConnection();
        connection.connect();
        //Ошибка 403
        saveFile();
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//            String c;
//            while ((c = in.readLine()) != null) {
//                sb.append(c);
//            }
//            System.out.println(sb.toString());
//        } catch (IOException e) {
//            throw new IOException("It is impossible to get the data");
//        }
        return link;
    }

    private void saveFile() throws IOException {
        try {
            properties.load(new FileReader(propFile));
        } catch (IOException e) {
            throw new IOException("It is impossible to read the properties");
        }

        File file = new File("C:\\Users\\Elizaveta\\OneDrive\\Рабочий стол\\Прога\\Java 2 сем\\ControlWork\\src\\domen");
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))){
            int b;
            while ((b = in.read())!=-1) {
                sb.append((char) b);
            }
        } catch (IOException e) {
            throw new IOException("failed");
        }

        ArrayList<String> domainAdresses = findArray("\"domain\":\\s\"(.+?)\",", sb.toString());
        ArrayList<String> dates = findArray("\"create_date\":\\s\"(.+?)\",", sb.toString());

        try(BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:\\Users\\Elizaveta\\OneDrive\\Рабочий стол\\Прога\\Java 2 сем\\ControlWork\\src\\table.csv")))) {
            for (int i = 0; i < domainAdresses.size(); i++) {
                if (twoMonthLost()) {
                    out.write(domainAdresses.get(i) + "," + dates.get(i) + "," + memory() + "\n");
                }
            }
        } catch (IOException e) {
            throw new IOException("It is impossible to record");
        }



    }

    private ArrayList<String> findArray(String regex, String line) {
        ArrayList<String> arr = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            arr.add(matcher.group().replace(",", "").replaceAll("\"", "").split(":")[1]);
        }
        return arr;


    }

    private boolean twoMonthLost() {
        return true;
    }

    private long memory() {
        return 0;
    }
}
