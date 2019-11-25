public class ReverseTest {
    public static void main(String[] args)
    {
        Reverse r = new Reverse();
        r.change("largefile.dat");
        r.change("alice29.txt");
        r.change("test.txt");

        // System.out.println(r.getBufferSize());
        // r.setBufferSize(1024);
        // System.out.println(r.getBufferSize());
        // r.change("largefile.dat");
    }
}
