
package com.avais.javaagent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
public class DemoClassFileTransformer implements ClassFileTransformer {
  @Override
  public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
  ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
  String instrumentedClassName = "avais.com.demoapp.Test";
  String instrumentedMethodName = "display";
  byte[] bytecode = classfileBuffer;
  try {
  ClassPool cPool = ClassPool.getDefault();
  CtClass ctClass;
      ctClass = cPool.makeClass(new ByteArrayInputStream(bytecode));
  CtMethod[] ctClassMethods = ctClass.getDeclaredMethods("display");
  for (CtMethod ctClassMethod : ctClassMethods) {
        if (ctClassMethod.getDeclaringClass().getName().equals(instrumentedClassName)
  && ctClassMethod.getName().equals(instrumentedMethodName)) {
  ctClassMethod.insertBefore("System.out.println(\"[Instrumentation] Entering method\");");
  ctClassMethod.insertAfter("System.out.println(\"[Instrumentation] Exiting method\");");
   ctClassMethod.insertAt(24, true, "Test = \"Original text was replaced by instrumentation from agent\";");
  bytecode = ctClass.toBytecode();  
  }
  }
  } catch (IOException e) {
  throw new IllegalClassFormatException(e.getMessage());
  } catch (RuntimeException e) {
  throw new IllegalClassFormatException(e.getMessage());
  } catch (CannotCompileException e) {
  throw new IllegalClassFormatException(e.getMessage());
  }   catch (NotFoundException ex) {
          Logger.getLogger(DemoClassFileTransformer.class.getName()).log(Level.SEVERE, null, ex);
      }
  return bytecode;
  }
}

