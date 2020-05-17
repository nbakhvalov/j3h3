import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class homework {


    public static void main(String[] args) {
        try {
            //task1();
            //task2();
            task3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
    public static void task1() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("texts/testOne.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
        }

        byte[] b = out.toByteArray();
        System.out.println(Arrays.toString(b));
        in.close();
        out.close();
    }

    // 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
    public static void task2() throws IOException {
        long a = System.currentTimeMillis();

        ArrayList<InputStream> al = new ArrayList<>();
        al.add(new FileInputStream("texts/testOne.txt"));
        al.add(new FileInputStream("texts/testTwo.txt"));
        al.add(new FileInputStream("texts/testTree.txt"));
        al.add(new FileInputStream("texts/testFour.txt"));
        al.add(new FileInputStream("texts/testFive.txt"));


        BufferedInputStream in = new BufferedInputStream(new SequenceInputStream(Collections.enumeration(al)));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("out.txt"));
        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
            System.out.println((char) x);
        }
        in.close();
        out.close();

        System.out.println(System.currentTimeMillis() - a);
    }


    // 3 прочитать данные из книги (с определнной страницы)
    // RandomAccessFile позволяет прыгать по потоку назад, без переоткрытия файлы
    public static void task3() throws IOException {
        final int PAGE_SIZE = 1800;
        RandomAccessFile raf = new RandomAccessFile("texts/testOne.txt", "r");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите страницу: ");
        int p = sc.nextInt() - 1;
        raf.seek(p * PAGE_SIZE);
        byte[] barr = new byte[1800];
        raf.read(barr);
        System.out.println(new String(barr));
        raf.close();
    }
}
