
package org.w3._2001.xmlschema;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import ch.ivyteam.ivy.scripting.objects.Date;

public class Adapter3
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (ch.ivyteam.ivy.scripting.objects.adapters.DateStringJaxbAdapter.parseDate(value));
    }

    public String marshal(Date value) {
        return (ch.ivyteam.ivy.scripting.objects.adapters.DateStringJaxbAdapter.printDate(value));
    }

}
