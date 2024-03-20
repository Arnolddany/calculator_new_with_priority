package com.example.mainone;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelloController implements Initializable {
    public Button resultedButton;
    public Button clearButton;
    private int index = 1;
    private String resultOfCalculations = " ";
    private String resultedInput;
    @FXML
    public Button degreeButton;
    @FXML
    public Button sqrtButton;
    @FXML
    public Button sinButton;
    @FXML
    public Button cosButton;
    @FXML
    public Button tanButton;
    @FXML
    public Button arcsinButton;
    @FXML
    public Button arccosButton;
    @FXML
    public Button arctanButton;
    @FXML
    public Button addButton;
    @FXML
    public Button subButton;
    @FXML
    public Button mulButton;
    @FXML
    public Button divButton;
    @FXML
    public Spinner<Double> spinnerOneField;
    public TextField input1;
    @FXML
    private TextArea listHistory;
    @FXML
    public TextField input2;
    @FXML
    public Label result;

    @FXML
    private TextArea taLog;
    private final ArrayList<String> historyList = new ArrayList<>();
    private final ArrayList<Integer> addIndexList = new ArrayList<>();
    private final ArrayList<Integer> subIndexList = new ArrayList<>();
    private final ArrayList<Integer> multiplicationIndexList = new ArrayList<>();
    private final ArrayList<Integer> dividingIndexList = new ArrayList<>();
    private final ArrayList<Integer> mulAndDivIndexList = new ArrayList<>();
    private final ArrayList<Integer> addAndSubIndexList = new ArrayList<>();
    private final ArrayList<Integer> allOperationsList = new ArrayList<>();
    private final ArrayList<Integer> allOperationsListNotSorted = new ArrayList<>();
    private final ArrayList<Double> numberList = new ArrayList<>();

    @FXML
    protected void clearInput2() {
        if (!input2.getText().isEmpty()) {
            input2.clear();
        }
    }
    @FXML
    private void addResultToHistory() {
        String timeOperation = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        String resultForHistory = null;
        if (resultedButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + " " + resultedInput;
        }
        index++;
        historyList.add(resultForHistory);
        log(String.valueOf(historyList));
        listHistory.appendText(resultForHistory + System.lineSeparator());
    }
    @FXML
    private void addResultToHistoryTwoOperands(double a, double b, double res) {
        String timeOperation = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        String resultForHistory = null;
        if (degreeButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] " + a + "^" + b + " = " + res;
        }
    }
    private void addResultToHistoryOneOperands(double a, double res) {
        String timeOperation = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        String resultForHistory = null;
        if (sqrtButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] √" + a + " = " + res;
        } else if (sinButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] sin(" + a + ") = " + res;
        } else if (cosButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] cos(" + a + ") = " + res;
        } else if (tanButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] tan(" + a + ") = " + res;
        } else if (arcsinButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] arcsin(" + a + ") = " + res;
        } else if (arccosButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] arccos(" + a + ") = " + res;
        } else if (arctanButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] arctan(" + a + ") = " + res;
        }
        index++;
        historyList.add(resultForHistory);
        log(String.valueOf(historyList));
        listHistory.appendText(resultForHistory + System.lineSeparator());
    }
    protected void getNumbers() {
        double a;
        log("Результат:");
        a = Double.parseDouble(resultedInput.substring(0, allOperationsList.getFirst()));
        log(String.valueOf(a));
        numberList.add(a);
        for (int i = 0; i < allOperationsList.size()-1; i++) {
            a = Double.parseDouble(resultedInput.substring(allOperationsList.get(i) + 1, allOperationsList.get(i + 1)));
            numberList.add(a);
            log(String.valueOf(a));
        }
        log(Arrays.toString(numberList.toArray()));
    }
    protected double calculate() {
        double result = 0;
        log(String.valueOf(allOperationsListNotSorted.size()));
        for (Integer integer : allOperationsListNotSorted) {
            double a = numberList.getFirst();
            double b = numberList.get(1);
            log(String.valueOf(mulAndDivIndexList.contains(integer)));
            if (mulAndDivIndexList.contains(integer)) {
                log(String.valueOf(a));
                log(String.valueOf(multiplicationIndexList.contains(integer)));
                if (multiplicationIndexList.contains(integer)) {
                    result = a * b;
                    log(String.valueOf(result));
                } else {
                    result = a / b;
                }
            } else {
                if (addIndexList.contains(integer)) {
                    result = a + b;
                    log(String.valueOf(result));
                } else {
                    result = a - b;
                }
            }
            numberList.removeFirst();
            numberList.set(0, result);
            log(Arrays.toString(numberList.toArray()));
        }
        log(String.valueOf(result));
        return result;
    }
    @FXML
    protected void calculations() {
        System.out.println(resultedInput);
        
        char symbol = '+';
        int index = resultedInput.indexOf(symbol);
        log("Плюсы");
        while (index != -1) {
            log(String.valueOf(index));
            addIndexList.add(index);
            index = resultedInput.indexOf(symbol, index + 1);
        }
        symbol = '-';
        index = resultedInput.indexOf(symbol);
        log("Минусы");
        while (index != -1) {
            log(String.valueOf(index));
            subIndexList.add(index);
            index = resultedInput.indexOf(symbol, index + 1);
        }
        symbol = '*';
        index = resultedInput.indexOf(symbol);
        log("Умножение");
        while (index != -1) {
            log(String.valueOf(index));
            multiplicationIndexList.add(index);
            index = resultedInput.indexOf(symbol, index + 1);
        }
        symbol = '/';
        index = resultedInput.indexOf(symbol);
        log("Деление");
        while (index != -1) {
            log(String.valueOf(index));
            dividingIndexList.add(index);
            index = resultedInput.indexOf(symbol, index + 1);
        }
        mulAndDivIndexList.addAll(multiplicationIndexList);
        mulAndDivIndexList.addAll(dividingIndexList);
        sortArray(mulAndDivIndexList);

        addAndSubIndexList.addAll(addIndexList);
        addAndSubIndexList.addAll(subIndexList);
        sortArray(addAndSubIndexList);

        allOperationsList.addAll(mulAndDivIndexList);
        allOperationsList.addAll(addAndSubIndexList);
        allOperationsList.add(resultedInput.length());
        sortArray(allOperationsList);

        allOperationsListNotSorted.addAll(mulAndDivIndexList);
        allOperationsListNotSorted.addAll(addAndSubIndexList);
    }

    private void sortArray(ArrayList<Integer> arrayList) {
        Collections.sort(arrayList);
    }
    private boolean isDiv(String string) {
        boolean isDiv = false;
        char symbol = string.charAt(string.length() - 1);
        if (symbol == '/') {
            isDiv = true;
        }
        return isDiv;
    }
    @FXML
    protected void checkArmed() {
        String symbol;
        if (addButton.isArmed()) {
            symbol = addButton.getText();
            input2.appendText(String.valueOf(symbol));
        } else if (subButton.isArmed()) {
            symbol = subButton.getText();
            input2.appendText(String.valueOf(symbol));
        } else if (divButton.isArmed()) {
            symbol = divButton.getText();
            input2.appendText(String.valueOf(symbol));
        } else if (mulButton.isArmed()) {
            symbol = mulButton.getText();
            input2.appendText(String.valueOf(symbol));
        } else if (resultedButton.isArmed()) {
            resultedInput = input2.getText();
            calculations();
            getNumbers();
            allOperationsList.removeLast();
            input2.appendText("=" + calculate());
            resultedInput = input2.getText();
            addResultToHistory();
        }
    }
    @FXML
    protected void inputCalculations() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            if ((!input2.getText().isEmpty()) && (isDiv(input2.getText()))) {
                if (Objects.equals(input1.getText(), "0")) {
                    input1.clear();
                    result.setText("На ноль делить нельзя!");
                } else{
                    input2.appendText(String.valueOf(a));
                    checkArmed();
                    result.setText("");

                }
            } else {
                input2.appendText(String.valueOf(a));
                checkArmed();
                result.setText("");
            }
        } else {
            result.setText("Заполните поле!");
        }
        input1.clear();
        input1.requestFocus();
    }

    @FXML
    protected void clearField() {
        input2.clear();
        resultedInput = "";
        numberList.clear();
        addIndexList.clear();
        subIndexList.clear();
        multiplicationIndexList.clear();
        dividingIndexList.clear();
        mulAndDivIndexList.clear();
        addAndSubIndexList.clear();
        allOperationsList.clear();
        allOperationsListNotSorted.clear();
    }
    @FXML
    protected void degreeNumbers() {
        if (!input2.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
            double b = Double.parseDouble(input2.getText());
            double res = Math.pow(a, b);
            clearInput2();
            result.setText("Результат: " + res);
            addResultToHistoryTwoOperands(a, b, res);
        } else {
            result.setText("Заполните оба поля!");
        }
    }

    @FXML
    protected void sqrtNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        double res = Math.sqrt(a);
        clearInput2();
        result.setText("Результат: " + res);
        addResultToHistoryOneOperands(a, res);
    }

    @FXML
    protected void sinNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        double res = Math.sin(a);
        clearInput2();
        result.setText("Результат: " + res);
        addResultToHistoryOneOperands(a, res);
    }

    @FXML
    protected void cosNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        double res = Math.cos(a);
        clearInput2();
        result.setText("Результат: " + res);
        addResultToHistoryOneOperands(a, res);
    }

    @FXML
    protected void tanNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        double res = Math.tan(a);
        clearInput2();
        result.setText("Результат: " + res);
        addResultToHistoryOneOperands(a, res);
    }

    @FXML
    protected void arcsinNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        if ((a >= -1) && (a <= 1)) {
            double res = Math.asin(a);
            clearInput2();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            result.setText("Недопустимое значение!");
        }
    }

    @FXML
    protected void arccosNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        if ((a >= -1) && (a <= 1)) {
            double res = Math.acos(a);
            clearInput2();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            result.setText("Недопустимое значение!");
        }
    }

    @FXML
    protected void arctanNumber() {
        clearInput2();
        double a = Double.parseDouble(String.valueOf(spinnerOneField.getValue()));
        double res = Math.atan(a);
        clearInput2();
        result.setText("Результат: " + res);
        addResultToHistoryOneOperands(a, res);
    }

    private boolean isDigitSymbol(String newValue) {
        boolean isDigit = false;
        char symbol = newValue.charAt(newValue.length() - 1);
        if (Character.isDigit(symbol)) {
            isDigit = true;
        }
        return isDigit;
    }

    private boolean isDigitSymbolBeforePoint(String newValue) {
        boolean isDigit = false;
        char symbol = newValue.charAt(newValue.length() - 2);
        if (Character.isDigit(symbol)) {
            isDigit = true;
        }
        return isDigit;
    }

    private boolean isDigitSymbolFirst(String newValue) {
        boolean isDigit = false;
        for (int i = 0; i < newValue.length(); i++) {
            char symbol = newValue.charAt(i);
            if (Character.isDigit(symbol)) {
                isDigit = true;
            }
        }

        return isDigit;
    }

    private int isPointSymbol(String newValue) {
        int index = -1;
        for (int i = 0; i < newValue.length(); i++) {
            if (newValue.contains(".")) {
                index = newValue.indexOf('.');
                System.out.println(index);
                break;
            }

        }
        return index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert input2 != null : "fx:id=\"input2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert listHistory != null : "fx:id=\"listHistory\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'hello-view.fxml'.";

        input1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                if ((newValue.lastIndexOf("-") == 0)) {
                    input1.setText(newValue);
                    System.out.println(newValue);
                } else {
                    if (isDigitSymbolFirst(newValue)) {
                        input1.setText(newValue);
                    } else {
                        input1.setText(oldValue);
                    }
                }
            } else if (newValue.length() > 1) {
                if ((newValue.endsWith(".")) && (newValue.lastIndexOf('.') == isPointSymbol(newValue)) && (isPointSymbol(newValue) != -1) && (!isDigitSymbol(newValue)) && (isDigitSymbolBeforePoint(newValue))) {
                    input1.setText(newValue);
                } else {
                    if ((isDigitSymbol(newValue))) {
                        input1.setText(newValue);
                    } else {
                        input1.setText(oldValue);
                    }

                }
            }
        });
    }

    private void log(String s) {
        taLog.appendText(s + System.lineSeparator());
        System.out.println(s);
    }
}