//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.12.18 at 04:52:56 AM EET 
//


package org.itroi.task;

import javax.xml.bind.annotation.*;

import org.itroi.entityuser.EntityUser;


/**
 * <p>Java class for userType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.itroi.org/entityUser}EntityUser">
 *       &lt;sequence>
 *         &lt;element name="userValue" type="{http://www.itroi.org/user}userType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userType", propOrder = {
    "userValue"
})
@XmlRootElement(name = "user")
public class UserType
    extends EntityUser
{

    @XmlElement(required = true)
    protected org.itroi.user.UserType userValue;

    /**
     * Gets the value of the userValue property.
     * 
     * @return
     *     possible object is
     *     {@link org.itroi.user.UserType }
     *     
     */
    public org.itroi.user.UserType getUserValue() {
        return userValue;
    }

    /**
     * Sets the value of the userValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.itroi.user.UserType }
     *     
     */
    public void setUserValue(org.itroi.user.UserType value) {
        this.userValue = value;
    }

}
