package lab15weatherdataobserver;

public class Lab15WeatherData {

   public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
	
		CurrentConditionDisplay currentDisplay  = new CurrentConditionDisplay(weatherData);
                StatisticsDisplay statisticDisplay = new StatisticsDisplay(weatherData);
                ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
                
                
                

                // Note you call setMeasurements() but never directly call display.
                // However all the observer objects call their display method.
                // Set your breakpoint at the line below and follow step by step through the
                // code use the debugger  - you need to do this for your sequence diagram. 
		weatherData.setMeasurements(80, 65, 30.4f);
		//weatherData.setMeasurements(82, 70, 29.2f);
		//weatherData.setMeasurements(78, 90, 29.2f);
                
               
	}
}
