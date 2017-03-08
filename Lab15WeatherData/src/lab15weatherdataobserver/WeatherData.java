
package lab15weatherdataobserver;
import java.util.*;

//WeatherData is a subject so it implement the subject interface
public class WeatherData implements Subject {
        // WeatherData has a one to many relationship with the observers. 
        // What instance variable needs to be placed in here to hold those "many" observers????????
	// call this variable observers
        private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
            observers = new ArrayList<Observer>();
	}
      	public void registerObeserver(Observer o) {
            // add the observer o to the arrayList that you define above???????????? 
            observers.add(o);
	}

	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
                    observers.remove(i);	
		}
	}
	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
                        observer.update(temperature, humidity, pressure);
		}
	}
	
	public void measurementsChanged() {
		notifyObserver();
	}
	//instead of real data coming 
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}

 
}
