package kyawthiha.kt.vocabularytrainingforkids.data;

public class V_Data {
private String trueAns,falseAns1,falseAns2,falseAns3,meaning;
    public V_Data(String trueAns,String falseAns1,String falseAns2,String falseAns3,String meaning){
        this.trueAns=trueAns;
        this.falseAns1=falseAns1;
        this.falseAns2=falseAns2;
        this.falseAns3=falseAns3;
        this.meaning=meaning;
    }

    public String getTrueAns() {
        return trueAns;
    }

    public void setTrueAns(String trueAns) {
        this.trueAns = trueAns;
    }

    public String getFalseAns1() {
        return falseAns1;
    }

    public void setFalseAns1(String falseAns1) {
        this.falseAns1 = falseAns1;
    }

    public String getFalseAns2() {
        return falseAns2;
    }

    public void setFalseAns2(String falseAns2) {
        this.falseAns2 = falseAns2;
    }

    public String getFalseAns3() {
        return falseAns3;
    }

    public void setFalseAns3(String falseAns3) {
        this.falseAns3 = falseAns3;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
