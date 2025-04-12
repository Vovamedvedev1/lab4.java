package org.example.lab4;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.JsonSerializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class СalculatorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_0;

    @FXML
    private Button but_1;

    @FXML
    private Button but_2;

    @FXML
    private Button but_3;

    @FXML
    private Button but_4;

    @FXML
    private Button but_5;

    @FXML
    private Button but_6;

    @FXML
    private Button but_7;

    @FXML
    private Button but_8;

    @FXML
    private Button but_9;

    @FXML
    private Button but_DD;

    @FXML
    private Button but_acos;

    @FXML
    private Button but_actg;

    @FXML
    private Button but_asin;

    @FXML
    private Button but_atg;

    @FXML
    private Button but_cos;

    @FXML
    private Button but_ctg;

    @FXML
    private Button but_del;

    @FXML
    private Button but_div;

    @FXML
    private Button but_exp;

    @FXML
    private Button but_left;

    @FXML
    private Button but_lg;

    @FXML
    private Button but_ln;

    @FXML
    private Button but_minus;

    @FXML
    private Button but_mult;

    @FXML
    private Button but_pi;

    @FXML
    private Button but_point;

    @FXML
    private Button but_pow;

    @FXML
    private Button but_res;

    @FXML
    private Button but_right;

    @FXML
    private Button but_sin;

    @FXML
    private Button but_tg;

    @FXML
    private Button put_plus;

    @FXML
    private TextField resultField;

    @FXML
    void createAlgebraString(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        if (sourceButton == but_del) {
            resultField.clear();
        } else if (sourceButton == but_DD) {
            int caretPosition = resultField.getCaretPosition();
            if (caretPosition > 0) {
                resultField.deleteText(caretPosition - 1, caretPosition);
            }
        } else if (sourceButton == but_res) {
            try {
                String postfixExpression = infixToPostfix(resultField.getText());
                double result = evaluatePostfix(postfixExpression);
                resultField.setText(String.valueOf(result));
            } catch (Exception e) {
                String message = e.getMessage();
                if (message.equals(""))
                    message = "Несбалансированное выражение.";
                HelloApplication.showAlert("Ошибка", message, message);
            }
        } else {
            resultField.insertText(resultField.getCaretPosition(), sourceButton.getText());
        }
    }

    @FXML
    void initialize() {
        assert but_0 != null : "fx:id=\"but_0\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_1 != null : "fx:id=\"but_1\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_2 != null : "fx:id=\"but_2\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_3 != null : "fx:id=\"but_3\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_4 != null : "fx:id=\"but_4\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_5 != null : "fx:id=\"but_5\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_6 != null : "fx:id=\"but_6\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_7 != null : "fx:id=\"but_7\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_8 != null : "fx:id=\"but_8\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_9 != null : "fx:id=\"but_9\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_DD != null : "fx:id=\"but_DD\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_acos != null : "fx:id=\"but_acos\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_actg != null : "fx:id=\"but_actg\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_asin != null : "fx:id=\"but_asin\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_atg != null : "fx:id=\"but_atg\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_cos != null : "fx:id=\"but_cos\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_ctg != null : "fx:id=\"but_ctg\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_del != null : "fx:id=\"but_del\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_div != null : "fx:id=\"but_div\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_exp != null : "fx:id=\"but_exp\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_left != null : "fx:id=\"but_left\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_lg != null : "fx:id=\"but_lg\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_ln != null : "fx:id=\"but_ln\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_minus != null : "fx:id=\"but_minus\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_mult != null : "fx:id=\"but_mult\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_pi != null : "fx:id=\"but_pi\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_point != null : "fx:id=\"but_point\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_pow != null : "fx:id=\"but_pow\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_res != null : "fx:id=\"but_res\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_right != null : "fx:id=\"but_right\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_sin != null : "fx:id=\"but_sin\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert but_tg != null : "fx:id=\"but_tg\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert put_plus != null : "fx:id=\"put_plus\" was not injected: check your FXML file 'Сalculator.fxml'.";
        assert resultField != null : "fx:id=\"resultField\" was not injected: check your FXML file 'Сalculator.fxml'.";
    }

    private String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        boolean needUnaryMinus = true;
        infix = infix.replaceAll("\\s+", "");
        for (int i = 0; i < infix.length(); ) {
            char c = infix.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < infix.length() && (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    num.append(infix.charAt(i++));
                }
                postfix.append(num).append(" ");
                needUnaryMinus = false;
            } else if (Character.isLetter(c)) {
                StringBuilder func = new StringBuilder();
                int start = i;
                while (i < infix.length() && Character.isLetter(infix.charAt(i))) {
                    func.append(infix.charAt(i++));
                }
                String function = func.toString();
                if (isConstant(function)) {
                    postfix.append(function).append(" ");
                    needUnaryMinus = false;
                } else if (isFunction(function)) {
                    stack.push(function);
                    needUnaryMinus = true;
                } else {
                    i = start;
                    postfix.append(c).append(" ");
                    i++;
                    needUnaryMinus = false;
                }
            } else if (c == '(') {
                stack.push(String.valueOf(c));
                i++;
                needUnaryMinus = true;
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    throw new IllegalArgumentException("Несбалансированное выражение");
                }
                i++;

                if (!stack.isEmpty() && isFunction(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                needUnaryMinus = false;
            } else if (isOperator(c)) {
                if (c == '-' && needUnaryMinus) {
                    stack.push("~");
                } else {
                    while (!stack.isEmpty() && !stack.peek().equals("(") && precedence(c) <= precedence(stack.peek().charAt(0))) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(String.valueOf(c));
                }
                i++;
                needUnaryMinus = true;
            } else {
                i++;
                needUnaryMinus = false;
                throw new IllegalArgumentException("Некорректный оператор: " + c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        return postfix.toString();
    }

    private boolean isConstant(String token) {
        return token.equals("PI") || token.equals("EXP");
    }

    private boolean isFunction(String token) {
        return token.equals("SIN") ||
                token.equals("COS") || token.equals("TG") || token.equals("CTG") ||
                token.equals("ASIN") || token.equals("ACOS") || token.equals("ATG") ||
                token.equals("ACTG") || token.equals("LN") || token.equals("LG");
    }

    private double evaluatePostfix(String postfix) {
        Deque<Double> stack = new LinkedList<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            try {
                double num = Double.parseDouble(token);
                stack.push(num);
            } catch (NumberFormatException e) {
                double element;
                switch (token) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        double b = stack.pop();
                        double a = stack.pop();
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        b = stack.pop();
                        a = stack.pop();
                        if (Math.abs(b) < Math.pow(10,-10))
                            throw new ArithmeticException("Ошибка деления на 0");
                        else
                            stack.push(a / b);
                        break;
                    case "^":
                        b = stack.pop();
                        a = stack.pop();
                        if (Math.abs(a) < Math.pow(10,-10) && b < 0)
                            throw new ArithmeticException("Ошибка деления на 0");
                        else if (a < Math.pow(10,-10) && (Math.abs(b) - Math.floor(Math.abs(b))) > Math.pow(10,-10))
                            throw new ArithmeticException("Ошибка возведения в дробную степень.");
                        else
                            stack.push(Math.pow(a,b));
                        break;
                    case "~":
                        stack.push(-stack.pop());
                        break;
                    case "PI":
                        stack.push(Math.PI);
                        break;
                    case "EXP":
                        stack.push(Math.E);
                        break;
                    case "SIN":
                        stack.push(Math.sin(stack.pop()));
                        break;
                    case "COS":
                        stack.push(Math.cos(stack.pop()));
                        break;
                    case "TG":
                        element = stack.pop();
                        if (Math.abs(Math.cos(element)) < Math.pow(10,-10))
                            throw new ArithmeticException("Некорректный аргумент при тангенсе" + element);
                        else
                            stack.push(Math.tan(element));
                        break;
                    case "CTG":
                        element = stack.pop();
                        if (Math.abs(Math.sin(element)) < Math.pow(10,-10))
                            throw new ArithmeticException("Некорректный аргумент при котангенсе" + element);
                        else
                            stack.push(1.0 / Math.tan(element));
                        break;
                    case "ASIN":
                        element = stack.pop();
                        if (Math.abs(element) > 1)
                            throw new ArithmeticException("Некорректный аргумент при арксинусе" + element);
                        else
                            stack.push(Math.asin(element));
                        break;
                    case "ACOS":
                        element = stack.pop();
                        if (Math.abs(element) > 1)
                            throw new ArithmeticException("Некорректный аргумент при арккосинусе" + element);
                        else
                            stack.push(Math.acos(element));
                        break;
                    case "ATG":
                        stack.push(Math.atan(stack.pop()));
                        break;
                    case "ACTG":
                        stack.push(Math.PI / 2 - Math.atan(stack.pop()));
                        break;
                    case "LN":
                        element = stack.pop();
                        if (element < Math.pow(10,-10))
                            throw new ArithmeticException("Некорректный аргумент при LN(" + element + ")");
                        else
                            stack.push(Math.log(element));
                        break;
                    case "LG":
                        element = stack.pop();
                        if (element <= Math.pow(10,-10))
                            throw new ArithmeticException("Некорректный аргумент при LG(" + element + ")");
                        else
                            stack.push(Math.log10(element));
                        break;
                    default:
                        throw new IllegalArgumentException("Некорректный токен: " + token);
                }
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Несбалансированное выражение");
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '~':
                return 4;
            default:
                return 0;
        }
    }
}
