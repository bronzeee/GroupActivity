package com.opticaline.groupactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.opticaline.groupactivity.view.GameView;

/**
 * Created by Administrator on 13-11-21.
 */
public class GameFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_layout, container, false);
        GameView gameView = (GameView) view.findViewById(R.id.gameView);
        gameView.drawRect(3, 3, R.color.blue);
        gameView.drawRect(3, 4, R.color.blue);
        gameView.drawRect(3, 5, R.color.blue);
        gameView.postInvalidate();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
