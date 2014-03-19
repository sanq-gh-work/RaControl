package sanq.ractrl.executor;

import sanq.ractrl.core.Command;
import sanq.ractrl.core.Const;
import sanq.ractrl.util.EnumClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pospelov
 * Date: 07.05.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class Executor {

    private static Executor instance;
    private List<MRunnable> executers ;

    private Executor() {
        registerRunnables();
    }

    public static  void execute  (Command cmd){
       if (instance==null) {
         instance = new Executor();
       }
        for (MRunnable el: instance.executers ) {
            el.run(cmd);
        }
    }


    private void registerRunnables() {
        executers = new ArrayList<MRunnable>();
        try {

            Class[] clasess = EnumClasses.getClasses(Const.COMMANDS_PACKAGE);
            for (Class clazz : clasess) {
                //adding if  implements MRunnable ...
                if (MRunnable.class.isAssignableFrom(clazz) ) {
                    executers.add((MRunnable)clazz.newInstance());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


}
