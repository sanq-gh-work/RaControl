package sanq.ractrl.command;

import sanq.ractrl.core.Command;
import sanq.ractrl.core.Const;
import sanq.ractrl.executor.MRunnable;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sanq
 * Date: 04.05.13
 * Time: 14:57
 * To change this template use File | Settings | File Templtes.
 */
public class CmdSystem implements MRunnable {
    //Const.TypeCommand typeCmd;

    @Override
    public void run(Command cmd) {
      shutDownSystem(cmd.getTypeCommand());
    }

    private  void shutDownServer(){
        System.out.println("Server is shutting down by command...");
        System.exit(0);
    }

    private void shutDownSystem(Const.TypeCommand typeCmd) {
        try {
            switch (typeCmd) {
                case SYSTEM_SHUTDOWN:
                    Runtime.getRuntime().exec(new String[]{"shutdown", "-s", "-f"});
                    break;
                case SYSTEM_REBOOT:
                    Runtime.getRuntime().exec(new String[]{"shutdown", "-r"});
                    break;
                case SYSTEM_CANCEL:
                    Runtime.getRuntime().exec(new String[]{"shutdown", "-a"});
                    break;
                case SERVER_SHUTDOWN:
                    System.out.println("Server is shutting down by command...");
                    System.exit(0);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
