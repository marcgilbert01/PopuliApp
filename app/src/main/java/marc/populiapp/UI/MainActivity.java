package marc.populiapp.UI;

import android.app.Fragment;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import marc.customviews.OpenRightView;
import marc.customviews.OpenRightViewHolder;
import marc.populiapp.Api.City;
import marc.populiapp.Api.Tour;
import marc.populiapp.Api.ToursData;
import marc.populiapp.Api.ToursLoaders.ToursLoader;
import marc.populiapp.Api.ToursLoaders.ToursLoaderFactory;
import marc.populiapp.Api.ToursLoaders.ToursLoaderPopuliCodeChallenge;
import marc.populiapp.R;

public class MainActivity extends AppCompatActivity implements TourDetailsFragment.OnFragmentInteractionListener{


    OpenRightView openRightView;
    PopuliOpenRightAdapter populiOpenRightAdapter;
    Context context;
    Handler handler;
    ToursLoader toursLoader;
    ProgressBar progressBarToursLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //
        context = this;
        handler = new Handler();
        openRightView = (OpenRightView) findViewById(R.id.openRightView);
        progressBarToursLoading = (ProgressBar) findViewById(R.id.progressBarToursLoading);

        // GET TOURS LOADER
        ToursLoaderPopuliCodeChallenge toursLoaderPopuliCodeChallenge = (ToursLoaderPopuliCodeChallenge) new ToursLoaderFactory().createToursLoader(ToursLoaderFactory.ToursLoaderType.POPULI_TEST);
        toursLoaderPopuliCodeChallenge.setContext(context);
        toursLoader = toursLoaderPopuliCodeChallenge;

        // LOAD ON FAB CLICKED
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(){
                    @Override
                    public void run() {
                        super.run();

                        // SHOW PROGRESS BAR
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBarToursLoading.setVisibility(View.VISIBLE);
                            }
                        });


                        toursLoader.loadTours(new ToursLoader.TourLoaderListener() {
                            @Override
                            public void onTourLoaded(ToursData toursData) {

                                if( toursData!=null ){
                                    populiOpenRightAdapter = new PopuliOpenRightAdapter(toursData);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            openRightView.setAdapter(populiOpenRightAdapter);
                                            progressBarToursLoading.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }.start();

            }
        });

    }


    @Override
    public void closeFragment() {

        openRightView.closeFragment();
    }

    @Override
    public void onBackPressed() {

        openRightView.closeFragment();
    }


    // ADAPTER FOR OPEN RIGHt VIEW ( RECYCLER VIEW )
    class PopuliOpenRightAdapter extends OpenRightView.OpenRightAdapter<PopuliOpenRightAdapter.PopuliViewHolder>{

        List<TourDisplay> tourDisplays = new ArrayList<>();
        class TourDisplay{
            Tour tour;
            boolean isHeader = false;
        }

        public PopuliOpenRightAdapter(ToursData toursData) {


            for(City city : toursData.getCities()){

                for(int t=0 ; t<city.getTours().size() ; t++ ){

                    TourDisplay tourDisplay = new TourDisplay();
                    tourDisplay.tour = city.getTours().get(t);
                    if( t==0 ){
                        tourDisplay.isHeader = true;
                    }
                    tourDisplays.add(tourDisplay);
                }
            }
        }

        @Override
        public Fragment getFragment(int i) {

            Fragment fragment = TourDetailsFragment.newInstance(  tourDisplays.get(i).tour );
            return fragment;
        }

        @Override
        public PopuliViewHolder onCreateOpenRightViewHolder(ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.tour, null);
            PopuliViewHolder populiViewHolder = new PopuliViewHolder(view);

            return populiViewHolder;
        }

        @Override
        public void onBindOpenRightViewHolder(PopuliViewHolder populiViewHolder, int i) {

            TourDisplay tourDisplay = tourDisplays.get(i);

            populiViewHolder.textViewTourTitle.setText( tourDisplay.tour.getTitle() );
            Picasso.with(context).load(  tourDisplay.tour.getImage() ).into(populiViewHolder.imageViewTour) ;
            if( tourDisplay.isHeader ){
                populiViewHolder.linearLayoutSection.setVisibility(View.VISIBLE);
                populiViewHolder.textViewCityName.setText(tourDisplay.tour.getCity().getName());
            }
            else{
                populiViewHolder.linearLayoutSection.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return tourDisplays.size();
        }

        class PopuliViewHolder extends OpenRightViewHolder{

            TextView textViewTourTitle;
            ImageView imageViewTour;
            TextView textViewCityName;
            LinearLayout linearLayoutSection;

            public PopuliViewHolder(View itemView) {
                super(itemView);
                textViewTourTitle = (TextView) itemView.findViewById(R.id.textViewTourTitle);
                imageViewTour = (ImageView) itemView.findViewById(R.id.imageViewTour);
                textViewCityName = (TextView) itemView.findViewById(R.id.textViewCityName);
                linearLayoutSection = (LinearLayout) itemView.findViewById(R.id.linearLayoutSection);

            }
        }

    }

}
