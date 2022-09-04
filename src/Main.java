import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Bitmap bitmap = new Bitmap(400, 400);
        //generateRandomBitmap(bitmap);
        //generateChess(bitmap);
        //bitmap.save(generateFilename());

        String filename = "bitmap_1661933126979.bmp";
        Bitmap.load(filename);
    }

    private static void generateRandomBitmap(Bitmap bitmap) {
        Random pixel = new Random();
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                bitmap.setPixel(i, j, pixel.nextBoolean());
            }
        }
    }

    // bisher nur Streifen, fertigstellen!
    private static void generateChess(Bitmap bitmap) {
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                boolean c;
                if (numberIsOdd(ceil(i / 50))) {
                    c = true;
                } else {
                    c = false;
                }
                bitmap.setPixel(i, j, c);
            }
        }
    }

    private static boolean numberIsOdd(int number) {
        return number % 2 == 0 ? false : true;
    }

    private static int ceil(double number) {
        return (int) Math.ceil(number);
    }

    private static String generateFilename() {
        Date date = new Date();
        return "bitmap_" + date.getTime() + ".bmp";
    }
}
