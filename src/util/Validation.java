/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author bader
 */
public class Validation {

    public static boolean textValidation(TextField tf) {

        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty()) {
            b = true;

        }
        return b;

    }
    public static boolean textValidation(TextField tf ,Label lb,String errorMessage){
    
    boolean b=true;
    String mag=null;
    if (!textValidation(tf)){
    b=false;
    mag=errorMessage;
    }
    lb.setText(mag);
    return b;
    
    }
    
    public static boolean textalphabet(TextField tf ,Label lb,String errorMessage)
    {
    boolean isAlphabet =true;
    
    String validationString=null;
    if(!tf.getText().matches("[a-z A-Z]+")){
    isAlphabet=false;
    
    }

    public static boolean textalphabet(TextField tf, Label lb, String errorMessage) {
        boolean isAlphabet = true;

        String validationString = null;
        if (!tf.getText().matches("[a-z A-Z]+")) {
            isAlphabet = false;

        }
        lb.setText(errorMessage);
        // System.out.println(tf.getText().matches("[a-z A-Z]+"));
        return isAlphabet;

    }

    public static boolean texNum(TextField tf, Label lb, String errorMessage) {
        boolean isNum = true;

        String validationString = null;
        if (!tf.getText().matches("[0-9]+")) {
            isNum = false;

        }
        lb.setText(errorMessage);
        //    System.out.println(tf.getText().matches("[0-9]+"));
        return isNum;

    }
    //[a-z0-9]

    public static boolean texAlphNum(TextField tf, Label lb, String errorMessage) {
        boolean isAlphNum = true;

        String validationString = null;
        if (!tf.getText().matches("[a-z0-9]+")) {
            isAlphNum = false;

        }
        lb.setText(errorMessage);
        //  System.out.println(tf.getText().matches("[a-z0-9]+"));
        return isAlphNum;

    }

    public static boolean texMail(TextField tf, Label lb, String errorMessage) {
        boolean isMail = true;

        String validationString = null;
        if (!tf.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")) {
            isMail = false;
            lb.setText(errorMessage);
        }

        // System.out.println(tf.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$"));
        return isMail;

    }

    public static boolean confirmPassword(TextField tf, TextField tf2, Label lb, String errorMessage) {
        boolean confirm = true;
        if (!tf.getText().equals(tf2.getText())) {

            confirm = false;

            //lb.setText(errorMessage);
            //System.out.println(confirm);
        }
        return confirm;
    }

}
