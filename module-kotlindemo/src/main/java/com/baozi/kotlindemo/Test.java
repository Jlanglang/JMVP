package com.baozi.kotlindemo;

class Test {
    public class City implements Wheel {
        Area area;

        @Override
        public void notifyData() {
            //更新当前city数据
            area.notifyData();
        }
    }

    public class Area implements Wheel {
        @Override
        public void notifyData() {
            //更新当前Area数据
        }
    }

    public class Province implements Wheel {
        City city;

        @Override
        public void notifyData() {
            //解析更新当前Province数据
            //....
            city.notifyData();
        }
    }

    interface Wheel {
        void notifyData();
    }
}
