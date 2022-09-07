import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("So, du möchtest also ein Bitmap erstellen?!");

        System.out.println("Wie breit soll das Bitmap sein? (min. 100 Pixel)");
        int width = Integer.parseInt(reader.readLine());

        System.out.println("Wie hoch soll das Bitmap sein? (min. 100 Pixel)");
        int height = Integer.parseInt(reader.readLine());

        System.out.println("Was für ein Bitmap möchtest du erstellen?");
        System.out.println("1 für Schachbrettmuster");
        System.out.println("2 für zufällige Pixel");
        System.out.println("3 für Import/Export");
        int option = Integer.parseInt(reader.readLine());

        // generate Bitmap
        String filename = generateFilename();
        Bitmap bitmap = new Bitmap(width, height);
        if (option == 1) {
            if (bitmap.getHeight() != bitmap.getWidth() || bitmap.getHeight() % 8 != 0 || bitmap.getWidth() % 8 != 0) {
                System.out.println("Die Dimensionen (quadratische Form, Kantenlänge Vielfaches von 8) deines Bitmaps passen nicht zu einem Schachbrett! Schauen wir mal was das gibt ...");
            }
            generateChess(bitmap);
        } else if (option == 2) {
            generateRandomBitmap(bitmap);
        } else if (option == 3) {
            System.out.println("Gib einen Dateinamen ein. Die Datei muss sich im Projektverzeichnis befinden.");
            filename = reader.readLine();
            Bitmap.load(filename);
        } else {
            System.out.println("Du hast es nicht geschafft valide Daten einzugeben! Traurig ...");
        }
        bitmap.save(filename);

        System.out.println("Vielen Dank. Deine Datei heißt: " + filename);
    }

    private static void generateRandomBitmap(Bitmap bitmap) {
        Random pixel = new Random();
        for (int row = 0; row < bitmap.getHeight(); row++) {
            for (int column = 0; column < bitmap.getWidth(); column++) {
                bitmap.setPixel(row, column, pixel.nextBoolean());
            }
        }
    }

    private static void generateChess(Bitmap bitmap) {
        boolean color = true;

        int squareBorder = bitmap.getHeight() / 8;
        squareBorder = squareBorder == 0 ? 1 : squareBorder;

        for (int row = 0; row < bitmap.getHeight(); row++) {
            for (int column = 0; column < bitmap.getWidth(); column++) {
                boolean c;

                // switch color of field horizontal
                if (numberIsOdd(column / squareBorder)) {
                    c = color;
                } else {
                    c = !color;
                }
                bitmap.setPixel(row, column, c);
            }

            // switch color of field vertical
            if (row % squareBorder == 0 && row != 0) {
                color = !color;
            }
        }
    }

    private static boolean numberIsOdd(int number) {
        return number % 2 != 0;
    }

    private static String generateFilename() {
        Date date = new Date();
        return "bitmap_" + date.getTime() + ".bmp";
    }
}
