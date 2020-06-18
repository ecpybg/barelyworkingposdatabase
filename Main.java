import javax.swing.JFrame;
import java.io.IOException;
class Main{
    public static void main(String[] args) throws IOException{
        Gui guiObject = new Gui();
        guiObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiObject.setSize(300, 125);
        guiObject.setResizable(false);
        guiObject.setVisible(true);
    }
}