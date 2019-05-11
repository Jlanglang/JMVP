package com.linfeng.demo;

public class Test {

    class Bean{
        String a=null;
        String b=null;
    }

    public void test() {
        Man man = new Man();
        CodeFarmer manCodeFarmer = new CodeFarmer(man);//男码农

        Woman woman = new Woman();
        CodeFarmer womanCodeFarmer = new CodeFarmer(woman);//女码农

        SeniorCodeFarmer manSenior = new SeniorCodeFarmer(manCodeFarmer);//男码农=>高级码农
        SeniorCodeFarmer manSenior1 = new SeniorCodeFarmer(man);//男=>高级

        SeniorCodeFarmer woManSenior = new SeniorCodeFarmer(womanCodeFarmer);//女高级
        SeniorCodeFarmer woManSenior1 = new SeniorCodeFarmer(woManSenior);//女高级


    }

    /**
     * 人
     */
    public interface Person {
        String sex();

        int age();
    }

    /**
     * 女人
     */
    public class Woman implements Person {

        @Override
        public String sex() {
            return "女";
        }

        @Override
        public int age() {
            return 0;
        }
    }

    /**
     * 男人
     */
    public class Man implements Person {

        @Override
        public String sex() {
            return "男";
        }

        @Override
        public int age() {
            return 0;
        }
    }

    /**
     * 码农
     */
    public class CodeFarmer implements Person {
        private Person person;

        public CodeFarmer(Person person) {
            this.person = person;
        }

        @Override
        public String sex() {
            return person.sex();
        }

        @Override
        public int age() {
            return person.age();
        }

        public String toString() {
            return "我是" + person.sex() + "码农";
        }
    }

    /**
     * 高级码农
     */
    public class SeniorCodeFarmer implements Person {
        private Person person;

        public SeniorCodeFarmer(Person person) {
            this.person = person;
        }

        @Override
        public String sex() {
            return person.sex();
        }

        @Override
        public int age() {
            return person.age();
        }

        public String toString() {
            return "我是" + person.sex() + "高级码农";
        }
    }

    static class Test1 {
        public static void main(String[] str) {
            f3();
        }

        static void f3() {

            String a = "1";
            String b = 1 + "";
            System.out.println(a.equals(b));
            System.out.println(a == b);

            String c = "4";
            String d = c;
            d = "5";
            System.out.println(c);

            d = c;
            c = "6";
            System.out.println(d);


            String e = new String("6");
            String f = "6";
            System.out.println(e.equals(f));
            System.out.println(e == f);
        }
    }
}


