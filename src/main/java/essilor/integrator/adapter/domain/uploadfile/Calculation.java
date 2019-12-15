//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.03 at 09:48:44 PM CEST 
//


package essilor.integrator.adapter.domain.uploadfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Calculation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Calculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Diameter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LensBase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CenterThickness" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Calculation", propOrder = {
    "diameter",
    "lensBase",
    "centerThickness",
    "weight"
})
public class Calculation {

    @XmlElement(name = "Diameter")
    protected String diameter;
    @XmlElement(name = "LensBase")
    protected String lensBase;
    @XmlElement(name = "CenterThickness")
    protected String centerThickness;
    @XmlElement(name = "Weight")
    protected String weight;

    /**
     * Gets the value of the diameter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiameter() {
        return diameter;
    }

    /**
     * Sets the value of the diameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiameter(String value) {
        this.diameter = value;
    }

    /**
     * Gets the value of the lensBase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLensBase() {
        return lensBase;
    }

    /**
     * Sets the value of the lensBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLensBase(String value) {
        this.lensBase = value;
    }

    /**
     * Gets the value of the centerThickness property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCenterThickness() {
        return centerThickness;
    }

    /**
     * Sets the value of the centerThickness property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCenterThickness(String value) {
        this.centerThickness = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeight(String value) {
        this.weight = value;
    }

}
