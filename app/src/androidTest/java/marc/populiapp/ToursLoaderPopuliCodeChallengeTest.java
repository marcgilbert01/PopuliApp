package marc.populiapp;

import android.test.AndroidTestCase;

import marc.populiapp.Api.ToursData;
import marc.populiapp.Api.ToursLoaders.ToursLoader;
import marc.populiapp.Api.ToursLoaders.ToursLoaderPopuliCodeChallenge;

/**
 * Created by gilbertm on 25/05/2016.
 */
public class ToursLoaderPopuliCodeChallengeTest extends AndroidTestCase{

    ToursLoaderPopuliCodeChallenge toursLoaderPopuliCodeChallenge;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        toursLoaderPopuliCodeChallenge = new ToursLoaderPopuliCodeChallenge();
        toursLoaderPopuliCodeChallenge.setContext(getContext());

    }


    public void testLoadTours() throws InterruptedException {

        final ToursData[] toursDataTest = new ToursData[1];

        toursLoaderPopuliCodeChallenge.loadTours(new ToursLoader.TourLoaderListener() {
            @Override
            public void onTourLoaded(ToursData toursData) {

                assertNotNull(toursData);

                toursDataTest[0] = toursData;

            }
        });


        Thread.sleep(5000);

        ToursData toursData = toursDataTest[0];

        assertNotNull(toursData);

        assertNotNull(toursData.getCities());

        assertNotNull(toursData.getCountries());

        assertNotNull(toursData.getTours());

        assertEquals( 2 , toursData.getCountries().size() );


    }



}
