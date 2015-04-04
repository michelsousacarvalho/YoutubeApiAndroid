package app.mobwise.mobwiseapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import static app.mobwise.mobwiseapp.DeveloperKey.DEVELOPER_KEY;


public class YoutubeFrament extends YouTubePlayerFragment implements  YouTubePlayer.OnInitializedListener {


    private static final String ARG_SECTION_NUMBER = "section_number";
    private YouTubePlayer activePlayer;

    public YoutubeFrament() {
    }


    private void init(){
        initialize(DEVELOPER_KEY, this);
    }



    public static YoutubeFrament newInstance(int sectionNumber, String url) {
        YoutubeFrament fragment = new YoutubeFrament();
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("nCgQDjiotG0");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(), "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }


//    private void init() {
//
//        initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) { }
//
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//                activePlayer = player;
//                activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                if (!wasRestored) {
//                    activePlayer.loadVideo(getArguments().getString("url"), 0);
//
//                }
//            }
//        });
//    }

    @Override
    public void onPause() {
        activePlayer.pause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.fragment_youtube, container, false);

        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getActivity().getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(DEVELOPER_KEY, this);
        return inflater.inflate(R.layout.fragment_youtube, container, false);
//        YoutubeFrament ytf = newInstance(1,"Tj75Arhq5ho");
//        ytf.init();
//        getFragmentManager().beginTransaction().add(R.id.youtube_fragment, this).commit();

//        return view;

    }







    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
