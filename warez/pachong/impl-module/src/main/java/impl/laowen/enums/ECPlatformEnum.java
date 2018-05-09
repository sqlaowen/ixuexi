package impl.laowen.enums;

/**
 * Created by iyou on 2016/12/17.
 */
public enum ECPlatformEnum {

    PLATFORM_JD(1, "京东");

    private Integer id;
    private String name;

    private ECPlatformEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //
    public static String getName(int id) {
        for (ECPlatformEnum ecPlatformEnum : ECPlatformEnum.values()) {
            if (ecPlatformEnum.getId() == id) {
                return ecPlatformEnum.name;
            }
        }
        return null;
    }

    /**
     * 电商平台枚举id
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * 电商平台枚举id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 电商平台枚举name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 电商平台枚举name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
