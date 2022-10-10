package Root.Cntroller;


public class vv {
    public static void main(String[]args){
String string[]=new String[3];
       string[0]="flow";
       string[1]="flower";
string[2]="flight";
    longestCommonPrefix(string);
    }
    public static void longestCommonPrefix(String[] strs) {
        char s[] = strs[0].toCharArray();
        int count = 0;
        int countr = 0;
        for (int i = 0; i < strs.length; i = i + 1) {

            for (int j = 0; j < strs.length; j = j + 1) {
                char d = strs[i].toCharArray()[i];
                char dd = strs[j].toCharArray()[j];
                if (d == dd) {
                    count = count + 1;
                }
            }
            if (count == strs.length) {
                countr = countr + 1;
            }
            count = 0;
        }
        System.out.println(countr);
    }
}


