package ch12_generics_enumeration_annotation;

import java.util.ArrayList;

public class AnnotationEx3 {
    @SuppressWarnings("deprecation") // deprecation 관련 경고 억제
    public static void main(String[] args) {
        NewClass nc = new NewClass();

        nc.oldField = 10; //@Deprecated가 붙은 대상을 사용
        System.out.println(nc.getOldField()); //@Deprecated가 붙은 대상을 사용

        @SuppressWarnings("unchecked") // 제네릭 관련 경고 억제
        ArrayList<NewClass> list = new ArrayList(); // 타입을 지정하지 않음
        list.add(nc);
    }
}
