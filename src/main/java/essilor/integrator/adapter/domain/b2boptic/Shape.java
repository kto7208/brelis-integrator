//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:48:00 PM CEST 
//


package essilor.integrator.adapter.domain.b2boptic;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Shape complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Shape">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="points">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="pPoints" type="{}PPoint" maxOccurs="unbounded" minOccurs="18"/>
 *                   &lt;element name="cPoints" type="{}CPoint" maxOccurs="unbounded" minOccurs="18"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Shape", propOrder = {
    "points"
})
@XmlSeeAlso({
    essilor.integrator.adapter.domain.b2boptic.Frame.Shape.Explicit.class
})
public class Shape {

    @XmlElement(required = true)
    protected Shape.Points points;

    /**
     * Gets the value of the points property.
     * 
     * @return
     *     possible object is
     *     {@link Shape.Points }
     *     
     */
    public Shape.Points getPoints() {
        return points;
    }

    /**
     * Sets the value of the points property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shape.Points }
     *     
     */
    public void setPoints(Shape.Points value) {
        this.points = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="pPoints" type="{}PPoint" maxOccurs="unbounded" minOccurs="18"/>
     *         &lt;element name="cPoints" type="{}CPoint" maxOccurs="unbounded" minOccurs="18"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pPoints",
        "cPoints"
    })
    public static class Points {

        protected List<PPoint> pPoints;
        protected List<CPoint> cPoints;

        /**
         * Gets the value of the pPoints property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pPoints property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPPoints().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PPoint }
         * 
         * 
         */
        public List<PPoint> getPPoints() {
            if (pPoints == null) {
                pPoints = new ArrayList<PPoint>();
            }
            return this.pPoints;
        }

        /**
         * Gets the value of the cPoints property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cPoints property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCPoints().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CPoint }
         * 
         * 
         */
        public List<CPoint> getCPoints() {
            if (cPoints == null) {
                cPoints = new ArrayList<CPoint>();
            }
            return this.cPoints;
        }

    }

}
