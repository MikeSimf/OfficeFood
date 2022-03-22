package mike.officefood.lunch;

public class OperationResult {
    //TODO: пока передаем текст, позже добавим вариативность
    private String result;

    public OperationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
