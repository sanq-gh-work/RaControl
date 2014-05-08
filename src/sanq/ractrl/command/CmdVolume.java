package sanq.ractrl.command;

import sanq.ractrl.core.Command;
import sanq.ractrl.core.Const;
import sanq.ractrl.executor.MRunnable;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: sanq
 * Date: 04.05.13
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class CmdVolume implements MRunnable {
    private static HashMap<String, Float> prevVolumeValues = new HashMap<String, Float>();

    @Override
    public void run(Command cmd) {
        try {
            setVolume(cmd.getTypeCommand());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    private void setVolume( Const.TypeCommand direct) throws LineUnavailableException {
        float value = 0;
        for (Map.Entry<String,Port> mixers : getVolumeControls().entrySet()) {
            mixers.getValue().open();
            if (mixers.getValue().isControlSupported(FloatControl.Type.VOLUME)) {
                FloatControl volume = (FloatControl) mixers.getValue().getControl(FloatControl.Type.VOLUME);

                if (direct != null) {
                    switch (direct){
                        case VOLUME_UP   :  value = volume.getValue() + Const.STEP_VOLUME; break;
                        case VOLUME_DOWN :  value = volume.getValue() - Const.STEP_VOLUME; break;
                        case VOLUME_MIN  :  value = Float.MIN_VALUE; break;
                        case VOLUME_PREV  :
                            value = prevVolumeValues.get(mixers.getKey())!=null ? prevVolumeValues.get(mixers.getKey()) : Float.MAX_VALUE;
                            break;
                        case VOLUME_MAX  :  value = Float.MAX_VALUE; break;
                    }
                }
                prevVolumeValues.put(mixers.getKey(),volume.getValue());
                setVolume(volume, value) ;
            }
                mixers.getValue().close();
        }
    }

    private void setVolume(FloatControl vol, float value) throws LineUnavailableException {
         if (value >= vol.getMaximum()){
            vol.setValue(vol.getMaximum());
        } else if ( value <= vol.getMinimum()) {
             vol.setValue(vol.getMinimum());
         } else{
             vol.setValue(value);
         }
    }



    private HashMap<String,Port> getVolumeControls() throws LineUnavailableException {
        HashMap<String,Port> res = new HashMap<String,Port>();
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : infos) {
            Mixer mixer = AudioSystem.getMixer(info);
            if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                res.put(mixer.getMixerInfo().toString(), port);
            }
        }
     return res;
    }
}