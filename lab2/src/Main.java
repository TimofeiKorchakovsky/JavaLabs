import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
//Корчаковский Тимофей 5 группа 2 курс
// Выведите номера столбцов, все элементы, которых четны.
//Для каждого столбца с  отрицательным  элементом
// на главной диагонали вывести его номер и сумму всех элементов этого столбца.
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number of lines: ");
        int n = in.nextInt();
        System.out.print("Input a number of columns: ");
        int m=in.nextInt();
        int [] [] matrix=new int [n] [m];
        Random numb = new Random();
        for(int i = 0;i < n;++i)
        {
            for(int j = 0;j < m;++j)
            {
                matrix[i][j]=numb.nextInt() % 100;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d\t", matrix[i][j]);
            }
            System.out.println();
        }
        int count = 0;
        System.out.println("Even columns:");
        for(int i =0;i<m;i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (matrix[j][i] % 2 == 0 && matrix[j][i]!=0)
                {
                    count++;
                }
            }
            if(count==n)
            {
                System.out.printf("%4d\t",i);
                System.out.print(" ");
            }
            count = 0;
        }
        count =0;
        int [] summ=new int[m];
        for(int i = 0;i<n;++i)
        {
            int j=i;
            if(matrix[i][j]<0)
            {
                summ[count]=i;
                count++;
            }
        }
        int sum=0;
        System.out.println(" ");
        System.out.println("Columns with negative element:");
        for(int i=0;i<count;i++)
        {
            System.out.printf("%4d\t",summ[i]);
            System.out.print(" ");
        }
        System.out.println(" ");
        for(int i = 0;i<count;++i)
        {
            for(int j = 0;j<n;++j)
            {
                sum+=matrix[j][summ[i]];
            }
            System.out.printf("%4d\t",sum);
            System.out.print(" ");
            sum=0;
        }
    }
}