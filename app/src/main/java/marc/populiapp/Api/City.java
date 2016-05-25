package marc.populiapp.Api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilbertm on 25/05/2016.
 */
public class City {

    private String id;
    private String name;
    private List<Tour> tours = new ArrayList<Tour>();

    public String getId() {
        return id;
    }

    public City setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public City setTours(List<Tour> tours) {
        this.tours = tours;
        return this;
    }
}