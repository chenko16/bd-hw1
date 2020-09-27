package ru.mephi.chenko.hadoop;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BindingPriceMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

    private static Integer MIN_BID_PRICE = 250;
    private static IntWritable ONE = new IntWritable(1);
    private static Map<String, String> cityMapCache = new HashMap<>();

    /**
     * Transform input records into a intermediate records
     * @param key Key of the processed record
     * @param value Value of the processed record
     * @param output Collector of output data
     * @param reporter Reporter of progress and update counters, status information etc.
     */
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) {
        try {
            String[] inputData = value.toString().split("\\t");
            String city = inputData[7];
            String cachedCityName = cityMapCache.get(city);
            if(cachedCityName != null) { // Если в кэше есть id города, то заменяем на название города
                city = cachedCityName;
            }

            // Фильтруем записи по значению поля bindingPrice
            if(Integer.parseInt(inputData[19]) > MIN_BID_PRICE) {
                output.collect(new Text(city), ONE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Setup before mapping: init distribution cache
     * @param conf Configuration of the job
     */
    @Override
    public void configure(JobConf conf) {
        try {
            URI[] cacheFilesUris = DistributedCache.getCacheFiles(conf);

            if(cacheFilesUris != null) { //Для каждоо файла в кэше считываем его и добавляем в мапу соотвествия id и названия городов
                for (URI fileURI : cacheFilesUris) {
                    BufferedReader brReader = new BufferedReader(new FileReader(fileURI.toString()));
                    String strLineRead = "";
                    while ((strLineRead = brReader.readLine()) != null) {
                        String[] cityMappings = strLineRead.split("\\s+");
                        cityMapCache.put(cityMappings[0].trim(), cityMappings[1].trim());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
