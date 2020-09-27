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
        if(args.length < 2) {
            System.out.println("Usage: hadoopApp inputFilesDirectory resultDirectory [cacheFilePath]");
        }

        JobConf conf = new JobConf(HadoopApplication.class);
        conf.setJobName("Binding price count");

        // Указываем классы с реализацией Mapper, Reducer и Partitioner
        conf.setMapperClass(BindingPriceMapper.class);
        conf.setCombinerClass(BindingPriceReducer.class);
        conf.setReducerClass(BindingPriceReducer.class);
        conf.setPartitionerClass(BindingPricePartitioner.class);

        // Если в аргументах есть файл для кэширования, то добавляем его в распределенных кэш
        if(args.length > 2) {
            DistributedCache.addCacheFile(new URI(args[2]), conf);
        }

        // Указываем путь до входных и выходных данных
        FileInputFormat.setInputPaths(conf, args[0]);
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // Устанавливаем формат файла результата
        conf.setOutputFormat(SequenceFileOutputFormat.class);

        // Указываем формат ключа и значения результата
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        // Устанавливаем количество Reducer
        conf.setNumReduceTasks(REDUCER_COUNT);

        long startJobTime = System.nanoTime();
        JobClient.runJob(conf);
        long finishJobTime = System.nanoTime();
        System.out.println("LOG [INFO] Execution time: " + (finishJobTime - startJobTime) + " ms");
    }
}
