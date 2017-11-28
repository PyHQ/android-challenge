package py.keeporswap;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import java.util.Queue;

public class MainActivity extends Activity {
    private Queue<Snippet> snippets = Snippet.getSnippets();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwapFragment firstFragment = SwapFragment.newInstance(snippets.remove());
        getFragmentManager().beginTransaction()
                .add(R.id.container, firstFragment).commit();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

    }

    public void loadNextFragment() {
        Fragment nextFragment = snippets.isEmpty() ? new AgainFragment()
                :  SwapFragment.newInstance(snippets.remove());
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(R.id.container, nextFragment).commit();
    }

    public void again() {
        snippets = Snippet.getSnippets();
        loadNextFragment();
    }

    public void requestFinish() {
        finish();
    }
}
