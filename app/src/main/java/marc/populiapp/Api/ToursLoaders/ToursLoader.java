package marc.populiapp.Api.ToursLoaders;

import marc.populiapp.Api.ToursData;

/**
 * Created by gilbertm on 25/05/2016.
 */
public interface ToursLoader {

    public void loadTours(TourLoaderListener tourLoaderListener);

    interface TourLoaderListener{

        public void onTourLoaded(ToursData toursData);
    }

}
