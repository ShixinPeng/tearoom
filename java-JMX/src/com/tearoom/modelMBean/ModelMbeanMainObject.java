package com.tearoom.modelMBean;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.modelmbean.RequiredModelMBean;

public class ModelMbeanMainObject {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();


        ObjectName helloName = new ObjectName("MyMBean:name=hello1");
        //Hello hello = new Hello();
        RequiredModelMBean hello = ModelMBeanUtils.createModlerMBean();
        server.registerMBean(hello, helloName);


        Hello hello1 = new Hello();

        hello1.setName("大狗");

        Hello hello2 = new Hello();

        hello2.setName("小猫");


        char read = (char) System.in.read();
        System.out.println("char: "+ read);
        hello1.sayHi();
        hello2.sayHi();
    }
}
