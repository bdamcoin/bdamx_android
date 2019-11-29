package com.application.bdamx.adapter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.application.bdamx.R;
import com.application.bdamx.model.paperkeyModel;

import java.util.List;

public class CustomViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<paperkeyModel> hotDealList;
    private LayoutInflater layoutInflater;
    public CustomViewPagerAdapter(Context context, List<paperkeyModel> hotDealList) {
        this.context = context;
        this.hotDealList = hotDealList;
        this.layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        System.out.println("sizeee==="+hotDealList.size());
        return hotDealList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((View) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.adapter_paper_key, container, false);
        paperkeyModel mHotDealObject = hotDealList.get(position);
        TextView key_btn = (TextView)view.findViewById(R.id.key_btn);
        TextView keycount = (TextView) view.findViewById(R.id.keycount);
        System.out.println("currnn==="+mHotDealObject.getKeyname());
        String value= String.valueOf(position+1);

        //bind value to the View Widgets
        key_btn.setText(mHotDealObject.getKeyname());
        keycount.setText(value+" of "+hotDealList.size());

        container.addView(view);
        return view;
    }
}