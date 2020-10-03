package ru.mephi.chenko;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mephi.chenko.hadoop.BindingPriceMapper;
import ru.mephi.chenko.hadoop.BindingPriceReducer;
import ru.mephi.chenko.util.InputRecordsGenerateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BindingPriceMapreduceTest {

    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @Before
    public void setup() {
        BindingPriceMapper mapper = new BindingPriceMapper();
        BindingPriceReducer reducer = new BindingPriceReducer();
        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper_ValidRecord() throws IOException {
        mapDriver.withInput(new LongWritable(), new Text(InputRecordsGenerateUtil.getValidRecord()))
                .withOutput(new Text("unknown"), new IntWritable(1))
                .runTest();
    }

    @Test
    public void testMapper_InvalidRecord() throws IOException {
        List<Pair<Text, IntWritable>> results = mapDriver
                .withInput(new LongWritable(), new Text(InputRecordsGenerateUtil.getInvalidRecord()))
                .run();
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void testReducer() throws IOException {
        List<IntWritable> values = new ArrayList<>();
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));

        reduceDriver.withInput(new Text("unknown"), values)
                .withOutput(new Text("unknown"), new IntWritable(2))
                .runTest();
    }

    @Test
    public void testMapReduce() throws IOException {
        mapReduceDriver.withInput(new LongWritable(), new Text(InputRecordsGenerateUtil.getValidRecord()))
                .withInput(new LongWritable(), new Text(InputRecordsGenerateUtil.getValidRecord()))
                .withInput(new LongWritable(), new Text(InputRecordsGenerateUtil.getInvalidRecord()))
                .withOutput(new Text("unknown"), new IntWritable(2))
                .runTest();
    }

}
