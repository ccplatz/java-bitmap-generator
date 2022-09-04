import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

public class Bitmap {

    private int width;
    private int height;
    private int[][] data;

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new int[this.width][this.height];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPixel(int x, int y, boolean c) {
        this.data[x][y] = c ? 1 : 0;
    }

    public void save(String filename) {
        try {
            PrintStream output = new PrintStream(filename);
            output.println("P1");
            output.println(this.width + " " + this.height);

            for (int i = 0; i < this.height; i++) {
                String line = "";
                for (int j = 0; j < this.width; j++) {
                    line += this.data[i][j];
                }
                output.println(line);
            }
            output.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static int load(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        String line = scanner.nextLine();
        String head = "P1";
        if (!line.equals(head)) {
            System.out.println("This ist not a Bitmap!");
            return 1;
        }

        int wdith = Integer.parseInt(scanner.next());
        int height = Integer.parseInt(scanner.next());
        Bitmap bitmap = new Bitmap(wdith, height);

        for (int i = 0; i < bitmap.height; i++) {
            for (int j = 0; j < bitmap.width; j++) {
                int next = Integer.parseInt(scanner.next());
                boolean c = next == 0 ? false : true;
                bitmap.setPixel(i, j, c);
            }
        }


        Date date = new Date();
        String filename1 = "bitmap_" + date.getTime() + ".bmp";

        bitmap.save(filename1);
        return 1;
    }
}
