package com.api_java.xml;

import java.io.*;

import javax.xml.bind.*;
import javax.xml.transform.stream.*;

public class XmlcepJAXB {
	public static String marshal(Object obj) {
		final StringWriter out = new StringWriter();
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(obj, new StreamResult(out));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Object unmarshal(Class classe, String stringXml) {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(classe);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new StreamSource(new StringReader(stringXml)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
