package ru.javawebinar.basejava;

/* реализовать метод через стрим int minValue(int[] values).
Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3}
вернет 123, а {9,8} вернет 89

реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная -
удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional -
решение в один стрим. */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sumOddOrEven = integers.stream().mapToInt(i -> i).sum() % 2;
        return integers.stream().filter(x -> (x % 2) != sumOddOrEven).collect(Collectors.toList());
    }


    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> 10 * x + y);
    }

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1,2,3,3,2,3}));
        System.out.println(minValue(new int[]{9, 8}));
        System.out.println(oddOrEven(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))));
        System.out.println(oddOrEven(new ArrayList<>(Arrays.asList(1, 2, 3, 4))));
    }
}
