import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.File;

public class Inject {

    /*Jar a injecter.*/
    private static final String JAR_PAR_DEFAUT = "cheat.jar";

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
            return;
        }

        // Mode liste : java Inject list
        if (args[0].equalsIgnoreCase("list")) {
            System.out.println("JVM joignables :");
            for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
                System.out.printf("  pid=%-8s  %s%n", vmd.id(), vmd.displayName());
            }
            return;
        }

        String pid = args[0];
        File jar = new File(args.length > 1 ? args[1] : JAR_PAR_DEFAUT).getAbsoluteFile();

        if (!jar.isFile()) {
            System.err.println("Jar introuvable : " + jar);
            return;
        }

        VirtualMachine vm = null;
        try {
            System.out.println("Attach sur pid " + pid + " ...");
            vm = VirtualMachine.attach(pid);

            System.out.println("Chargement de " + jar.getName() + " ...");
            vm.loadAgent(jar.getAbsolutePath(), "");   // chemin ABSOLU obligatoire
            System.out.println("Mod Injecte");

        } catch (AgentInitializationException e) {
            System.err.println("agentmain plante (code " + e.returnValue() + ") :");
            e.printStackTrace();
        } catch (AgentLoadException e) {
            System.err.println("Agent refuse : " + e.getMessage());
            System.err.println("debug 1");
        } catch (AttachNotSupportedException e) {
            System.err.println("Attach impossible : " + e.getMessage());
            System.err.println("debug 2");
        } catch (Exception e) {
            System.err.println("sa marche pas" + e);
            e.printStackTrace();
        } finally {
            if (vm != null) {
                try { vm.detach(); } catch (Exception ignored) { }
            }
        }
    }

    private static void usage() {
        System.out.println("""
             java -cp . Inject pid .jar
             java -cp . Inject list
            """);
    }
}
