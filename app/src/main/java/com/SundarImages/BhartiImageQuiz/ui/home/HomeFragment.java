package com.SundarImages.BhartiImageQuiz.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.SundarImages.BhartiImageQuiz.QuizStartActicity;
import com.SundarImages.BhartiImageQuiz.R;

public class HomeFragment extends Fragment {
    Button startQuiz1,startQuiz2;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        startQuiz1 = root.findViewById(R.id.starQuiz1);
        startQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QuizStartActicity.class));
            }
        });
      /*  startQuiz2 = root.findViewById(R.id.starQuiz2);
        startQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QuixStartActivity2.class));
            }
        });*/
        return root;
    }
}