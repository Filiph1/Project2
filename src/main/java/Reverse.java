
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.time.Instant;
import java.time.Duration;




public class Reverse {

    private int bufferSize = 65536;
    private RandomAccessFile in;
    private FileOutputStream out;
    private BufferedOutputStream fout;
    private Instant start;
    private Instant stop;

    public void setBufferSize(int bufferSize)
    {
        this.bufferSize = bufferSize;
    }
    public int getBufferSize()
    {
        return this.bufferSize;
    }

    public void change(String s)
    {
         start = Instant.now();

        String input = Reverse.class.getResource(s).getFile();
        String outputFile = "rev"+s;
        String output = input.replace(s, outputFile);




        File file = new File(input);
        File fileOutput = new File(output);

         in = null;
         out = null;
         fout = null;

        System.out.println("Original file: " + s);
        byte [] buffer = new byte[bufferSize];
        int lb = bufferSize;


        try {
            in = new RandomAccessFile(file, "r");
            out = new FileOutputStream(fileOutput);
            fout = new BufferedOutputStream(out);

            System.out.println("Original file's size: " + in.length() + " bytes");

            long idx = in.length();
            if(idx > 0) {
                while (idx > 0) {
                    idx = idx - lb;

                    if (idx < 0) {
                        lb = lb + (int) idx;
                        idx = 0;
                        buffer = null;
                        buffer = new byte[lb];
                    }

                    in.seek(idx);

                    in.readFully(buffer, 0, lb);

                    reverse(buffer);

                    fout.write(buffer, 0, lb);

                    fout.flush();
                }
            }


            if (in != null) {
                in.close();
            }
            if (fout != null) {

                fout.close();
                out.close();
            }
             stop = Instant.now();
            long timeElapsed = Duration.between(start, stop).toMillis();
            System.out.println("It took: " + timeElapsed + " milliseconds to reverse file");
            System.out.println("The output file name: " + outputFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (java.io.IOException i) {
            i.printStackTrace();


        } catch (SecurityException ex) {
            System.out.println(ex.getMessage());

        }


    }

    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

}