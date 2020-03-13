public class BinarySearch {

    public static void main(String[] args){

        int[] testList = {2,3,5,7,10,20,30,35};
        binarySearch(testList,3);

    }
    public static void binarySearch(int[] sortedList,int soughtObject){
        boolean found = false;
        int tries = 0 ;
        int top = 0;
        int bot = sortedList.length-1;
        while(!found){
            tries++;
            System.out.print("Attempts: " +  tries);
            int searchPos =( (top + bot) / 2 );
            System.out.println("Looking at pos" + "");
            if(sortedList[searchPos] == soughtObject){
                System.out.println("Foundit!");
                found = true;
            }if(sortedList[searchPos] < soughtObject){
                top = searchPos;
            }if(sortedList[searchPos] > soughtObject){
                bot = searchPos;
            }
            if(tries >= sortedList.length){
                System.out.println("Isnt in list!");
                break;
            }

        }
    }

}
