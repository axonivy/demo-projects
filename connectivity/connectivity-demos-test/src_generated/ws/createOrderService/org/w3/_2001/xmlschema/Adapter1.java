
package org.w3._2001.xmlschema;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import ch.ivyteam.ivy.scripting.objects.DateTime;

public class Adapter1
    extends XmlAdapter<String, DateTime>
{


    public DateTime unmarshal(String value) {
        return (ch.ivyteam.ivy.scripting.objects.adapters.DateTimeStringJaxbAdapter.parseDateTime(value));
    }

    public String marshal(DateTime value) {
        return (ch.ivyteam.ivy.scripting.objects.adapters.DateTimeStringJaxbAdapter.printDateTime(value));
    }

}
