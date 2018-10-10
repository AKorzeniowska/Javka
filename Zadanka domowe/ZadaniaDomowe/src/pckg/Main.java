package pckg;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataFrame data=new DataFrame(new String[]{"int", "float"}, new String[]{"kol1", "kol2"});
        System.out.println(data.size());
        ArrayList<?> a=new ArrayList<>();
        a=data.get("kol1");

    }
}
