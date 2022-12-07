package br.com.redirect.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.ext.FileBasedTimestampSynchronizer;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import br.com.redirect.exceptions.HException;

public class UUIDTimeIDGenerator {

	 private static TimeBasedGenerator generator;
	 private static FileBasedTimestampSynchronizer synchronizer;
	 private static File uuid1, uuid2;
	 
   private static void getInstance(String appName) {
       try {
       	if(uuid1 == null)
       		uuid1 = new File(System.getProperty("java.io.tmpdir") + File.separator + "uuid1"+appName+".lck");
       	
       	if(uuid2 == null)
       		uuid2 = new File(System.getProperty("java.io.tmpdir") + File.separator + "uuid2"+appName+".lck");
          	
       	if(synchronizer == null)
          		synchronizer = new FileBasedTimestampSynchronizer(uuid1,uuid2);
          	
          	if(generator == null)
          		generator = Generators.timeBasedGenerator(EthernetAddress.fromInterface(), synchronizer);
       } catch (Exception e) {
       	e.printStackTrace();
       	throw new HException(e);
       }
   }

   public static void shutdown() {
       try {
           synchronizer.deactivate();
       } catch (IOException e) {
       }
   }

   public static synchronized String getId(String appName) {
   	getInstance(appName);
       return generator.generate().toString();
   }
}
