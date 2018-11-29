package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pckg.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("Duplicates")

public class Controller {
    public Button nazwa;
    public Label okienkoWyników;
    public Label adresPliczku;
    public Button countMin;
    public Button countMax;
    public Button countSum;
    public Button countMean;
    public Button countVar;
    public Button countStd;
    public Button doChart;

    public TextField putColnames;
    public TextField putGroupby;
    File file=null;
    DataFrame input=null;
    DataFrame counted=null;


    public void wczytywanko(ActionEvent actionEvent) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(nazwa.getScene().getWindow());
        if (file != null) {
            adresPliczku.setText(file.getPath());
            input=inputStraight();
        }
        putGroupby.setPromptText("Nazwa kolumny do grupowania");
        putColnames.setPromptText("Nazwy kolumn do wykresu");
    }

    public DataFrame inputStraight (){
        DataFrame x= null;
        try {
            input = new DataFrame(file.getPath(), new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
    public DataFrame.DataMap input(){
        try {
            if (putGroupby.getText().equals(""))
                throw new NoColumnsNamesException();
            String [] data=putGroupby.getText().split(", ");
            if (data.length!=1)
                throw new WrongColumnsNumberException();
            if (!input.getCols().contains(putGroupby.getText()))
                throw new WrongColumnsNamesException();
            DataFrame.DataMap z = input.groupby(new String[]{putGroupby.getText()});
            return z;
        } catch (NoColumnsNamesException e){
            e.noInputAlert();
        } catch (WrongColumnsNumberException e){
            e.invalidNumberAlert();
        } catch (WrongColumnsNamesException e){
            e.invalidNameAlert();
        }
        return null;
    }

    public void functionUni(DataFrame a){
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(a.toString());
            counted=a;
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

    public void countingMin(ActionEvent actionEvent) {
        if (input()!=null)
            functionUni(input().min());
    }

    public void countingMax(ActionEvent actionEvent) {
        if (input()!=null)
            functionUni(input().max());
    }

    public void countingSum(ActionEvent actionEvent) {
        if (input()!=null)
            functionUni(input().sum());
    }

    public void countingMean(ActionEvent actionEvent) {
        if (input()!=null)
            functionUni(input().mean());
    }

    public void countingStd(ActionEvent actionEvent) {
        if (input()!=null)
            functionUni(input().std());
    }

    public void countingVar(ActionEvent actionEvent) {
        if (input()!=null)
            functionUni(input().var());
    }


    public void doingChart(ActionEvent actionEvent) {
        try {
            if (input==null)
                throw new NoChosenFileException();
            XYChart.Series series = new XYChart.Series();
            series.setName("wykresik");
            try {
                if (putColnames.getText().equals(""))
                    throw new NoColumnsNamesException();
                String text = putColnames.getText();
                String[] data = text.split(", ");
                if (data.length!=2)
                    throw new WrongColumnsNumberException();
                if (!counted.getCols().contains(data[0]) || !counted.getCols().contains(data[1]))
                    throw new WrongColumnsNamesException();

                final CategoryAxis xAxisString = new CategoryAxis();
                final NumberAxis xAxisNumber = new NumberAxis();
                final NumberAxis yAxis = new NumberAxis();
                final LineChart<?,?> chart;

                Value[] axisX = counted.wholeColumn(data[0]);
                Value[] axisY = counted.wholeColumn(data[1]);
                yAxis.setLabel(data[1]);

                if (counted.getClassOfCol(data[0]).equals(DateTimeValue.class) || counted.getClassOfCol(data[0]).equals(StringValue.class)) {
                    chart=new LineChart<String, Number>(xAxisString, yAxis);
                    xAxisString.setLabel(data[0]);
                    for (int i = 0; i<axisX.length; i++) {
                        series.getData().add(new XYChart.Data<>(axisX[i].getValue().toString(), axisY[i].getValue()));
                    }
                }
                else{
                    chart=new LineChart<Number, Number>(xAxisNumber, yAxis);
                    xAxisNumber.setLabel(data[0]);
                    for (int i = 0; i<axisX.length; i++) {
                        series.getData().add(new XYChart.Data<>(axisX[i].getValue(), axisY[i].getValue()));
                    }
                }


//                yAxis.setAutoRanging(false);
//                yAxis.setUpperBound((double)counted.maxInCol(data[1]).getValue()+1);
//                yAxis.setLowerBound((double)counted.maxInCol(data[1]).getValue()-1);



                Scene scene = new Scene(chart, 380, 400);
                chart.getData().add(series);

                Stage stage = (Stage) nazwa.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (NoColumnsNamesException e){
                e.noInputAlert();
            } catch (WrongColumnsNumberException e){
                e.invalidNumberAlert();
            } catch (WrongColumnsNamesException e){
                e.invalidNameAlert();
            }
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

}
