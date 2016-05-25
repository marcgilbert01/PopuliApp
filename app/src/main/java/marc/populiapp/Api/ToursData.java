package marc.populiapp.Api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilbertm on 25/05/2016.
 */
public class ToursData {

    List<Tour> tours;
    List<City> cities;
    List<Country> countries;

    public List<Tour> getTours() {
        return tours;
    }

    public ToursData setTours(List<Tour> tours) {
        this.tours = tours;
        return this;
    }

    public List<City> getCities() {
        return cities;
    }

    public ToursData setCities(List<City> cities) {
        this.cities = cities;
        return this;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public ToursData setCountries(List<Country> countries) {
        this.countries = countries;
        return this;
    }




}
