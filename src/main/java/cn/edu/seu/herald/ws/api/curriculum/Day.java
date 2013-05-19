//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.19 at 02:37:34 ���� CST 
//


package cn.edu.seu.herald.ws.api.curriculum;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.Date;


/**
 * <p>Java class for day.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="day">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MON"/>
 *     &lt;enumeration value="TUE"/>
 *     &lt;enumeration value="THU"/>
 *     &lt;enumeration value="WED"/>
 *     &lt;enumeration value="FRI"/>
 *     &lt;enumeration value="SAT"/>
 *     &lt;enumeration value="SUN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "day")
@XmlEnum
public enum Day {

    /**
     * 星期一
     */
    MON,
    /**
     * 星期二
     */
    TUE,
    /**
     * 星期三
     */
    WED,
    /**
     * 星期四
     */
    THU,
    /**
     * 星期五
     */
    FRI,
    /**
     * 星期六
     */
    SAT,
    /**
     * 星期日
     */
    SUN;

    /**
     * 获取日期中的星期
     *
     * @param date 日期
     * @return 星期
     */
    public static Day getDayOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return parseCalendarDay(dayOfWeek);
    }

    /**
     * 获取今天星期几
     * @return 星期
     */
    public static Day getDayOfToday() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return parseCalendarDay(dayOfWeek);
    }

    private static Day parseCalendarDay(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return SUN;
            case Calendar.MONDAY:
                return MON;
            case Calendar.TUESDAY:
                return TUE;
            case Calendar.WEDNESDAY:
                return WED;
            case Calendar.THURSDAY:
                return THU;
            case Calendar.FRIDAY:
                return FRI;
            case Calendar.SATURDAY:
                return SAT;
            default:
                throw new RuntimeException("There is no Day for " + dayOfWeek);
        }
    }

    public String value() {
        return name();
    }

    public static Day fromValue(String v) {
        return valueOf(v);
    }

}
