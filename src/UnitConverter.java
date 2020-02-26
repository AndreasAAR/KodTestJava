import java.util.*;

/*
Figures out the
 */


public class UnitConverter {
    static Scanner scanner = new Scanner(System.in);

    //We know that transitrel is always current as firstUnit, and toInfer has target as first
    //The method returns smaller relationships to add to units
    private static void relationshipConnect(HashMap<String, Relationship> rulesToAdd, HashMap<String, HashMap<String, Relationship>> rules){
        Set<String> allUnits = rules.keySet();
        //Have to go all possible levels
        for(String firstUnit:allUnits){
            //This ones first-level connections
            Set<String> secondLevel = rules.get(firstUnit).keySet();
            //The subset relations
            for(String secondUnit: secondLevel){
                Set<String> thirdLevel = rules.get(secondUnit).keySet();
                for(String  thirdUnit : thirdLevel){
                     connectThree(firstUnit,secondUnit,thirdUnit,rulesToAdd,rules);
                 }
            }

        }
    }

    private static void connectThree(String first, String second, String third,HashMap<String, Relationship> rulesToAdd, HashMap<String, HashMap<String, Relationship>> rules){
        if(!first.equals(third) && !first.equals(second) && !third.equals(second)){

        }
    }

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
                addRelationshipInput(rules);
            }
        }
        ArrayList<String> units = new ArrayList<>();
        units.addAll(Arrays.asList(unitOrder));
        createSystem(units, rules);
        //To utilize new inferrings
        createSystem(units, rules);
        //Printing out the correct output!
        createSystem(units, rules);
        presentSystem(rules);
    }

    private static void presentSystem(HashMap<String, HashMap<String, Relationship>> rules){

      String largest = findLargest(rules);
        System.out.println(largest);

    }

    private static String findLargest(HashMap<String, HashMap<String, Relationship>> rules){
        String largest = null;
        ArrayList<String> units = new ArrayList<>();
        units.addAll(rules.keySet());
        for(String unit: units){
            int numLarger = 0;
            Set<String> relations = rules.get(unit).keySet();
            for(String relation : relations){
                if(!rules.get(unit).get(relation).firstUnitIsSmaller()) {
                    numLarger++;
                }
            }
            if(numLarger == (units.size()-1)){
                largest = unit;
                return largest;
            }

        }
        return largest;
    }

    //Adds relationships with smaller other first
    private static void addRelationshipInput(HashMap<String, HashMap<String, Relationship>> rules) {
        String currentLine = scanner.nextLine();
        String[] lineTokens = currentLine.split(" ");
        //First unit is always assumed to be 1!
        //    public Relationship(int valueInFirst, String firstUnit, int valueInSecond,String secondUnit){
        Relationship firstRelationship = new Relationship(1,lineTokens[0], Integer.parseInt((lineTokens[2])), lineTokens[3]);
        Relationship secondRelationship = new Relationship( Integer.parseInt((lineTokens[2])), lineTokens[3],1,lineTokens[0]);
        //HashMap of relationships for first Unit
        addRule( rules, firstRelationship);
        addRule( rules, secondRelationship);
    }

    //You always add a relationship to the rule with firstEntry defining
    private static void addRule(HashMap<String, HashMap<String, Relationship>> rules, Relationship currentRelationship){
        HashMap<String,Relationship> currentMap = rules.get(currentRelationship.getFirstUnit());

        if (currentMap == null) {
            currentMap = new HashMap<>();
            currentMap.put(currentRelationship.getSecondUnit(), currentRelationship);
            //Put new HashMap into first Units list of relationships
            rules.put(currentRelationship.getFirstUnit(),currentMap);
        } else {
            //Access existing map, add rule
            HashMap<String, Relationship>  existingMap = rules.get(currentRelationship.getFirstUnit());
            existingMap.put(currentRelationship.getSecondUnit(),currentRelationship);
        }
    }

    //Makes sure ALL rules are represented!
    private static void createSystem(ArrayList<String> units, HashMap<String, HashMap<String, Relationship>> rules) {
        HashMap<String, Relationship> rulesToAdd = new HashMap<>();
        for(String currentUnit: units){
            //Get every unit
            HashMap<String,Relationship> currentSubMap = rules.get(currentUnit);
            //If already represented
            if(currentSubMap != null){
                //If not all relations represented (we might not fill all though)
                if( currentSubMap.entrySet().size() < units.size()-1 ){
                    fillUnitRules(rulesToAdd,rules);
                }
            }else{
                rules.put(currentUnit, new HashMap<String, Relationship>());
                fillUnitRules(rulesToAdd,rules);
            }
            if(!rulesToAdd.isEmpty()){
                for(String addTo: rulesToAdd.keySet()){
                    Relationship add = rulesToAdd.get(addTo);
                    rules.get(add.getFirstUnit()).put(add.getSecondUnit(),add);
                }
                rulesToAdd.clear();
            }
        }


    }

    private static void fillUnitRules(HashMap<String, Relationship> rulesToAdd,HashMap<String, HashMap<String, Relationship>> rules){
        relationshipConnect(rulesToAdd,rules);
    }


}


class Relationship{
   private String firstUnit;
   private String secondUnit;
   private int valueInSecond;
   private int valueInFirst;

    public boolean firstUnitIsSmaller(){
        return  !(valueInFirst == 1);
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

    public int getValueInFirst() {
        return valueInFirst;
    }

    public Relationship(int valueInFirst, String firstUnit, int valueInSecond,String secondUnit){
        this.firstUnit = firstUnit;
        this.secondUnit = secondUnit;
        this.valueInSecond = valueInSecond;
        this.valueInFirst = valueInFirst;
    }

    @Override
    public boolean equals(Object o) {
        Relationship other = (Relationship)o;
        if(other.secondUnit == this.secondUnit && other.firstUnit == this.firstUnit)
        return true;

        return false;
    }
}
