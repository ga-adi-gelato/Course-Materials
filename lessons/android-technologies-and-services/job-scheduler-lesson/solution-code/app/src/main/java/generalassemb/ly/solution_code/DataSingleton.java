package generalassemb.ly.solution_code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alanjcaceres on 8/22/16.
 */
public class DataSingleton {

    private static DataSingleton instance;

    private String firstDataString = "The time";
    private String secondDataString = "Random #";
    private String thirdDataString = "#ff0000";

    public interface DataSingletonListener{
        void onFirstStringChanged(String oldString);
        void onSecondStringChanged(String oldString);
        void onThirdStringChanged(String oldString);
    }

    private List<DataSingletonListener> listeners;

    private DataSingleton(){
        listeners = new ArrayList<>();
    }

    public static DataSingleton getInstance(){
        if(instance == null){
            instance = new DataSingleton();
        }
        return instance;
    }

    public void addDataChangeListener(DataSingletonListener listener){
        listeners.add(listener);
    }

    public void updateFirstString(String newString){
        String oldString = firstDataString;
        firstDataString = newString;
        notifyFirstStringChanged(oldString);
    }

    public void updateSecondString(String newString){
        String oldString = secondDataString;
        secondDataString = newString;
        notifySecondStringChanged(oldString);
    }

    public void updateThirdValue(String newValue){
        String oldValue = thirdDataString;
        thirdDataString = newValue;
        notifyThirdValueChanged(oldValue);
    }

    public String getFirstDataString(){
        return firstDataString;
    }

    public String getSecondDataString(){
        return secondDataString;
    }

    public String getThirdDataString(){
        return thirdDataString;
    }

    private void notifyFirstStringChanged(String priorString){
        for(DataSingletonListener listener : listeners){
            listener.onFirstStringChanged(priorString);
        }
    }

    private void notifySecondStringChanged(String priorString){
        for(DataSingletonListener listener : listeners){
            listener.onSecondStringChanged(priorString);
        }
    }

    private void notifyThirdValueChanged(String priorValue){
        for(DataSingletonListener listener : listeners){
            listener.onThirdStringChanged(priorValue);
        }
    }

}
