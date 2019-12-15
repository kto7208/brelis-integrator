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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IProfilerResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IProfilerResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autorefractorEye" type="{}RXDataTypeSimple"/>
 *         &lt;element name="wavefrontEye">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pupilCenter" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="zernikes" type="{}Zernikes"/>
 *                   &lt;element name="map" type="{}IProfilerMap" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="wavefrontCornea" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="vertexPosition">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="zernikes" type="{}Zernikes"/>
 *                   &lt;element name="map" type="{}IProfilerMap" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="side" use="required" type="{}Sides" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IProfilerResult", propOrder = {
    "autorefractorEye",
    "wavefrontEye",
    "wavefrontCornea"
})
public class IProfilerResult {

    @XmlElement(required = true)
    protected RXDataTypeSimple autorefractorEye;
    @XmlElement(required = true)
    protected IProfilerResult.WavefrontEye wavefrontEye;
    protected IProfilerResult.WavefrontCornea wavefrontCornea;
    @XmlAttribute(name = "side", required = true)
    protected Sides side;

    /**
     * Gets the value of the autorefractorEye property.
     * 
     * @return
     *     possible object is
     *     {@link RXDataTypeSimple }
     *     
     */
    public RXDataTypeSimple getAutorefractorEye() {
        return autorefractorEye;
    }

    /**
     * Sets the value of the autorefractorEye property.
     * 
     * @param value
     *     allowed object is
     *     {@link RXDataTypeSimple }
     *     
     */
    public void setAutorefractorEye(RXDataTypeSimple value) {
        this.autorefractorEye = value;
    }

    /**
     * Gets the value of the wavefrontEye property.
     * 
     * @return
     *     possible object is
     *     {@link IProfilerResult.WavefrontEye }
     *     
     */
    public IProfilerResult.WavefrontEye getWavefrontEye() {
        return wavefrontEye;
    }

    /**
     * Sets the value of the wavefrontEye property.
     * 
     * @param value
     *     allowed object is
     *     {@link IProfilerResult.WavefrontEye }
     *     
     */
    public void setWavefrontEye(IProfilerResult.WavefrontEye value) {
        this.wavefrontEye = value;
    }

    /**
     * Gets the value of the wavefrontCornea property.
     * 
     * @return
     *     possible object is
     *     {@link IProfilerResult.WavefrontCornea }
     *     
     */
    public IProfilerResult.WavefrontCornea getWavefrontCornea() {
        return wavefrontCornea;
    }

    /**
     * Sets the value of the wavefrontCornea property.
     * 
     * @param value
     *     allowed object is
     *     {@link IProfilerResult.WavefrontCornea }
     *     
     */
    public void setWavefrontCornea(IProfilerResult.WavefrontCornea value) {
        this.wavefrontCornea = value;
    }

    /**
     * Gets the value of the side property.
     * 
     * @return
     *     possible object is
     *     {@link Sides }
     *     
     */
    public Sides getSide() {
        return side;
    }

    /**
     * Sets the value of the side property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sides }
     *     
     */
    public void setSide(Sides value) {
        this.side = value;
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
     *       &lt;sequence>
     *         &lt;element name="vertexPosition">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="zernikes" type="{}Zernikes"/>
     *         &lt;element name="map" type="{}IProfilerMap" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vertexPosition",
        "zernikes",
        "map"
    })
    public static class WavefrontCornea {

        @XmlElement(required = true)
        protected IProfilerResult.WavefrontCornea.VertexPosition vertexPosition;
        @XmlElement(required = true)
        protected Zernikes zernikes;
        protected IProfilerMap map;

        /**
         * Gets the value of the vertexPosition property.
         * 
         * @return
         *     possible object is
         *     {@link IProfilerResult.WavefrontCornea.VertexPosition }
         *     
         */
        public IProfilerResult.WavefrontCornea.VertexPosition getVertexPosition() {
            return vertexPosition;
        }

        /**
         * Sets the value of the vertexPosition property.
         * 
         * @param value
         *     allowed object is
         *     {@link IProfilerResult.WavefrontCornea.VertexPosition }
         *     
         */
        public void setVertexPosition(IProfilerResult.WavefrontCornea.VertexPosition value) {
            this.vertexPosition = value;
        }

        /**
         * Gets the value of the zernikes property.
         * 
         * @return
         *     possible object is
         *     {@link Zernikes }
         *     
         */
        public Zernikes getZernikes() {
            return zernikes;
        }

        /**
         * Sets the value of the zernikes property.
         * 
         * @param value
         *     allowed object is
         *     {@link Zernikes }
         *     
         */
        public void setZernikes(Zernikes value) {
            this.zernikes = value;
        }

        /**
         * Gets the value of the map property.
         * 
         * @return
         *     possible object is
         *     {@link IProfilerMap }
         *     
         */
        public IProfilerMap getMap() {
            return map;
        }

        /**
         * Sets the value of the map property.
         * 
         * @param value
         *     allowed object is
         *     {@link IProfilerMap }
         *     
         */
        public void setMap(IProfilerMap value) {
            this.map = value;
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
         *       &lt;sequence>
         *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "x",
            "y"
        })
        public static class VertexPosition {

            protected float x;
            protected float y;

            /**
             * Gets the value of the x property.
             * 
             */
            public float getX() {
                return x;
            }

            /**
             * Sets the value of the x property.
             * 
             */
            public void setX(float value) {
                this.x = value;
            }

            /**
             * Gets the value of the y property.
             * 
             */
            public float getY() {
                return y;
            }

            /**
             * Sets the value of the y property.
             * 
             */
            public void setY(float value) {
                this.y = value;
            }

        }

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
     *       &lt;sequence>
     *         &lt;element name="pupilCenter" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="zernikes" type="{}Zernikes"/>
     *         &lt;element name="map" type="{}IProfilerMap" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pupilCenter",
        "zernikes",
        "map"
    })
    public static class WavefrontEye {

        protected IProfilerResult.WavefrontEye.PupilCenter pupilCenter;
        @XmlElement(required = true)
        protected Zernikes zernikes;
        protected IProfilerMap map;

        /**
         * Gets the value of the pupilCenter property.
         * 
         * @return
         *     possible object is
         *     {@link IProfilerResult.WavefrontEye.PupilCenter }
         *     
         */
        public IProfilerResult.WavefrontEye.PupilCenter getPupilCenter() {
            return pupilCenter;
        }

        /**
         * Sets the value of the pupilCenter property.
         * 
         * @param value
         *     allowed object is
         *     {@link IProfilerResult.WavefrontEye.PupilCenter }
         *     
         */
        public void setPupilCenter(IProfilerResult.WavefrontEye.PupilCenter value) {
            this.pupilCenter = value;
        }

        /**
         * Gets the value of the zernikes property.
         * 
         * @return
         *     possible object is
         *     {@link Zernikes }
         *     
         */
        public Zernikes getZernikes() {
            return zernikes;
        }

        /**
         * Sets the value of the zernikes property.
         * 
         * @param value
         *     allowed object is
         *     {@link Zernikes }
         *     
         */
        public void setZernikes(Zernikes value) {
            this.zernikes = value;
        }

        /**
         * Gets the value of the map property.
         * 
         * @return
         *     possible object is
         *     {@link IProfilerMap }
         *     
         */
        public IProfilerMap getMap() {
            return map;
        }

        /**
         * Sets the value of the map property.
         * 
         * @param value
         *     allowed object is
         *     {@link IProfilerMap }
         *     
         */
        public void setMap(IProfilerMap value) {
            this.map = value;
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
         *       &lt;sequence>
         *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "x",
            "y"
        })
        public static class PupilCenter {

            protected float x;
            protected float y;

            /**
             * Gets the value of the x property.
             * 
             */
            public float getX() {
                return x;
            }

            /**
             * Sets the value of the x property.
             * 
             */
            public void setX(float value) {
                this.x = value;
            }

            /**
             * Gets the value of the y property.
             * 
             */
            public float getY() {
                return y;
            }

            /**
             * Sets the value of the y property.
             * 
             */
            public void setY(float value) {
                this.y = value;
            }

        }

    }

}
