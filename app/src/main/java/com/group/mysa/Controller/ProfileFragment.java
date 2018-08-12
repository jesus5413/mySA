package com.group.mysa.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.group.mysa.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    private Toolbar mToolBar;
    private ViewPager pager;

    private ProfileSectionsPageAdapter mPagerAdapter;
    private TabLayout mTabLayout;


    public ProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mToolBar = (Toolbar) view.findViewById(R.id.profile_tool_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolBar);
        mToolBar.setTitle("mySA");

        pager = (ViewPager)view.findViewById(R.id.profile_tab_pager);
        mPagerAdapter = new ProfileSectionsPageAdapter(getChildFragmentManager());
        pager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout)view.findViewById(R.id.profile_tab_layout);
        mTabLayout.setupWithViewPager(pager);

        return view;
    }

}
