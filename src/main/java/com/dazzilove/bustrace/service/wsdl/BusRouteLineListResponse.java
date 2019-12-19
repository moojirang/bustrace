//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.3.0 버전을 통해 생성되었습니다. 
// <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2019.12.16 시간 11:52:07 PM KST 
//


package com.dazzilove.bustrace.service.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>busRouteLineListResponse complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="busRouteLineListResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.api.javaweb/}apiResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="msgBody" type="{http://ws.api.javaweb/}busRouteLineList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "busRouteLineListResponse", propOrder = {
    "msgBody"
})
public class BusRouteLineListResponse
    extends ApiResponse
{

    protected BusRouteLineList msgBody;

    /**
     * msgBody 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BusRouteLineList }
     *     
     */
    public BusRouteLineList getMsgBody() {
        return msgBody;
    }

    /**
     * msgBody 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BusRouteLineList }
     *     
     */
    public void setMsgBody(BusRouteLineList value) {
        this.msgBody = value;
    }

}
