package com.dublabs.Domain;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Map;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Created by tofiques on 12/4/17.
 */
public class AprioriEngine {

    private String RelationName;
    private Map<String, String[]> AttributeMap;

    private ArrayList<String[]> Data;

    public Map<String, String[]> getAttributeMap() {
        return AttributeMap;
    }

    public String getRelationName() {
        return RelationName;
    }

    public ArrayList<String[]> getData() {
        return Data;
    }

    public AprioriEngine(String relationName, Map<String, String[]> attrMap, ArrayList<String[]> data) {

		/* the data format of ARFF file for Apriori alorithm is as follows
		 * @relation <name>
		 *
		 * @attribute <attribute name> <type. use nominal for Apriori>
		 *
		 * @data
		 * <csv>
		 *
		 *
		 * EXAMPLE
		 * @relation Test-Data
		 *
		 * @attribute Person1 {cool, not_cool}
		 * @attribute Person2 {cool, not_cool}
		 *
		 * @data
		 * cool, not_cool
		 * cool, not_cool
		 * cool, cool
		 * cool, cool
		 * cool, not_cool
		 * cool, cool
		 *
		 */


		/*
		 * Code below will create a input stream pass to Weka
		 * this avoids having to write to file system
		 */

        // TODO Auto-generated constructor stub
        this.RelationName = relationName;
        this.AttributeMap = attrMap;
        this.Data = data;
    }

    public String Run() {

        try {
            // builder to be converted to input stream
            StringBuilder builder = new StringBuilder();

            // add relation name
            builder.append(CreateRelationName());

            // add attributes
            builder.append(CreateAttibutes());

            // add data
            builder.append(CreateData());
            System.out.println(builder.toString());

            // load input stream
            DataSource source = new DataSource(new ByteArrayInputStream(builder.toString().getBytes()));

            //get instances object
            Instances data = source.getDataSet();

            //the Apriori algorithm
            Apriori model = new Apriori();

            // optional. set model props if needed
            ConfigureModel(model);

            //build model
            model.buildAssociations(data);

            //print out the extracted rules
            return String.valueOf(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Failure during processing";
    }

    public static Apriori ConfigureModel(Apriori model) {

        // Set and model properties here.
        return model;
    }

    private String CreateRelationName() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("@relation %s\n", getRelationName()));
        builder.append("\n");

        return builder.toString();
    }

    private String CreateAttibutes() {

        // create attributes in following format @attribute <name> {<Csv Values>}
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String[]> entry : getAttributeMap().entrySet())
        {
            builder.append(String.format("@attribute %s {%s}\n", entry.getKey(),String.join(",", entry.getValue())));
        }

        builder.append("\n");

        return builder.toString();
    }

    private String CreateData() {

        // create attributes in following format @attribute <name> {<Csv Values>}
        StringBuilder builder = new StringBuilder();
        builder.append("@data\n\n");

        // TODO
        //for (String[] row : getData()) {
        //	builder.append(String.format("{%s}\n",String.join(",", row)));
        //}

        // TEST data for now
        String testData = "Taken,Taken,None,None,None,Taken,Taken,None\n" +
                "Taken,None,Taken,Taken,Taken,Taken,Taken,Taken\n" +
                "Taken,None,None,None,Taken,None,None,None\n" +
                "Taken,None,Taken,Taken,Taken,None,Taken,None\n" +
                "Taken,None,Taken,None,None,None,None,Taken\n" +
                "Taken,None,Taken,None,Taken,None,Taken,None\n" +
                "Taken,None,Taken,None,Taken,Taken,Taken,None\n" +
                "None,None,None,Taken,None,None,None,None\n" +
                "Taken,None,Taken,Taken,None,None,Taken,None\n" +
                "None,None,None,Taken,None,None,None,Taken\n" +
                "Taken,Taken,None,None,None,None,Taken,Taken\n" +
                "None,None,Taken,Taken,None,Taken,None,None\n" +
                "Taken,Taken,Taken,None,Taken,None,None,Taken\n" +
                "Taken,Taken,Taken,Taken,None,Taken,Taken,Taken\n" +
                "None,None,None,Taken,None,Taken,Taken,Taken\n" +
                "Taken,None,Taken,None,None,None,None,None\n" +
                "Taken,None,None,Taken,Taken,Taken,Taken,None\n" +
                "Taken,None,None,Taken,None,Taken,None,None\n" +
                "None,None,None,Taken,None,Taken,None,Taken\n" +
                "Taken,None,None,None,None,Taken,None,Taken\n" +
                "Taken,Taken,Taken,None,None,None,Taken,None\n" +
                "Taken,Taken,Taken,Taken,None,Taken,None,Taken\n" +
                "None,None,None,None,None,Taken,None,None\n" +
                "Taken,Taken,None,Taken,None,None,Taken,Taken\n" +
                "None,None,None,Taken,Taken,Taken,Taken,Taken\n" +
                "None,None,Taken,Taken,None,Taken,Taken,None\n" +
                "None,Taken,Taken,None,None,None,None,None\n" +
                "Taken,Taken,Taken,Taken,Taken,Taken,Taken,Taken\n" +
                "None,None,None,None,None,None,Taken,Taken\n" +
                "None,Taken,None,None,None,None,Taken,None\n" +
                "Taken,Taken,Taken,None,Taken,None,None,Taken\n" +
                "Taken,None,Taken,Taken,None,None,Taken,Taken\n" +
                "Taken,None,Taken,Taken,None,Taken,Taken,None\n" +
                "None,None,Taken,None,None,Taken,Taken,Taken\n" +
                "None,Taken,None,None,Taken,Taken,Taken,None\n" +
                "None,Taken,Taken,None,Taken,None,Taken,Taken\n" +
                "None,Taken,Taken,None,None,None,Taken,Taken\n" +
                "None,Taken,None,None,None,Taken,None,Taken\n" +
                "None,None,None,None,Taken,None,None,None\n" +
                "Taken,None,Taken,Taken,None,None,None,Taken\n" +
                "None,Taken,None,None,None,None,None,None\n" +
                "Taken,None,None,None,None,Taken,Taken,Taken\n" +
                "Taken,None,Taken,Taken,None,Taken,Taken,Taken\n" +
                "Taken,None,Taken,None,Taken,Taken,None,None\n" +
                "Taken,None,Taken,Taken,None,Taken,Taken,Taken\n" +
                "Taken,None,Taken,Taken,Taken,Taken,None,Taken\n" +
                "None,Taken,Taken,None,Taken,Taken,None,Taken\n" +
                "Taken,Taken,Taken,Taken,Taken,Taken,None,None\n" +
                "None,Taken,None,None,None,None,None,Taken\n" +
                "None,None,None,None,None,None,None,None\n" +
                "Taken,Taken,Taken,Taken,None,Taken,None,None\n" +
                "Taken,Taken,None,None,Taken,None,None,Taken\n" +
                "Taken,Taken,None,Taken,Taken,Taken,None,None\n" +
                "None,Taken,None,None,Taken,Taken,Taken,Taken\n" +
                "None,None,None,Taken,None,None,Taken,Taken\n" +
                "None,None,Taken,Taken,None,None,Taken,None\n" +
                "Taken,None,Taken,None,None,None,None,None\n" +
                "Taken,Taken,None,Taken,None,Taken,Taken,Taken\n" +
                "None,Taken,Taken,None,None,Taken,Taken,None\n" +
                "Taken,None,None,None,Taken,None,None,None\n" +
                "Taken,None,None,Taken,Taken,Taken,Taken,Taken\n" +
                "None,None,None,Taken,None,Taken,Taken,None\n" +
                "Taken,None,None,Taken,Taken,None,None,Taken\n" +
                "Taken,Taken,Taken,None,None,None,None,Taken\n" +
                "None,Taken,Taken,None,None,None,Taken,Taken\n" +
                "None,Taken,None,Taken,Taken,Taken,Taken,None\n" +
                "None,None,None,Taken,None,Taken,None,Taken\n" +
                "Taken,Taken,Taken,Taken,None,None,Taken,None\n" +
                "Taken,None,Taken,Taken,Taken,None,None,Taken\n" +
                "Taken,Taken,Taken,Taken,Taken,None,None,Taken\n" +
                "Taken,None,None,Taken,None,Taken,Taken,Taken\n" +
                "Taken,Taken,None,Taken,None,None,None,Taken\n" +
                "None,Taken,Taken,Taken,Taken,None,Taken,Taken\n" +
                "None,None,Taken,Taken,Taken,Taken,None,None\n" +
                "Taken,Taken,None,None,Taken,Taken,Taken,None\n" +
                "Taken,None,None,Taken,None,None,Taken,None\n" +
                "None,None,None,None,Taken,Taken,None,Taken\n" +
                "None,Taken,None,Taken,None,Taken,Taken,None\n" +
                "None,None,None,Taken,None,Taken,None,Taken\n" +
                "Taken,None,Taken,None,Taken,None,None,Taken\n" +
                "Taken,None,None,None,None,None,Taken,Taken\n" +
                "Taken,None,None,None,Taken,None,None,None\n" +
                "None,None,None,None,None,None,None,None\n" +
                "Taken,Taken,None,None,None,Taken,None,Taken\n" +
                "None,None,Taken,None,Taken,Taken,Taken,None\n" +
                "Taken,None,None,Taken,Taken,Taken,Taken,Taken\n" +
                "None,Taken,None,None,None,None,None,Taken\n" +
                "None,Taken,Taken,None,None,None,None,None\n" +
                "None,None,Taken,None,Taken,Taken,None,Taken\n" +
                "Taken,Taken,None,None,Taken,None,None,Taken\n" +
                "None,Taken,Taken,None,None,None,Taken,None\n" +
                "None,Taken,Taken,None,Taken,None,Taken,None\n" +
                "None,None,None,None,Taken,Taken,Taken,Taken\n" +
                "Taken,None,None,None,None,Taken,None,Taken\n" +
                "None,None,Taken,None,None,None,Taken,Taken\n" +
                "None,Taken,Taken,None,Taken,None,None,Taken\n" +
                "Taken,Taken,None,None,Taken,Taken,None,None\n" +
                "None,None,Taken,Taken,Taken,Taken,None,None\n" +
                "Taken,Taken,None,None,None,None,Taken,None\n" +
                "None,None,None,Taken,Taken,Taken,Taken,None";
        builder.append(testData);

        return builder.toString();
    }
}
