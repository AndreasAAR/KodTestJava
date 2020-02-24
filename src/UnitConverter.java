import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

/*

Figures out the

 */


public class UnitConverter {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String leading;
        leading = null;
        while (true) {
            //add converting func
            if (leading != null && !leading.isEmpty() && !leading.equals("0")) {
                convert(leading);
            }
            leading = scanner.nextLine();
            if (leading != null && leading.equals("0")) {
                System.exit(0);
            }

        }

    }


    private static void convert(String leadingUnit) {

        HashMap<String,HashMap<String,Relationship>> rules = new HashMap<>();
        String[] unitOrder = null;
        int numLines = Integer.parseInt(leadingUnit);
        for (int i = 0; i < numLines; i++) {
            if (i == 0) {
                String units = scanner.nextLine();
                unitOrder = units.split(" ");
            } else {
                addRelationship(rules);
            }
        }
        ArrayList<String> units = new ArrayList<>();

        List<String> list = Arrays.asList(unitOrder);
        units.addAll(list);
        createSystem(units, rules);
    }

    private static void addRelationship(HashMap<String,HashMap<String,Relationship>>  rules) {
        String current = scanner.nextLine();
        String[] lineTokens = current.split(" ");
        Relationship currentRelationship = new Relationship(lineTokens[0], Integer.parseInt((lineTokens[2])), lineTokens[3]);

        if(rules.get(currentRelationship.firstUnit) == null){
            HashMap<String,Relationship> currentMap = new HashMap<>();
            currentMap.put(currentRelationship.secondUnit,currentRelationship);
            rules.put(currentRelationship.firstUnit,currentMap);
        }else{
           // if(rules.get(currentRelationship.firstUnit).get())
        }
    }

    private static HashSet<Relationship> createSystem(ArrayList<String> units, ArrayList<Relationship> relationships) {
        HashSet<Relationship> fullSystem = new HashSet<>();
        fullSystem.addAll(relationships);

        //Add all possible relationships
        for(String unit : units){

            //Check all existing relationships
            for(Relationship relation: relationships){
                if(relation.firstUnit.equals(unit)){

                }
            }
        }
        return fullSystem;
    }
}

class Relationship{
    String firstUnit;

    public String getFirstUnit() {
        return firstUnit;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public int getValueInSecond() {
        return valueInSecond;
    }

    String secondUnit;
    int valueInSecond;
    public Relationship(String firstUnit, int valueInSecond,String secondUnit){
        this.firstUnit = firstUnit;
        this.secondUnit = secondUnit;
        this.valueInSecond = valueInSecond;
    }

    @Override
    public boolean equals(Object o) {
        Relationship other = (Relationship)o;
        if(other.secondUnit == this.secondUnit && other.firstUnit == this.firstUnit)
        return true;

        return false;
    }
}
