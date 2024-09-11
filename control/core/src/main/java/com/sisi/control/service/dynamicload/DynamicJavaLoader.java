package com.sisi.control.service.dynamicload;

import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;

public class DynamicJavaLoader extends ForwardingJavaFileManager<JavaFileManager> {
    /**
     * Creates a new instance of {@code ForwardingJavaFileManager}.
     *
     * @param fileManager delegate to this file manager
     */
    protected DynamicJavaLoader(JavaFileManager fileManager) {
        super(fileManager);
    }

    // compiled classes in bytes:
//    final Map<String, byte[]> classBytes = new HashMap<String, byte[]>();
//
//    final Map<String, List<JavaFileObject>> classObjectPackageMap = new HashMap<>();
//
//    private JavacFileManager javaFileManager;
//
//    DynamicJavaLoader(JavaFileManager fileManager) {
//        super(fileManager);
//        this.javaFileManager = javaFileManager;
//    }
//
//    public Map<String, byte[]> getClassBytes() {
//        return new HashMap<String, byte[]>(this.classBytes);
//    }
//
//    @Override
//    public void flush() throws IOException {
//    }
//
//    @Override
//    public void close() throws IOException {
//        classBytes.clear();
//    }
//
//
//    public List<JavaFileObject> getLibJarsOptions(String packgeName) {
//        List<JavaFileObject> result = new ArrayList<>();
//        try {
//            String jarBaseFile = MemoryClassLoader.getPath();
//            JarFile jarFile = new JarFile(new File(jarBaseFile));
//            List<JarEntry> entries = jarFile.stream().filter(jarEntry -> {
//                return jarEntry.getName().endsWith(".jar");
//            }).collect(Collectors.toList());
//            JarFile libTempJarFile = null;
//            List<JarEntry> tempEntries = null;
//            for (JarEntry entry : entries) {
//                tempEntries = new ArrayList<>();
//                libTempJarFile = jarFile.getNestedJarFile(jarFile.getEntry(entry.getName()));
//                Enumeration<JarEntry> tempEntriesEnum = libTempJarFile.entries();
//                while (tempEntriesEnum.hasMoreElements()) {
//                    JarEntry jarEntry = tempEntriesEnum.nextElement();
//                    String classPath = jarEntry.getName().replace("/", ".");
//                    if (!classPath.endsWith(".class") || jarEntry.getName().lastIndexOf("/") == -1) {
//                        continue;
//                    } else if (classPath.substring(0, jarEntry.getName().lastIndexOf("/")).equals(packgeName)) {
//                        tempEntries.add(jarEntry);
//                    }
//                }
//                for (JarEntry tempEntry : tempEntries) {
//                    result.add(new MemorySpringBootInfoJavaClassObject(tempEntry.getName().replace("/", ".").replace(".class", ""),
//                            new URL(libTempJarFile.getUrl(), tempEntry.getName()), javaFileManager));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//
//    }
}
