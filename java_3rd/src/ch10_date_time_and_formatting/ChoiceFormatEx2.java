package ch10_date_time_and_formatting;

import java.text.ChoiceFormat;

public class ChoiceFormatEx2 {
    public static void main(String[] args) {
        // # 은 경계값을 범위에 포함
        // < 는 경계값을 범위에 포함시키지 않음
        String pattern = "60#D|70#C|80<B|90#A";
        int[] scores = {91, 90, 80, 88, 70, 52, 60};

        ChoiceFormat form = new ChoiceFormat(pattern);
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i] + ":" + form.format(scores[i]));
        }
    }
}
