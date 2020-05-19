package ch15_i_o;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx3 {
    public static void main(String[] args) {
        byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] outSrc = null;

        byte[] temp = new byte[4]; // 이전 예제와 배열의 크기가 다르다.

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        System.out.println("Input Source : " + Arrays.toString(inSrc));

        try {
            while (input.available() > 0) {
                // 틀린 예제
//                input.read(temp);
//                output.write(temp);

                // 정상 작동 코드 예제
                int len = input.read(temp);
                output.write(temp, 0, len);
//                System.out.println("temp : " + Arrays.toString(temp));

                outSrc = output.toByteArray();
                printArray(temp, outSrc);
            }
        } catch (IOException e) {
        }
    }

    private static void printArray(byte[] temp, byte[] outSrc) {
        System.out.println("temp : " + Arrays.toString(temp));
        System.out.println("Output Source : " + Arrays.toString(outSrc));
    }
}
