package ch15_i_o;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamEx1 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("123.txt");
            // BufferedOutputStream의 버퍼 크기를 5로 한다.
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
            // 파일 123.txt에 1부터 9까지 출력한다.
            for (int i = '1'; i <= '9'; i++) {
                bos.write(i);
            }

            // fos.close(); // 1부터 5까지만 출력된다.
            bos.close(); // 9까지 다 나오게 하려면 bos를 close 해주면 되고, 이때 fos도 같이 close된다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
