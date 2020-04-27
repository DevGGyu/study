package ch12_generics_enumeration_annotation;

class Fruit3 {
    public String toString() {
        return "Fruit";
    }
}

class Apple3 extends Fruit3 {
    public String toString() {
        return "Apple";
    }
}

class Grape3 extends Fruit3 {
    public String toString() {
        return "Grape";
    }
}

class Juice {
    String name;

    public Juice(String name) {
        this.name = name + "Juice";
    }

    @Override
    public String toString() {
        return name;
    }
}

class Juicer {
    static Juice makeJuice(FruitBox3<? extends Fruit3> box) {
        String tmp = "";

        for (Fruit3 f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}

public class FruitBoxEx3 {
    public static void main(String[] args) {
        FruitBox3<Fruit3> fruit3FruitBox3 = new FruitBox3<>();
        FruitBox3<Apple3> apple3FruitBox3 = new FruitBox3<>();

        fruit3FruitBox3.add(new Apple3());
        fruit3FruitBox3.add(new Grape3());
        apple3FruitBox3.add(new Apple3());
        apple3FruitBox3.add(new Apple3());

        System.out.println(Juicer.makeJuice(fruit3FruitBox3));
        System.out.println(Juicer.makeJuice(apple3FruitBox3));
    }
}

class FruitBox3<T extends Fruit3> extends Box<T> {
}