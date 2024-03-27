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
    private final ArrayList<Double> numberList = new ArrayList<>();
    private final ArrayList<Operation> numAndAction = new ArrayList<>();

    @FXML
    protected void clearInput1() {
        if (!input1.getText().isEmpty()) {
            input1.clear();
        }
    }
    @FXML
    private void addResultToHistory() {
        String timeOperation = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        String resultForHistory = null;
        if (resultedButton.isArmed()) {
            resultForHistory = index + ") [" + timeOperation + "] " + resultedInput;
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
        index++;
        historyList.add(resultForHistory);
        log(String.valueOf(historyList));
        listHistory.appendText(resultForHistory + System.lineSeparator());
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
    private void test(){
//        String st = "abcworldxyz";
//        System.out.println(st);
//        int wo = st.indexOf("wo");
//        System.out.println(wo);
//        int ld = st.indexOf("ld");
//        String str = "one";
//        System.out.println(ld);
//        StringBuilder stringBuilder = new StringBuilder(st);
//        stringBuilder.replace(wo, ld+2, str);
//        System.out.println(stringBuilder);
        // new CharSequence()
        // st.replace();
        resultedInput = "8.0+7.0*6.0/5.0-4.0";
        int i = 0;
        while (resultedInput.contains("+") || resultedInput.contains("-") || resultedInput.contains("*") || resultedInput.contains("/")) {
            resultedInput = gets(resultedInput, i);
        }
    }
    private String gets(String input, int index) {
        getSymbols(input);
        log(Arrays.toString(allOperationsList.toArray()));
        getNumbers(input);
        log(Arrays.toString(numberList.toArray()));
        allOperationsList.removeLast();
        calculate();
        double result = 0;
            if (numAndAction.get(index).getAction() == '*') {
                result = numAndAction.get(index).getA() * numAndAction.get(index).getB();
                log(String.valueOf(result));
            } else if (numAndAction.get(index).getAction() == '/') {
                log(String.valueOf(numAndAction.get(index).getA()));
                log(String.valueOf(numAndAction.get(index).getB()));
                result = numAndAction.get(index).getA() / numAndAction.get(index).getB();
                log(String.valueOf(result));
            } else if (numAndAction.get(index).getAction() == '+') {
                result = numAndAction.get(index).getA() + numAndAction.get(index).getB();
                log(String.valueOf(result));
            } else if (numAndAction.get(index).getAction() == '-') {
                result = numAndAction.get(index).getA() - numAndAction.get(index).getB();
                log(String.valueOf(result));
            }
            log(String.valueOf(numAndAction.get(index).getA()));
            int a = input.indexOf(String.valueOf(numAndAction.get(index).getA()));
            log(String.valueOf(a));
            int b = input.indexOf(String.valueOf(numAndAction.get(index).getB()));
            log(String.valueOf(b));
            String string = String.valueOf(numAndAction.get(index).getB());
            int length = string.length();
            StringBuilder stringResult = new StringBuilder(input);
            stringResult.replace(a, b+length, String.valueOf(result));
            log(String.valueOf(stringResult));
            input = String.valueOf(stringResult);
            clearArrays();
        return input;
    }

    protected void getNumbers(String input) {
        double a;
        log("Результат:");
        a = Double.parseDouble(input.substring(0, allOperationsList.getFirst()));
        numberList.add(a);
        for (int i = 0; i < allOperationsList.size()-1; i++) {
            a = Double.parseDouble(input.substring(allOperationsList.get(i) + 1, allOperationsList.get(i + 1)));
            numberList.add(a);
        }
        log(Arrays.toString(numberList.toArray()));
    }
    protected void calculate() {
        for (Integer integer : allOperationsList) {
            double a = numberList.getFirst();
            double b = numberList.get(1);
            char action;
            if (mulAndDivIndexList.contains(integer)) {
                if (multiplicationIndexList.contains(integer)) {
                    action = '*';
                    numAndAction.add(new Operation(a, b, action));
                } else {
                    action = '/';
                    numAndAction.add(new Operation(a, b, action));
                }
            } else {
                if (addIndexList.contains(integer)) {
                    action = '+';
                    numAndAction.add(new Operation(a, b, action));
                } else {
                    action = '-';
                    numAndAction.add(new Operation(a, b, action));
                }
            }
            numberList.removeFirst();
        }
        log(Arrays.toString(numAndAction.toArray()));
        Collections.sort(numAndAction);
        log(Arrays.toString(numAndAction.toArray()));
    }
    private String getResult(String input) {
        getSymbols(input);
        log(Arrays.toString(allOperationsList.toArray()));
        getNumbers(input);
        log(Arrays.toString(numberList.toArray()));
        allOperationsList.removeLast();
        calculate();
        double result = 0;
        if (numAndAction.getFirst().getAction() == '*') {
            result = numAndAction.getFirst().getA() * numAndAction.getFirst().getB();
            log(String.valueOf(result));
        } else if (numAndAction.getFirst().getAction() == '/') {
            log(String.valueOf(numAndAction.getFirst().getA()));
            log(String.valueOf(numAndAction.getFirst().getB()));
            result = numAndAction.getFirst().getA() / numAndAction.getFirst().getB();
            log(String.valueOf(result));
        } else if (numAndAction.getFirst().getAction() == '+') {
            result = numAndAction.getFirst().getA() + numAndAction.getFirst().getB();
            log(String.valueOf(result));
        } else if (numAndAction.getFirst().getAction() == '-') {
            result = numAndAction.getFirst().getA() - numAndAction.getFirst().getB();
            log(String.valueOf(result));
        }
        log(String.valueOf(numAndAction.getFirst().getA()));
        String removePart = numAndAction.getFirst().getA() + String.valueOf(numAndAction.getFirst().getAction()) + String.valueOf(numAndAction.getFirst().getB());
        int a = input.indexOf(removePart);
        log(String.valueOf(a));
        int b = removePart.length();
        log(String.valueOf(b));
        StringBuilder stringResult = new StringBuilder(input);
        stringResult.replace(a, a+b, String.valueOf(result));
        log(String.valueOf(stringResult));
        input = String.valueOf(stringResult);
        clearArrays();
        return input;
    }
    @FXML
    protected void getSymbols(String input) {
        System.out.println(input);
        
        char symbol = '+';
        int index = input.indexOf(symbol);
        log("Плюсы");
        while (index != -1) {
            log(String.valueOf(index));
            addIndexList.add(index);
            index = input.indexOf(symbol, index + 1);
        }
        symbol = '-';
        index = input.indexOf(symbol);
        log("Минусы");
        while (index != -1) {
            log(String.valueOf(index));
            subIndexList.add(index);
            index = input.indexOf(symbol, index + 1);
        }
        symbol = '*';
        index = input.indexOf(symbol);
        log("Умножение");
        while (index != -1) {
            log(String.valueOf(index));
            multiplicationIndexList.add(index);
            index = input.indexOf(symbol, index + 1);
        }
        symbol = '/';
        index = input.indexOf(symbol);
        log("Деление");
        while (index != -1) {
            log(String.valueOf(index));
            dividingIndexList.add(index);
            index = input.indexOf(symbol, index + 1);
        }
        mulAndDivIndexList.addAll(multiplicationIndexList);
        mulAndDivIndexList.addAll(dividingIndexList);

        addAndSubIndexList.addAll(addIndexList);
        addAndSubIndexList.addAll(subIndexList);

        allOperationsList.addAll(mulAndDivIndexList);
        allOperationsList.addAll(addAndSubIndexList);
        allOperationsList.add(resultedInput.length());
        sortArray(allOperationsList);
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
    private String resulted() {
        while (resultedInput.contains("+") || resultedInput.contains("-") || resultedInput.contains("*") || resultedInput.contains("/")) {
            if (resultedInput.indexOf('-') != 0){
                resultedInput = getResult(resultedInput);
            } else {
                return resultedInput;
            }
        }
        return resultedInput;
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
            input2.appendText("=" + resulted());
            resultedInput = input2.getText();
            addResultToHistory();
            input2.clear();
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
        input1.requestFocus();
        resultedInput = "";
        numberList.clear();
        addIndexList.clear();
        subIndexList.clear();
        multiplicationIndexList.clear();
        dividingIndexList.clear();
        mulAndDivIndexList.clear();
        addAndSubIndexList.clear();
        allOperationsList.clear();
        resultedInput="";
    }
    @FXML
    protected void clearArrays() {
        numberList.clear();
        addIndexList.clear();
        subIndexList.clear();
        multiplicationIndexList.clear();
        dividingIndexList.clear();
        mulAndDivIndexList.clear();
        addAndSubIndexList.clear();
        allOperationsList.clear();
        numAndAction.clear();
    }
    @FXML
    protected void degreeNumbers() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            double b = 2;
            double res = Math.pow(a, b);
            clearInput1();
            result.setText("Результат: " + res);
            addResultToHistoryTwoOperands(a, b, res);
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
    }

    @FXML
    protected void sqrtNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            double res = Math.sqrt(a);
            clearInput1();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
    }

    @FXML
    protected void sinNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            double res = Math.sin(a);
            clearInput1();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
    }

    @FXML
    protected void cosNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            double res = Math.cos(a);
            clearInput1();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
    }

    @FXML
    protected void tanNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            double res = Math.tan(a);
            clearInput1();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
    }

    @FXML
    protected void arcsinNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            if ((a >= -1) && (a <= 1)) {
                double res = Math.asin(a);
                clearInput1();
                result.setText("Результат: " + res);
                addResultToHistoryOneOperands(a, res);
            } else {
                clearInput1();
                input1.requestFocus();
                result.setText("Недопустимое значение!");
            }
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }

    }

    @FXML
    protected void arccosNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            if ((a >= -1) && (a <= 1)) {
                double res = Math.acos(a);
                clearInput1();
                result.setText("Результат: " + res);
                addResultToHistoryOneOperands(a, res);
            } else {
                clearInput1();
                input1.requestFocus();
                result.setText("Недопустимое значение!");
            }
        } else {
            clearInput1();
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
    }

    @FXML
    protected void arctanNumber() {
        if (!input1.getText().isEmpty()) {
            double a = Double.parseDouble(String.valueOf(input1.getText()));
            double res = Math.atan(a);
            clearInput1();
            input1.requestFocus();
            result.setText("Результат: " + res);
            addResultToHistoryOneOperands(a, res);
        } else {
            input1.requestFocus();
            result.setText("Заполните поле!");
        }
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