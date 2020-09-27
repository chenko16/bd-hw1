package ru.mephi.chenko.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class BindingPriceReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * Reduce the processed records
     * @param key Key of the processed records
     * @param values Values of the processed records
     * @param output Collector of output data
     * @param reporter Reporter of progress and update counters, status information etc.
     */
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }

        output.collect(key, new IntWritable(sum));
    }


}
