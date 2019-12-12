//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.3.0 버전을 통해 생성되었습니다. 
// <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2019.12.12 시간 04:02:56 PM KST 
//


package com.dazzilove.bustrace.service.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>busRouteLine complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="busRouteLine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lineSeq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "busRouteLine", propOrder = {
    "lineSeq",
    "locationId",
    "x",
    "y"
})
public class BusRouteLine {

    protected String lineSeq;
    protected String locationId;
    protected double x;
    protected double y;

    /**
     * lineSeq 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineSeq() {
        return lineSeq;
    }

    /**
     * lineSeq 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineSeq(String value) {
        this.lineSeq = value;
    }

    /**
     * locationId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * locationId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationId(String value) {
        this.locationId = value;
    }

    /**
     * x 속성의 값을 가져옵니다.
     * 
     */
    public double getX() {
        return x;
    }

    /**
     * x 속성의 값을 설정합니다.
     * 
     */
    public void setX(double value) {
        this.x = value;
    }

    /**
     * y 속성의 값을 가져옵니다.
     * 
     */
    public double getY() {
        return y;
    }

    /**
     * y 속성의 값을 설정합니다.
     * 
     */
    public void setY(double value) {
        this.y = value;
    }

}
