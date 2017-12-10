
public class Main {
    public Main(){
        System.out.println("Im main");
        String html = "100?<span class=\"Price__tax\">?? 0 ??</span>";
        String[] parts = html.split("<span");

        System.out.println("Total parts: "+parts.length);

        String costData = parts[0];
        String costValue = costData.replaceAll("\\D+","");
        System.out.println("cost: "+costValue);
    }

    public static void main(String[] args){
        new Main();
    }
}
