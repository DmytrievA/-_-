//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.12.04 at 05:22:44 PM EET 
//


package org.itroi.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.itroi.entity.Entity;


/**
 * <p>Java class for groupRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="groupRole">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.itroi.org/entity}Entity">
 *       &lt;sequence>
 *         &lt;element name="groupRoleName" type="{http://www.itroi.org/user}groupRoleName"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupRole", propOrder = {
    "groupRoleName"
})
public class GroupRole
    extends Entity
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GroupRoleName groupRoleName;

    /**
     * Gets the value of the groupRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link GroupRoleName }
     *     
     */
    public GroupRoleName getGroupRoleName() {
        return groupRoleName;
    }

    /**
     * Sets the value of the groupRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupRoleName }
     *     
     */
    public void setGroupRoleName(GroupRoleName value) {
        this.groupRoleName = value;
    }

}