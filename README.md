1. I'm trying to run ExampleInstrumentedTest located in androidTest folder.

2. Please, pay your attention on ExampleInstrumentedTest.kt and app/build.gradle files

3. When I use just one dependency with previous version 1.12.5 (androidTestImplementation "io.mockk:mockk-android:1.12.5") - it works fine

4. Now I have to include io.mockk:mockk:1.12.7, otherwise project won't compile (missing 'mockk' and 'every' methods).

5. I was required to add 'packagingOptions' block to possible fix an error (see build.gradle for detailed description).

6. I also get an error 'Unable to dlopen libmockkjvmtiagent.so'. Trying to fix it with:

        testOptions {
            packagingOptions {
                jniLibs { useLegacyPackaging = true }
            }
        }
 
 Now, if I run test without any uncommitted changes, I'll get 'io.mockk.proxy.MockKAgentException: 'dispatcher.jar' not found'. See full stacktrace below:
 
     2022-08-23 18:30:50.227 14514-14549/com.example.playground E/TestRunner: failed: useAppContext(com.example.playground.ExampleInstrumentedTest)
    2022-08-23 18:30:50.227 14514-14549/com.example.playground E/TestRunner: ----- begin exception -----
    2022-08-23 18:30:50.233 14514-14549/com.example.playground E/TestRunner: java.lang.ExceptionInInitializerError
            at com.example.playground.ExampleInstrumentedTest.useAppContext(ExampleInstrumentedTest.kt:33)
            at java.lang.reflect.Method.invoke(Native Method)
            at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
            at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
            at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
            at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
            at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
            at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
            at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
            at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
            at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
            at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
            at org.junit.runners.Suite.runChild(Suite.java:128)
            at org.junit.runners.Suite.runChild(Suite.java:27)
            at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
            at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
            at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
            at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
            at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
            at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:56)
            at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:444)
            at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2145)
         Caused by: io.mockk.proxy.MockKAgentException: 'dispatcher.jar' not found
            at io.mockk.proxy.android.AndroidMockKAgentFactory.init(AndroidMockKAgentFactory.kt:47)
            at io.mockk.impl.JvmMockKGateway.<init>(JvmMockKGateway.kt:46)
            at io.mockk.impl.JvmMockKGateway.<clinit>(JvmMockKGateway.kt:186)
            at com.example.playground.ExampleInstrumentedTest.useAppContext(ExampleInstrumentedTest.kt:33) 
            at java.lang.reflect.Method.invoke(Native Method) 
            at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59) 
            at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12) 
            at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56) 
            at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17) 
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306) 
            at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100) 
            at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366) 
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103) 
            at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63) 
            at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331) 
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79) 
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329) 
            at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66) 
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293) 
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306) 
            at org.junit.runners.ParentRunner.run(ParentRunner.java:413) 
            at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162) 
            at org.junit.runners.Suite.runChild(Suite.java:128) 
            at org.junit.runners.Suite.runChild(Suite.java:27) 
            at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331) 
            at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79) 
            at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329) 
            at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66) 
            at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293) 
            at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306) 
            at org.junit.runners.ParentRunner.run(ParentRunner.java:413) 
            at org.junit.runner.JUnitCore.run(JUnitCore.java:137) 
            at org.junit.runner.JUnitCore.run(JUnitCore.java:115) 
            at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:56) 
            at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:444) 
            at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2145) 
    2022-08-23 18:30:50.234 14514-14549/com.example.playground E/TestRunner: ----- end exception -----


Any ideas about running this test with MockK 1.12.7?
