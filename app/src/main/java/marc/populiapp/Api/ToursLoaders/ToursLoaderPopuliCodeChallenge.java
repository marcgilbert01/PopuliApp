package marc.populiapp.Api.ToursLoaders;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import marc.populiapp.Api.City;
import marc.populiapp.Api.Country;
import marc.populiapp.Api.Tour;
import marc.populiapp.Api.ToursData;

/**
 * Created by gilbertm on 25/05/2016.
 */
public class ToursLoaderPopuliCodeChallenge implements ToursLoader{

    static final String URL = "http://design.populi.rocks/populi-test-data/countries.json";
    static final String URL_IMAGES_PATH = "http://design.populi.rocks/populi-test-data/";


    Context context;

    public ToursLoaderPopuliCodeChallenge setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public void loadTours(final TourLoaderListener tourLoaderListener) {


        RequestQueue queue = Volley.newRequestQueue( context );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Country[] countries = new Gson().fromJson(response, Country[].class);

                        ToursData toursData = createToursData(countries);

                        tourLoaderListener.onTourLoaded(toursData);

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(stringRequest);


    }




    private ToursData createToursData(Country[] countries){

        ToursData toursData = new ToursData();

        toursData.setCountries( new ArrayList<Country>(Arrays.asList(countries)) );

        List<City> cities = new ArrayList<>();
        List<Tour> tours = new ArrayList<>();
        for(Country country : toursData.getCountries() ){
            for(City city : country.getCities()  ){
               cities.add(city);
               for(Tour tour : city.getTours() ){
                   tour.setImage( URL_IMAGES_PATH + tour.getImage() );
                   tour.setCountry(country);
                   tour.setCity(city);
                   tours.add(tour);
               }
            }
        }

        toursData.setCities(cities);
        toursData.setTours(tours);

        return toursData;
    }





}
