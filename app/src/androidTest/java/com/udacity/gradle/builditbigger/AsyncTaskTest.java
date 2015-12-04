package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AsyncTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public AsyncTaskTest() {
        super(MainActivity.class);
    }

    private MainActivity mActivity;
    private MainActivityFragment mFragment;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);
        mActivity = getActivity();
        mButton = (Button) mActivity.findViewById(R.id.jokeButton);
        mTextView = (TextView) mActivity.findViewById(R.id.joke_textview);
    }

    private Fragment startFragment(Fragment fragment) {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, fragment, "tag");
        transaction.commit();
        getInstrumentation().waitForIdleSync();
        Fragment frag = mActivity.getSupportFragmentManager().findFragmentByTag("tag");
        return frag;
    }

    @MediumTest
    public void testStringRetrieval(){
        String errorCode = "404";

        //two things I want to check for: first that I'm not getting something null back, and second that the string returned doesn't contain a 404 error. As long as those tests pass, the backend is returning a string.
        TouchUtils.clickView(this, mButton);
        MainActivityFragment.EndpointsAsyncTask task = (MainActivityFragment.EndpointsAsyncTask) new MainActivityFragment().new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "joke"));
        try {
            assertNotNull(task.get());
            assert (!task.get().contains(errorCode));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}