import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class AppNoir extends GraphicsApp {

    private static final Color RED = new Color(234, 49, 63); // "Selbstgemischter" RGB-Farbe (rot)
    private static final Color YELLOW = new Color(234, 182, 56); // "Selbstgemischter" RGB-Farbe (gelb)
    private static final Color GREEN = new Color(76, 149, 80); // "Selbstgemischter" RGB-Farbe (grün)
    private static final Color BLUE = new Color(53, 129, 184); // "Selbstgemischter" RGB-Farbe (blau)
    private static final Color TURQUOISE = new Color(9, 82, 86); // "Selbstgemischter" RGB-Farbe (türkis)
    private static final Color PURPLE = new Color(55, 39, 114); // "Selbstgemischter" RGB-Farbe (lila)
    private static final Color CREAM = new Color(241, 255, 250); // "Selbstgemischter" RGB-Farbe (creme)
    private static final Color GREY = new Color(47, 61, 76); // "Selbstgemischter" RGB-Farbe (grau)
    @Override
    public void initialize() {
        setCanvasSize(500, 500);
    }

    @Override
    public void draw() {
        drawBackground(CREAM);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch("AppNoir");
    }
}
