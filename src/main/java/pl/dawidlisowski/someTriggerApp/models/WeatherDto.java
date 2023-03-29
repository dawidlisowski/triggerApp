package pl.dawidlisowski.someTriggerApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDto {

    @JsonProperty("main")
    private TempDto tempDto;

    @JsonProperty("clouds")
    private CloudsDto cloudsDto;

    @JsonProperty("id")
    private int id;

    public TempDto getTempDto() {
        return tempDto;
    }

    public CloudsDto getCloudsDto() {
        return cloudsDto;
    }

    public int getId() {
        return id;
    }

    public static class TempDto {
        @JsonProperty("temp")
        private double temperature;

        public double getTemperature() {
            return temperature - 273.15;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }

    public static class CloudsDto {
        @JsonProperty("all")
        private double clouds;

        public double getClouds() {
            return clouds;
        }

        public void setClouds(double clouds) {
            this.clouds = clouds;
        }
    }
}
