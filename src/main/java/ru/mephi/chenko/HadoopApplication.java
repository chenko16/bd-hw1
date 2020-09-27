package ru.mephi.chenko;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import ru.mephi.chenko.hadoop.BindingPricePartitioner;
import ru.mephi.chenko.hadoop.BindingPriceMapper;
import ru.mephi.chenko.hadoop.BindingPriceReducer;

import java.net.URI;

public class HadoopApplication {

    private static int REDUCER_COUNT = 3;

    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(HadoopApplication.class);
        conf.setJobName("Binding price count");

        conf.setMapperClass(BindingPriceMapper.class);
        conf.setCombinerClass(BindingPriceReducer.class);
        conf.setReducerClass(BindingPriceReducer.class);
        conf.setPartitionerClass(BindingPricePartitioner.class);

        if(args.length > 2) {
            DistributedCache.addCacheFile(new URI(args[2]), conf);
        }

        FileInputFormat.setInputPaths(conf, args[0]);
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        conf.setOutputFormat(SequenceFileOutputFormat.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setNumReduceTasks(REDUCER_COUNT);

        JobClient.runJob(conf);
    }
}
