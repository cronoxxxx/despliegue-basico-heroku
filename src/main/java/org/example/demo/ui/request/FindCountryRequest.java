//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.24 at 02:33:13 PM IST 
//


package org.example.demo.ui.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import org.example.demo.common.Constantes;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name"}, namespace = Constantes.NAMESPACE_URI)
@XmlRootElement(name = "findCountryRequest", namespace = Constantes.NAMESPACE_URI)
public class FindCountryRequest {
    @XmlElement(required = true)
    private String name;
}
