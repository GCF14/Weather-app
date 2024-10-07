import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;
import java.awt.*;


public class weather_gui extends JFrame {
    private JSONObject weather_data;

    private BufferedImage background;
    private JLabel humidity_text;  
    private JLabel windspeed_text; 
    private JLabel local_time_text; 
    private JLabel temp_text;
    private JLabel weather_description; 


    public weather_gui() {
        super("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(true);
        
        try {
            background = ImageIO.read(new File("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        JPanel background_panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        background_panel.setLayout(null);
        background_panel.setBounds(0, 0, getWidth(), getHeight());

        
        add_components(background_panel);

       
        setContentPane(background_panel);


        setVisible(true);
    }

    private void add_components(JPanel panel) {
        JTextField search = new JTextField();
        search.setBounds(15, 15, 351, 45);
        search.setFont(new Font("Dialog", Font.PLAIN, 24));
        panel.add(search);


        JLabel windspeed_icon = new JLabel();
        windspeed_icon.setBounds(225, 480, 90, 90);
        windspeed_icon.setHorizontalAlignment(JLabel.CENTER);
        windspeed_icon.setVerticalAlignment(JLabel.CENTER);
        panel.add(windspeed_icon);


        ImageIcon icon = load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\windspeed.png");
        if (icon != null) {
            windspeed_icon.setIcon(icon);
        }

        JLabel humidity_icon = new JLabel();
        humidity_icon.setBounds(15, 480, 90, 90);
        humidity_icon.setHorizontalAlignment(JLabel.CENTER);
        humidity_icon.setVerticalAlignment(JLabel.CENTER);
        panel.add(humidity_icon);

        ImageIcon icon2 = load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\humidity.png");
        if (icon2 != null) {
            humidity_icon.setIcon(icon2);
        }

        

        try {
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\SamsungSans-Regular.ttf")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
    
            
            Font new_font = customFont.deriveFont(20f);
    
            
            local_time_text = new JLabel("Current Time: ");
            local_time_text.setBounds(15, 70, 420, 36);
            local_time_text.setFont(new_font); 
            local_time_text.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(local_time_text);
    
            humidity_text = new JLabel("<html><div style='text-align: center;'>Humidity<br>0%</div></html>");
            humidity_text.setBounds(90, 500, 100, 55);
            humidity_text.setFont(customFont);  
            humidity_text.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(humidity_text);
    
            Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\SamsungSans-Bold.ttf")).deriveFont(48f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(customFont2);


            temp_text = new JLabel("0°C");
            temp_text.setBounds(25, 370, 400, 54);
            temp_text.setFont(customFont2);
            temp_text.setHorizontalAlignment(JLabel.CENTER); 
            panel.add(temp_text);

            windspeed_text = new JLabel("<html>Windspeed<br><div style='text-align: center;'>0km/h</div></html>");
            windspeed_text.setBounds(310, 500, 100, 55);
            windspeed_text.setFont(customFont);
            windspeed_text.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(windspeed_text);

            Font custom_font = customFont.deriveFont(Font.PLAIN, 27f);
            weather_description = new JLabel();
            weather_description.setBounds(0, 430, 450, 36);
            weather_description.setFont(custom_font);
            weather_description.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(weather_description);

    
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }


        JLabel weather_icon = new JLabel();
        weather_icon.setBounds(0, 125, 450, 217);
        weather_icon.setHorizontalAlignment(JLabel.CENTER); 
        weather_icon.setVerticalAlignment(JLabel.CENTER); 
        weather_icon.setHorizontalTextPosition(JLabel.CENTER); 
        weather_icon.setVerticalTextPosition(JLabel.BOTTOM); 
        panel.add(weather_icon);

        ImageIcon icon3 = load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\cloudy.png");
        if (icon3 != null) {
            weather_icon.setIcon(icon3);
        }


        JButton search_button = new JButton(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\search.png"));
        search_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search_button.setBounds(375, 13, 47, 45);
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String user_input = search.getText();

                if (user_input.replaceAll("\\s", "").length() <= 0){
                    return;
                }

                weather_data = weather_app.get_weather_data(user_input);          

                String weather_condition = (String) weather_data.get("weather_condition");


                switch(weather_condition) {
                    case "Clear sky":
                    case "Mainly clear":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\Clear.png"));
                        break;
                    case "Partly cloudy":
                    case "Overcast":
                    case "Fog":
                    case "Depositing rime fog":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\cloudy.png"));
                        break;
                    case "Drizzle: Light":
                    case "Drizzle: moderate":
                    case "Drizzle: dense intensity":
                    case "Freezing Drizzle: Light":
                    case "Freezing Drizzle: dense intensity":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\Drizzle.png"));
                        break;
                    case "Rain: Slight":
                    case "Rain: moderate":
                    case "Rain: heavy intensity":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\Drizzle.png"));
                        break;
                    case "Freezing Rain: Light":
                    case "Freezing Rain: heavy intensity":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\Freezing drizzle.png"));
                        break;
                    case "Snow fall: Slight":
                    case "Snow fall: moderate":
                    case "Snow fall: heavy intensity":
                    case "Snow grains":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\snow.png"));
                        break;
                    case "Rain showers: Slight":
                    case "Rain showers: moderate, and violent":
                    case "Rain showers: violent":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\rain.png"));
                        break;
                    case "Snow showers slight":
                    case "Snow showers heavy":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\snow-shower.png"));
                        break;
                    case "Thunderstorm: Slight or moderate":
                    case "Thunderstorm with slight hail":
                    case "Thunderstorm with heavy hail":
                        weather_icon.setIcon(load_image("C:\\Users\\chris\\OneDrive\\Desktop\\VSCODE codes\\.vscode\\Java Projects\\weather app\\src\\assets\\Thunderstorm.png"));
                        break;
                }

                String localTime = (String) weather_data.get("local_time");
                local_time_text.setText("Time & Date: " + localTime);

                double temperature = (double) weather_data.get("temperature");
                temp_text.setText(temperature + "°C");

                weather_description.setText(weather_condition);

                long humidity = (long) weather_data.get("humidity");
                humidity_text.setText("<html>Humidity<br><div style='text-align: center;'>" + humidity + "%</div></html>");

                double wind_speed = (double) weather_data.get("windspeed");
                windspeed_text.setText("<html><b>Windspeed<br><div style='text-align: center;'>" + wind_speed + "km/h</div></html>");

            }
        });
        
        panel.add(search_button);
        panel.revalidate();
        panel.repaint();
    }

    private ImageIcon load_image(String resourcePath){
        try{
            BufferedImage image = ImageIO.read(new File(resourcePath));

            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.printf("Could not find resource\n");
        return null;
    }


}