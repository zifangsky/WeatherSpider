package cn.zifangsky.model.bo;

import cn.zifangsky.model.WeatherCity;
import cn.zifangsky.model.WeatherCountry;
import cn.zifangsky.model.WeatherProvince;
import cn.zifangsky.model.WeatherStation;
import cn.zifangsky.model.WeatherWeather;

public class WeatherWeatherBO {
	private WeatherCountry country;
	private WeatherProvince province;
	private WeatherCity city;
	private WeatherStation station;
	private WeatherWeather weather;

	public WeatherCountry getCountry() {
		return country;
	}

	public void setCountry(WeatherCountry country) {
		this.country = country;
	}

	public WeatherProvince getProvince() {
		return province;
	}

	public void setProvince(WeatherProvince province) {
		this.province = province;
	}

	public WeatherCity getCity() {
		return city;
	}

	public void setCity(WeatherCity city) {
		this.city = city;
	}

	public WeatherStation getStation() {
		return station;
	}

	public void setStation(WeatherStation station) {
		this.station = station;
	}

	public WeatherWeather getWeather() {
		return weather;
	}

	public void setWeather(WeatherWeather weather) {
		this.weather = weather;
	}

}
