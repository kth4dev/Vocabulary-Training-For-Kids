package kyawthiha.kt.vocabularytrainingforkids.data;

public class HS_Data {
private String category_name,writing_HS,listening_HS,FlashCard_HS,MultipleChoice_HS;
        public HS_Data(String cname,String w,String l,String f,String m){
            this.category_name=cname;
            this.writing_HS=w;
            this.listening_HS=l;
            this.FlashCard_HS=f;
            this.MultipleChoice_HS=m;
        }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getWriting_HS() {
        return writing_HS;
    }

    public void setWriting_HS(String writing_HS) {
        this.writing_HS = writing_HS;
    }

    public String getListening_HS() {
        return listening_HS;
    }

    public void setListening_HS(String listening_HS) {
        this.listening_HS = listening_HS;
    }

    public String getFlashCard_HS() {
        return FlashCard_HS;
    }

    public void setFlashCard_HS(String flashCard_HS) {
        FlashCard_HS = flashCard_HS;
    }

    public String getMultipleChoice_HS() {
        return MultipleChoice_HS;
    }

    public void setMultipleChoice_HS(String multipleChoice_HS) {
        MultipleChoice_HS = multipleChoice_HS;
    }
}
