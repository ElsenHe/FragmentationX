package me.liam.support;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import me.liam.anim.DefaultAnimation;
import me.liam.anim.FragmentAnimation;
import me.liam.anim.NoneAnim;
import me.liam.helper.FragmentUtils;

public class SupportActivity extends AppCompatActivity implements ISupportActivity {

    SupportTransaction supportTransaction;

    FragmentAnimation defaultAnimation = new DefaultAnimation();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportTransaction = new SupportTransaction(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 1){
            pop();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void setDefaultAnimation(FragmentAnimation animation) {
        defaultAnimation = animation;
        List<SupportFragment> fragmentList = new ArrayList<>();
        FragmentUtils.getAllFragments(fragmentList,getSupportFragmentManager());
        for (SupportFragment f : fragmentList){
            f.setFragmentAnimation(defaultAnimation);
        }
    }

    @Override
    public FragmentAnimation getDefaultAnimation() {
        return defaultAnimation;
    }

    @Override
    public <T extends Fragment> T findFragmentByClass(Class cls) {
        return FragmentUtils.findFragmentByClass(getSupportFragmentManager(),cls);
    }

    @Override
    public void loadRootFragment(int containerId, SupportFragment to, FragmentAnimation anim, boolean playEnterAnim) {
        supportTransaction.loadRootFragment(getSupportFragmentManager(),containerId,to,anim,playEnterAnim);
    }

    @Override
    public void loadRootFragment(int containerId, SupportFragment to) {
        supportTransaction.loadRootFragment(getSupportFragmentManager(),containerId,to,getDefaultAnimation(),false);
    }

    @Override
    public void loadMultipleRootFragments(int containerId, int showPosition, SupportFragment... fragments) {
        supportTransaction.loadMultipleRootFragments(getSupportFragmentManager(),containerId,showPosition,fragments);
    }

    @Override
    public void showHideAllFragment(SupportFragment show) {
        supportTransaction.showHideAllFragment(getSupportFragmentManager(), show);
    }

    @Override
    public void start(SupportFragment to) {
        supportTransaction.start(FragmentUtils.getTopOne(getSupportFragmentManager()),to);
    }

    @Override
    public void start(SupportFragment from, SupportFragment to) {
        supportTransaction.start(from,to);
    }

    @Override
    public void pop() {
        supportTransaction.pop(getSupportFragmentManager());
    }


}
