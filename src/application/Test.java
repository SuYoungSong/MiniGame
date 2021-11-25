package application;

public class Test {
    public static void retuenTest(){
        for(int i = 0; i < 10; i++){

            switch(i){
                case 1:
                    System.out.println("Case 1");
                    break;
                case 2:
                    System.out.println("Case 2");
                    break;
                case 3:
                    System.out.println("Case 3");
                    return;
                case 4:
                    System.out.println("Case 4");
                    break;
            }
            System.out.println("반복중 >> " + i);
        }
    }



    public static void main(String[] args) {

        retuenTest();

    }
}
