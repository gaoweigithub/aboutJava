package elasticsearch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public enum ESHotelIndexEnum {
    hotellist("tapp_hotellist", "tapp_hotellist", true),
    submitorder("tapp_submitorder", "tapp_submitorder", true),
    hotelsingleroom("tapp_hotelsingleroom", "tapp_hotelsingleroom", true),
    hotelroomlist("tapp_hotelroomlist", "tapp_hotelroomlist", true),
    gaowei("gaowei", "redisLimitor", false);

    public String getIndexNameWithDate() {
        if (withDate) {
            SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMM");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return indexName + sdf.format(new Date());
        } else {
            return indexName;
        }
    }

    public String getIndexName() {
        return indexName;
    }

    private String indexName;

    public String getTypeName() {
        return typeName;
    }

    private String typeName;
    private boolean withDate;

    public boolean isWithDate() {
        return withDate;
    }

    ESHotelIndexEnum(String indexName, String typeName, boolean withDate) {
        this.indexName = indexName;
        this.typeName = typeName;
        this.withDate = withDate;
    }

    public static void main(String[] args) {
        ESHotelIndexEnum[] values = ESHotelIndexEnum.values();
        for (ESHotelIndexEnum v : values) {
            System.out.println(v.getIndexNameWithDate());
            System.out.println(v.getIndexName());
        }
    }
}
