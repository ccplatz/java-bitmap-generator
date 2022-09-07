import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Bitmap {

    private int width;
    private int height;
    private int[][] data;

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new int[this.height][this.width];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPixel(int height, int width, boolean c) {
        this.data[height][width] = c ? 1 : 0;
    }

    public void save(String filename) {
        try {

            // print the file header
            PrintStream output = new PrintStream(filename);
            output.println("P1");
            output.println(this.width + " " + this.height);

            // go through rows and columns and write pixels line by line
            for (int row = 0; row < this.height; row++) {
                String line = "";
                for (int column = 0; column < this.width; column++) {
                    line += this.data[row][column];
                }
                output.println(line);
            }
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void load(String filename) throws FileNotFoundException {

        // create file and scanner instances
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // check for bitmap header
        String headlineOfFile = scanner.nextLine();
        String headOfBitmap = "P1";
        if (!headlineOfFile.equals(headOfBitmap)) {
            System.out.println("This ist not a Bitmap!");
        }

        // read height and width, create Bitmap
        int width = Integer.parseInt(scanner.next());
        int height = Integer.parseInt(scanner.next());
        scanner.nextLine(); // go to next line and leave the linefeed
        Bitmap bitmap = new Bitmap(width, height);

        // read line by line and character by character from file
        int pixel = 0;
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int column = 0; column < line.length(); column++) {
                // read character, make a string from it, parse to int
                pixel = Integer.parseInt(String.valueOf(line.charAt(column)));
                boolean c = pixel != 0;
                bitmap.setPixel(row, column, c);
            }
            row++;
        }

        // save read file
        String filenameNew = "loaded_and_exported_" + filename;
        bitmap.save(filenameNew);
    }
}
