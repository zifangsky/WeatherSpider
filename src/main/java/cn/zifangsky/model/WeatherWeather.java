package cn.zifangsky.model;

public class WeatherWeather {
    private Long id;

    private Long stationId;
    //分时段天气
    private String hour;
    //今天天气
    private String today;
    //明天天气
    private String nextday;
    
    private String next2day;

    private String next3day;

    private String next4day;

    private String next5day;

    private String next6day;
    //最近24小时综合情况
    private String t24situation;
    //其他
    private String other;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour == null ? null : hour.trim();
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today == null ? null : today.trim();
    }

    public String getNextday() {
        return nextday;
    }

    public void setNextday(String nextday) {
        this.nextday = nextday == null ? null : nextday.trim();
    }

    public String getNext2day() {
        return next2day;
    }

    public void setNext2day(String next2day) {
        this.next2day = next2day == null ? null : next2day.trim();
    }

    public String getNext3day() {
        return next3day;
    }

    public void setNext3day(String next3day) {
        this.next3day = next3day == null ? null : next3day.trim();
    }

    public String getNext4day() {
        return next4day;
    }

    public void setNext4day(String next4day) {
        this.next4day = next4day == null ? null : next4day.trim();
    }

    public String getNext5day() {
        return next5day;
    }

    public void setNext5day(String next5day) {
        this.next5day = next5day == null ? null : next5day.trim();
    }

    public String getNext6day() {
        return next6day;
    }

    public void setNext6day(String next6day) {
        this.next6day = next6day == null ? null : next6day.trim();
    }

    public String getT24situation() {
        return t24situation;
    }

    public void setT24situation(String t24situation) {
        this.t24situation = t24situation == null ? null : t24situation.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

	@Override
	public String toString() {
		return "WeatherWeather [id=" + id + ", stationId=" + stationId + ", hour=" + hour + ", today=" + today
				+ ", nextday=" + nextday + ", next2day=" + next2day + ", next3day=" + next3day + ", next4day="
				+ next4day + ", next5day=" + next5day + ", next6day=" + next6day + ", t24situation=" + t24situation
				+ ", other=" + other + "]";
	}
    
}