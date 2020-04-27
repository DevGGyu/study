package ch12_generics_enumeration_annotation;

class Fruit2 implements Eatable {
    public String toString() {
        return "Fruit";
    }
}

class Apple2 extends Fruit2 {
    public String toString() {
        return "Apple";
    }
}

class Grape2 extends Fruit2 {
    public String toString() {
        return "Grape";
    }
}

class Toy2 {
    public String toString() {
        return "Toy";
    }
}

interface Eatable {
}

public class FruitBoxEx2 {
    public static void main(String[] args) {
        FruitBox2<Fruit2> fruitBox = new FruitBox2<>();
        FruitBox2<Apple2> appleBox = new FruitBox2<>();
        FruitBox2<Grape2> grapeBox = new FruitBox2<>();
//        FruitBox<Grape> grapeBox = new FruitBox<Apple>(); // 에러. 타입 불일치
//        FruitBox<Toy> toyBox = new FruitBox<Toy>(); // 에러

        fruitBox.add(new Fruit2());
        fruitBox.add(new Apple2());
        fruitBox.add(new Grape2());
        appleBox.add(new Apple2());
//        appleBox.add(new Grape2()); // 에러. Grape는 Apple의 자손이 아님
        grapeBox.add(new Grape2());

        System.out.println("fruitBox-" + fruitBox);
        System.out.println("appleBox-" + appleBox);
        System.out.println("grapeBox-" + grapeBox);
    }
}

class FruitBox2<T extends Fruit2 & Eatable> extends Box<T> {
}