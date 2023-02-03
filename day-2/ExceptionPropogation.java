public class ExceptionPropogation {
    public static void main(String[] args){
        try{
            throwsExceptionA();
        } catch(Exception e){
            System.out.println("Exception occured:");
            e.printStackTrace();
        }
    }
    public static void throwsExceptionA () throws Exception {
        throwsExceptionB();
    }
    public static void throwsExceptionB () throws Exception {
        throwsExceptionC();
    }
    public static void throwsExceptionC () throws Exception {
        throw new Exception();
    }
}