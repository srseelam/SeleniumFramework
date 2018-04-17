package framework;
 
public class ValidateOS {
                private static String OS = System.getProperty("os.name").toLowerCase();
                private static String OSarch = System.getProperty("os.arch");
               
               
                public static boolean isWindows(){
                                return (OS.indexOf("win") >= 0 || (OS.indexOf("Win") >= 0));
                }
                public static boolean isMac(){
                                return (OS.indexOf("mac") >= 0);
                }
                public static boolean isNix(){
                                return (OS.indexOf("nix") >=0 || OS.indexOf("nux") >=0 || OS.indexOf("aix") > 0);
                }
                public static boolean is64bit(){
                                return (OSarch.indexOf("64") != -1);
                }
               
               
}