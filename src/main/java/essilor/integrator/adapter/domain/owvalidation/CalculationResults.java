//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.03 at 09:46:51 PM CEST 
//


package essilor.integrator.adapter.domain.owvalidation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CalculationResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalculationResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalculationImage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="Left" type="{http://OpsysWeb.eu/}Calculation" minOccurs="0"/>
 *         &lt;element name="Right" type="{http://OpsysWeb.eu/}Calculation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationResults", propOrder = {
    "calculationImage",
    "left",
    "right"
})
public class CalculationResults {

    @XmlElement(name = "CalculationImage")
    protected byte[] calculationImage;
    @XmlElement(name = "Left")
    protected Calculation left;
    @XmlElement(name = "Right")
    protected Calculation right;

    /**
     * Gets the value of the calculationImage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCalculationImage() {
        return calculationImage;
    }

    /**
     * Sets the value of the calculationImage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCalculationImage(byte[] value) {
        this.calculationImage = value;
    }

    /**
     * Gets the value of the left property.
     * 
     * @return
     *     possible object is
     *     {@link Calculation }
     *     
     */
    public Calculation getLeft() {
        return left;
    }

    /**
     * Sets the value of the left property.
     * 
     * @param value
     *     allowed object is
     *     {@link Calculation }
     *     
     */
    public void setLeft(Calculation value) {
        this.left = value;
    }

    /**
     * Gets the value of the right property.
     * 
     * @return
     *     possible object is
     *     {@link Calculation }
     *     
     */
    public Calculation getRight() {
        return right;
    }

    /**
     * Sets the value of the right property.
     * 
     * @param value
     *     allowed object is
     *     {@link Calculation }
     *     
     */
    public void setRight(Calculation value) {
        this.right = value;
    }

}
