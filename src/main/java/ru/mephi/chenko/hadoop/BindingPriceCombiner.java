package ru.mephi.chenko.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class BindingPriceCombiner extends MapReduceBase implements Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    /**
     * Combine the processed records
     * @param key Key of the processed records
     * @param values Values of the processed records
     * @param output Collector of output data
     * @param reporter Reporter of progress and update counters, status information etc.
     */
    @Override
    public void reduce(IntWritable key, Iterator<IntWritable> values, OutputCollector<IntWritable, IntWritable> output, Reporter reporter) throws IOException {
        int sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }

        output.collect(key, new IntWritable(sum));
    }
}
