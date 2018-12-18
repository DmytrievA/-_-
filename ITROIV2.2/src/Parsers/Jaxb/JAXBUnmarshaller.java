package Parsers.Jaxb;

import org.itroi.tasks.TasksType;
import org.itroi.user.UserType;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBUnmarshaller  {

	public UserType unmarshal(String filePath) {
		try {
			File file=new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(UserType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (UserType) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static void main(String[] arg) {
		JAXBUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
		UserType tasks =  jaxbUnmarshaller.unmarshal("src/jaxb.xml");
		System.out.println(tasks.toString());
	}
}
