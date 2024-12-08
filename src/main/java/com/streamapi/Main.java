package com.streamapi;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

//        Создайте список заказов с разными продуктами и их стоимостями.
        var orders1 = new ArrayList<>(orders.stream()
                .collect(Collectors.toMap(
                        Order::getProduct,
                        order -> order,
                        (a, b) -> a
                ))
                .values());
        System.out.println("Создайте список заказов с разными продуктами и их стоимостями.");
        System.out.println(orders1);
        System.out.println();

//        Группируйте заказы по продуктам.
        var orders2 = orders.stream()
                .collect(
                        Collectors.groupingBy(Order::getProduct)
                );
        System.out.println("Группируйте заказы по продуктам.");
        System.out.println(orders2);
        System.out.println();

//        Для каждого продукта найдите общую стоимость всех заказов
        var orders3 = orders.stream()
                .collect(
                        Collectors.groupingBy(
                                Order::getProduct,
                                Collectors.summingDouble(Order::getCost))
                );
        System.out.println("Для каждого продукта найдите общую стоимость всех заказов");
        System.out.println(orders3);
        System.out.println();

//        Отсортируйте продукты по убыванию общей стоимости.
        var orders4 = orders.stream()
                .collect(
                        Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
                .collect(Collectors.toList());
        System.out.println(" Отсортируйте продукты по убыванию общей стоимости.");
        System.out.println(orders4);
        System.out.println();

//        Выберите три самых дорогих продукта.
        var orders5 = orders.stream()
                .collect(Collectors.toMap(
                        Order::getProduct,
                        Order::getCost,
                        Math::max
                        ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Выберите три самых дорогих продукта.");
        System.out.println(orders5);
        System.out.println();

//        Выберите три самых дорогих продукта.
        var orders52 = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Выберите три самых дорогих продукта.");
        System.out.println(orders52);
        System.out.println();

//        Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
        var orders6 = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .collect(Collectors.teeing(
                        Collectors.toList(),
                        Collectors.summingDouble(Order::getCost),
                        AbstractMap.SimpleEntry::new
                ));

        System.out.println("Выведите результат: список трех самых дорогих продуктов и их общая стоимость.");
        System.out.println(orders6);
        System.out.println();
    }
}