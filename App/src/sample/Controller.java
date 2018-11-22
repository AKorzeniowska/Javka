package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pckg.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
    final NumberAxis xAxis=new NumberAxis();
    final NumberAxis yAxis=new NumberAxis();
    final LineChart<Number, Number> chart=new LineChart<Number, Number>(xAxis,yAxis);
    public TextField putColnames;
    public TextField putGroupby;
    File file=null;
    DataFrame input;

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
            x = new DataFrame(file.getPath(), new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x;
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

    public void countingMin(ActionEvent actionEvent) {
        if (input()==null)
            return;
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(input().min().toString());
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

    public void countingMax(ActionEvent actionEvent) {
        if (input()==null)
            return;
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(input().max().toString());
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

    public void countingSum(ActionEvent actionEvent) {
        if (input()==null)
            return;
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(input().sum().toString());
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

    public void countingMean(ActionEvent actionEvent) {
        if (input()==null)
            return;
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(input().mean().toString());
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

    public void countingStd(ActionEvent actionEvent) {
        if (input()==null)
            return;
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(input().std().toString());
        } catch (NoChosenFileException e){
            e.alert();
        }
    }

    public void countingVar(ActionEvent actionEvent) {
        if (input()==null)
            return;
        try {
            if (input==null)
                throw new NoChosenFileException();
            okienkoWyników.setText(input().var().toString());
        } catch (NoChosenFileException e){
            e.alert();
        }
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
                if (!input.getCols().contains(data[0]) || !input.getCols().contains(data[1]))
                    throw new WrongColumnsNamesException();
                Value[] axisX = input.wholeColumn(data[0]);
                Value[] axisY = input.wholeColumn(data[1]);
                xAxis.setLabel(data[0]);
                yAxis.setLabel(data[1]);
                for (int i = 0; i < 100; i++) {
                    series.getData().add(new XYChart.Data<>(axisX[i].getValue(), axisY[i].getValue()));
                }
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

    public void puttingColnames(ActionEvent actionEvent) {
    }
}
