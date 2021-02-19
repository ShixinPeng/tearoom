package com.tearoom;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class MainObject {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("serverInfoMBean:name=serverInfo");
        server.registerMBean(new ServerInfo(), name);

        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setCode(1);
        ServerInfo serverInfo2 = new ServerInfo();
        serverInfo2.setCode(2);
        char read = (char) System.in.read();
        System.out.println("char: "+ read);



    }
}
