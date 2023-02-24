package fi.tuni.prog3.jsoncountries;

public class Country implements Comparable<Country> {

    private String name;
    private double area;
    private long population;
    private double gdp;

    public Country(String name, double area, long population, double gdp) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public double getGdp() {
        return gdp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }

    @Override
    public int compareTo(Country other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + "\n" +
        "  Area: " + String.format("%.1f", area).replace(",", ".") + " km2"+"\n" +
        "  Population: " + population + "\n" +
        "  GDP: " + String.format("%.1f", gdp).replace(",", ".") + " (2015 USD)"+ "\n";
    }

}