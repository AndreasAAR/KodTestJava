
import java.lang.Math;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PNormDistance {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1000 vid inlupp
        boolean done = false;
        for(int i = 0; i < 1000 && !done; i++){
           String input = scanner.nextLine();
           done =  processCase(input);
        }
    }

    static public boolean processCase(String input){
        String[] array = input.split(" ");
        //If ending 0 we can quit as tokens are just 1, not five
        //"The last test case is followed by a line containing a single zero."
        if(array.length < 5)
            return true;
        //Turning string tokens into double
        double x1 =  Double.parseDouble(array[0]);
        double y1 =  Double.parseDouble(array[1]);
        double x2 =  Double.parseDouble(array[2]);
        double y2 =  Double.parseDouble(array[3]);
        double p =  Double.parseDouble(array[4]);
        giveDistance(x1,y1,x2,y2,p);
        return false;
    }

    public static void giveDistance(double x1, double y1, double x2, double y2, double p){

        //Pnorm formula
        //(|𝑥1−𝑥2|^𝑝+|𝑦1−𝑦2|^𝑝)1/𝑝

        //|𝑥1−𝑥2|^𝑝
        double xintern = Math.pow(Math.abs(x1-x2),p);
        //|𝑦1−𝑦2|^𝑝
        double yintern = Math.pow(Math.abs(y1-y2),p);

        //(|𝑥1−𝑥2|^𝑝+|𝑦1−𝑦2|^𝑝)1/𝑝 , 1.0 to ensure double calc
        double pnorm =Math.pow((xintern+yintern),(1.0/p));

        //adjust to 10 decimals
       DecimalFormat df = new DecimalFormat("#.##########");
       String roundedPnorm = df.format(pnorm);
        System.out.println(roundedPnorm);

    }

}


