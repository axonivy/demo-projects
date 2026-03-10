
package org.w3._2001.xmlschema;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import ch.ivyteam.ivy.scripting.objects.Time;

public class Adapter2
    extends XmlAdapter<String, Time>
{


    public Time unmarshal(String value) {
        return (ch.ivyteam.ivy.scripting.objects.adapters.TimeStringJaxbAdapter.parseTime(value));
    }

    public String marshal(Time value) {
        return (ch.ivyteam.ivy.scripting.objects.adapters.TimeStringJaxbAdapter.printTime(value));
    }

}
