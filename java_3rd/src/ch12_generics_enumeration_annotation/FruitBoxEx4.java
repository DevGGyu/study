package ch12_generics_enumeration_annotation;

import java.util.Comparator;

import static java.util.Collections.sort;

class Fruit4 {
    String name;
    int weight;

    public Fruit4(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + "(" + weight + ")";
    }
}

class Apple4 extends Fruit4 {
    public Apple4(String name, int weight) {
        super(name, weight);
    }
}

class Grape4 extends Fruit4 {
    public Grape4(String name, int weight) {
        super(name, weight);
    }
}

class AppleComp implements Comparator<Apple4> {
    @Override
    public int compare(Apple4 o1, Apple4 o2) {
        return o2.weight - o1.weight;
    }
}

class GrapeComp implements Comparator<Grape4> {
    @Override
    public int compare(Grape4 o1, Grape4 o2) {
        return o2.weight - o1.weight;
    }
}

class FruitComp implements Comparator<Fruit4> {
    @Override
    public int compare(Fruit4 o1, Fruit4 o2) {
        return o1.weight - o2.weight;
    }
}

public class FruitBoxEx4 {
    public static void main(String[] args) {
        FruitBox4<Apple4> apple4FruitBox4 = new FruitBox4<>();
        FruitBox4<Grape4> grape4FruitBox4 = new FruitBox4<>();

        apple4FruitBox4.add(new Apple4("GreenApple", 300));
        apple4FruitBox4.add(new Apple4("GreenApple", 100));
        apple4FruitBox4.add(new Apple4("GreenApple", 200));

        grape4FruitBox4.add(new Grape4("GreenGrape", 400));
        grape4FruitBox4.add(new Grape4("GreenGrape", 300));
        grape4FruitBox4.add(new Grape4("GreenGrape", 200));

        sort(apple4FruitBox4.getList(), new AppleComp());
        sort(grape4FruitBox4.getList(), new GrapeComp());
        System.out.println(apple4FruitBox4);
        System.out.println(grape4FruitBox4);
        System.out.println();
        sort(apple4FruitBox4.getList(), new FruitComp());
        sort(grape4FruitBox4.getList(), new FruitComp());
        System.out.println(apple4FruitBox4);
        System.out.println(grape4FruitBox4);
    }
}

class FruitBox4<T extends Fruit4> extends Box<T> {
}