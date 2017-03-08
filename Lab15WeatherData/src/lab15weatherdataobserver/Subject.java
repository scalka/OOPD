package lab15weatherdataobserver;

// subject interface
public interface Subject {
   public void registerObeserver(Observer o);
   public void removeObserver(Observer o);
   //subject will call when there is a change 
   public void notifyObserver();
    
}
