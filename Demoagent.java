
package com.avais.javaagent;
import java.lang.instrument.Instrumentation;
/**
 *
 * @author Avais
 */
public class Demoagent {
    public static void premain(String args, Instrumentation instrumentation)
    {
         System.out.println("[Agent] Start agent during JVM startup using argument '-javaagent'");
  instrumentation.addTransformer(new DemoClassFileTransformer());
    }
     public static void agentmain(String args, Instrumentation instrumentation) {
  System.out.println("[Agent] Load agent into running JVM using Attach API");
  instrumentation.addTransformer(new DemoClassFileTransformer());
}}
   