public class Task {
    private int energyConsumption, timeConsumption, nowResult, prognosisResult;
    private String name;

    public double getConsumption(){
        return (double) (energyConsumption+timeConsumption)/2;
    }
    public double getResult(){
        return (double) (nowResult+prognosisResult)/2;
    }
    public double getProductivity(){
        if (this.getConsumption()!=0)
        return (this.getResult()/this.getConsumption());
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
    }

    public void setNowResult(int nowResult) {
        this.nowResult = nowResult;
    }

    public void setPrognosisResult(int prognosisResult) {
        this.prognosisResult = prognosisResult;
    }

    public void setTimeConsumption(int timeConsumption) {
        this.timeConsumption = timeConsumption;
    }

    public void setName(String name) {
        this.name = name;
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
    }

    public Task (String name){
        this.name = name;
    }

    public String[] toStringArray (){
        return new String[]{name,"" + timeConsumption, ""+energyConsumption, ""+ getConsumption(),
        ""+nowResult,""+prognosisResult,""+ getResult(),""+ getProductivity()};
    }
}
