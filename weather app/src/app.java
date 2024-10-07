import javax.swing.SwingUtilities;

public class app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                weather_gui gui = new weather_gui();
                gui.setVisible(true);
            }

        });
    }
}