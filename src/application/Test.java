package application;

public class Test {
    public static void main(String[] args) {
        String str = "@@payload:##nickname##angryduck22";
        String str2 = "@@payload:##nickname##angryduck22##true";
        String str3 = "@@payload:##checkNickname##Foxconn";

        if(str3.contains("@@payload:")) {
            String payload[] = str3.replace("@@payload:##", "").split("##");
            System.out.println("payload >> ");
            for(int i = 0; i < payload.length; i++){
                System.out.println(i + ": " + payload[i]);
            }
            System.out.println();
        }

        String phoneNum = "010-8888-2231-00321";
        String removeFirst = phoneNum.replace("010-", "");
        String splitPhoneNum[] = removeFirst.split("-");
        for(int i = 0; i < splitPhoneNum.length; i++){
            System.out.println(i + ": " + splitPhoneNum[i]);
        }
    }
}

