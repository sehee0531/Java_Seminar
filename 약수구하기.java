import java.util.Scanner;

public class 약수구하기 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("약수를 구할 숫자를 입력하세요>>");
        
        int num = sc.nextInt();
        int a=0;

        if(num<1)
        {
            System.out.println("1보다 큰 숫자를 입력해주세요");
        }
        else{
            for(int i=1;i<=num;i++){
                if(num%i==0)
                {
                    System.out.print(i+" ");
                    a=a+i;
                }
           
            }
            System.out.println("총합>>"+a);
            sc.close();
        }

        

    }
    
}
