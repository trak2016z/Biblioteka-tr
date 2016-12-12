package pl.ilonamajchrowska.biblioteka.core;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.ilonamajchrowska.biblioteka.config.AppConfig;
import javax.servlet.Filter;

public class SpringMvcInitializer 
       extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
	    Filter[] filters;

	    CharacterEncodingFilter encFilter;
	    HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();

	    encFilter = new CharacterEncodingFilter();

	    encFilter.setEncoding("UTF-8");
	    encFilter.setForceEncoding(true);

	    filters = new Filter[] {httpMethodFilter, encFilter};
	    return filters;
	}
}