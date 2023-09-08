public class Task {
    static final int parametersCount= 8;
    private int energyConsumption, timeConsumption, nowResult, prognosisResult;
    private String name;
    private String [] outString;

    public double getConsumption(){
        return Math.round((energyConsumption+timeConsumption)/2.0*1000.0)/1000.;
    }
    public double getResult(){
        return Math.round((nowResult+prognosisResult)/2.0*1000)/1000.0;
    }
    public double getProductivity(){
        if (this.getConsumption()!=0) {

        return Math.round(this.getResult()/this.getConsumption()*1000.0)/1000.0;
        }

        else return 0;
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

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
        outString[2] = ""+energyConsumption;
        outString[3] = ""+getConsumption();
        outString[7] = ""+getProductivity();
    }

    public void setNowResult(int nowResult) {
        this.nowResult = nowResult;
        outString[4] = ""+nowResult;
        outString[6]=""+getResult();
        outString[7]=""+getProductivity();
    }

    public void setPrognosisResult(int prognosisResult) {
        this.prognosisResult = prognosisResult;
        outString[5] = ""+prognosisResult;
        outString[6]=""+getResult();
        outString[7]=""+getProductivity();
    }

    public void setTimeConsumption(int timeConsumption) {
        this.timeConsumption = timeConsumption;
        outString[1] = ""+timeConsumption;
        outString[3] = ""+getConsumption();
        outString[7] = ""+getProductivity();
    }

    public void setName(String name) {
        this.name = name;
        outString[0]=name;
    }

    public String getName() {
        return name;
    }

    public Task (){

    }
    public Task (String name, int ECons, int TCons, int NRes, int PRes){
        this.prognosisResult = PRes;
        this.nowResult = NRes;
        this.timeConsumption = TCons;
        this.energyConsumption = ECons;
        this.name = name;
        outString = new String[]{name,"" + timeConsumption, ""+energyConsumption, ""+ getConsumption(),
                ""+nowResult,""+prognosisResult,""+ getResult(),""+ getProductivity()};
    }

    public Task (String name){
        this.name = name;

        outString=new String[]{name, "0","0","0","0","0","0","0"};
    }

    public String[] toStringArray (){
        return outString;
    }
}
