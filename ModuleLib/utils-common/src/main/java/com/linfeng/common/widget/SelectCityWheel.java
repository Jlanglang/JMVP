//package com.linfeng.common.widget;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.View;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.xingdt.technician.common.widget.wheelview.OnWheelChangedListener;
//import com.xingdt.technician.common.widget.wheelview.WheelView;
//import com.xingdt.technician.common.widget.wheelview.adapters.ArrayWheelAdapter;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author jlanglang  2016/9/6 16:49
// * @版本 2.0 选择城市
// */
//public class SelectCityWheel implements OnWheelChangedListener {
//    public static final int PROVICE_AND_CITY = 0;
//    public static final int All = 2;
//    private static int DEFUTAL_TYPE = All;
//    public Context context;
//    /**
//     * 所有省
//     */
//    public String[] mProvinceDatas;
//    /**
//     * 所以省代码
//     */
//    public int[] mProvinceCodes;
//    /**
//     * key - 省 value - 市
//     */
//    public Map<String, String[]> mCitisDatasMap = new HashMap();
//    /**
//     * key - 省 value - 市代码
//     */
//    public Map<Integer, int[]> mCitisCodeMap = new HashMap();
//    /**
//     * key - 市 value - 区
//     */
//    private Map<String, String[]> mAreaNamesMap = new HashMap<>();
//    /**
//     * key - 市 value - 区代码
//     */
//    public Map<Integer, int[]> mAreaCodeMap = new HashMap();
//
//
//    public String mCurrentProviceName;
//    public String mCurrentCityName;
//    public String mCurrentAreName;
//
//    public int mCurrentCityCode;
//    public int mCurrentProviceCode;
//    public int mCurrentAreaCode;
//
//    public WheelView mViewProvince;
//    public WheelView mViewCity;
//    public WheelView mViewArea;
//
//    private MaterialDialog mDialog;
//    public int items = 5;//可见条目数
//    public View mContent;
//    public SelectCallBack mSelectCityWheel;
//
//    public void setSelectCityWheel(SelectCallBack selectCityWheel) {
//        this.mSelectCityWheel = selectCityWheel;
//    }
//
//
//    public SelectCityWheel(Context context) {
//        this.context = context;
//        initContent();
//        initListener();
//        initDialog();
//    }
//    private void initDialog() {
//        mDialog = new MaterialDialog.Builder(context)
//                .customView(mContent, true)
//                .title("选择城市")
//                .canceledOnTouchOutside(false)
////                .positiveText(R.string.md_ok_label)
////                .negativeText(R.string.md_choose_label)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        if (mSelectCityWheel!=null){
//                            mSelectCityWheel.onClick(dialog, which, mCurrentProviceName, mCurrentProviceCode, mCurrentCityName, mCurrentCityCode,mCurrentAreName,mCurrentAreaCode);
//                        }
//                    }
//                })
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.dismiss();
//                    }
//                }).build();
//    }
//
//    private void initListener() {
//        mViewProvince.addChangingListener(this);
//        mViewCity.addChangingListener(this);
//        mViewArea.addChangingListener(this);
//    }
//
//    private void initContent() {
////        mContent = View.inflate(context, R.layout.pop_wheel, null);
////        mViewProvince = (WheelView) mContent.findViewById(R.id.id_province);
////        mViewCity = (WheelView) mContent.findViewById(R.id.id_city);
////        mViewArea = (WheelView) mContent.findViewById(R.id.id_area);
//        // 设置可见条目数量
//        mViewProvince.setVisibleItems(items);
//        mViewCity.setVisibleItems(items);
//        mViewArea.setVisibleItems(items);
//    }
////
////    /**
////     * 解析省市区的数据
////     */
////    public void initProvinceDatas(List<ProviceAndCityBean> proviceAndCityBeen) {
////        if (proviceAndCityBeen != null && !proviceAndCityBeen.isEmpty()) {
////            mCurrentProviceName = proviceAndCityBeen.get(0).getProvinceName();
////            List<ProviceAndCityBean.CitysBean> citys = proviceAndCityBeen.get(0).getCitys();
////            if (citys != null && !citys.isEmpty()) {
////                mCurrentCityName = citys.get(0).getCityName();
////            }
////        }
////        mProvinceDatas = new String[proviceAndCityBeen.size()];
////        mProvinceCodes = new int[proviceAndCityBeen.size()];
////
////        for (int i = 0; i < proviceAndCityBeen.size(); i++) {
////            // 遍历所有省的数据
////            mProvinceDatas[i] = proviceAndCityBeen.get(i).getProvinceName();
////            mProvinceCodes[i] = proviceAndCityBeen.get(i).getProvinceId();
////            List<ProviceAndCityBean.CitysBean> citys = proviceAndCityBeen.get(i).getCitys();
////            String[] cityNames = new String[citys.size()];
////            int[] cityCodes = new int[citys.size()];
////
////            for (int j = 0; j < citys.size(); j++) {
////                // 遍历省下面的所有市的数据
////                cityNames[j] = citys.get(j).getCityName();
////                cityCodes[j] = citys.get(j).getCityId();
////                List<ProviceAndCityBean.CitysBean.AreasBean> areas = citys.get(j).getAreas();
////                String[] areaNames = new String[areas.size()];
////                int[] areaCodes = new int[areas.size()];
////                // 遍历省下面的所有区的数据
////                for (int z = 0; z < areas.size(); z++) {
////                    areaCodes[z] = areas.get(z).getAreaId();
////                    areaNames[z] = areas.get(z).getAreaName();
////                }
////                // 市-区的数据，保存到mAreaNamesMap
////                mAreaCodeMap.put(cityCodes[j], areaCodes);
////                mAreaNamesMap.put(cityNames[j], areaNames);
////            }
////
////            // 省-市的数据，保存到mCitisDatasMap
////            mCitisDatasMap.put(mProvinceDatas[i], cityNames);
////            mCitisCodeMap.put(mProvinceCodes[i], cityCodes);
////        }
////    }
//
//    public void showWheel(int type) {
//        switch (type) {
//            case All:
//                mViewArea.setVisibility(View.VISIBLE);
//                break;
//            case PROVICE_AND_CITY:
//                mViewArea.setVisibility(View.GONE);
//                break;
//        }
//        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceDatas));
//        updateProvince();
//        mDialog.show();
//    }
//
//    public void showWheel() {
//        showWheel(DEFUTAL_TYPE);
//    }
//
//    /**
//     * 根据wheelView当前指标位置更新省份和城市数据
//     */
//    public void updateProvince() {
//        int pCurrent = mViewProvince.getCurrentItem();
//        mCurrentProviceName = mProvinceDatas[pCurrent];
//        mCurrentProviceCode = mProvinceCodes[pCurrent];
//
//        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
//        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
//        mViewCity.setCurrentItem(0);
//        updateCitys();
//    }
//
//    /**
//     * 根据wheelView当前指标位置更新城市数据
//     */
//    public void updateCitys() {
//        int pCurrent = mViewCity.getCurrentItem();
//        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName).length > 0 ? mCitisDatasMap.get(mCurrentProviceName)[pCurrent] : "";
//        mCurrentCityCode = mCitisCodeMap.get(mCurrentProviceCode).length > 0 ? mCitisCodeMap.get(mCurrentProviceCode)[pCurrent] : -1;
//        if (DEFUTAL_TYPE == All) {
//            String[] areas = mAreaNamesMap.get(mCurrentCityName);
//            mViewArea.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
//            mViewArea.setCurrentItem(0);
//            updateAreas();
//        }
//    }
//
//    private void updateAreas() {
//        int pCurrent = mViewArea.getCurrentItem();
//        mCurrentAreName = mAreaNamesMap.get(mCurrentCityName).length > 0 ? mAreaNamesMap.get(mCurrentCityName)[pCurrent] : "";
//        mCurrentAreaCode = mAreaCodeMap.get(mCurrentCityCode).length > 0 ? mAreaCodeMap.get(mCurrentCityCode)[pCurrent] : -1;
//    }
//
//    @Override
//    public void onChanged(WheelView wheel, int oldValue, int newValue) {
//        if (wheel == mViewProvince) {
//            updateProvince();
//        } else if (wheel == mViewCity) {
//            updateCitys();
//        } else if (wheel == mViewArea) {
//            updateAreas();
//        }
//    }
//
//    public interface SelectCallBack {
//        void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which, String currentProviceName, int currentProviceCode, String currentCityName, int currentCityCode, String currentAreaName, int currentAreaCode);
//    }
//}
