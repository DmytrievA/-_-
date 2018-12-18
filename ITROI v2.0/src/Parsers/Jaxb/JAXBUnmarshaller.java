package Parsers.Jaxb;

import org.itroi.tasks.TasksType;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBUnmarshaller  {

	public TasksType unmarshal(String filePath) {
		try {
			File file=new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(TasksType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (TasksType) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] arg) {
		JAXBUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
		TasksType tasks = jaxbUnmarshaller.unmarshal("src/jaxb.xml");
		System.out.println(tasks.toString());
	}
}
