package JUC.内部类.测试;

public class SelfNumber<T> {
    private T actualNumber;

    public T getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(T actualNumber) {
        this.actualNumber = actualNumber;
    }
    public static class SelfInnerClass<K>{
        private K actualNumber;

        public K getActualNumber() {
            return actualNumber;
        }

        public void setActualNumber(K actualNumber) {
            this.actualNumber = actualNumber;
        }
    }

    public static void main(String[] args) {
        SelfNumber<Long> sl = new SelfNumber<>();
        SelfNumber<Integer> si = new SelfNumber<>();

//        sl.setActualNumber(Long.valueOf(123));
//        si.setActualNumber(Integer.valueOf(321));
//        System.out.println(sl.getActualNumber());
//        System.out.println(si.getActualNumber());

        SelfNumber.SelfInnerClass<Long> sli =new SelfNumber.SelfInnerClass();
        SelfNumber.SelfInnerClass<Integer> sii =new SelfNumber.SelfInnerClass();
        sli.setActualNumber(Long.valueOf(123));

        System.out.println("sli" + sli.getActualNumber());
        System.out.println("sii" + sii.getActualNumber());

    }
}
