/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankwithguianddb;

/**
 *
 * @author Andrei
 */
public class FiltruSingleton {
    private static Filtru filtru=null;
    
    private FiltruSingleton(){}
    
    public static Filtru getFiltru(){
        if(filtru==null)
            filtru = new Filtru();
        return filtru;
    }
}

class Filtru {
    private String searchTerm;
    private double minValue;
    private double maxValue;
    private boolean dataAsc;
    private String tip;
    
    public Filtru(String searchTerm,double minValue,double maxValue,boolean dataAsc,String tip){
        this.searchTerm=searchTerm;
        this.minValue=minValue;
        this.maxValue=maxValue;
        this.dataAsc=dataAsc;
        this.tip=tip;
    }
    public Filtru(){
        this("",0,100000000,true,"All");
    }
    public String getSearchTerm(){
        return searchTerm;
    }
    public void setSearchTerm(String term){
        this.searchTerm=term;
    }
    public double getMinValue(){
        return minValue;
    }
    public void setMinValue(double value){
        this.minValue=value;
    }
    public double getMaxValue(){
        return maxValue;
    }
    public void setMaxValue(double value){
        this.maxValue=value;
    }
    public boolean getDataAsc(){
        return this.dataAsc;
    }
    public void setDataAsc(boolean val){
        this.dataAsc=val;
    }
    public String getTip(){
        return this.tip;
    }
    public void setTip(String tip){
        this.tip=tip;
    }
}