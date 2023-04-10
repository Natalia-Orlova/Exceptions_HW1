package hw1;

import java.util.Random;

public class Program {

    static Random random = new Random();

    public static void main(String[] args) {
        //task1();
        task2();
    }

    /**
     Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
     каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов
     не равны, необходимо как-то оповестить пользователя.
     */
    static void task1() {
        try {
            int[] res = getDiffArray(generateArray(), generateArray());
            for (int e : res) {
                System.out.printf("%d\t", e);
            }
            System.out.println();
        }
        catch (CustomArraySizeException e) {
            System.out.println(e.getMessage());
            System.out.printf("Длина первого массива: %d\nДлина второго массива: %d\n",
                    e.getLength1(), e.getLength2());
        }
    }

    /**
     * Метод нахождения разницы элементов 2-х массивов
     * @param arr1
     * @param arr2
     * @return Одномерный массив, каждый элемент которого равен разности элементов 2-х входящих массивов в той же ячейке
     */
    static int[] getDiffArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new NullPointerException("Оба массива должны быть заполнены");
        }
        if (arr1.length != arr2.length) {
            throw new CustomArraySizeException("Массивы должны быть одинаковой длины", arr1.length, arr2.length);
        }

        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = Math.abs(arr1[i] - arr2[i]); // выводит разницу элементов массива по модулю
        }
        return result;
    }

    /**
     * Метод генерации целочисленного массива
     * @return Одномерный массив
     */
    static int[] generateArray () {
        int [] array = new int[random.nextInt(7) + 1];
        if (random.nextInt(20) == 0)
            array = null; // Намеренная ошибка, обнуление массива
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(-10, 10);
                System.out.printf("%d ", array[i]);
            }
        }
        System.out.println();
        return array;
    }

    /** Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
     каждый элемент которого равен частному элементов двух входящих массивов в той же ячейке. Если длины массивов
     не равны, необходимо как-то оповестить пользователя. Важно: При выполнении метода единственное исключение,
     которое пользователь может увидеть - RuntimeException, т.е. ваше. */

    static void task2() {
        try {
            float[] res = getDivisionArray(generateArray(), generateArray()); //используем метод генерации массив из task1
            for (float e : res) {
                System.out.printf("%.2f\t", e); // округляем до 2 знаков после запятой
            }
            System.out.println();
        }
        catch (CustomArraySizeException e) {
            System.out.println(e.getMessage());
            System.out.printf("Длина первого массива: %d\nДлина второго массива: %d\n",
                    e.getLength1(), e.getLength2());
        }
    }

    static float[] getDivisionArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new NullPointerException("Оба массива должны быть заполнены");
        }
        if (arr1.length != arr2.length) {
            throw new CustomArraySizeException("Массивы должны быть одинаковой длины", arr1.length, arr2.length);
        }
        float[] res = new float[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i] == 0) {
                throw new RuntimeException("Делить на ноль нельзя!");
            }
            res[i] = (float) arr1[i] / arr2[i]; //преобразуем значение к целевому типу float
        }
        return res;
    }
}

class CustomArraySizeException extends RuntimeException {
    private int length1;
    private int length2;

    public int getLength1() {
        return length1;
    }

    public int getLength2() {
        return length2;
    }

    public CustomArraySizeException(String message, int length1, int length2) {
        super(message);
        this.length1 = length1;
        this.length2 = length2;
    }
}
