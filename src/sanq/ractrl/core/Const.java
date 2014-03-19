package sanq.ractrl.core;

/**
 * Created with IntelliJ IDEA.
 * User: sanq
 * Date: 04.05.13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public interface Const {

    public final static String  COMMANDS_PACKAGE = "sanq.ractrl.command";
    public final static float STEP_VOLUME = 0.1F;
    public final static int PORT = 1935;
    public final static String EOC = "END_COMMAND";

    // code commands

    public static enum TypeCommand {

        SERVER_SHUTDOWN(0),
        SYSTEM_SHUTDOWN(1),
        SYSTEM_REBOOT(2),
        SYSTEM_CANCEL(3),
        VOLUME_UP(4),
        VOLUME_DOWN(5),
        VOLUME_MIN(6),
        VOLUME_PREV(7),
        VOLUME_MAX(8);

        public int getValue(){
            return cmd;
        }

        public static TypeCommand fromInteger(int x) {
            switch(x) {
                case 0: return SERVER_SHUTDOWN;
                case 1: return SYSTEM_SHUTDOWN;
                case 2: return SYSTEM_REBOOT;
                case 3: return SYSTEM_CANCEL;
                case 4: return VOLUME_UP;
                case 5: return VOLUME_DOWN;
                case 6: return VOLUME_MIN;
                case 7: return VOLUME_PREV;
                case 8: return VOLUME_MAX;
            }
            return null;
        }

        private final int cmd;
        private TypeCommand(int cmd) {
            this.cmd = cmd;
        }
    }

}

