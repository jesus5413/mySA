package com.group.mysa.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.group.mysa.Database.Database;
import com.group.mysa.Model.FeedInfo;
import com.group.mysa.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CityFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author jesusnieto
 */
public class CityFragment extends Fragment {
//     TODO: Rename parameter arguments, choose names that match
//     the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private String uid;

    private RecyclerView cityFeedList;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private Query chatQuery;
    private Intent browserIntent;
    private Toolbar mToolBar;




    public CityFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("feed");
        mDatabase.keepSynced(true);

        mToolBar = (Toolbar) view.findViewById(R.id.city_tool_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolBar);
        mToolBar.setTitle("mySA");

        cityFeedList = (RecyclerView) view.findViewById(R.id.city_chat_recyclerView);
        cityFeedList.setHasFixedSize(true);
        cityFeedList.setLayoutManager(new LinearLayoutManager(getContext()));




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        onActivityStarted();
        firebaseRecyclerAdapter.startListening();
    }

    public void onActivityStarted(){
        chatQuery = mDatabase.orderByKey();

        FirebaseRecyclerOptions<FeedInfo> userOption = new FirebaseRecyclerOptions.Builder<FeedInfo>().setQuery(chatQuery, FeedInfo.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FeedInfo, CityChatViewHolder>(userOption) {
            @Override
            protected void onBindViewHolder(@NonNull final CityChatViewHolder holder, int position, @NonNull final FeedInfo model) {
                holder.setTitle(model.getTitle());
                holder.setDescription(model.getDescription());
                holder.setImage(getActivity().getApplicationContext(), model.getImgUrl());
                holder.setLikes(Integer.toString(model.getCounter()));

                holder.feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Clicking the feedback button");
                    }
                });

                holder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Clicking the like button");
                        Database.likeDBFeature(model.getPostid(), model.getCounter());
                        Database.storeLikedPosts(uid, model.getPostid(), model);

                    }
                });

                holder.attend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Clicking the attend button: " + model.getPostid());
                        Database.newKeyAndValue(uid,model.getPostid(),model);



                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        System.out.println(model.getLink());
                        String url = model.getLink();
                        if(!url.startsWith("https://") && !url.startsWith("http://")){
                            url = "http://" + url;
                        }
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    }

                });
            }

            @NonNull
            @Override
            public CityChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_chat_feed, parent, false);
                return new CityChatViewHolder(view);
            }
        };

        cityFeedList.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class CityChatViewHolder extends RecyclerView.ViewHolder{
        View mView;
        Button like;
        Button feedback;
        Button attend;

        public CityChatViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            like = (Button)mView.findViewById(R.id.like);
            attend = (Button)mView.findViewById(R.id.attend);
            feedback = (Button)mView.findViewById(R.id.feedback);
        }

        public void setTitle(String title){
            TextView mTitle = mView.findViewById(R.id.post_title);
            mTitle.setText(title);

        }

        public void setDescription(String description){
            TextView mDescription = mView.findViewById(R.id.post_description);
            mDescription.setText(description);
        }

        public void setImage(Context ctx, String imgUrl){
            ImageView imgView = mView.findViewById(R.id.post_image);
            Picasso.get().load(imgUrl).into(imgView);
        }

        public void setLikes(String likes){
            TextView likeCounter = mView.findViewById(R.id.likes_counter);
            likeCounter.setText(likes);
        }

    }



}
