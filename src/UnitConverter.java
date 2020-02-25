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

        HashMap<String, HashMap<String, Relationship>> rules = new HashMap<>();
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
        units.addAll(Arrays.asList(unitOrder));

       createSystem(units, rules);
    }

    //Adds relationships properly
    private static void addRelationship(HashMap<String, HashMap<String, Relationship>> rules) {
        String currentLine = scanner.nextLine();
        String[] lineTokens = currentLine.split(" ");
        Relationship currentRelationship = new Relationship(lineTokens[0], Integer.parseInt((lineTokens[2])), lineTokens[3]);

        //HashMap of relationships for first Unit
       HashMap<String,Relationship> currentMap = rules.get(currentRelationship.firstUnit);
        if (currentMap == null) {
            currentMap = new HashMap<>();
            currentMap.put(currentRelationship.secondUnit, currentRelationship);
            //Put new HashMap into first Units list of relationships
            rules.put(currentRelationship.firstUnit,currentMap);
        } else {
            //Access existing map, add rule
            HashMap<String, Relationship>  existingMap = rules.get(currentRelationship.firstUnit);
            existingMap.put(currentRelationship.secondUnit,currentRelationship);
        }
    }


    //Makes sure ALL rules are represented!
    private static void createSystem(ArrayList<String> units, HashMap<String, HashMap<String, Relationship>> rules) {
        HashMap<String, HashMap<String, Relationship>> fullSystem = new HashMap<>();

        for(String currentUnit: units){
            //Get every unit
            HashMap<String,Relationship> currentSubMap = rules.get(currentUnit);
            //If already represented
            if(currentSubMap != null){
                //If not all relations represented!
                if( currentSubMap.entrySet().size() < units.size()-1 ){
                    fillUnitRules(units,currentUnit,rules);
                }
            }

        }
    }

    private static void fillUnitRules(ArrayList<String> units, String currentUnit, HashMap<String, HashMap<String, Relationship>> rules){



    }

    private Relationship convertRelationship(Relationship rel1, Relationship rel2){
        Relationship commonRelationship = null;

        return commonRelationship;
    }

}




class Relationship{
    String firstUnit;

    public boolean firstSmallRelationship(){
        return  valueInSecond < 1;
    }

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
