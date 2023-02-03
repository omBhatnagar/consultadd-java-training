public class Fibonacci {
    public static void main(String[] args){
        int num1 = 0, num2 = 1, result;
        System.out.print("Fibonacci Series: ");
        System.out.print(num1 + " " + num2);
        for(int i = 0; i<10; i++){
            result = num1 + num2;
            num1 = num2;
            num2 = result;
            System.out.print(" "+result);
        }
    }
}