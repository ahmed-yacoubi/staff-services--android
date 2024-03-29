package com.alaqsa.edu.ps.staffservices.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaqsa.edu.ps.staffservices.adapter.SchedulesViewPagerAdapter;
import com.alaqsa.edu.ps.staffservices.databinding.FragmentNotificationsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private List<Fragment> fragmentList;
    public SchedulesViewPagerAdapter adapter;

    public interface onNotificationEventListener {
        void notificationEvent();
    }

    onNotificationEventListener notificationEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            notificationEventListener = (NotificationsFragment.onNotificationEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    public NotificationsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationEventListener.notificationEvent();
        fragmentList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewPager();

        initTabLayout();


    }


    private void initTabLayout() {


        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.homeTapLayout, binding.homeViewPager, new TabLayoutMediator.TabConfigurationStrategy() {

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("الإشعارات");

                        break;
                    case 1:
                        tab.setText("الرسائل");
                        break;


                }

            }
        });
        tabLayoutMediator.attach();

    }


    private void initViewPager() {
        fragmentList.add(ContainerTabFragment.newInstance("notification_msg", "notification"));
        fragmentList.add(ContainerTabFragment.newInstance("notification_msg", "msg"));

        adapter = new SchedulesViewPagerAdapter(getActivity(), fragmentList);
        binding.homeViewPager.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}