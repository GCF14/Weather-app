import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class weather_app {

     private static final Map<String, String> cityToTimeZoneMap = new HashMap<>();

    static {

        cityToTimeZoneMap.put("London", "Europe/London");
        cityToTimeZoneMap.put("New York", "America/New_York");
        cityToTimeZoneMap.put("Tokyo", "Asia/Tokyo");
        cityToTimeZoneMap.put("Los Angeles", "America/Los_Angeles");
        cityToTimeZoneMap.put("Chicago", "America/Chicago");
        cityToTimeZoneMap.put("Houston", "America/Chicago");
        cityToTimeZoneMap.put("Miami", "America/New_York");
        cityToTimeZoneMap.put("Toronto", "America/Toronto");
        cityToTimeZoneMap.put("Vancouver", "America/Vancouver");
        cityToTimeZoneMap.put("Mexico City", "America/Mexico_City");
        cityToTimeZoneMap.put("São Paulo", "America/Sao_Paulo");
        cityToTimeZoneMap.put("Buenos Aires", "America/Argentina/Buenos_Aires");
        cityToTimeZoneMap.put("Rio de Janeiro", "America/Sao_Paulo");
        cityToTimeZoneMap.put("Berlin", "Europe/Berlin");
        cityToTimeZoneMap.put("Paris", "Europe/Paris");
        cityToTimeZoneMap.put("Madrid", "Europe/Madrid");
        cityToTimeZoneMap.put("Rome", "Europe/Rome");
        cityToTimeZoneMap.put("Moscow", "Europe/Moscow");
        cityToTimeZoneMap.put("Istanbul", "Europe/Istanbul");
        cityToTimeZoneMap.put("Dubai", "Asia/Dubai");
        cityToTimeZoneMap.put("Beijing", "Asia/Shanghai");
        cityToTimeZoneMap.put("Shanghai", "Asia/Shanghai");
        cityToTimeZoneMap.put("Hong Kong", "Asia/Hong_Kong");
        cityToTimeZoneMap.put("Singapore", "Asia/Singapore");
        cityToTimeZoneMap.put("Sydney", "Australia/Sydney");
        cityToTimeZoneMap.put("Melbourne", "Australia/Melbourne");
        cityToTimeZoneMap.put("Perth", "Australia/Perth");
        cityToTimeZoneMap.put("Auckland", "Pacific/Auckland");
        cityToTimeZoneMap.put("Johannesburg", "Africa/Johannesburg");
        cityToTimeZoneMap.put("Cairo", "Africa/Cairo");
        cityToTimeZoneMap.put("Nairobi", "Africa/Nairobi");
        cityToTimeZoneMap.put("Lagos", "Africa/Lagos");
        cityToTimeZoneMap.put("Delhi", "Asia/Kolkata");
        cityToTimeZoneMap.put("Mumbai", "Asia/Kolkata");
        cityToTimeZoneMap.put("Bangkok", "Asia/Bangkok");
        cityToTimeZoneMap.put("Kuala Lumpur", "Asia/Kuala_Lumpur");
        cityToTimeZoneMap.put("Jakarta", "Asia/Jakarta");
        cityToTimeZoneMap.put("Seoul", "Asia/Seoul");

       
        cityToTimeZoneMap.put("Osaka", "Asia/Tokyo");
        cityToTimeZoneMap.put("Hong Kong", "Asia/Hong_Kong");
        cityToTimeZoneMap.put("Manila", "Asia/Manila");
        cityToTimeZoneMap.put("Barcelona", "Europe/Madrid");
        cityToTimeZoneMap.put("Amsterdam", "Europe/Amsterdam");
        cityToTimeZoneMap.put("Lisbon", "Europe/Lisbon");
        cityToTimeZoneMap.put("Budapest", "Europe/Budapest");
        cityToTimeZoneMap.put("Los Angeles", "America/Los_Angeles");
        cityToTimeZoneMap.put("Mumbai", "Asia/Kolkata");
        cityToTimeZoneMap.put("Chicago", "America/Chicago");
        cityToTimeZoneMap.put("Bangkok", "Asia/Bangkok");
        cityToTimeZoneMap.put("Madrid", "Europe/Madrid");
        cityToTimeZoneMap.put("Dubai", "Asia/Dubai");
        cityToTimeZoneMap.put("Sydney", "Australia/Sydney");
        cityToTimeZoneMap.put("Toronto", "America/Toronto");
        cityToTimeZoneMap.put("Rome", "Europe/Rome");
        cityToTimeZoneMap.put("Melbourne", "Australia/Melbourne");
        cityToTimeZoneMap.put("Berlin", "Europe/Berlin");
        cityToTimeZoneMap.put("Lima", "America/Lima");
        cityToTimeZoneMap.put("Lagos", "Africa/Lagos");
        cityToTimeZoneMap.put("Abu Dhabi", "Asia/Dubai");
        cityToTimeZoneMap.put("Guangzhou", "Asia/Shanghai");
        cityToTimeZoneMap.put("Cairo", "Africa/Cairo");
        cityToTimeZoneMap.put("Kuala Lumpur", "Asia/Kuala_Lumpur");
        cityToTimeZoneMap.put("Riyadh", "Asia/Riyadh");
        cityToTimeZoneMap.put("Copenhagen", "Europe/Copenhagen");
        cityToTimeZoneMap.put("Vancouver", "America/Vancouver");
        cityToTimeZoneMap.put("Porto", "Europe/Lisbon");
        cityToTimeZoneMap.put("Miami", "America/New_York");
        cityToTimeZoneMap.put("Moscow", "Europe/Moscow");
        cityToTimeZoneMap.put("Prague", "Europe/Prague");
        cityToTimeZoneMap.put("Rio de Janeiro", "America/Sao_Paulo");
        cityToTimeZoneMap.put("Vienna", "Europe/Vienna");
        cityToTimeZoneMap.put("Shenzhen", "Asia/Shanghai");
        cityToTimeZoneMap.put("Boston", "America/New_York");
        cityToTimeZoneMap.put("Jakarta", "Asia/Jakarta");
        cityToTimeZoneMap.put("Cape Town", "Africa/Johannesburg");
        cityToTimeZoneMap.put("Austin", "America/Chicago");
        cityToTimeZoneMap.put("Athens", "Europe/Athens");

        
        cityToTimeZoneMap.put("Paris", "Europe/Paris"); 
        cityToTimeZoneMap.put("İstanbul", "Europe/Istanbul"); 
        cityToTimeZoneMap.put("São Paulo", "America/Sao_Paulo"); 
        cityToTimeZoneMap.put("Osaka", "Asia/Tokyo"); 
        cityToTimeZoneMap.put("Hong Kong", "Asia/Hong_Kong"); 
        cityToTimeZoneMap.put("Mexico City", "America/Mexico_City");
        cityToTimeZoneMap.put("San Francisco", "America/Los_Angeles"); 
        cityToTimeZoneMap.put("Manila", "Asia/Manila"); 
        cityToTimeZoneMap.put("Barcelona", "Europe/Madrid"); 
        cityToTimeZoneMap.put("Amsterdam", "Europe/Amsterdam"); 
        cityToTimeZoneMap.put("Lisbon", "Europe/Lisbon"); 
        cityToTimeZoneMap.put("Budapest", "Europe/Budapest"); 
        cityToTimeZoneMap.put("Los Angeles", "America/Los_Angeles"); 
        cityToTimeZoneMap.put("Mumbai", "Asia/Kolkata"); 
        cityToTimeZoneMap.put("Chicago", "America/Chicago"); 
        cityToTimeZoneMap.put("Bangkok", "Asia/Bangkok"); 
        cityToTimeZoneMap.put("Madrid", "Europe/Madrid"); 
        cityToTimeZoneMap.put("Dubai", "Asia/Dubai"); 
        cityToTimeZoneMap.put("Sydney", "Australia/Sydney"); 
        cityToTimeZoneMap.put("Toronto", "America/Toronto"); 
        cityToTimeZoneMap.put("Rome", "Europe/Rome"); 
        cityToTimeZoneMap.put("Melbourne", "Australia/Melbourne"); 
        cityToTimeZoneMap.put("Berlin", "Europe/Berlin"); 
        cityToTimeZoneMap.put("Lima", "America/Lima"); 
        cityToTimeZoneMap.put("Lagos", "Africa/Lagos"); 
        cityToTimeZoneMap.put("Abu Dhabi", "Asia/Dubai"); 
        cityToTimeZoneMap.put("Guangzhou", "Asia/Shanghai"); 
        cityToTimeZoneMap.put("Cairo", "Africa/Cairo"); 
        cityToTimeZoneMap.put("Kuala Lumpur", "Asia/Kuala_Lumpur"); 
        cityToTimeZoneMap.put("Riyadh", "Asia/Riyadh"); 
        cityToTimeZoneMap.put("Copenhagen", "Europe/Copenhagen"); 
        cityToTimeZoneMap.put("Vancouver", "America/Vancouver"); 
        cityToTimeZoneMap.put("Porto", "Europe/Lisbon"); 
        cityToTimeZoneMap.put("Miami", "America/New_York"); 
        cityToTimeZoneMap.put("Moscow", "Europe/Moscow"); 
        cityToTimeZoneMap.put("Prague", "Europe/Prague"); 
        cityToTimeZoneMap.put("Rio de Janeiro", "America/Sao_Paulo"); 
        cityToTimeZoneMap.put("Vienna", "Europe/Vienna"); 
        cityToTimeZoneMap.put("Shenzhen", "Asia/Shanghai"); 
        cityToTimeZoneMap.put("Boston", "America/New_York"); 
        cityToTimeZoneMap.put("Jakarta", "Asia/Jakarta"); 
        cityToTimeZoneMap.put("Cape Town", "Africa/Johannesburg"); 
        cityToTimeZoneMap.put("Austin", "America/Chicago"); 
        cityToTimeZoneMap.put("Athens", "Europe/Athens"); 

        cityToTimeZoneMap.put("Manila", "Asia/Manila");
        cityToTimeZoneMap.put("Quezon City", "Asia/Manila");
        cityToTimeZoneMap.put("Cebu City", "Asia/Manila");
        cityToTimeZoneMap.put("Davao City", "Asia/Manila");
        cityToTimeZoneMap.put("Makati City", "Asia/Manila");
        cityToTimeZoneMap.put("Pasig City", "Asia/Manila");
        cityToTimeZoneMap.put("Taguig City", "Asia/Manila");
        cityToTimeZoneMap.put("Cagayan de Oro", "Asia/Manila");
        cityToTimeZoneMap.put("Angeles City", "Asia/Manila");
        cityToTimeZoneMap.put("Baguio City", "Asia/Manila");
        cityToTimeZoneMap.put("Bacolod City", "Asia/Manila");
        cityToTimeZoneMap.put("Iloilo City", "Asia/Manila");
        cityToTimeZoneMap.put("Naga City", "Asia/Manila");
        cityToTimeZoneMap.put("Cotabato City", "Asia/Manila");
        cityToTimeZoneMap.put("Zamboanga City", "Asia/Manila");
        cityToTimeZoneMap.put("General Santos", "Asia/Manila");

        cityToTimeZoneMap.put("United States", "America/New_York");
        cityToTimeZoneMap.put("Canada", "America/Toronto");
        cityToTimeZoneMap.put("United Kingdom", "Europe/London");
        cityToTimeZoneMap.put("France", "Europe/Paris");
        cityToTimeZoneMap.put("Germany", "Europe/Berlin");
        cityToTimeZoneMap.put("Australia", "Australia/Sydney");
        cityToTimeZoneMap.put("Japan", "Asia/Tokyo");
        cityToTimeZoneMap.put("China", "Asia/Shanghai");
        cityToTimeZoneMap.put("India", "Asia/Kolkata");
        cityToTimeZoneMap.put("Brazil", "America/Sao_Paulo");
        cityToTimeZoneMap.put("Mexico", "America/Mexico_City");
        cityToTimeZoneMap.put("Russia", "Europe/Moscow");
        cityToTimeZoneMap.put("South Africa", "Africa/Johannesburg");
        cityToTimeZoneMap.put("South Korea", "Asia/Seoul");
        cityToTimeZoneMap.put("Italy", "Europe/Rome");
        cityToTimeZoneMap.put("Spain", "Europe/Madrid");
        cityToTimeZoneMap.put("Netherlands", "Europe/Amsterdam");
        cityToTimeZoneMap.put("Singapore", "Asia/Singapore");
        cityToTimeZoneMap.put("Switzerland", "Europe/Zurich");
        cityToTimeZoneMap.put("Sweden", "Europe/Stockholm");
        cityToTimeZoneMap.put("Denmark", "Europe/Copenhagen");
        cityToTimeZoneMap.put("Norway", "Europe/Oslo");
        cityToTimeZoneMap.put("Finland", "Europe/Helsinki");
        cityToTimeZoneMap.put("Ireland", "Europe/Dublin");
        cityToTimeZoneMap.put("Portugal", "Europe/Lisbon");
        cityToTimeZoneMap.put("Greece", "Europe/Athens");
        cityToTimeZoneMap.put("Poland", "Europe/Warsaw");
        cityToTimeZoneMap.put("Austria", "Europe/Vienna");
        cityToTimeZoneMap.put("Hungary", "Europe/Budapest");
        cityToTimeZoneMap.put("Czech Republic", "Europe/Prague");
        cityToTimeZoneMap.put("Turkey", "Europe/Istanbul");
        cityToTimeZoneMap.put("Philippines", "Asia/Manila");
    }

   
    public static JSONObject get_weather_data(String location_name){
        
        JSONArray location_data = get_location_data(location_name);

        if (location_data == null || location_data.isEmpty()) {
            System.out.println("Error: No location data found.");
            return null;
        }

        JSONObject location = (JSONObject) location_data.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");


        String urlString = "https://api.open-meteo.com/v1/forecast?" +
        "latitude=" + latitude + "&longitude=" + longitude + 
        "&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=Asia%2FSingapore";
        

        try{
            HttpURLConnection connection = Api_response(urlString);

            if(connection.getResponseCode() != 200){
                System.out.printf("Error: Could not connect to API\n");
                return null;
            }

            
            StringBuilder result_json = new StringBuilder();
            Scanner sc = new Scanner(connection.getInputStream());
            while(sc.hasNext()){
                
                result_json.append(sc.nextLine());
            }

            sc.close();

            connection.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject json_object = (JSONObject) parser.parse(String.valueOf(result_json));

            JSONObject hourly = (JSONObject) json_object.get("hourly");

            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            JSONArray temperature_data = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperature_data.get(index);

            JSONArray weathercode = (JSONArray) hourly.get("weathercode");
            String weather_condition = convert_code((long) weathercode.get(index));

            JSONArray relativeHumidity = (JSONArray) hourly.get("relativehumidity_2m");
            long humidity = (long) relativeHumidity.get(index);

            JSONArray windspeedData = (JSONArray) hourly.get("windspeed_10m");
            double windspeed = (double) windspeedData.get(index);

            
            String localTime = getLocalTime(location_name);


            JSONObject weather_data = new JSONObject();
            weather_data.put("temperature", temperature);
            weather_data.put("weather_condition", weather_condition);
            weather_data.put("humidity", humidity);
            weather_data.put("windspeed", windspeed);
            weather_data.put("local_time", localTime); 
    

            return weather_data;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

   
    public static JSONArray get_location_data(String location_name){
        
        location_name = location_name.replaceAll(" ", "+");

        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                            location_name + "&count=10&language=en&format=json";

        try{
            HttpURLConnection connection = Api_response(urlString);

            if(connection.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
                return null;
            }else{
               
                StringBuilder resultJson = new StringBuilder();
                Scanner sc = new Scanner(connection.getInputStream());

                while(sc.hasNext()){
                    resultJson.append(sc.nextLine());
                }

                
                sc.close();
                connection.disconnect();

                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static HttpURLConnection Api_response(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.connect();
            return connection;
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    private static int findIndexOfCurrentTime(JSONArray timeList){
        String currentTime = getCurrentTime();
        int i;

       
        for(i = 0; i < timeList.size(); i++){
            String time = (String) timeList.get(i);
            if(time.equalsIgnoreCase(currentTime)){
                return i;
            }
        }

        return 0;
    }

    private static String getCurrentTime(){

        LocalDateTime current_date_time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
        String formatted = current_date_time.format(formatter);

        return formatted;
    }

    private static String convert_code(long weather_code) {
        String weather_condition = " ";

        switch ((int) weather_code) {
            case 0:
                weather_condition = "Clear sky";
                break;
            case 1: 
                weather_condition = "Mainly clear";
                break;
            case 2: 
                weather_condition = "Partly cloudy";
                break;
            case 3:
                weather_condition = "Overcast";
                break;
            case 45:
                weather_condition = "Fog";
                break;
            case 48:
                weather_condition = "Depositing rime fog";
                break;
            case 51:
                weather_condition = "Drizzle: Light";
                break;
            case 53:
                weather_condition = "Drizzle: moderate";
                break;
            case 55:
                weather_condition = "Drizzle: dense intensity";
                break;
            case 56:
                weather_condition = "Freezing Drizzle: Light";
                break;
            case 57:
                weather_condition = "Freezing Drizzle: dense intensity";
                break;
            case 61:
                weather_condition = "Rain: Slight";
                break;
            case 63:
                weather_condition = "Rain: moderate";
                break;
            case 65:
                weather_condition = "Rain: heavy intensity";
                break;
            case 66:
                weather_condition = "Freezing Rain: Light";
                break;
            case 67:
                weather_condition = "Freezing Rain: heavy intensity";
                break;
            case 71:
                weather_condition = "Snow fall: Slight";
                break;
            case 73:
                weather_condition = "Snow fall: moderate";
                break;
            case 75:
                weather_condition = "Snow fall: heavy intensity";
                break;
            case 77:
                weather_condition = "Snow grains";
                break;
            case 80:
                weather_condition = "Rain showers: Slight";
                break;
            case 81:
                weather_condition = "Rain showers: moderate, and violent";
                break;
            case 82:
                weather_condition = "Rain showers: violent";
                break;
            case 85:
                weather_condition = "Snow showers slight";
                break;
            case 86:
                weather_condition = "Snow showers heavy";
                break;
            case 95:
                weather_condition = "Thunderstorm: Slight or moderate";
                break;
            case 96:
                weather_condition = "Thunderstorm with slight hail";
                break;
            case 99:
                weather_condition = "Thunderstorm with heavy hail";
                break;
            default:
                weather_condition = "Unknown weather code";
        }
    
        return weather_condition;

    }

    
    private static String getLocalTime(String cityName) {
        String timeZoneId = cityToTimeZoneMap.get(cityName);
        if (timeZoneId == null) {
            return "Unknown time zone";
        }

        ZoneId zoneId = ZoneId.of(timeZoneId);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return zonedDateTime.format(formatter);
    }

}
