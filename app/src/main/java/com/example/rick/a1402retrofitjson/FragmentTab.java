package com.example.rick.a1402retrofitjson;

import android.support.v4.app.Fragment;

/**
 * Суперкласс фрагментов страниц в табах
 * используется для ленивой загрузки данных на неактивных страницах, грузим, если таб активен
 *
 * в активити, где собираем ViewPager viewPager добавляем addOnPageChangeListener
 *
 * viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 *
 *             @Override
 *             public void onPageSelected(int position) {
 *                 FragmentTab fragment = (FragmentTab)adapter.getItem(position);
 *                 if(!fragment.isDataLoaded()){
 *                     fragment.loadData();
 *                 }
 *
 *
 *             }
 *
 *             @Override
 *             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
 *             }
 *
 *             @Override
 *             public void onPageScrollStateChanged(int state) {
 *             }
 *         });
 *
 *
 *
 */
public abstract class FragmentTab extends Fragment {

    private Boolean isLoaded;
    private final DataService mBackendModule = DataService.getInstance();

    public void loadData(){
        setIsLoaded(true);
        //TODO реализовать в наследниках
        /**
         * используем метод прогрузки данных необходимые для конкретного таба
         * например
         *
         * getDataService().loadUsers();
         *
         */
    }

    public FragmentTab(){
        isLoaded = false;
    }

    public Boolean isDataLoaded(){
        return isLoaded;
    }

    public void setIsLoaded(Boolean isLoaded){
        this.isLoaded = isLoaded;
    }

    public DataService getDataService(){
        return mBackendModule;
    }


}
