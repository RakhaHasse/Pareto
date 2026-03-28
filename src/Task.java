public class Task {
    static final int parametersCount= 8;
    private int energyConsumption, timeConsumption, nowResult, prognosisResult;
    private String name;
    private String [] outString;
    private static Double specialRuleForZeroConsumption;
    private boolean isTaskUpdated;
    public double getConsumption(){
        return Math.round((energyConsumption+timeConsumption)/2.0*1000.0)/1000.0;
    }
    public double getResult(){
        return Math.round((nowResult+prognosisResult)/2.0*1000)/1000.0;
    }
    public double getProductivity(){
        return (this.getConsumption()!=0) ?
                Math.round(this.getResult()/this.getConsumption()*1000.0)/1000.0 :
                (this.getResult() * specialRuleForZeroConsumption); //Special fallback for zero consumption to avoid division by zero
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public int getNowResult() {
        return nowResult;
    }

    public int getPrognosisResult() {
        return prognosisResult;
    }

    public int getTimeConsumption() {
        return timeConsumption;
    }

    public static void setSpecialRuleForZeroConsumption(double setUpLink) {
        specialRuleForZeroConsumption = setUpLink;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
        this.isTaskUpdated = true;
        /*
        outString[2] = ""+energyConsumption;
        outString[3] = ""+getConsumption();
        outString[7] = ""+getProductivity();
        */
    }

    public void setNowResult(int nowResult) {
        this.nowResult = nowResult;
        this.isTaskUpdated = true;
        /*
        outString[4] = ""+nowResult;
        outString[6]=""+getResult();
        outString[7]=""+getProductivity();
        */
    }

    public void setPrognosisResult(int prognosisResult) {
        this.prognosisResult = prognosisResult;
        this.isTaskUpdated = true;
       /*
        outString[5] = ""+prognosisResult;
        outString[6]=""+getResult();
        outString[7]=""+getProductivity();
        */
    }

    public void setTimeConsumption(int timeConsumption) {
        this.timeConsumption = timeConsumption;
        this.isTaskUpdated = true;
        /*
        outString[1] = ""+timeConsumption;
        outString[3] = ""+getConsumption();
        outString[7] = ""+getProductivity();
         */
    }

    public void setName(String name) {
        this.name = name;
        this.isTaskUpdated = true;
    }

    public String getName() {
        return name;
    }

    public Task (){
        this.name="New task";
        updateOutString();
        }
    public Task (String name, int ECons, int TCons, int NRes, int PRes){
        this.prognosisResult = PRes;
        this.nowResult = NRes;
        this.timeConsumption = TCons;
        this.energyConsumption = ECons;
        this.name = name;
        updateOutString();
    }

    public Task (String name){
        this.name = name;
        this.prognosisResult = 1;
        this.nowResult = 1;
        this.timeConsumption = 1;
        this.energyConsumption = 1;
        updateOutString();
    }

    public String[] toStringArray (){
        return isTaskUpdated ? updateOutString() : outString;
    }

    private String [] updateOutString() {
        outString = new String[]{name,"" + timeConsumption, ""+energyConsumption, ""+ getConsumption(),
                ""+nowResult,""+prognosisResult,""+ getResult(),"" + getProductivity()};
        isTaskUpdated = false;
        return outString;
    }
}
