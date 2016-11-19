
package com.uknowho.sample.batch.xmlmodel;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.uknowho.sample.batch.xmlmodel package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.uknowho.sample.batch.xmlmodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SortModel }
     * 
     */
    public SortModel createSortModel() {
        return new SortModel();
    }

    /**
     * Create an instance of {@link PaginationModel }
     * 
     */
    public PaginationModel createPaginationModel() {
        return new PaginationModel();
    }

    /**
     * Create an instance of {@link NotificationEmailModel }
     * 
     */
    public NotificationEmailModel createNotificationEmailModel() {
        return new NotificationEmailModel();
    }

    /**
     * Create an instance of {@link TemplateModel }
     * 
     */
    public TemplateModel createTemplateModel() {
        return new TemplateModel();
    }

}
