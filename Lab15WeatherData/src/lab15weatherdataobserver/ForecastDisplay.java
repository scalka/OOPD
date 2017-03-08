
package lab15weatherdataobserver;

public class ForecastDisplay implements Observer, DisplayElement {
	private float currentPressure = 29.92f;  
	private float lastPressure;
	private WeatherData weatherData;

	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
                // register ForecastDisplay object as an observer with the weatherData subject/publisher
		weatherData.registerObeserver(this);
	}

        // rememebr this update() is called from the subject/publisher WeatherData - notifyObservers()
	public void update(float temp, float humidity, float pressure) {
                // set ForecastDisplay's instance variables based on the new data passed in here.
                lastPressure = currentPressure;
                currentPressure = pressure;

                display();
	}

	public void display() {
            // display something sensible for this observer
            System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else if (currentPressure < lastPressure) {
			System.out.println("Watch out for cooler, rainy weather");
		}
	}
}

