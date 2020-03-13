import java.lang.Math;
public class findClosestZero {
    public static void main(String[] args){
        int [] list =  {10,-1,5,7,9};
        findClosestZero(list);
    }
    public static void findClosestZero(int[] list){
          int bestIndex = 0;
          int minVal = list[0];
           for(int i = 0; i < list.length; i++){
               int testVal = Math.abs(0-list[i]);
               if(minVal > testVal){
                   minVal = testVal;
                   bestIndex = i;
               }
           }
           System.out.print("Smallest diff: " + list[bestIndex]);
    }
}
