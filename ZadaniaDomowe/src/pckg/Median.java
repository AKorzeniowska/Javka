package pckg;

public class Median implements Applyable {
    @Override
    public DataFrame apply(DataFrame x) {
        DataFrame result=x.emptyDataFrame();
        Value [] toAdd=new Value[result.cols.size()];
        int iterator=0;
        int size=0;
        for (String z : x.cols){
            size=x.dataBase.get(z).size();
            if (size%2==1)
                toAdd[iterator]=x.dataBase.get(z).get((size+1)/2);
            else if (size%2==0)
                toAdd[iterator]=x.dataBase.get(z).get(size/2).add(x.dataBase.get(z).get((size+2)/2));
            iterator++;
        }
        return result;
    }
}
