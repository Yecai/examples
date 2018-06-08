package com.xiaopan;

import com.sun.org.apache.xml.internal.security.Init;

import javax.management.*;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class JmxReader {
    private static JMXConnector jmxc;
    private static MBeanServerConnection mbsc;

    public static void main(String[] args) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
            jmxc = JMXConnectorFactory.connect(url, null);
            mbsc = jmxc.getMBeanServerConnection();

            String availableProcessors = getAttribute("java.lang:type=OperatingSystem", "AvailableProcessors");
            String threadCount = getAttribute("java.lang:type=Threading", "ThreadCount");
            String processCpuTime = getAttribute("java.lang:type=OperatingSystem", "ProcessCpuTime");
            Map<String, Long> heapMemoryUsage = getHeapMemoryUsage();
            String loadedClassCount = getAttribute("java.lang:type=ClassLoading", "LoadedClassCount");

            Set<ObjectName> objectNames = new TreeSet<ObjectName>(mbsc.queryNames(null, null));
            for (ObjectName name : objectNames) {
//                System.out.println(name);
            }

            System.out.println();
            System.out.println();
            System.out.println();



            Set<ObjectInstance> objectInstances = mbsc.queryMBeans(null, null);
            Set<String> names = new TreeSet<String>();
            Set<MBeanInfo> mBeanInfos = new TreeSet<MBeanInfo>();
            for (ObjectInstance objectInstance : objectInstances) {
                names.add(objectInstance.getObjectName().toString());
                MBeanInfo mBeanInfo = mbsc.getMBeanInfo(objectInstance.getObjectName());
//                mBeanInfos.add(mBeanInfo);
                if ("java.lang:type=OperatingSystem".equalsIgnoreCase(objectInstance.getObjectName().toString())) {
                    System.out.println(objectInstance.getObjectName());
                    MBeanAttributeInfo[] attributeInfos = mBeanInfo.getAttributes();
                    for (MBeanAttributeInfo attributeInfo : attributeInfos) {
                        System.out.println("1===" + attributeInfo.getName());
//                        System.out.println("2===" + attributeInfo.getType());
//                        System.out.println("3===" + attributeInfo.getDescription());
//                        Descriptor descriptor = attributeInfo.getDescriptor();
//                        String[] fieldNames = descriptor.getFieldNames();
//                        for (String fieldName : fieldNames) {
//                            System.out.println("4===" + fieldName);
//                            System.out.println("4===" + descriptor.getFieldValue(fieldName));
//                        }

                        Object attribute = mbsc.getAttribute(objectInstance.getObjectName(), attributeInfo.getName());
                        System.out.println("4===" + attribute.toString());
                    }
                }

            }

//            names.forEach(name -> System.out.println(name));
//            System.out.println("availableProcessors : " + availableProcessors);
//            System.out.println("processCpuTime : " + processCpuTime);
//            System.out.println("heapMemoryUsage : " + heapMemoryUsage);
//            System.out.println("loadedClassCount : " + loadedClassCount);
//            System.out.println("threadCount : " + threadCount);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jmxc != null) {
                try {
                    jmxc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getAttribute(String objectName, String attribute) throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        return ((Object) mbsc.getAttribute(new ObjectName(objectName), attribute)).toString();
    }

    private static Map<String, Long> getHeapMemoryUsage() throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        ObjectName mbeanName = new ObjectName("java.lang:type=Memory");
        CompositeDataSupport compositeDataSupport = (CompositeDataSupport) mbsc.getAttribute(mbeanName, "HeapMemoryUsage");

        Map<String, Long> heapMemoryUsage = new HashMap<String, Long>();
        heapMemoryUsage.put("init", (Long) compositeDataSupport.get("init"));
        heapMemoryUsage.put("max", (Long) compositeDataSupport.get("max"));
        heapMemoryUsage.put("committed", (Long) compositeDataSupport.get("committed"));
        heapMemoryUsage.put("used", (Long) compositeDataSupport.get("used"));
        return heapMemoryUsage;
    }

}
