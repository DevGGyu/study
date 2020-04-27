package ch12_generics_enumeration_annotation;

class NewClass {
    int newField;

    int getNewField() {
        return newField;
    }

    @Deprecated
    int oldField;

    @Deprecated
    public int getOldField() {
        return oldField;
    }
}

public class AnnotationEx2 {
    public static void main(String[] args) {
        NewClass nc = new NewClass();

        nc.oldField = 10;
        System.out.println(nc.getOldField());
    }
}
