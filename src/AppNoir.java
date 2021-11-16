import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * In dieser App wird ein Bild aus einer PNG-Datei angezeigt. Alle 180 Frames (~ 3 Sekunden) wechselt
 * die Darstellung dabei zwischen der originalen, farbigen Variante und eine Graustufendarstellung.
 */
public class AppNoir extends GraphicsApp {

    // Breite des Anwendungsfensters
    private static final int WINDOW_WIDTH = 900;
    // Höhe des Anwendungsfensters
    private static final int WINDOW_HEIGHT = 600;
    // Relativer Pfad zur Bilddatei innerhalb des Projektordners
    private static final String IMAGE_PATH = "assets/atlantic-puffin.png";
    // Anzahl der Frames, nach der zwischen Farb- und Graustufendarstellung umgeschaltet wird
    private static final int FRAME_DELAY_BETWEEN_MODE_TOGGLE = 180;

    // Bild in Originalform
    private Image originalImage;
    // Bild als Graustufe
    private Image grayscaleImage;
    // Indikator (Flag) ob im aktuellen Frame die Originalform oder die Graustufe gezeichnet werden soll
    private boolean drawGrayscaleImage ;
    // Zähler für die Anzahl der Frames seit dem letzten Wechsel des Darstellungsmodus
    private int framesSinceLastModeToggle;

    @Override
    public void initialize() {
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        drawGrayscaleImage = false; // Deaktiviert zu Beginn die Graustufendarstellung
        framesSinceLastModeToggle = 0; // Setzt den Zähler für die Frames auf Null
        // Erstellt aus der Datei im angegebenen Pfad ein Bild mit den Originalfarben
        originalImage = createImage(IMAGE_PATH, false);
        // Erstellt aus der Datei im angegebenen Pfad ein Bild mit Graustufen
        grayscaleImage = createImage(IMAGE_PATH, true);
    }

    /**
     * Die Methode erzeugt ein Anwendungsfüllendes Image-Objekt aus der angegebenen Datei. Falls für den Parameter
     * grayscale der Wert true übergeben wird, werden die Farbwerte des Bildes vor Rückgabe zu Graustufen umgewandelt.
     *
     * @param path Relativer Pfad zur Bilddatei (innerhalb des Projektordners)
     * @param grayscale Hinweis, ob das Bild im Original (false) oder als Graustufe (true) erstellt werden soll
     * @return Das geladene und erstellte Image-Objekt
     */
    private Image createImage(String path, boolean grayscale) {
        // Erstellt das Bild und positioniert es in der oberen, linken Ecke
        Image image = new Image(0, 0, path);
        // Skaliert die Breite des Bildes um die Breite der Anwendungsfläche abzudecken
        image.setWidth(getWidth(), true);
        // Skaliert die Höhe des Bildes um die Höhe der Anwendungsfläche abzudecken
        image.setHeight(getHeight(), true);
        // Wandelt das Bild bei Bedarf in Graustufen um
        if (grayscale) {
            // Alle Pixel des Bildes als Graustufe
            int[][] greyscalePixels = getGreyscalePixelsFor(image);
            // Überschreiben der Originalpixel mit den Graustufenwerten
            image.setPixelArray(greyscalePixels);
        }
        return image;
    }

    /**
     * Gibt eine zweidimensionale Pixelmatrix aller Farbwerte aus dem übergebenen Bild zurück,
     * in der alle Originalfarben in Graustufen umgewandelt wurden.
     *
     * @param image Das Originalbild
     * @return Die Pixelmatrix mit den Graustufenwerten
     */
    private int[][] getGreyscalePixelsFor(Image image) {
        // Auslesen der (farbigen) Originalpixel aus dem Bild
        int[][] pixels = image.getPixelArray();
        // Wir iterieren mit zwei Schleifen ...
        for (int x = 0; x < pixels.length; x++) {
            // ... über alle Pixel des Bildes ...
            for (int y = 0; y < pixels[0].length; y++) {
                // ... und lesen die Farbwerte aus: Aus den int-Werten im Array können Farb-Objekte erstellt werden
                Color color = new Color(pixels[x][y]);
                // Wir erzeugen einen Durchschnittswert aus den drei Farbkanälen
                int colorAverage = (color.red() + color.green() + color.blue()) / 3;
                // Aus dem Durchschnittswert wird eine neue Farbe gemischt (das ist die einfachste Form der Graustufe)
                Color grayscaleColor = new Color(colorAverage, colorAverage, colorAverage);
                // Die Graustufe wird, als int-Wert, in das Pixel-Array gespeichert
                pixels[x][y] = grayscaleColor.toInt();
            }
        }
        // Das Array mit den modifizierten Pixel- bzw. Farbwerten wird zurückgegeben
        return pixels;
    }

    @Override
    public void draw() {
        drawImage();
        // Mit jedem Frame wird der Zähler inkrementiert ...
        framesSinceLastModeToggle++;
        // ... erreichen wir den angegebenen Schwellenwert ...
        if (framesSinceLastModeToggle == FRAME_DELAY_BETWEEN_MODE_TOGGLE) {
            // ... schalten wir zwischen den Anzeigemodi um ...
            drawGrayscaleImage = !drawGrayscaleImage;
            // ... und setzen den Zähler zurück
            framesSinceLastModeToggle = 0;
        }
    }

    private void drawImage() {
        if (drawGrayscaleImage) {
            grayscaleImage.draw();
        } else {
            originalImage.draw();
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch("AppNoir");
    }
}
