package fi.tuni.prog3.jsoncountries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryData {

    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) throws IOException {
        List<Country> countries = new ArrayList<>();

        String[] fileNames = {areaFile, populationFile, gdpFile};

        for (int i = 0; i < 3; i++) {
    

            BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]));
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            reader.close();

            // Loop through array of records
            for (JsonElement record : jsonObject.getAsJsonObject("Root").getAsJsonObject("data").getAsJsonArray("record")) {
                JsonObject recordAsObject = record.getAsJsonObject();

                JsonArray fieldArray = recordAsObject.getAsJsonArray("field");


                String name = null; 
                double area = 0.0;
                long population = 0;
                double gdp = 0.0;

                // Loop through array of fields
                for(int j = 0; j < 3; j++){
                    JsonObject fieldAsObject = fieldArray.get(j).getAsJsonObject();
                    if(j == 0){
                        name = fieldAsObject.get("value").getAsString();
                    }
                    else if(j == 1){
                        String value = fieldAsObject.get("value").getAsString();
                        if(value.toLowerCase().contains("area")){
                            area = fieldArray.get(j+1).getAsJsonObject().get("value").getAsDouble();
                        }
                        else if(value.toLowerCase().contains("population")){
                            population = fieldArray.get(j+1).getAsJsonObject().get("value").getAsLong();

                        }
                        else if(value.toLowerCase().contains("gdp")){
                            gdp = fieldArray.get(j+1).getAsJsonObject().get("value").getAsDouble();
                        }
                    }

                    if (name != null){
                        Country country = findCountry(countries, name);
                        if (country == null) {
                            country = new Country(name, area, population, gdp);
                            countries.add(country);
                        }
                        else{
                            if(area != 0.0){
                                country.setArea(area);
                            }
                            else if(population != 0){
                                country.setPopulation(population);
                            }
                            else if(gdp != 0.0){
                                country.setGdp(gdp);
                            }
                        }    
                    }
                }
            }
        }
        return countries;
    }

    public static void writeToJson(List<Country> countries, String countryFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(countries);
        FileWriter writer = new FileWriter(countryFile);
        writer.write(json);
        writer.close();
    }

    private static Country findCountry(List<Country> countries, String countryName){
        for (Country country : countries) {
            if (country.getName().equals(countryName)) {
                return country;
            }
        }
        return null;
    }
}