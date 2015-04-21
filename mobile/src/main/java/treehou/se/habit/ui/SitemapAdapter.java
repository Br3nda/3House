package treehou.se.habit.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import treehou.se.habit.core.LinkedPage;
import treehou.se.habit.core.Server;

/**
 * Created by ibaton on 2014-09-11.
 */
public class SitemapAdapter extends FragmentStatePagerAdapter {

    private List<LinkedPage> mPages;
    private SitemapFragment mSitemapFragment;
    private Server mServer;

    public SitemapAdapter(Server server, SitemapFragment sitemapFragment, FragmentManager fragmentManager, List<LinkedPage> pages){
        super(fragmentManager);

        mSitemapFragment = sitemapFragment;
        mPages = pages;
        mServer = server;
    }

    @Override
    public Fragment getItem(int i) {
        return PageFragment.newInstance(mSitemapFragment, mServer, mPages.get(i));
    }

    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPages.get(position).getTitle();
    }

    @Override
    public int getItemPosition(Object object){
        return FragmentStatePagerAdapter.POSITION_NONE;
    }
}
