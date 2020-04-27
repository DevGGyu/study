package ch12_generics_enumeration_annotation;

import java.util.Arrays;

class MyArrayList<T> {
    T[] arr;

    @SafeVarargs
    @SuppressWarnings("varargs")
    MyArrayList(T... arr) {
        this.arr = arr;
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // 애너테이션 추가
//    @SuppressWarnings("unchecked")
    public static <T> MyArrayList<T> asList(T... a) {
        return new MyArrayList<>(a);
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}

public class AnnotationEx4 {
    //    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        MyArrayList<String> list = MyArrayList.asList("1", "2", "3");
        System.out.println(list);
    }
}
