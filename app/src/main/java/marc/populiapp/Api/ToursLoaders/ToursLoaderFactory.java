package marc.populiapp.Api.ToursLoaders;

import java.util.EnumMap;

/**
 * Created by gilbertm on 25/05/2016.
 */
public class ToursLoaderFactory {

    public enum ToursLoaderType{

        POPULI_TEST( new ToursLoaderPopuliCodeChallenge());

        ToursLoader toursLoader;

        ToursLoaderType(ToursLoader toursLoader) {
            this.toursLoader = toursLoader;
        }
    }

    public ToursLoader createToursLoader(ToursLoaderType toursLoaderType){

        ToursLoader toursLoader = null;
        try {
            toursLoader = toursLoaderType.toursLoader.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return toursLoader;
    }



}
