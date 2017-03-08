/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab15weatherdataobserver;

// which 2 interfaces does StatisticsDisplay implement??????????
public class StatisticsDisplay implements Observer, DisplayElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;
	private WeatherData weatherData;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
                weatherData.registerObeserver(this);
		// register with the weatherData object everytime a 
                // CurrentConditionsDisplay object is created????????????????
		// rememebr you pass in this object itself as a parameter to the register method.?????? 
	}

	public void update(float temp, float humidity, float pressure) {
            // set the instance variables based on the new data passed in here.
		tempSum += temp;
		numReadings++;

		if (temp > maxTemp) {
			maxTemp = temp;
		}
 
		if (temp < minTemp) {
			minTemp = temp;
		}
                
                // Now StatisticsDisplay needs to re-display its data????????????????????????????????

	}

	public void display() {
		// display something sensible here ????????????????????
	}
}

