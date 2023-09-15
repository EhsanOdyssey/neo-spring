package neo.ehsanodyssey.java.spring.converters;

import neo.ehsanodyssey.java.spring.entities.Resource;
import neo.ehsanodyssey.java.spring.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResourceConverter implements Converter<String, Resource> {

	@Autowired
	private ResourceService service;
	
	@Override
	public Resource convert(String source) {
		System.out.println("invoking convert method of ResourceConverter");
		System.out.println("source :"+source);
		return service.find(new Long(source));
	}

}
