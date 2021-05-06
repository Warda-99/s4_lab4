package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML private TextField tf_className;
    @FXML private TextArea ta_output;
    @FXML private AnchorPane classField;

    Class cls = null;
    Object ob;
    Controller controller;
    Method[] methods;

    private int countFields = 0;
    ArrayList<String> fieldsList = new ArrayList<>();

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private TextField textField5;
    private TextField textField6;
    private TextField textField7;
    private TextField textField8;
    private TextField textField9;
    private TextField textField10;

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;
    private Label label6;
    private Label label7;
    private Label label8;
    private Label label9;
    private Label label10;

    @FXML
    public void initialize() {
        controller = new Controller();
        ta_output.setEditable(false);
        buildScene();
        hideElements();
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        try {
            setChanges();
            printInfo(ob.toString());
        } catch (InvocationTargetException | IllegalAccessException | NoSuchFieldException | NumberFormatException e) {
            e.printStackTrace();
            printInfo("Złe parametry");
        }
    }

    @FXML
    private void createObject(ActionEvent event) {
        countFields = 0;    //reset zmiennej coutFields
        resetFields();
        printInfo("");   //czyszczenie konsoli

        try {
            String className = tf_className.getText();  //czytanie nazyw klasy
            cls = Class.forName(className);
            ob = (Object) cls.newInstance();            //tworzenie instancji klasy
            methods = cls.getDeclaredMethods();         //wczytanie metod klasy
            Field[] fields = cls.getDeclaredFields();   //wczytanie pól klasy

            for (Field field : fields) {                //tworzenie listy z polami klasy
                fieldsList.add(field.getName());
                countFields++;
            }

            printInfo("Klasa: " + className);
            writeFieldName();
            showElements(countFields);

        } catch (IllegalAccessException | InstantiationException  e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            printInfo("Nie ma takiej klasy. Spróbuj sample.Character");
        }

    }

    private static boolean isSetter(Method method, String s){
        String virableName = method.getName().toLowerCase();

        if(method.getName().startsWith("set") && virableName.endsWith(s) && method.getParameterCount() == 1
                && method.getReturnType().equals(void.class)){
            return true;
        }
        return false;
    }

    private void printInfo(String s) {
        ta_output.setText(s);
        ta_output.setWrapText(true);
    }

    public void invokeGetter(Object obj, String variableName, TextField tf) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
            Method getter = pd.getReadMethod();
            Object f = getter.invoke(obj);
            //System.out.println(f.getClass().getTypeName());

            if (f.getClass().getTypeName() == "java.lang.String")
                tf.setText((String) f);

            if (f.getClass().getTypeName() == "java.lang.Integer")
                tf.setText(String.valueOf((Integer) f));

            if (f.getClass().getTypeName() == "java.lang.Double")
                tf.setText(String.valueOf((Double) f));


        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        }
    }

    private void invokeSetter(String name, TextField tf) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Field f = cls.getDeclaredField(name);

        //System.out.println(f.getType().getTypeName());

        if (f.getType().getTypeName() == "java.lang.String") {
            for(Method method : methods) {
                if(isSetter(method, name)) {
                    method.invoke(ob, tf.getText());
                }
            }
        }

        if (f.getType().getTypeName() == "int" || f.getType().getTypeName() == "java.lang.Integer") {
            for(Method method : methods) {
                if(isSetter(method, name)) {
                    int value = Integer.parseInt(tf.getText());
                    method.invoke(ob, value);
                }
            }
        }

        if (f.getType().getTypeName() == "java.lang.Double") {
            for(Method method : methods) {
                if(isSetter(method, name)) {
                    Double value = Double.parseDouble(tf.getText());
                    method.invoke(ob, value);
                }
            }
        }

    }

    private void buildScene() {

        label1 = new Label("");
        label2 = new Label("");
        label3 = new Label("");
        label4 = new Label("");
        label5 = new Label("");
        label6 = new Label("");
        label7 = new Label("");
        label8 = new Label("");
        label9 = new Label("");
        label10 = new Label("");

        textField1 = new TextField ();
        textField2 = new TextField ();
        textField3 = new TextField ();
        textField4 = new TextField ();
        textField5 = new TextField ();
        textField6 = new TextField ();
        textField7 = new TextField ();
        textField8 = new TextField ();
        textField9 = new TextField ();
        textField10 = new TextField ();

        classField.setTopAnchor(textField1, 0.0);
        classField.setTopAnchor(textField2, 30.0);
        classField.setTopAnchor(textField3, 60.0);
        classField.setTopAnchor(textField4, 90.0);
        classField.setTopAnchor(textField5, 120.0);
        classField.setTopAnchor(textField6, 150.0);
        classField.setTopAnchor(textField7, 180.0);
        classField.setTopAnchor(textField8, 210.0);
        classField.setTopAnchor(textField9, 240.0);
        classField.setTopAnchor(textField10, 270.0);

        classField.setLeftAnchor(label1, 150.0);
        classField.setTopAnchor(label1, 4.0);
        classField.setLeftAnchor(label2, 150.0);
        classField.setTopAnchor(label2, 34.0);
        classField.setLeftAnchor(label3, 150.0);
        classField.setTopAnchor(label3, 64.0);
        classField.setLeftAnchor(label4, 150.0);
        classField.setTopAnchor(label4, 94.0);
        classField.setLeftAnchor(label5, 150.0);
        classField.setTopAnchor(label5, 124.0);
        classField.setLeftAnchor(label6, 150.0);
        classField.setTopAnchor(label6, 154.0);
        classField.setLeftAnchor(label7, 150.0);
        classField.setTopAnchor(label7, 184.0);
        classField.setLeftAnchor(label8, 150.0);
        classField.setTopAnchor(label8, 214.0);
        classField.setLeftAnchor(label9, 150.0);
        classField.setTopAnchor(label9, 244.0);
        classField.setLeftAnchor(label10, 150.0);
        classField.setTopAnchor(label10, 274.0);

        classField.getChildren().addAll(textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8, textField9, textField10);
        classField.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
    }

    private void setChanges() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        int size = fieldsList.size();

        if(size>=1)
            invokeSetter(fieldsList.get(0), textField1);
        if(size>=2)
            invokeSetter(fieldsList.get(1), textField2);
        if(size>=3)
            invokeSetter(fieldsList.get(2), textField3);
        if(size>=4)
            invokeSetter(fieldsList.get(3), textField4);
        if(size>=5)
            invokeSetter(fieldsList.get(4), textField5);
        if(size>=6)
            invokeSetter(fieldsList.get(5), textField6);
        if(size>=7)
            invokeSetter(fieldsList.get(6), textField7);
        if(size>=8)
            invokeSetter(fieldsList.get(7), textField8);
        if(size>=9)
            invokeSetter(fieldsList.get(8), textField9);
        if(size>=10)
            invokeSetter(fieldsList.get(9), textField10);

    }

    private void writeFieldName() {
        int size = fieldsList.size();

        if(size>=1) {
            label1.setText(fieldsList.get(0));
            controller.invokeGetter(ob, fieldsList.get(0), textField1);
        }
        if(size>=2) {
            label2.setText(fieldsList.get(1));
            controller.invokeGetter(ob, fieldsList.get(1), textField2);
        }
        if(size>=3) {
            label3.setText(fieldsList.get(2));
            controller.invokeGetter(ob, fieldsList.get(2), textField3);
        }
        if(size>=4) {
            label4.setText(fieldsList.get(3));
            controller.invokeGetter(ob, fieldsList.get(3), textField4);
        }
        if(size>=5) {
            label5.setText(fieldsList.get(4));
            controller.invokeGetter(ob, fieldsList.get(4), textField5);
        }
        if(size>=6) {
            label6.setText(fieldsList.get(5));
            controller.invokeGetter(ob, fieldsList.get(5), textField6);
        }
        if(size>=7) {
            label7.setText(fieldsList.get(6));
            controller.invokeGetter(ob, fieldsList.get(6), textField7);
        }
        if(size>=8) {
            label8.setText(fieldsList.get(7));
            controller.invokeGetter(ob, fieldsList.get(7), textField8);
        }
        if(size>=9) {
            label9.setText(fieldsList.get(8));
            controller.invokeGetter(ob, fieldsList.get(8), textField9);
        }
        if(size>=10) {
            label10.setText(fieldsList.get(9));
            controller.invokeGetter(ob, fieldsList.get(9), textField10);
        }
    }

    private void showElements(int x) {
        if (x>=10) {
            textField10.setVisible(true);
            label10.setVisible(true);
        }
        if (x>=9) {
            textField9.setVisible(true);
            label9.setVisible(true);
        }
        if (x>=8) {
            textField8.setVisible(true);
            label8.setVisible(true);
        }
        if (x>=7) {
            textField7.setVisible(true);
            label7.setVisible(true);
        }
        if (x>=6) {
            textField6.setVisible(true);
            label6.setVisible(true);
        }
        if (x>=5) {
            textField5.setVisible(true);
            label5.setVisible(true);
        }
        if (x>=4) {
            textField4.setVisible(true);
            label4.setVisible(true);
        }
        if (x>=3) {
            textField3.setVisible(true);
            label3.setVisible(true);
        }
        if (x>=2) {
            textField2.setVisible(true);
            label2.setVisible(true);
        }
        if (x>=1) {
            textField1.setVisible(true);
            label1.setVisible(true);
        }
    }

    private void hideElements() {
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);
        textField9.setVisible(false);
        textField10.setVisible(false);

        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        label9.setVisible(false);
        label10.setVisible(false);
    }

    private void resetFields() {
        hideElements();
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        textField1.clear();
        textField2.clear();
        textField3.clear();
        textField4.clear();
        textField5.clear();
        textField6.clear();
        textField7.clear();
        textField8.clear();
        textField9.clear();
        textField10.clear();
    }

}
