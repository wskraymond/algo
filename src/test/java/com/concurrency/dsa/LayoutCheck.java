package com.concurrency.dsa;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class LayoutCheck {
    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        Class<?> cursor = Class.forName("com.concurrency.dsa.SPSCRingBuffer$PaddedCursor");
        System.out.println(ClassLayout.parseClass(cursor).toPrintable());

        System.out.println(ClassLayout.parseClass(SPSCRingBuffer.class).toPrintable());

        System.out.println(ClassLayout.parseClass(ArraySpinLock.class).toPrintable());
        System.out.println(GraphLayout.parseInstance(new ArraySpinLock(16)).toPrintable());

    }
}
