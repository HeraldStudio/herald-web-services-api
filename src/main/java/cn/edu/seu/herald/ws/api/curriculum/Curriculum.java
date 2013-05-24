//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.24 at 05:40:35 ���� CST 
//


package cn.edu.seu.herald.ws.api.curriculum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for curriculum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="curriculum">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="courses" type="{http://herald.seu.edu.cn/ws/curriculum}courses"/>
 *         &lt;element name="timeTable" type="{http://herald.seu.edu.cn/ws/curriculum}timeTable"/>
 *       &lt;/sequence>
 *       &lt;attribute name="term" type="{http://herald.seu.edu.cn/ws/curriculum}semester" />
 *       &lt;attribute name="cardNumber" type="{http://herald.seu.edu.cn/ws/curriculum}cardNumber" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "curriculum", propOrder = {
    "courses",
    "timeTable"
})
@XmlRootElement(name = "curriculum")
public class Curriculum {

    @XmlElement(required = true)
    protected Courses courses;
    @XmlElement(required = true)
    protected TimeTable timeTable;
    @XmlAttribute
    protected String term;
    @XmlAttribute
    protected String cardNumber;

    /**
     * Gets the value of the courses property.
     * 
     * @return
     *     possible object is
     *     {@link Courses }
     *     
     */
    public Courses getCourses() {
        return courses;
    }

    /**
     * Sets the value of the courses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Courses }
     *     
     */
    public void setCourses(Courses value) {
        this.courses = value;
    }

    /**
     * Gets the value of the timeTable property.
     * 
     * @return
     *     possible object is
     *     {@link TimeTable }
     *     
     */
    public TimeTable getTimeTable() {
        return timeTable;
    }

    /**
     * Sets the value of the timeTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeTable }
     *     
     */
    public void setTimeTable(TimeTable value) {
        this.timeTable = value;
    }

    /**
     * Gets the value of the term property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerm(String value) {
        this.term = value;
    }

    /**
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

}
