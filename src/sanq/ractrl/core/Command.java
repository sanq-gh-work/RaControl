package sanq.ractrl.core;

/**
 * Created with IntelliJ IDEA.
 * User: sanq
 * Date: 04.05.13
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */


public class Command {
    private Const.TypeCommand typeCommand;
    private int commandText;

    public Const.TypeCommand getTypeCommand() {
        return typeCommand;
    }

    public void setCommandCode(int commandText) {
        this.commandText = commandText;
        typeCommand = Const.TypeCommand.fromInteger(commandText);
    }

    public int getCommandCode() {
        return commandText;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandText='" + commandText + '\'' +
                '}';
    }
}
