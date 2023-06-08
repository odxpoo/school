package odx.lotto.database;

public class Data {
    private int id;
    private String generatedNumbers;
    private String inputNumbers;
    public Data(int id, String generatedNumbers, String inputNumbers) {
        this.id = id;
        this.generatedNumbers = generatedNumbers;
        this.inputNumbers=  inputNumbers;
    }
    public Data() {}
    public void setId(int id) {
        this.id = id;
    }
    public void setGeneratedNumbers(String generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }
    public void setInputNumbers(String inputNumbers) {
        this.inputNumbers = inputNumbers;
    }
    public int getId() {
        return this.id;
    }
    public String getGeneratedNumbers() {
        return this.generatedNumbers;
    }
    public String getInputNumbers() {
        return inputNumbers;
    }
}
