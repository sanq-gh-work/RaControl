package test.sanq.ractrl.command;

import org.junit.Test;
import sanq.ractrl.command.CmdVolume;
import sanq.ractrl.core.Command;
import sanq.ractrl.core.Const;

/**
 * Created with IntelliJ IDEA.
 * User: Sanq
 * Date: 08.05.14
 * Time: 12:09
 */
public class CommandTest {

    @Test
    public void testSetVolume() throws Exception {
        CmdVolume cmdVolume = new CmdVolume();
        Command cmd = new Command();
        cmd.setCommandCode(Const.TypeCommand.VOLUME_MIN.getValue());
        cmdVolume.run(cmd);
    }
}
