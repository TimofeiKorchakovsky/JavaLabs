import java.util.Random;
import java.util.Scanner;
//Корчаковский Тимофей 5 группа 2 курс
//21.	Элемент матрицы называется локальным минимумом, если он строго меньше всех имеющихся
// у него соседей. Соседями элемента ajj в матрице назовем элементы aki
// с i-1<=k<=i+1, j-1<=l<=j+1,(k,l)!=(i,j).
// Подсчитать количество локальных минимумов заданной матрицы.
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number of rows: ");
        int n = in.nextInt();
        System.out.print("Input a number of columns: ");
        int m=in.nextInt();
        int [] [] matrix=new int [n] [m];
        Random numb = new Random();
        for(int i = 0;i < n;++i)
        {
            for(int j = 0;j < m;++j)
            {
                matrix[i][j]=numb.nextInt(10);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d\t", matrix[i][j]);
            }
            System.out.println();
        }
        int localMinCount = countLocalMinima(matrix);

        System.out.println("Number of local minima: " + localMinCount);
    }

    public static int countLocalMinima(int[][] matrix) {
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        int count = 0;

        for (int i = 1; i < rowCount - 1; i++) {
            for (int j = 1; j < columnCount - 1; j++) {
                int currentElement = matrix[i][j];
                boolean isLocalMin = true;


                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (!(k == i && l == j) && matrix[k][l] <= currentElement)
                        {
                            isLocalMin = false;

                        }
                    }

                }

                if (isLocalMin) {
                    count++;
                }
            }
        }

        return count;
    }
}
