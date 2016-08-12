package ly.generalassemb.jobschedulerlesson;

/**
 * Created by alanjcaceres on 8/11/16.
 */
public class DataSingleton {

    private String myText;

    public interface DataChangeListener {
        void onDataChanged(String oldText);
    }

    private DataChangeListener dataChangeListener;

    private static DataSingleton instance;

    private DataSingleton(){
    }
    public static DataSingleton getInstance(){
        if(instance == null){
            instance = new DataSingleton();
        }
        return instance;
    }

    public void setListener(DataChangeListener listener){
        dataChangeListener = listener;
    }

    private void notifyDataChanged(String text){
        if(dataChangeListener != null) {
            dataChangeListener.onDataChanged(text);
        }
    }

    public void updateMyText(String text){
        String oldText = myText;
        myText = text;
        notifyDataChanged(oldText);

    }

    public String getMyText(){
        return myText;
    }

}
