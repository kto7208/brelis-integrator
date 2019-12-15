//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 07:48:00 PM CEST 
//


package essilor.integrator.adapter.domain.b2boptic;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TracerBinaryFormat.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TracerBinaryFormat">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DLL BRIOT"/>
 *     &lt;enumeration value="DVI"/>
 *     &lt;enumeration value="GT3000"/>
 *     &lt;enumeration value="MO1"/>
 *     &lt;enumeration value="NIDEK"/>
 *     &lt;enumeration value="OMA3.02"/>
 *     &lt;enumeration value="PHI"/>
 *     &lt;enumeration value="T4"/>
 *     &lt;enumeration value="WECO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TracerBinaryFormat")
@XmlEnum
public enum TracerBinaryFormat {

    @XmlEnumValue("DLL BRIOT")
    DLL_BRIOT("DLL BRIOT"),
    DVI("DVI"),
    @XmlEnumValue("GT3000")
    GT_3000("GT3000"),
    @XmlEnumValue("MO1")
    MO_1("MO1"),
    NIDEK("NIDEK"),
    @XmlEnumValue("OMA3.02")
    OMA_3_02("OMA3.02"),
    PHI("PHI"),
    @XmlEnumValue("T4")
    T_4("T4"),
    WECO("WECO");
    private final String value;

    TracerBinaryFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TracerBinaryFormat fromValue(String v) {
        for (TracerBinaryFormat c: TracerBinaryFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
