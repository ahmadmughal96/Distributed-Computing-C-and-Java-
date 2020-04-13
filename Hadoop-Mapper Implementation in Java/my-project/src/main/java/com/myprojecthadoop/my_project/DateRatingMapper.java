package com.myprojecthadoop.my_project;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class DateRatingMapper extends MapReduceBase
        implements Mapper<WritableComparable,Writable,WritableComparable,Writable>,
        Reducer<WritableComparable,Writable,WritableComparable,Writable> {

    private static Pattern userRatingDate = Pattern.compile("^(\\d+),(\\d+),(\\d{4}-\\d{2}-\\d{2})$");

    /**
     * Given a line of input, if it is a UserID,RatingValue,RatingDate line,
     * it extracts the UserID and RatingValue, and emits (UserID, {RatingValue, 1})
     */
    public void map(WritableComparable key, Writable values,
                    OutputCollector output, Reporter reporter) throws IOException {

        /* key is line number(?). But I don't care */
        /* values is the line of the file */
        String line = ((Text)values).toString();
        /* Use a full blown Matcher, so I can pull out the grouped ID and Rating */
        Matcher dateRating = userRatingDate.matcher(line);
        /* Apparently only need one instance, the OutputCollector will create
         * its own copies of these objects.
         */
        Text date = new Text();
        IntWritable ratingCount = new IntWritable(1);

        if(line.matches("^\\d+:$")) {
            /* This is the Movie ID line. Ignore it */
        } else if (dateRating.matches()) {
            /* It is a line to pull data from */
            date.set(dateRating.group(3));

            /* Add them to the output */
            output.collect(date, ratingCount);

        } else {
            /* Should not occur. The input is in an invalid format, or
             * my regex is wrong.
             */
        }

    }

    /**
     * Combine function!
     *
     * Assumes that the values iterator returns {IntWritable, IntWritable}
     * array, and simply sums the two values, and emits (key, {sum of 1, sum of 2})
     */
    public void reduce(WritableComparable key, Iterator values,
                       OutputCollector output, Reporter reporter) throws IOException {

        int count = 0;
        IntWritable ratingInput = null;

        while(values.hasNext()) {

            ratingInput = (IntWritable) values.next();;
            count += ratingInput.get();
        }

        IntWritable outputCount = new IntWritable(count);
        output.collect(key, outputCount);

    }

}