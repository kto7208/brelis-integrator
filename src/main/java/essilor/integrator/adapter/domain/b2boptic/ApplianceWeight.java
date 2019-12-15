//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:48:00 PM CEST 
//


package essilor.integrator.adapter.domain.b2boptic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApplianceWeight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplianceWeight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="weightingNear" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="weightingMiddle" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="weightingFar" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="applianceWeightUnity" use="required" type="{}ApplianceWeightUnity" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplianceWeight", propOrder = {
    "weightingNear",
    "weightingMiddle",
    "weightingFar"
})
public class ApplianceWeight {

    protected Float weightingNear;
    protected Float weightingMiddle;
    protected Float weightingFar;
    @XmlAttribute(name = "applianceWeightUnity", required = true)
    protected ApplianceWeightUnity applianceWeightUnity;

    /**
     * Gets the value of the weightingNear property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWeightingNear() {
        return weightingNear;
    }

    /**
     * Sets the value of the weightingNear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWeightingNear(Float value) {
        this.weightingNear = value;
    }

    /**
     * Gets the value of the weightingMiddle property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWeightingMiddle() {
        return weightingMiddle;
    }

    /**
     * Sets the value of the weightingMiddle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWeightingMiddle(Float value) {
        this.weightingMiddle = value;
    }

    /**
     * Gets the value of the weightingFar property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWeightingFar() {
        return weightingFar;
    }

    /**
     * Sets the value of the weightingFar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWeightingFar(Float value) {
        this.weightingFar = value;
    }

    /**
     * Gets the value of the applianceWeightUnity property.
     * 
     * @return
     *     possible object is
     *     {@link ApplianceWeightUnity }
     *     
     */
    public ApplianceWeightUnity getApplianceWeightUnity() {
        return applianceWeightUnity;
    }

    /**
     * Sets the value of the applianceWeightUnity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplianceWeightUnity }
     *     
     */
    public void setApplianceWeightUnity(ApplianceWeightUnity value) {
        this.applianceWeightUnity = value;
    }

}
