package marc.populiapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import marc.customviews.OpenRightView;
import marc.customviews.OpenRightViewHolder;
import marc.populiapp.Api.Tour;
import marc.populiapp.Api.ToursData;
import marc.populiapp.Api.ToursLoaders.ToursLoader;
import marc.populiapp.Api.ToursLoaders.ToursLoaderFactory;

public class MainActivity extends AppCompatActivity {


    OpenRightView openRightView;
    PopuliOpenRightAdapter populiOpenRightAdapter;
    Context context;
    ToursLoader toursLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                new Thread(){



                }.start();


                toursLoader.loadTours(new ToursLoader.TourLoaderListener() {
                    @Override
                    public void onTourLoaded(ToursData toursData) {

                        List<Tour> tours = toursData.getTours();
                        if( tours!=null ){

                            populiOpenRightAdapter = new PopuliOpenRightAdapter(tours);
                            openRightView.setAdapter(populiOpenRightAdapter);
                        }
                    }
                });

            }
        });

        //
        context = this;
        openRightView = (OpenRightView) findViewById(R.id.openRightView);
        toursLoader = new ToursLoaderFactory().createToursLoader(ToursLoaderFactory.ToursLoaderType.POPULI_TEST);

    }



    class PopuliOpenRightAdapter extends OpenRightView.OpenRightAdapter<PopuliOpenRightAdapter.PopuliViewHolder>{

        List<Tour> tourList;

        public PopuliOpenRightAdapter(List<Tour> tourList) {
            this.tourList = tourList;
        }

        @Override
        public Fragment getFragment(int i) {
            return null;
        }

        @Override
        public PopuliViewHolder onCreateOpenRightViewHolder(ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.tour, null);
            PopuliViewHolder populiViewHolder = new PopuliViewHolder(view);

            return populiViewHolder;
        }

        @Override
        public void onBindOpenRightViewHolder(PopuliViewHolder populiViewHolder, int i) {

            populiViewHolder.textViewTourTitle.setText( tourList.get(i).getTitle() );

        }

        @Override
        public int getItemCount() {
            return tourList.size();
        }

        class PopuliViewHolder extends OpenRightViewHolder{

            TextView textViewTourTitle;

            public PopuliViewHolder(View itemView) {
                super(itemView);
                textViewTourTitle = (TextView) itemView.findViewById(R.id.textViewTourTitle);
            }


        }


    }

}
