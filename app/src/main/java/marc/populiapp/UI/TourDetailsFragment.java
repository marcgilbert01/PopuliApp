package marc.populiapp.UI;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import marc.populiapp.Api.Tour;
import marc.populiapp.R;


public class TourDetailsFragment extends Fragment {

    private static final String ARG_TOUR = "Tour";
    private Activity activity;
    private Tour tour;
    private OnFragmentInteractionListener mListener;


    public TourDetailsFragment() {
        // Required empty public constructor
    }

    public static TourDetailsFragment newInstance(Tour tour) {

        TourDetailsFragment fragment = new TourDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TOUR , tour);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tour = getArguments().getParcelable(ARG_TOUR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_tour , container , false);
        // TITLE
        TextView textViewTourTitle = (TextView) view.findViewById(R.id.textViewTourBigTitle);
        textViewTourTitle.setText( tour.getTitle() );
        // IMAGE
        ImageView imageViewTour = (ImageView) view.findViewById(R.id.imageViewTourBigImg);
        Picasso.with(activity).load( tour.getImage() ).into(imageViewTour) ;
        // DESCRIPTION
        TextView textViewTourDescription = (TextView) view.findViewById(R.id.textViewTourDescription);
        textViewTourDescription.setText( tour.getDesc() );
        // CLOSE BUTTON
        Button buttonClose = (Button) view.findViewById(R.id.buttonCloseFragment);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeFragment();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void closeFragment();
    }
}
