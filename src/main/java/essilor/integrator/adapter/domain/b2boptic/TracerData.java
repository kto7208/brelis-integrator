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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TracerData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TracerData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tracerType" type="{}TracerType"/>
 *         &lt;element name="tracerVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tracerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="binaries">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>hexBinary">
 *                 &lt;attribute name="format" use="required" type="{}TracerBinaryFormat" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="adjustion" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TracerData", propOrder = {
    "tracerType",
    "tracerVersion",
    "tracerID",
    "binaries",
    "adjustion"
})
public class TracerData {

    @XmlElement(required = true)
    protected TracerType tracerType;
    @XmlElement(required = true)
    protected String tracerVersion;
    protected String tracerID;
    @XmlElement(required = true)
    protected TracerData.Binaries binaries;
    protected Float adjustion;

    /**
     * Gets the value of the tracerType property.
     * 
     * @return
     *     possible object is
     *     {@link TracerType }
     *     
     */
    public TracerType getTracerType() {
        return tracerType;
    }

    /**
     * Sets the value of the tracerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TracerType }
     *     
     */
    public void setTracerType(TracerType value) {
        this.tracerType = value;
    }

    /**
     * Gets the value of the tracerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTracerVersion() {
        return tracerVersion;
    }

    /**
     * Sets the value of the tracerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTracerVersion(String value) {
        this.tracerVersion = value;
    }

    /**
     * Gets the value of the tracerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTracerID() {
        return tracerID;
    }

    /**
     * Sets the value of the tracerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTracerID(String value) {
        this.tracerID = value;
    }

    /**
     * Gets the value of the binaries property.
     * 
     * @return
     *     possible object is
     *     {@link TracerData.Binaries }
     *     
     */
    public TracerData.Binaries getBinaries() {
        return binaries;
    }

    /**
     * Sets the value of the binaries property.
     * 
     * @param value
     *     allowed object is
     *     {@link TracerData.Binaries }
     *     
     */
    public void setBinaries(TracerData.Binaries value) {
        this.binaries = value;
    }

    /**
     * Gets the value of the adjustion property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAdjustion() {
        return adjustion;
    }

    /**
     * Sets the value of the adjustion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAdjustion(Float value) {
        this.adjustion = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>hexBinary">
     *       &lt;attribute name="format" use="required" type="{}TracerBinaryFormat" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Binaries {

        @XmlValue
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] value;
        @XmlAttribute(name = "format", required = true)
        protected TracerBinaryFormat format;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(byte[] value) {
            this.value = value;
        }

        /**
         * Gets the value of the format property.
         * 
         * @return
         *     possible object is
         *     {@link TracerBinaryFormat }
         *     
         */
        public TracerBinaryFormat getFormat() {
            return format;
        }

        /**
         * Sets the value of the format property.
         * 
         * @param value
         *     allowed object is
         *     {@link TracerBinaryFormat }
         *     
         */
        public void setFormat(TracerBinaryFormat value) {
            this.format = value;
        }

    }

}
