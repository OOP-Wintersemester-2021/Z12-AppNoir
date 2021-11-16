import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class AppNoir extends GraphicsApp {


    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 600;
    private static final String IMAGE_PATH = "assets/atlantic-puffin.png";
    private static final int FRAME_DELAY_BETWEEN_MODE_TOGGLE = 300;

    private Image originalImage;
    private Image grayscaleImage;
    private boolean drawGrayscaleImage;
    private int frameCounter = 0;

    @Override
    public void initialize() {
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        drawGrayscaleImage = false;
        originalImage = createImage(IMAGE_PATH, false);
        grayscaleImage = createImage(IMAGE_PATH, true);
    }

    private Image createImage(String path, boolean grayscale) {
        Image image = new Image(0, 0, path);
        image.setWidth(getWidth(), true);
        image.setHeight(getHeight(), true);
        if (grayscale) {
            int[][] greyscalePixels = getGreyscalePixelsFor(image);
            image.setPixelArray(greyscalePixels);
        }
        return image;
    }

    private int[][] getGreyscalePixelsFor(Image image) {
        int[][] pixels = image.getPixelArray();
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                Color color = new Color(pixels[x][y]);
                int colorAverage = (color.red() + color.green() + color.blue()) / 3;
                Color grayscaleColor = new Color(colorAverage, colorAverage, colorAverage);
                pixels[x][y] = grayscaleColor.toInt();
            }
        }
        return pixels;
    }

    @Override
    public void draw() {
        if(frameCounter % FRAME_DELAY_BETWEEN_MODE_TOGGLE == 0) {
            drawGrayscaleImage = !drawGrayscaleImage;
        }
        frameCounter++;
        drawImage();
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
