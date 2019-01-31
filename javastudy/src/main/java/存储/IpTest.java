package 存储;

public class IpTest {
    public static void main(String[] args) {
        IpEntity ipEntity1 = new IpEntity(192, 168, 0, 1);
        IpEntity ipEntity2 = new IpEntity(255, 255, 255, 255);
        IpEntity ipEntity3 = new IpEntity(192, 168, 1, 1);
        IpEntity ipEntity4 = new IpEntity(10, 10, 10, 10);


        //encode
        System.out.println(ipEntity1.toNum());
        System.out.println(ipEntity2.toNum());
        System.out.println(ipEntity3.toNum());
        System.out.println(ipEntity4.toNum());

        //decode
        System.out.println(new IpEntity(3232235521L));
    }

    static class IpEntity {
        private int first;
        private int second;
        private int thrid;
        private int fourth;

        public IpEntity(int first, int second, int thrid, int fourth) {
            this.first = first;
            this.second = second;
            this.thrid = thrid;
            this.fourth = fourth;
        }

        public IpEntity(long num) {
            this.first = (int) ((num & ((long) 255 << 24))>>24);
            this.second = (int) ((num & (255 << 16))>>16);
            this.thrid = (int) ((num & (255 << 8))>>8);
            this.fourth = (int) (num & 255);
        }

        public long toNum() {
            return fourth | thrid << 8 | second << 16 | (long) first << 24;
//            return fourth + thrid * 256 + second * 256 * 256 + (long)first * 256 * 256 * 256;
        }

        @Override
        public String toString() {
            return String.format("%d.%d.%d.%d", first, second, thrid, fourth);
        }
    }
}
