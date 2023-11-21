import java.util.Scanner;
import java.util.Vector;
import java.util.Random;
//Корчаковский Тимофей 5 группа 2 курс
//17.Подсчитать количество столбцов заданной матрицы,
// которые составлены из попарно различных чисел.
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number of rows: ");
        int rows = in.nextInt();
        System.out.print("Input a number of columns: ");
        int columns=in.nextInt();
        Vector<Vector<Integer>> matrix = new Vector<Vector<Integer>>();
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            Vector<Integer> row = new Vector<Integer>();
            for (int j = 0; j < columns; j++) {
                int randomNumber = random.nextInt(20); // Генерация случайного числа от 0 до 99
                row.add(randomNumber);
            }
            matrix.add(row);
        }
        for (Vector<Integer> row : matrix) {
            System.out.println(row);
        }
        int columnCount = countColumnsWithUniqueNumbers(matrix);
        System.out.println("The number of columns with pairwise different numbers: " + columnCount);
    }

    public static int countColumnsWithUniqueNumbers(Vector<Vector<Integer>> matrix) {
        int rowCount = matrix.size();
        int columnCount = matrix.get(0).size();
        int count = 0;

        for (int j = 0; j < columnCount; j++) {
            Vector<Integer> uniqueNumbers = new Vector<>();
            boolean isColumnUnique = true;

            for (int i = 0; i < rowCount; i++) {
                int element = matrix.get(i).get(j);
                if (uniqueNumbers.contains(element)) {
                    isColumnUnique = false;
                    break;
                }
                uniqueNumbers.add(element);
            }

            if (isColumnUnique) {
                count++;
            }
        }

        return count;
    }
}
