package marc.populiapp.Api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilbertm on 25/05/2016.
 */
public class Country {

    private String id;
    private String name;
    private List<City> cities = new ArrayList<City>();

    public String getId() {
        return id;
    }

    public Country setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public List<City> getCities() {
        return cities;
    }

    public Country setCities(List<City> cities) {
        this.cities = cities;
        return this;
    }
}
