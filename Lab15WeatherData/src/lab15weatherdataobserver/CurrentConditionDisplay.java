package lab15weatherdataobserver;
// which 2 interfaces does CurrentConditionsDisplay implement??????????
public class CurrentConditionDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionDisplay(Subject weatherData) {
                //In the constructor we save the reference to the weatherData object and then use it to register this object as an observer.
		this.weatherData = weatherData;
                weatherData.registerObeserver(this);
        }
	
	public void update(float temperature, float humidity, float pressure) {
		//update CurrentConditionsDisplay instance varaibles with the new values passed in???????????
                this.temperature = temperature;
                this.humidity = humidity;
                display();
	}
	
	public void display() {
                System.out.println("Current conditions: " + temperature 
                        + "F degrees and " + humidity + "% humidity");
	}
}
