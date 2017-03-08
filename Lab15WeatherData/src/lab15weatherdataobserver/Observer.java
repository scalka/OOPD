package lab15weatherdataobserver;
//observer interface
public interface Observer {
    //the subject will call it when it needs to notify an observer of a change
    public void update(float temp, float humidity, float pressure);
    
}
